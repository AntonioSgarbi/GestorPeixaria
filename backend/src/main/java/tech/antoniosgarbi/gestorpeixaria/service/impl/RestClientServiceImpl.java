package tech.antoniosgarbi.gestorpeixaria.service.impl;

import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import tech.antoniosgarbi.gestorpeixaria.service.contract.RestClientService;

@Service
@Profile("heroku")
public class RestClientServiceImpl implements RestClientService {

    private final RestTemplate restTemplate;

    public RestClientServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public Object post(String resourceUrl, MultiValueMap<String, String> request, String username, String password) {
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(request,
                createHeadersWithUsernamePassword(username, password));
        ResponseEntity<String> response = this.restTemplate.exchange(resourceUrl, HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }

    private HttpHeaders createHeadersWithUsernamePassword(String username, String password) {
        return new HttpHeaders() {
            @Serial
            private static final long serialVersionUID = -3479660769604597728L;

            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Accept", MediaType.APPLICATION_JSON.toString());
                set("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
                set("Authorization", authHeader);
            }
        };
    }
}
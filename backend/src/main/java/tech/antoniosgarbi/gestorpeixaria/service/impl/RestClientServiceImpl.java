package tech.antoniosgarbi.gestorpeixaria.service.impl;

import lombok.Getter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import tech.antoniosgarbi.gestorpeixaria.service.contract.RestClientService;

import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@Getter
@Profile("heroku")
public class RestClientServiceImpl implements RestClientService {

    private final RestTemplate restTemplate;

    public RestClientServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public Object postWithBasicAuth(String resourceUrl, MultiValueMap<String, String> request, String username, String password) {
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(request, createHeadersWithBasicAuth(username, password));
        ResponseEntity<String> response = this.restTemplate.exchange(resourceUrl, HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }

    private static HttpHeaders createHeadersWithBasicAuth(String username, String password) {
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
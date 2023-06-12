package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestClientServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RestTemplateBuilder builder;

    private RestClientServiceImpl underTest;

    @BeforeEach
    void setUp() {
        when(this.builder.build()).thenReturn(restTemplate);

        this.underTest = new RestClientServiceImpl(builder);
    }


    @Test
    void postWithBasicAuth() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("from", "from");
        map.add("to", "to");
        map.add("subject", "subject");

        when(this.restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        Object response = this.underTest.postWithBasicAuth("test", map, "username", "password");

        verify(this.restTemplate).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class));
    }

}
package tech.antoniosgarbi.gestorpeixaria.service.contract;

import org.springframework.util.MultiValueMap;

public interface RestClientService {
    Object post(String resourceUrl, MultiValueMap<String, String> request, String username, String password);
}

package com.example.frontend.service;

import com.example.frontend.dto.CakeDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CakeClientRestService {

    private final String CAKE_RESOURCE = "api/v1/cakes";

    private final RestTemplate restTemplate;


    public CakeClientRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CakeDto> getAllCakes(){
        return restTemplate.exchange(
                CAKE_RESOURCE,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
    }
}

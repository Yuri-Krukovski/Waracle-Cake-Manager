package com.example.backend;

import com.example.backend.domain.model.Cake;
import com.example.backend.repository.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@Component
public class DataInitializer {

    private final RestTemplate restTemplate;

    private final CakeRepository cakeRepository;

    private final String URL_JSON = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";

    @Autowired
    public DataInitializer(RestTemplate restTemplate, CakeRepository cakeRepository) {
        this.restTemplate = restTemplate;
        this.cakeRepository = cakeRepository;
    }

    @PostConstruct
    public void init() {
        ResponseEntity<List<Cake>> responseEntity =
                restTemplate.exchange(
                        URL_JSON,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        }
                );
        List<Cake> cakes;
        if (!Objects.requireNonNull(responseEntity.getBody()).isEmpty()) {
            cakes = responseEntity.getBody();
            cakes.forEach(cakeRepository::save);
        }


    }
}

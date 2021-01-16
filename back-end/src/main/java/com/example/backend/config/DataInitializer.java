package com.example.backend.config;

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

import static com.example.backend.config.Constants.JSON_CAKE_URL;

@Component
public class DataInitializer {

    private final RestTemplate restTemplate;

    private final CakeRepository cakeRepository;

    private String PATH = JSON_CAKE_URL;

    @Autowired
    public DataInitializer(RestTemplate restTemplate, CakeRepository cakeRepository) {
        this.restTemplate = restTemplate;
        this.cakeRepository = cakeRepository;
    }

    @PostConstruct
    public void init() {
        ResponseEntity<List<Cake>> responseEntity =
                restTemplate.exchange(
                        PATH,
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

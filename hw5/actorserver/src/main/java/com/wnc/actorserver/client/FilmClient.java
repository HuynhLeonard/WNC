package com.wnc.actorserver.client;

import com.wnc.actorserver.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FilmClient {
    private RestTemplate restTemplate;
    private final String filmUrl = "http://localhost:8081/film";

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.secret}")
    private String apiSecret;

    @Autowired
    public FilmClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ApiResponse getAllFilms() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);
        headers.set("X-API-SECRET", apiSecret);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ApiResponse> response = restTemplate.exchange(filmUrl, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }
}

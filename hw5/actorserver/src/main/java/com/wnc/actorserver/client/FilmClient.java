package com.wnc.actorserver.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wnc.actorserver.dto.ApiResponse;
import com.wnc.actorserver.exception.AppException;
import com.wnc.actorserver.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cglib.core.Local;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class FilmClient {
    private final ObjectMapper jacksonObjectMapper;
    private RestTemplate restTemplate;
    private final String filmUrl = "http://localhost:8081/film";

    @Value("${api.secret}")
    private String apiSecret;

    @Autowired
    public FilmClient(RestTemplateBuilder restTemplateBuilder, ObjectMapper jacksonObjectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    public ApiResponse getAllFilms(HttpServletRequest request) throws AppException {
        HttpHeaders headers = new HttpHeaders();

        // test LocalDatetime
        //String time = LocalDateTime.of(2024,11,5,10,10,0).toString();

        String time = LocalDateTime.now().toString();
        String secretToken = generateToken(filmUrl + time + apiSecret);

        headers.set("token", secretToken);
        headers.set("time", time);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<ApiResponse> response = restTemplate.exchange(filmUrl, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
                    }
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            try {
                String body = e.getResponseBodyAsString();
                System.out.println(body);
                return jacksonObjectMapper.readValue(body, ApiResponse.class);
            } catch (Exception parseException) {
                throw new AppException(ErrorCode.ACTOR_EXISTED);
            }
        }
    }

    public String generateToken(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(data.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

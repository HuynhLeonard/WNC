package com.wnc.actorserver.controller;

import com.wnc.actorserver.client.FilmClient;
import com.wnc.actorserver.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@RestController
@RequestMapping("film")
@AllArgsConstructor
@Tag(name = "Film Controller")
public class FilmController {
    private final FilmClient filmClient;

    @GetMapping("")
    ResponseEntity<ApiResponse<?>> getAllFilm(HttpServletRequest request) {
        ApiResponse<Object> response = filmClient.getAllFilms(request);
        return ResponseEntity.ok(response);
    }

}

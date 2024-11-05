package com.wnc.actorserver.controller;

import com.wnc.actorserver.client.FilmClient;
import com.wnc.actorserver.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("film")
@AllArgsConstructor
@Tag(name = "Film Controller")
public class FilmController {
    private final FilmClient filmClient;

    @GetMapping("")
    ResponseEntity<ApiResponse<?>> getAllFilm() {
        ApiResponse<Object> response = filmClient.getAllFilms();
        return ResponseEntity.ok(response);
    }
}

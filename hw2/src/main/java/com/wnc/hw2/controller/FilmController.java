package com.wnc.hw2.controller;

import com.wnc.hw2.dto.ApiResponse;
import com.wnc.hw2.dto.request.FilmCreateRequest;
import com.wnc.hw2.dto.request.FilmUpdateRequest;
import com.wnc.hw2.model.Actor;
import com.wnc.hw2.model.Film;
import com.wnc.hw2.repository.FilmRepository;
import com.wnc.hw2.service.FilmService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("film")
@AllArgsConstructor
@Tag(name = "Film Controller")
public class FilmController {
    private final FilmService filmService;

    @GetMapping
    ResponseEntity<ApiResponse<List<Film>>> getAllFilm() {
        List<Film> films = filmService.findAllFilm();
        ApiResponse<List<Film>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(films);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<Film>> getFilmById(@PathVariable("id") Long id) {
        ApiResponse<Film> apiResponse = new ApiResponse<>();
        Film film = filmService.findFilmById(id);
        apiResponse.setResult(film);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    ResponseEntity<ApiResponse<Film>> createFilm(@RequestBody FilmCreateRequest film) {
        ApiResponse<Film> apiResponse = new ApiResponse<>();
        Film newFilm = filmService.createFilm(film);
        apiResponse.setResult(newFilm);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping({"/{id}"})
    ResponseEntity<ApiResponse<String>> deleteFilm(@PathVariable("id") Long id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        filmService.deleteFilm(id);
        apiResponse.setResult("Film deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping("/{id}")
    ResponseEntity<ApiResponse<Film>> updateFilm(@PathVariable("id") Long id, @RequestBody FilmUpdateRequest film) {
        ApiResponse<Film> apiResponse = new ApiResponse<>();
        Film updatedFilm = filmService.updateFilm(film, id);
        apiResponse.setResult(updatedFilm);
        return ResponseEntity.ok(apiResponse);
    }
}

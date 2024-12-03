package com.wnc.filmserver.controller;

import com.wnc.filmserver.dto.ApiResponse;
import com.wnc.filmserver.dto.request.FilmCreateRequest;
import com.wnc.filmserver.dto.request.FilmUpdateRequest;
import com.wnc.filmserver.model.Film;
import com.wnc.filmserver.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("film")
@AllArgsConstructor
@Tag(name = "Film Controller")
public class FilmController {
    private final FilmService filmService;

    @Operation(summary = "Get all films", description = "Return a list containing all existing films", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Api called successfully", content = {
                    @Content(examples = @ExampleObject(name = "Case: API called successfully with 1 result", value = "{\n  \"code\": 1000,\n  \"result\": [\n   {\n    \"id\": 1,\r\n      \"title\": \"ACADEMY DINOSAUR\",\r\n      \"description\": \"A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies\",\r\n      \"year\": 2006,\r\n      \"language_id\": {\r\n        \"id\": 1,\r\n        \"name\": \"English\",\r\n        \"lastUpdate\": \"2006-02-15 05:02:19\"\r\n      },\r\n      \"original_language_id\": null,\r\n      \"rentalDuration\": 6,\r\n      \"rental_rate\": 1,\r\n      \"length\": 86,\r\n      \"replacement_cost\": 21,\r\n      \"rating\": \"PARENTAL_GUIDANCE_SUGGESTED\",\r\n      \"special_features\": \"Deleted Scenes,Behind the Scenes\",\r\n      \"lastUpdate\": \"2006-02-15 05:03:42\"\r\n}"))
            })
    })
    @GetMapping
    ResponseEntity<ApiResponse<List<Film>>> getAllFilm() {
        List<Film> films = filmService.findAllFilm();
        ApiResponse<List<Film>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(films);
        return ResponseEntity.ok(apiResponse);
    }

    @Operation(summary = "Get film by id", description = "Return the film object matched the given id", parameters = {
            @Parameter(name = "id", description = "The id of the film", example = "1")
    }, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Api called successfully", content = {
                    @Content(examples = @ExampleObject(name = "Case: Exist an actor has the given id", value = "{\n  \"code\": 1000,\n  \"result\": [\n   {\n    \"id\": 1,\r\n      \"title\": \"ACADEMY DINOSAUR\",\r\n      \"description\": \"A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies\",\r\n      \"year\": 2006,\r\n      \"language_id\": {\r\n        \"id\": 1,\r\n        \"name\": \"English\",\r\n        \"lastUpdate\": \"2006-02-15 05:02:19\"\r\n      },\r\n      \"original_language_id\": null,\r\n      \"rentalDuration\": 6,\r\n      \"rental_rate\": 1,\r\n      \"length\": 86,\r\n      \"replacement_cost\": 21,\r\n      \"rating\": \"PARENTAL_GUIDANCE_SUGGESTED\",\r\n      \"special_features\": \"Deleted Scenes,Behind the Scenes\",\r\n      \"lastUpdate\": \"2006-02-15 05:03:42\"\r\n}"))
            })
    })
    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<Film>> getFilmById(@PathVariable("id") Long id) {
        ApiResponse<Film> apiResponse = new ApiResponse<>();
        Film film = filmService.findFilmById(id);
        apiResponse.setResult(film);
        return ResponseEntity.ok(apiResponse);
    }

    @Operation(summary = "Create a new actor", description = "Create a new actor with first name and last name", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Api called successfully", content = {
                    @Content(examples = @ExampleObject(name = "Case: Created successfully", value = "{\r\n  \"code\": 1000,\r\n  \"result\": {\r\n    \"id\": 1005,\r\n    \"title\": \"New Film\",\r\n    \"description\": \"Creating new film for database\",\r\n    \"year\": null,\r\n    \"language_id\": {\r\n      \"id\": 1,\r\n      \"name\": \"English\",\r\n      \"lastUpdate\": \"2006-02-15 05:02:19\"\r\n    },\r\n    \"original_language_id\": {\r\n      \"id\": 1,\r\n      \"name\": \"English\",\r\n      \"lastUpdate\": \"2006-02-15 05:02:19\"\r\n    },\r\n    \"rentalDuration\": 1,\r\n    \"rental_rate\": 1,\r\n    \"length\": 100,\r\n    \"replacement_cost\": 1,\r\n    \"rating\": \"GENERAL_AUDIENCES\",\r\n    \"special_features\": \"[Trailer]\",\r\n  }\r\n}"))
            })
    })
    @PostMapping
    ResponseEntity<ApiResponse<Film>> createFilm(@RequestBody @Valid FilmCreateRequest film) {
        ApiResponse<Film> apiResponse = new ApiResponse<>();
        Film newFilm = filmService.createFilm(film);
        apiResponse.setResult(newFilm);
        return ResponseEntity.ok(apiResponse);
    }

    @Operation(summary = "Delete a film", description = "Delete an existing film with id", parameters = {
            @Parameter(name = "id", description = "Id of the film to be deleted", example = "1")
    }, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Api called successfully", content = {
                    @Content(examples = @ExampleObject(name = "Case: Deleted successfully", value = "{\n" +
                            "  \"code\": 1000,\n" +
                            "  \"message\": \"Film 1001 has been deleted.\",\n" +
                            "  \"result\": \"true\"\n" +
                            "}"))
            })
    })    @DeleteMapping({"/{id}"})
    ResponseEntity<ApiResponse<String>> deleteFilm(@PathVariable("id") Long id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        filmService.deleteFilm(id);
        apiResponse.setResult("Film deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @Operation(summary = "Update a film", description = "Edit an existing film with film infomation", parameters = {
            @Parameter(name = "id", description = "Id of the film to be edited", example = "1")
    }, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Api called successfully", content = {
                    @Content(examples = @ExampleObject(name = "Case: Updated successfully", value = "{\r\n  \"code\": 1000,\r\n  \"result\": {\r\n    \"id\": 1000,\r\n    \"title\": \"New Film\",\r\n    \"description\": \"New description\",\r\n    \"year\": 2014,\r\n    \"language_id\": {\r\n      \"id\": 1,\r\n      \"name\": \"English\",\r\n      \"lastUpdate\": \"2006-02-15 05:02:19\"\r\n    },\r\n    \"original_language_id\": null,\r\n    \"rentalDuration\": 30,\r\n    \"rental_rate\": 1,\r\n    \"length\": 0,\r\n    \"replacement_cost\": 1,\r\n    \"rating\": \"GENERAL_AUDIENCES\",\r\n    \"special_features\": \"Trailer\",\r\n    \"lastUpdate\": \"2024-10-15 13:12:54\"\r\n  }\r\n}"))
            })
    })
    @PatchMapping("/{id}")
    ResponseEntity<ApiResponse<Film>> updateFilm(@PathVariable("id") Long id, @RequestBody @Valid FilmUpdateRequest film) {
        ApiResponse<Film> apiResponse = new ApiResponse<>();
        Film updatedFilm = filmService.updateFilm(film, id);
        apiResponse.setResult(updatedFilm);
        return ResponseEntity.ok(apiResponse);
    }
}

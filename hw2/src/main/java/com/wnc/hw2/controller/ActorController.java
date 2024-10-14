package com.wnc.hw2.controller;

import com.wnc.hw2.dto.ActorDTO;
import com.wnc.hw2.dto.ApiResponse;
import com.wnc.hw2.dto.request.ActorCreateRequest;
import com.wnc.hw2.exception.ErrorCode;
import com.wnc.hw2.model.Actor;
import com.wnc.hw2.service.ActorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("actor")
@AllArgsConstructor
@Tag(name = "Actor Controller")
public class ActorController {
    private final ActorService actorService;

    @Operation(summary = "Get all actors", description = "Return a list containing all existing actors", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Api called successfully", content = {
                    @Content(examples = @ExampleObject(name = "Case: API called successfully with 1 result", value = "{\n  \"code\": 1000,\n  \"result\": [\n   {\n    \"actor_id\": 1,\n    \"first_name\": \"PENELOPE\",\n    \"last_name\": \"GUINESS\",\n    \"lastUpdate\": \"2006-02-15 04:34:33\"\n  }\n  ]\n}"))
            })
    })
    @GetMapping
    ResponseEntity<ApiResponse<List<Actor>>> getAllActor() {
        List<Actor> actors = actorService.getAllActor();
        ApiResponse<List<Actor>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(actors);
        return ResponseEntity.ok(apiResponse);
    }

    @Operation(summary = "Get actor by id", description = "Return the actor object matched the given id", parameters = {
            @Parameter(name = "id", description = "The id of the actor", example = "1")
    }, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Api called successfully", content = {
                    @Content(examples = @ExampleObject(name = "Case: Exist an actor has the given id", value = "{\n" +
                            "  \"code\": 1,\n" +
                            "  \"result\": {\n" +
                            "    \"actor_id\": 1,\n" +
                            "    \"first_name\": \"PENELOPE\",\n" +
                            "    \"last_name\": \"GUINESS\",\n" +
                            "    \"lastUpdate\": \"2006-02-15 04:34:33\"\n" +
                            "  }\n" +
                            "}"))
            })
    })
    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<Actor>> getActorById(@PathVariable("id") Long id){
        ApiResponse<Actor> apiResponse = new ApiResponse<>();
            Actor actor = actorService.getSingleActor(id);
            apiResponse.setResult(actor);
            return ResponseEntity.ok(apiResponse);
    }

    @Operation(summary = "Create a new actor", description = "Create a new actor with first name and last name", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Api called successfully", content = {
                    @Content(examples = @ExampleObject(name = "Case: Created successfully", value = "{\n" +
                            "  \"code\": 1000,\n" +
                            "  \"result\": \"Actor{first_name='THeAnh', last_name='Dinh'}\"\n" +
                            "}"))
            })
    })
    @PostMapping
    public ResponseEntity<?> createActor(@RequestBody @Valid ActorCreateRequest actorDTO) {
        Actor actor = actorService.createNewActor(actorDTO);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setResult(actor.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @Operation(summary = "Delete an actor", description = "Delete an existing actor with id", parameters = {
            @Parameter(name = "id", description = "Id of the actor to be deleted", example = "1")
    }, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Api called successfully", content = {
                    @Content(examples = @ExampleObject(name = "Case: Deleted successfully", value = "{\n" +
                            "  \"code\": 1000,\n" +
                            "  \"message\": \"Actor 201 has been deleted.\",\n" +
                            "  \"result\": \"true\"\n" +
                            "}"))
            })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id) {
        try{
            boolean success = actorService.deleteActor(id);
            ApiResponse<Boolean> apiResponse = new ApiResponse<>();
            apiResponse.setResult(success);
            apiResponse.setMessage("Actor " + id + " has been updated.");
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @Operation(summary = "Update an actor", description = "Edit an existing actor with first name and last name", parameters = {
            @Parameter(name = "id", description = "Id of the actor to be edited", example = "1")
    }, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Api called successfully", content = {
                    @Content(examples = @ExampleObject(name = "Case: Updated successfully", value = "{\n" +
                            "  \"code\": 1000,\n" +
                            "  \"message\": \"Actor 201 has been updated.\",\n" +
                            "  \"result\": \"Actor{first_name='string', last_name='string'}\"\n" +
                            "}"))
            })
    })
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateActor(@PathVariable Long id, @RequestBody @Valid ActorDTO actorDTO) {
        try {
            Actor actor = actorService.updateActor(id, actorDTO);
            ApiResponse<String> apiResponse = new ApiResponse<>();
            apiResponse.setResult(actor.toString());
            apiResponse.setMessage("Actor " + id + " has been updated.");
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }
}
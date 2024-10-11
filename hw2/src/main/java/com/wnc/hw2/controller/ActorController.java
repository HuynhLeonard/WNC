package com.wnc.hw2.controller;

import com.wnc.hw2.dto.ActorDTO;
import com.wnc.hw2.dto.ApiResponse;
import com.wnc.hw2.dto.request.ActorCreateRequest;
import com.wnc.hw2.exception.ErrorCode;
import com.wnc.hw2.model.Actor;
import com.wnc.hw2.service.ActorService;
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
public class ActorController {
    private final ActorService actorService;

    @GetMapping
    ResponseEntity<ApiResponse<List<Actor>>> getAllActor() {
        List<Actor> actors = actorService.getAllActor();
        ApiResponse<List<Actor>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(actors);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<Actor>> getActorById(@PathVariable("id") Long id){
        ApiResponse<Actor> apiResponse = new ApiResponse<>();
            Actor actor = actorService.getSingleActor(id);
            apiResponse.setResult(actor);
            return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<?> createActor(@RequestBody @Valid ActorCreateRequest actorDTO) {
        Actor actor = actorService.createNewActor(actorDTO);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setResult(actor.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id) {
        try{
            boolean success = actorService.deleteActor(id);
            return ResponseEntity.ok("Actor " + id + " has been deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(200).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateActor(@PathVariable Long id, @RequestBody @Valid ActorDTO actorDTO) {
        try {
            Actor actor = actorService.updateActor(id, actorDTO);
            return ResponseEntity.ok(actor);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(200).body(e.getMessage());
        }

    }
}
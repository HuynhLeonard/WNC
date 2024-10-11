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
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(actors);
        //return new ResponseEntity<List<Actor>>(actors, HttpStatus.OK);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<Actor>> getActorById(@PathVariable("id") Long id){
        ApiResponse apiResponse = new ApiResponse();
            Actor actor = actorService.getSingleActor(id);
            apiResponse.setResult(actor);
            return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<?> createActor(@RequestBody @Valid ActorCreateRequest actorDTO) {
        Actor actor = actorService.createNewActor(actorDTO);
        return new  ResponseEntity<String>(actor.toString(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id) {
        try{
            boolean success = actorService.deleteActor(id);
            return ResponseEntity.ok("Actor " + id + " has been deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(200).body(e.getMessage());
        }
        //Actor actor = actorService.deleteActor(id);
        //return ResponseEntity.ok(actor);
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
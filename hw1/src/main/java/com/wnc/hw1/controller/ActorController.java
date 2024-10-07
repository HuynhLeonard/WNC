package com.wnc.hw1.controller;

import com.wnc.hw1.dto.ActorDTO;
import com.wnc.hw1.model.Actor;
import com.wnc.hw1.service.ActorService;
import jakarta.persistence.EntityNotFoundException;
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
    ResponseEntity<List<Actor>> getAllActor() {
        List<Actor> actors = actorService.getAllActor();
        //return new ResponseEntity<List<Actor>>(actors, HttpStatus.OK);
        return ResponseEntity.ok(actors);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getActorById(@PathVariable("id") Long id){
        try {
            Actor actor = actorService.getSingleActor(id);
            //return new ResponseEntity<Actor>(actor, HttpStatus.OK);
            return ResponseEntity.ok(actor);
        } catch (EntityNotFoundException e) {
            //return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createActor(@RequestBody ActorDTO actorDTO) {
        Actor actor = actorService.createNewActor(actorDTO);
        return new  ResponseEntity<String>(actor.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id) {
        try{
            boolean success = actorService.deleteActor(id);
            return ResponseEntity.ok("Actor " + id + " has been deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        //Actor actor = actorService.deleteActor(id);
        //return ResponseEntity.ok(actor);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateActor(@PathVariable Long id, @RequestBody ActorDTO actorDTO) {
        try {
            Actor actor = actorService.updateActor(id, actorDTO);
            return ResponseEntity.ok(actor);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }
}

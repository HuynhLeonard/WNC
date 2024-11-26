package com.wnc.gRPC.client_server.controller;

import com.wnc.gRPC.client_server.dto.ActorDTO;
import com.wnc.gRPC.client_server.service.ClientActorService;
import com.wnc.gRPC.gRPC_server.entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {
    @Autowired
    private ClientActorService clientActorService;

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActor() {
        return ResponseEntity.ok(clientActorService.getAllActors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") int id) {
        return ResponseEntity.ok(clientActorService.getActorById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable("id") int id, @RequestBody ActorDTO actor) {
        return ResponseEntity.ok(clientActorService.updateActor(id, actor));
    }
}

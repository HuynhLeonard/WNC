package com.client.client.controller;

import com.client.client.model.RequestModel;
import com.client.client.service.SumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SimpleController {
    private SumService service;

    @Autowired
    public SimpleController(SumService service) {
        this.service = service;
    }

    @PostMapping("/sum")
    public ResponseEntity<Integer> sum(@RequestBody RequestModel request) {
        return ResponseEntity.status(200).body(service.sum(request.getA(), request.getB()));
    }
}

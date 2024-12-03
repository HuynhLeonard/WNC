package com.wnc.actorserver.controller;

import java.util.List;

import com.wnc.actorserver.dto.ApiResponse;
import com.wnc.actorserver.dto.request.UserCreationRequest;
import com.wnc.actorserver.model.User;
import com.wnc.actorserver.service.UserService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<User>builder()
                .result(userService.createUser(request))
                .build();
    }

}

package com.wnc.hw2.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

// adding code for film later, start with "2xxx"
@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    ACTOR_EXISTED(1002, "Actor existed", HttpStatus.BAD_REQUEST),
    FIRSTNAME_INVALID(1003, "First name must be under {min} characters", HttpStatus.BAD_REQUEST),
    LASTNAME_INVALID(1004, "Last name must be under {min} characters", HttpStatus.BAD_REQUEST),
    ACTOR_NOT_EXISTED(1005, "Actor not existed", HttpStatus.NOT_FOUND),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
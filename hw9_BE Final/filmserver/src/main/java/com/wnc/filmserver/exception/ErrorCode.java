package com.wnc.filmserver.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    INVALID_TOKEN(9001, "Invalid token", HttpStatus.BAD_REQUEST),
    INVALID_TIME(9002, "Request time expired", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(9003, "Unauthorized", HttpStatus.UNAUTHORIZED),

    //UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    MISSING_FIELD(1006, "Missing fields", HttpStatus.BAD_REQUEST),
    ACTOR_EXISTED(1002, "Actor existed", HttpStatus.BAD_REQUEST),
    FIRSTNAME_INVALID(1003, "First name must be between 1 and 45 characters", HttpStatus.BAD_REQUEST),
    LASTNAME_INVALID(1004, "Last name must be between 1 and 45 characters", HttpStatus.BAD_REQUEST),
    ACTOR_NOT_EXISTED(1005, "Actor not existed", HttpStatus.NOT_FOUND),

    // Film-related errors (starting with "2xxx")
    FILM_NOT_FOUND(2001, "Film not found", HttpStatus.NOT_FOUND),
    FILM_TITLE_INVALID(2002, "Film title must be between 1 and 255 characters", HttpStatus.BAD_REQUEST),
    FILM_DESCRIPTION_TOO_LONG(2003, "Film description exceeds allowed length", HttpStatus.BAD_REQUEST),
    FILM_RELEASE_YEAR_INVALID(2004, "Film release year is invalid", HttpStatus.BAD_REQUEST),
    FILM_ALREADY_EXISTS(2005, "Film already exists", HttpStatus.BAD_REQUEST),
    FILM_RENTAL_DURATION_INVALID(2006, "Film rental duration must be between 1 and 30 days", HttpStatus.BAD_REQUEST),
    FILM_RATING_INVALID(2007, "Film rating is invalid. Accepted value: G,NC-17,PG-13,R,PG", HttpStatus.BAD_REQUEST),
    FILM_SPECIAL_FEATURES_INVALID(2008, "Film special features are invalid", HttpStatus.BAD_REQUEST),
    FILM_RENTAL_RATE_INVALID(2009, "Film rental rate must be a positive value", HttpStatus.BAD_REQUEST),
    FILM_REPLACEMENT_COST_INVALID(2010, "Film replacement cost must be a positive value", HttpStatus.BAD_REQUEST),
    INVALID_LANGUAGE_ID(2011, "ID Language is invalid", HttpStatus.BAD_REQUEST),
    FILM_LENGTH_INVALID(2012, "Film length must be a positive value", HttpStatus.BAD_REQUEST),
    SPECIAL_FEATURE_INVALID(2013, "Special feature is invalid. Accepted values: Trailers, Commentaries, Deleted Scenes, Behind the Scenes.", HttpStatus.BAD_REQUEST)
    ;



    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    public static ErrorCode getByFieldName(String fieldName) {
        try {
            return ErrorCode.valueOf(fieldName);
        } catch (IllegalArgumentException e) {
            // Handle invalid field name
            throw new RuntimeException("ErrorCode not found for field name: " + fieldName);
        }
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
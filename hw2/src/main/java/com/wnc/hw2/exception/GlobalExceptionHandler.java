package com.wnc.hw2.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.wnc.hw2.dto.ApiResponse;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException e){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(404);
        apiResponse.setMessage(e.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }

    // handling for missing data in request.
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
//        String enumKey = exception.getFieldError().getDefaultMessage();
//
//        ErrorCode errorCode = ErrorCode.INVALID_KEY;
//        Map<String, Object> attributes = null;
//        try {
//            errorCode = ErrorCode.valueOf(enumKey);
//
//            var constraintViolation =
//                    exception.getBindingResult().getAllErrors().getFirst().unwrap(ConstraintViolation.class);
//
//            attributes = constraintViolation.getConstraintDescriptor().getAttributes();
//
//        } catch (IllegalArgumentException e) {
//
//        }
//
//        ApiResponse apiResponse = new ApiResponse();
//
//        apiResponse.setCode(errorCode.getCode());
//        apiResponse.setMessage(
//                Objects.nonNull(attributes)
//                        ? mapAttribute(errorCode.getMessage(), attributes)
//                        : errorCode.getMessage());
//
//        return ResponseEntity.badRequest().body(apiResponse);
//    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<List<ApiResponse<Object>>> handlingValidation2(MethodArgumentNotValidException ex) {
        List<ApiResponse<Object>> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            //System.out.println(errorMessage);

            ErrorCode errorCode = ErrorCode.getByFieldName(errorMessage);
            ApiResponse<Object> apiResponse = new ApiResponse<>();
            apiResponse.setCode(errorCode.getCode());
            apiResponse.setMessage(fieldName + ": " + errorCode.getMessage());
            errors.add(apiResponse);
        });

        return ResponseEntity.badRequest().body(errors);
    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
            String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));

            return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }
}

package com.example.college.dto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MethodArgumentNotValidException {

    @ExceptionHandler
    public ResponseEntity<ApiResponse<Void>> methodArgumentNotValidException(org.springframework.web.bind.MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(ApiResponse.<Void>builder()
                .code(-3)
                .massage("validation error")
                .errors(e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(fieldError -> {
                            String field = fieldError.getField();
                            String message = fieldError.getDefaultMessage();
                            String rejectionValue = String.valueOf(fieldError.getRejectedValue());
                            return new ErrorDto(field, String.format("message :: %s,rejection :: %s", message, rejectionValue));
                        }).toList())

                .build());
    }

}


package com.example.college.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String massage;
    private int code;
    private boolean success;
    private T data;

    private List<ErrorDto> errors;
}

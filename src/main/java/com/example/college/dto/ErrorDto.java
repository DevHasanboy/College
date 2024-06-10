package com.example.college.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    private String fieldMessage;
    private String message;
}

package com.example.college.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudentDto {
    private Integer id;
    @NotNull(message = "courseId cannot be null or empty")
    private Integer courseId;
    @NotNull(message = "courseId cannot be null or empty")
    private Integer studentId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

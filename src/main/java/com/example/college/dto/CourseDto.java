package com.example.college.dto;

import com.example.college.model.CourseStudent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Integer id;
    @NotNull(message = "departmentId cannot be null or empty")
    private Integer departmentId;
    @NotNull(message = "departmentId cannot be null or empty")
    private Integer instructorId;
    @NotNull(message = "departmentId cannot be null or empty")
    private Integer duration;
    @NotBlank(message = "name cannot be null or empty")
    private String name;

    private List<CourseStudentDto> courseStudents;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}

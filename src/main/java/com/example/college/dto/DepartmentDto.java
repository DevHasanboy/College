package com.example.college.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Integer id;
    @NotBlank(message = "name cannot be null or empty")
    private String name;
    @NotBlank(message = "location cannot be null or empty")
    private String location;
    private List<InstructorDto> instructors;
    private List<CourseDto> courses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

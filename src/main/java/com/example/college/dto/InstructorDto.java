package com.example.college.dto;

import com.example.college.model.CourseStudent;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDto {
    private Integer id;
    private Integer departmentId;
    @NotBlank(message = "headedby cannot be null or empty")
    private String headedby;
    @NotBlank(message = "firstName cannot be null or empty")
    private String firstName;
    @NotBlank(message = "lastName cannot be null or empty")
    private String lastName;
    @NotBlank(message = "lastName cannot be null or empty")
    private String phone;
    private List<CourseStudentDto> courseStudents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}

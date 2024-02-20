package com.example.college.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer departmentId;
    private Integer instructorId;
    private Integer duration;
    private String name;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<CourseStudent> courseStudents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}

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
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Instructor>instructors;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Course> courses;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

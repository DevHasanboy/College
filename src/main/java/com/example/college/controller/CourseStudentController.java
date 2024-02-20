package com.example.college.controller;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.CourseStudentDto;
import com.example.college.impl.CourseStudentImpl;
import com.example.college.service.CourseStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("course_student")
@RequiredArgsConstructor
public class CourseStudentController implements CourseStudentService {
    private final CourseStudentImpl courseStudent;

    @PostMapping
    @Override
    public ApiResponse<CourseStudentDto> create(@RequestBody @Valid CourseStudentDto dto) {
        return this.courseStudent.create(dto);
    }


    @GetMapping(value = ("/{id}"))
    @Override
    public ApiResponse<CourseStudentDto> get(@PathVariable(value = "id") Integer id) {
        return this.courseStudent.get(id);
    }

    @PutMapping(value = "/{id}")
    @Override
    public ApiResponse<CourseStudentDto> update(@RequestBody CourseStudentDto dto, @PathVariable(value = "id") Integer id) {
        return this.courseStudent.update(dto, id);
    }


    @DeleteMapping(value = "/{id}")
    @Override
    public ApiResponse<CourseStudentDto> delete(@PathVariable(value = "id") Integer id) {
        return this.courseStudent.delete(id);
    }


    @GetMapping(value = "/get_all")
    @Override
    public ApiResponse<List<CourseStudentDto>> getAll() {
        return this.courseStudent.getAll();
    }
}

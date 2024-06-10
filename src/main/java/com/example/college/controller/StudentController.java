package com.example.college.controller;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.StudentDto;
import com.example.college.impl.StudentServiceImpl;
import com.example.college.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor

public class StudentController implements StudentService {

    private final StudentServiceImpl studentService;

    @PostMapping
    @Override
    public ApiResponse<StudentDto> create(@RequestBody StudentDto dto) {
        return this.studentService.create(dto);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ApiResponse<StudentDto> get(@PathVariable(value = "id") Integer id) {
        return this.studentService.get(id);
    }

    @PutMapping(value = "/{id}")
    @Override
    public ApiResponse<StudentDto> update(@RequestBody StudentDto dto, @PathVariable(value = "id") Integer id) {
        return this.studentService.update(dto, id);
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ApiResponse<StudentDto> delete(@PathVariable(value = "id") Integer id) {
        return this.studentService.delete(id);
    }

    @GetMapping(value = "/get-all")
    @Override
    public ApiResponse<List<StudentDto>> getAll() {
        return this.studentService.getAll();
    }
}

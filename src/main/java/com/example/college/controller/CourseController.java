package com.example.college.controller;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.CourseDto;
import com.example.college.impl.CourseServiceImpl;
import com.example.college.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
@RequiredArgsConstructor
public class CourseController implements CourseService {

    private final CourseServiceImpl courseService;

    @PostMapping
    @Override
    public ApiResponse<CourseDto> create(@RequestBody @Valid CourseDto dto) {
        return this.courseService.create(dto);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ApiResponse<CourseDto> get(@PathVariable (value = "id") Integer id) {
        return this.courseService.get(id);
    }

    @PutMapping(value = "/{id}")
    @Override
    public ApiResponse<CourseDto> update(@RequestBody CourseDto dto,@RequestParam (value = "id") Integer id) {
        return this.courseService.update(dto, id);
    }
    @DeleteMapping(value = "/{id}")
    @Override
    public ApiResponse<CourseDto> delete(@PathVariable (value = "id") Integer id) {
        return this.courseService.delete(id);
    }

    @GetMapping("/get_all")
    @Override
    public ApiResponse<List<CourseDto>> getAll() {
        return this.courseService.getAll();
    }
}

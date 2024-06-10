package com.example.college.controller;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.DepartmentDto;
import com.example.college.impl.DepartmentServiceImpl;
import com.example.college.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("depart")
@RequiredArgsConstructor
public class DepartmentController implements DepartmentService {

    private final DepartmentServiceImpl departmentService;

    @PostMapping
    @Override
    public ApiResponse<DepartmentDto> create(@RequestBody @Valid DepartmentDto dto) {
        return this.departmentService.create(dto);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ApiResponse<DepartmentDto> get(@PathVariable(value = "id") Integer id) {
        return this.departmentService.get(id);
    }

    @PutMapping(value = "/{id}")
    @Override
    public ApiResponse<DepartmentDto> update(@RequestBody DepartmentDto dto, @PathVariable(value = "id") Integer id) {
        return this.departmentService.update(dto, id);
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ApiResponse<DepartmentDto> delete(@PathVariable(value = "id") Integer id) {
        return this.departmentService.delete(id);
    }


    @GetMapping(value = "/get_all")
    @Override
    public ApiResponse<List<DepartmentDto>> getAll() {
        return this.departmentService.getAll();
    }
}

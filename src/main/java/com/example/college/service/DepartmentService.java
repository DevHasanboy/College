package com.example.college.service;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.DepartmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    ApiResponse<DepartmentDto> create(DepartmentDto dto);
    ApiResponse<DepartmentDto> get(Integer id);
    ApiResponse<DepartmentDto> update(DepartmentDto dto,Integer id);
    ApiResponse<DepartmentDto> delete(Integer id);
    ApiResponse<List<DepartmentDto>> getAll();
}

package com.example.college.service;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    ApiResponse<StudentDto> create(StudentDto dto);
    ApiResponse<StudentDto> get(Integer id);
    ApiResponse<StudentDto> update(StudentDto dto,Integer id);
    ApiResponse<StudentDto> delete(Integer id);
   ApiResponse<List<StudentDto>> getAll();
}

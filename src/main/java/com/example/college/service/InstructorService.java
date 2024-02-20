package com.example.college.service;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.InstructorDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface InstructorService {

    ApiResponse<InstructorDto> create(InstructorDto dto);
    ApiResponse<InstructorDto> get(Integer id);
    ApiResponse<InstructorDto> update(InstructorDto dto,Integer id);
    ApiResponse<InstructorDto> delete(Integer id);
    ApiResponse<List<InstructorDto>> getAll();
}

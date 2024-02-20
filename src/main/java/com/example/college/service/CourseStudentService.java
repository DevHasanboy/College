package com.example.college.service;


import com.example.college.dto.ApiResponse;
import com.example.college.dto.CourseStudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseStudentService {

    ApiResponse<CourseStudentDto> create(CourseStudentDto dto);
    ApiResponse<CourseStudentDto> get(Integer id);
    ApiResponse<CourseStudentDto> update(CourseStudentDto dto,Integer id);
    ApiResponse<CourseStudentDto> delete(Integer id);
    ApiResponse<List<CourseStudentDto>> getAll();
}

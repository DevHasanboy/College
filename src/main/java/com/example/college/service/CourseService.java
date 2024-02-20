package com.example.college.service;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.CourseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

     ApiResponse<CourseDto> create(CourseDto dto);
     ApiResponse<CourseDto> get(Integer id);
     ApiResponse<CourseDto> update(CourseDto dto,Integer id);
     ApiResponse<CourseDto> delete(Integer id);
     ApiResponse<List<CourseDto>> getAll();

}

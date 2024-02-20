package com.example.college.impl;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.CourseDto;
import com.example.college.impl.mapper.CourseMapper;
import com.example.college.model.Course;
import com.example.college.repository.CourseRepository;
import com.example.college.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.List;


@Component
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    @Override
    public ApiResponse<CourseDto> create(CourseDto dto) {
        try {
            Course course=this.courseMapper.toEntity(dto);
            course.setCreatedAt(LocalDateTime.now());
            this.courseRepository.save(course);
            return ApiResponse.<CourseDto>builder()
                    .success(true)
                    .massage("ok")
                    .data(this.courseMapper.toDto(
                           course
                    ))

                    .build();
        }catch (Exception e){
            return ApiResponse.<CourseDto>builder()
                    .code(-1)
                    .massage(String.format("while is saving error",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<CourseDto> get(Integer id) {
        return this.courseRepository.findByIdAndDeletedAtIsNull(id)
                .map(course -> {
                   return ApiResponse.<CourseDto>builder()
                           .success(true)
                           .massage("ok")
                           .data(this.courseMapper.toDtoWithCoursStudent(course))
                           .build();
                }).orElse(ApiResponse.<CourseDto>builder()
                        .code(-1)
                        .massage(String.format("not foun of id : %d",id))
                        .build());
    }

    @Override
    public ApiResponse<CourseDto> update(CourseDto dto, Integer id) {
        return this.courseRepository.findByIdAndDeletedAtIsNull(id)
                .map(course -> {
                    this.courseMapper.toUpdate(course,dto);
                    course.setUpdatedAt(LocalDateTime.now());
                    courseRepository.save(course);
                    return ApiResponse.<CourseDto>builder()
                            .success(true)
                            .massage("ok")
                            .data(this.courseMapper.toDto(course))
                            .build();
                }).orElse(ApiResponse.<CourseDto>builder()
                        .code(-1)
                        .massage(String.format("not foun of id : %d",id))
                        .build());
    }

    @Override
    public ApiResponse<CourseDto> delete(Integer id) {
        return this.courseRepository.findByIdAndDeletedAtIsNull(id)
                .map(course -> {
                    course.setDeletedAt(LocalDateTime.now());
                    courseRepository.delete(course);
                    return ApiResponse.<CourseDto>builder()
                            .success(true)
                            .massage("ok")
                            .data(this.courseMapper.toDto(course))
                            .build();
                }).orElse(ApiResponse.<CourseDto>builder()
                        .code(-1)
                        .massage(String.format("not foun of id : %d",id))
                        .build());
    }

    @Override
    public ApiResponse<List<CourseDto>> getAll() {
        List<Course> list=this.courseRepository.findAllByDeletedAtIsNull();
        if (list.isEmpty()){
            return ApiResponse.<List<CourseDto>>builder()
                    .code(-1)
                    .massage("courses is not")
                    .build();
        }
        return ApiResponse.<List<CourseDto>>builder()
                .success(true)
                .massage("ok")
                .data(list.stream().map(this.courseMapper::toDto).toList())
                .build();
    }
}

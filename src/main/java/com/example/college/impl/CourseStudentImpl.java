package com.example.college.impl;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.CourseStudentDto;
import com.example.college.impl.mapper.CourseStudentMapper;
import com.example.college.model.CourseStudent;
import com.example.college.repository.CourseStudentRepository;
import com.example.college.service.CourseStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
@RequiredArgsConstructor
public class CourseStudentImpl implements CourseStudentService {

    private final CourseStudentRepository courseStudentRepository;
    private final CourseStudentMapper courseStudentMapper;
    @Override
    public ApiResponse<CourseStudentDto> create(CourseStudentDto dto) {
        try {
            CourseStudent courseStudent=this.courseStudentMapper.toEntity(dto);
            courseStudent.setCreatedAt(LocalDateTime.now());
            this.courseStudentRepository.save(courseStudent);
            return ApiResponse.<CourseStudentDto>builder()
                    .success(true)
                    .massage("ok")
                    .data(this.courseStudentMapper.toDto(courseStudent))
                    .build();

        }catch (Exception e){
            return ApiResponse.<CourseStudentDto>builder()
                    .code(-1)
                    .massage(String.format("'while saving error %s :",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<CourseStudentDto> get(Integer id) {
        return this.courseStudentRepository.findByIdAndDeletedAtIsNull(id)
                .map(courseStudent -> {
                    return ApiResponse.<CourseStudentDto>builder()
                            .success(true)
                            .massage("ok")
                            .data(this.courseStudentMapper.toDto(courseStudent))

                            .build();
                }).orElse(ApiResponse.<CourseStudentDto>builder()
                        .code(-1)
                        .massage(String.format("'not found is id %d :",id))
                        .build());
    }

    @Override
    public ApiResponse<CourseStudentDto> update(CourseStudentDto dto, Integer id) {
        try {
            return this.courseStudentRepository.findByIdAndDeletedAtIsNull(id)
                    .map(courseStudent -> {
                        courseStudent.setUpdatedAt(LocalDateTime.now());
                        courseStudentMapper.toUpdate(courseStudent,dto);
                        courseStudentRepository.save(courseStudent);
                        return
                                ApiResponse.<CourseStudentDto>builder()
                                        .success(true)
                                        .massage("ok")
                                        .data(this.courseStudentMapper.toDto(courseStudent))
                                        .build();

                    }).orElse(ApiResponse.<CourseStudentDto>builder()
                            .code(-1)
                            .massage(String.format("'not found is id %d :",id))
                            .build());
        }catch (Exception e){
            return ApiResponse.<CourseStudentDto>builder()
                    .code(-1)
                    .massage(String.format("'while updating error %s :",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<CourseStudentDto> delete(Integer id) {
        return this.courseStudentRepository.findByIdAndDeletedAtIsNull(id)
                .map(courseStudent -> {
                    courseStudent.setDeletedAt(LocalDateTime.now());
                    courseStudentRepository.delete(courseStudent);
                    return ApiResponse.<CourseStudentDto>builder()
                            .success(true)
                            .massage("ok")
                            .data(this.courseStudentMapper.toDto(courseStudent))
                            .build();
                }).orElse(ApiResponse.<CourseStudentDto>builder()
                        .code(-1)
                        .massage(String.format("'not found is id %d :",id))
                        .build());
    }

    @Override
    public ApiResponse<List<CourseStudentDto>>  getAll() {
        List<CourseStudent> list=this.courseStudentRepository.findAllByDeletedAtIsNull();
        if (list.isEmpty()){
            return ApiResponse.<List<CourseStudentDto>>builder()
                    .code(-1)
                    .massage("error")
                    .build();
        }
        return
                ApiResponse.<List<CourseStudentDto>>builder()
                        .success(true)
                        .massage("ok")
                        .data( list.stream().map(this.courseStudentMapper::toDto).toList())
                        .build();
    }
}

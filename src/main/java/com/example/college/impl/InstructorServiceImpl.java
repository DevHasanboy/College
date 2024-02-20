package com.example.college.impl;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.InstructorDto;
import com.example.college.impl.mapper.InstructorMapper;
import com.example.college.model.Instructor;
import com.example.college.repository.InstructorRepository;
import com.example.college.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor

public class InstructorServiceImpl implements InstructorService {
    private final InstructorMapper instructorMapper;
    private final InstructorRepository instructorRepository;
    @Override
    public ApiResponse<InstructorDto> create(InstructorDto dto) {
        try {
            Instructor instructor=this.instructorMapper.toEntity(dto);
            instructor.setCreatedAt(LocalDateTime.now());
            instructorRepository.save(instructor);
            return ApiResponse.<InstructorDto>builder()
                    .success(true)
                    .massage("ok")
                    .data(this.instructorMapper.toDto(instructor))
                    .build();
        }catch (Exception e){
            return ApiResponse.<InstructorDto>builder()
                    .code(-1)
                    .massage(String.format("while is error saving",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<InstructorDto> get(Integer id) {
        return this.instructorRepository.findByIdAndDeletedAtIsNull(id)
                .map(instructor -> {
                    return ApiResponse.<InstructorDto>builder()
                            .success(true)
                            .massage("ok")
                            .data(this.instructorMapper.toDto(instructor))
                            .build();
                }).orElse(ApiResponse.<InstructorDto>builder()
                        .code(-1)
                        .massage(String.format("not found id %d :",id))
                        .build());
    }

    @Override
    public ApiResponse<InstructorDto> update(InstructorDto dto, Integer id) {
        try {
            return this.instructorRepository.findByIdAndDeletedAtIsNull(id)
                    .map(instructor -> {
                        instructor.setUpdatedAt(LocalDateTime.now());
                        instructorMapper.toUpdate(instructor,dto);
                        instructorRepository.save(instructor);
                        return ApiResponse.<InstructorDto>builder()
                                .success(true)
                                .massage("ok")
                                .data(this.instructorMapper.toDto(instructor))
                                .build();
                    }).orElse(ApiResponse.<InstructorDto>builder()
                            .code(-1)
                            .massage(String.format("not found id %d :",id))
                            .build());

        }catch (Exception e){
            return ApiResponse.<InstructorDto>builder()
                    .code(-1)
                    .massage(String.format("while is error updating",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<InstructorDto> delete(Integer id) {
        return this.instructorRepository.findByIdAndDeletedAtIsNull(id)
                .map(instructor -> {
                    instructor.setDeletedAt(LocalDateTime.now());
                    instructorRepository.delete(instructor);
                    return ApiResponse.<InstructorDto>builder()
                            .success(true)
                            .massage("ok")
                            .data(this.instructorMapper.toDto(instructor))
                            .build();
                }).orElse(ApiResponse.<InstructorDto>builder()
                        .code(-1)
                        .massage(String.format("not found id %d :",id))
                        .build());
    }

    @Override
    public ApiResponse<List<InstructorDto>> getAll() {
       List<Instructor> list=this.instructorRepository.findAllByDeletedAtIsNull();
       if (list.isEmpty()){
           return
                   ApiResponse.<List<InstructorDto>>builder()
                           .code(-1)
                           .massage("error")
                           .build();
       }
       return ApiResponse.<List<InstructorDto>>builder()
               .success(true)
               .massage("ok")
               .data(list.stream().map(this.instructorMapper::toDto).toList())
               .build();
    }
}

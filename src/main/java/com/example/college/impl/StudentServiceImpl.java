package com.example.college.impl;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.StudentDto;
import com.example.college.impl.mapper.StudentMapper;
import com.example.college.model.Student;
import com.example.college.repository.StudentRepository;
import com.example.college.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
@RequiredArgsConstructor

public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;






    @Override
    public ApiResponse<StudentDto> create(StudentDto dto) {
        try {
            Student student=this.studentMapper.ToEntity(dto);
            student.setCreatedAt(LocalDateTime.now());
            this.studentRepository.save(student);
            return ApiResponse.<StudentDto>builder()
                    .success(true)
                    .massage("ok")
                    .data(this.studentMapper.ToDto(student))
                    .build();
        }catch (Exception e){
            return ApiResponse.<StudentDto>builder()
                    .code(-1)
                    .massage(String.format("while is saving error %s :: ",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<StudentDto> get(Integer id) {
        return this.studentRepository.findByIdAndDeletedAtIsNull(id)
                .map(student ->
                        ApiResponse.<StudentDto>builder()
                                .success(true)
                                .massage("ok")
                                .data(this.studentMapper.ToDto(student))
                                .build()

                        ).orElse(ApiResponse.<StudentDto>builder()
                        .code(-1)
                        .massage(String.format("not found of id %d :: id",id))
                        .build());
    }

    @Override
    public ApiResponse<StudentDto> update(StudentDto dto, Integer id) {
        try {
            return this.studentRepository.findByIdAndDeletedAtIsNull(id)
                    .map(student -> {

                        student.setUpdatedAt(LocalDateTime.now());
                        studentMapper.toUpdate(student,dto);
                        studentRepository.save(student);
                        return ApiResponse.<StudentDto>builder()
                                .success(true)
                                .massage("ok")
                                .data(this.studentMapper.ToDto(student))
                                .build();
                    }).orElse(ApiResponse.<StudentDto>builder()
                            .code(-1)
                            .massage(String.format("not found of id %d :: id",id))
                            .build());

        }catch (Exception e){
            return ApiResponse.<StudentDto>builder()
                    .code(-1)
                    .massage(String.format("while is updating error %s :: ",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<StudentDto> delete(Integer id) {
        return this.studentRepository.findByIdAndDeletedAtIsNull(id)
                .map(student -> {
                    student.setDeletedAt(LocalDateTime.now());
                    studentRepository.delete(student);
                    return ApiResponse.<StudentDto>builder()
                            .success(true)
                            .massage("ok")
                            .data(this.studentMapper.ToDto(student))
                            .build();
                }).orElse(ApiResponse.<StudentDto>builder()
                        .code(-1)
                        .massage(String.format("not found of id %d :: id",id))
                        .build());
    }

    @Override
    public ApiResponse<List<StudentDto>> getAll() {
        List<Student> list=this.studentRepository.findAllByDeletedAtIsNull();
        if (list.isEmpty()){
            return ApiResponse.<List<StudentDto>>builder()
                    .success(true)
                    .massage("ok")
                    .build();
        }
        return ApiResponse.<List<StudentDto>>builder()
                .success(true)
                .massage("ok")
                .data(list.stream().map(this.studentMapper::ToDto).toList())
                .build();
    }
}

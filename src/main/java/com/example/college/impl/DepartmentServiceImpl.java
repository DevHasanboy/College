package com.example.college.impl;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.DepartmentDto;
import com.example.college.impl.mapper.DepartmentMapper;
import com.example.college.model.Department;
import com.example.college.repository.DepartmentRepository;
import com.example.college.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public ApiResponse<DepartmentDto> create(DepartmentDto dto) {
        try {
            Department department=this.departmentMapper.toEntity(dto);
            department.setCreatedAt(LocalDateTime.now());
            departmentRepository.save(department);
            return ApiResponse.<DepartmentDto>builder()
                    .success(true)
                    .massage("ok")
                    .data(this.departmentMapper.toDtoWithInstructorAndCourse(department))
                    .build();
        }catch (Exception e){
            return ApiResponse.<DepartmentDto>builder()
                    .code(-1)
                    .massage(String.format("while is saving error : %s",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<DepartmentDto> get(Integer id) {
        return this.departmentRepository.findByIdAndDeletedAtIsNull(id)
            .map(department -> {
                return ApiResponse.<DepartmentDto>builder()
                        .success(true)
                        .massage("ok")
                        .data(this.departmentMapper.toDto(department))
                        .build();

            }).orElse(ApiResponse.<DepartmentDto>builder()
                        .code(-1)
                        .massage(String.format("not found of id :%d",id))
                        .build());

    }

    @Override
    public ApiResponse<DepartmentDto> update(DepartmentDto dto, Integer id) {
        return this.departmentRepository.findByIdAndDeletedAtIsNull(id)
                .map(department -> {
                    department.setUpdatedAt(LocalDateTime.now());
                    departmentMapper.toUpdate(department,dto);
                    departmentRepository.save(department);
                    return ApiResponse.<DepartmentDto>builder()
                            .success(true)
                            .massage("ok")
                            .data(this.departmentMapper.toDto(department))
                            .build();

                }).orElse(ApiResponse.<DepartmentDto>builder()
                        .code(-1)
                        .massage(String.format("not found of id :%d",id))
                        .build());
    }

    @Override
    public ApiResponse<DepartmentDto> delete(Integer id) {
        return this.departmentRepository.findByIdAndDeletedAtIsNull(id)
                .map(department -> {
                    department.setDeletedAt(LocalDateTime.now());
                    departmentRepository.delete(department);
                    return ApiResponse.<DepartmentDto>builder()
                            .success(true)
                            .massage("ok")
                            .data(this.departmentMapper.toDto(department))
                            .build();
                }).orElse(ApiResponse.<DepartmentDto>builder()
                        .code(-1)
                        .massage(String.format("not found of id :%d",id))
                        .build());
    }

    @Override
    public ApiResponse<List<DepartmentDto>> getAll() {
        List<Department> list=this.departmentRepository.findAllByDeletedAtIsNull();
        if (list.isEmpty()){
            return ApiResponse.<List<DepartmentDto>>builder()
                    .code(-1)
                    .massage("error")
                    .build();
        }
        return ApiResponse.<List<DepartmentDto>>builder()
                .success(true)
                .massage("ok")
                .data(list.stream().map(this.departmentMapper::toDto).toList())
                .build();
    }
}

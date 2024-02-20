package com.example.college.impl.mapper;

import com.example.college.dto.DepartmentDto;
import com.example.college.model.Department;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public  abstract class DepartmentMapper {

    @Autowired
    protected CourseMapper courseMapper;

    @Autowired
    protected  InstructorMapper instructorMapper;

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract Department toEntity(DepartmentDto dto);

    public abstract DepartmentDto toDto(Department department);


    @Mapping(target = "instructors",expression = "java(department.getInstructors().stream().map(this.instructorMapper::toDto).toList())")
    @Mapping(target = "courses",expression = "java(department.getCourses().stream().map(this.courseMapper::toDto).toList())")

    public abstract DepartmentDto toDtoWithInstructorAndCourse(Department department);

    public void add(){
        Department department=new Department();
        DepartmentDto dto=new DepartmentDto();
        dto.setCourses(department.getCourses().stream().map(this.courseMapper::toDto).toList());
    }




    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public  abstract void toUpdate(@MappingTarget Department department, DepartmentDto dto);
}

package com.example.college.impl.mapper;

import com.example.college.dto.StudentDto;
import com.example.college.model.Student;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")

public  abstract class StudentMapper {

    @Autowired
    protected CourseStudentMapper courseStudentMapper;

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract Student ToEntity(StudentDto dto);

    public abstract StudentDto ToDto(Student student);

    @Mapping(target = "courseStudents", expression = "java(student.getCourseStudents().stream().map(this.courseStudentMapper::toDto).toList())")
    public abstract StudentDto ToDtoWithCoursSetudent(Student student);




    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract void toUpdate(@MappingTarget Student student, StudentDto dto);
}

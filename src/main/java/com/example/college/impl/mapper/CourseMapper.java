package com.example.college.impl.mapper;

import com.example.college.dto.CourseDto;
import com.example.college.dto.StudentDto;
import com.example.college.model.Course;
import com.example.college.model.Student;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public abstract class CourseMapper {

    @Autowired
    protected CourseStudentMapper courseStudentMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)

    public abstract Course toEntity(CourseDto dto);

    public abstract CourseDto toDto(Course course);


    @Mapping(target = "courseStudents", expression = "java(course.getCourseStudents().stream().map(this.courseStudentMapper::toDto).toList())")
    public abstract CourseDto toDtoWithCoursStudent(Course course);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)

    public abstract void toUpdate(@MappingTarget Course course, CourseDto dto);
}

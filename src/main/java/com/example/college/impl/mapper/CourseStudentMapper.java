package com.example.college.impl.mapper;

import com.example.college.dto.CourseStudentDto;
import com.example.college.model.CourseStudent;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public abstract class CourseStudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract CourseStudent toEntity(CourseStudentDto dto);

    public abstract CourseStudentDto toDto(CourseStudent courseStudent);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract void toUpdate(@MappingTarget CourseStudent courseStudent, CourseStudentDto dto);
}

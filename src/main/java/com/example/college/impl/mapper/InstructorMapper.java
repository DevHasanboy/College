package com.example.college.impl.mapper;

import com.example.college.dto.InstructorDto;
import com.example.college.model.Instructor;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public abstract class InstructorMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract Instructor toEntity(InstructorDto dto);

    public abstract InstructorDto toDto(Instructor instructor);


@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Mapping(target = "createdAt",ignore = true)
@Mapping(target = "updatedAt",ignore = true)
@Mapping(target = "deletedAt",ignore = true)
    public abstract void toUpdate(@MappingTarget Instructor instructor, InstructorDto dto);
}

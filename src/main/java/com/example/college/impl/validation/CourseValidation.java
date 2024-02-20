package com.example.college.impl.validation;


import com.example.college.dto.CourseDto;
import com.example.college.dto.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseValidation {

    public List<ErrorDto> validate(CourseDto dto){

        List<ErrorDto> errors=new ArrayList<>();

        if (StringUtils.isBlank(dto.getName())){
            errors.add(new ErrorDto("name","name cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getDuration().toString())){
            errors.add(new ErrorDto("duration","duration cannot be null or empty"));
        }
        return errors;
    }
}

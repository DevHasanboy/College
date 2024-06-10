package com.example.college.impl.validation;

import com.example.college.dto.ErrorDto;
import com.example.college.dto.InstructorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InstructorValidation {

    public List<ErrorDto> validate(InstructorDto dto){
        List<ErrorDto> list=new ArrayList<>();
        if (StringUtils.isBlank(dto.getFirstName())){
            list.add(new ErrorDto("firstName","firstName cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getHeadedby())){
            list.add(new ErrorDto("headedby","'headedby cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getPhone())){
            list.add(new ErrorDto("phone","phone cannot be null oer empty"));
        }
        return list;
    }
}

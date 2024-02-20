package com.example.college.impl.validation;
import com.example.college.dto.DepartmentDto;
import com.example.college.dto.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentValidation {

    public List<ErrorDto> validate(DepartmentDto dto){
        List<ErrorDto> list=new ArrayList<>();
        if (StringUtils.isBlank(dto.getName())){
            list.add(new ErrorDto("name","name cannot be null or empty"));
        }
        if(StringUtils.isBlank(dto.getLocation())){

            list.add(new ErrorDto("location","location cannot be null or empty"));
        }
        return null;
    }
}

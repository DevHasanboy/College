package com.example.college.impl.validation;

import com.example.college.dto.CourseStudentDto;
import com.example.college.dto.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseStudentValidation {

    public List<ErrorDto> validate(CourseStudentDto dto){
        List<ErrorDto> list=new ArrayList<>();
        if (StringUtils.isBlank(dto.getCourseId().toString())){
            list.add(new ErrorDto("courseId","courseId cannot be null or empty"));
        } if (StringUtils.isBlank(dto.getStudentId().toString())){
            list.add(new ErrorDto("studentId","studentId cannot be null or empty"));
        }
        return list;
    }
}

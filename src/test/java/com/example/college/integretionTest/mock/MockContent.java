package com.example.college.integretionTest.mock;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.CourseDto;
import com.example.college.model.Course;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MockContent {

    public static CourseDto getRequestCourse(){

        return CourseDto.builder()
                .instructorId(1)
                .departmentId(1)
                .name("Humo")
                .build();
    }

    public static CourseDto getRequestCourseWithId(){

        return CourseDto.builder()
                .id(1)
                .instructorId(1)
                .departmentId(1)
                .name("Humo")
                .build();
    }

    public static Course getRequestCourseEntity(){

        return Course.builder()
                .id(1)
                .instructorId(1)
                .departmentId(1)
                .name("Humo")
                .build();
    }


   public static  <T> ApiResponse<T> getFullContent(
           String json,
           ObjectMapper objectMapper,
           Class<T> clazz) throws JsonProcessingException {

       var response= objectMapper.readValue(json,ApiResponse.class);
      objectMapper.readValue(objectMapper.writeValueAsString(response.getData()),clazz);
       return ApiResponse.<T>builder()
               .success(response.isSuccess())
               .code(response.getCode())
               .data( objectMapper.readValue(objectMapper.writeValueAsString(response.getData()),clazz))
               .massage(response.getMassage())


               .build();

   }

}

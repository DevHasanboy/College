package com.example.college.integretionTest;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.CourseDto;
import com.example.college.impl.mapper.CourseMapper;
import com.example.college.impl.validation.CourseValidation;
import com.example.college.integretionTest.mock.MockContent;
import com.example.college.model.Course;
import com.example.college.repository.CourseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static java.lang.reflect.Array.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Testcontainers
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCurs {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CourseValidation courseValidation;


    @Autowired
    private CourseMapper courseMapper;

    @MockBean
    private CourseRepository courseRepository;

    @Test
    @DisplayName(value = "test create positive")
    void shouldTestcreatCoursPositive() throws Exception {
        String json = this.objectMapper.writeValueAsString(MockContent.getRequestCourse());
        mockMvc.perform(post("/course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        //        .header("Authorization","this token")
                        .content(json)

                )

                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    String resultJson = result.getResponse().getContentAsString();
                  var fulContent=  MockContent.getFullContent(resultJson, objectMapper, Course.class);

                  assertThat(fulContent.isSuccess()).isFalse();
                  assertThat(fulContent.getMassage()).isEqualTo("validation error");
                  assertThat(fulContent.getCode()).isEqualTo(-3);
                  assertThat(fulContent.getData()).isNull();

                });

    }

    @AfterEach
    @BeforeEach
    public void deleteall() {
        this.courseRepository.deleteAll();
    }

    @Test
    @DisplayName(value = "test create exception")
    public void shouldTestcreatCoursException() throws Exception {

        when(this.courseRepository.save(any()))
                .thenThrow(RuntimeException.class);


        String json=this.objectMapper.writeValueAsString(MockContent.getRequestCourse());
        this.mockMvc.perform(
                post("/course")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)

        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                   var fulContent= MockContent.getFullContent(
                            result.getResponse().getContentAsString(),
                            objectMapper,
                            Course.class
                    );



                   assertThat(fulContent.isSuccess()).isFalse();



                });




    }

    @Test
    @DisplayName(value = "test create validation")
    public void shouldTestcreatCoursValid() throws Exception {


        String json=this.objectMapper.writeValueAsString(MockContent.getRequestCourseWithId());

      this.mockMvc.perform(
              post("/course")
                      .accept(MediaType.APPLICATION_JSON)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(json)
      )

              .andDo(print())
              .andExpect(status().isBadRequest())
              .andExpect(result -> {

              });


    }

    @Test
    public void shouldGetCoursePositive() throws Exception {

        this.courseRepository.save(MockContent.getRequestCourseEntity());

        this.mockMvc.perform(
                get("/course")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)

        )
                .andDo(print());

    }

    @Test
    public void shouldGetCourseNegative() throws Exception {

        this.mockMvc.perform(
                get("/course")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())

                .andExpect(result -> {
                   result.getResponse().getContentAsString().equals(405);
                   var fullContent= MockContent.getFullContent(
                           result.getResponse().getContentAsString(),
                           this.objectMapper,
                           CourseDto.class);

                });

    }

}

package com.example.college.controller;

import com.example.college.dto.ApiResponse;
import com.example.college.dto.InstructorDto;
import com.example.college.impl.InstructorServiceImpl;
import com.example.college.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("insturct")
@RequiredArgsConstructor
public class InstructorController implements InstructorService {

    private final InstructorServiceImpl instructorService;

    @PostMapping
    @Override
    public ApiResponse<InstructorDto> create(@RequestBody InstructorDto dto) {
        return this.instructorService.create(dto);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ApiResponse<InstructorDto> get(@PathVariable(value = "id") Integer id) {
        return this.instructorService.get(id);
    }

    @PutMapping(value = "/{id}")
    @Override
    public ApiResponse<InstructorDto> update(@RequestBody InstructorDto dto,@PathVariable(value = "id") Integer id) {
        return this.instructorService.update(dto, id);
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ApiResponse<InstructorDto> delete(@PathVariable(value = "id") Integer id) {
        return this.instructorService.delete(id);
    }


    @GetMapping(value = "/get_all")
    @Override
    public ApiResponse<List<InstructorDto>> getAll() {
        return this.instructorService.getAll();
    }
}

package com.example.college.repository;

import com.example.college.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {


    Optional<Student> findByIdAndDeletedAtIsNull(Integer id);

   @Query(
           nativeQuery = true,
           value = "select * from student"
   )
    List<Student> findAllByDeletedAtIsNull();
}

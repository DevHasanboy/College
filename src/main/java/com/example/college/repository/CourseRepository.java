package com.example.college.repository;

import com.example.college.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {


    Optional<Course> findByIdAndDeletedAtIsNull(Integer id);

   @Query(
           nativeQuery = true,
           value = "select * from course where id>=2 and length(name)<=5"
   )
    List<Course> findAllByDeletedAtIsNull();

}

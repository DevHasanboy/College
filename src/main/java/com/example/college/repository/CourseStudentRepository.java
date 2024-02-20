package com.example.college.repository;

import com.example.college.model.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent,Integer> {

    Optional<CourseStudent> findByIdAndDeletedAtIsNull(Integer id);

    @Query(
            nativeQuery = true,
            value ="select  * from cousestudent"
    )
    List<CourseStudent> findAllByDeletedAtIsNull();
}

package com.example.college.repository;

import com.example.college.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Integer> {

    Optional<Instructor> findByIdAndDeletedAtIsNull(Integer id);

    @Query(
            nativeQuery = true,
            value = "select * from instructor"

    )
    List<Instructor> findAllByDeletedAtIsNull();
}

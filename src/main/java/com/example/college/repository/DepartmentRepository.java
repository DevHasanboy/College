package com.example.college.repository;

import com.example.college.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {


    Optional<Department> findByIdAndDeletedAtIsNull(Integer id);

    @Query(
            nativeQuery = true,
            value = "select *" +
                    "from department where id>=2"


    )
    List<Department> findAllByDeletedAtIsNull();
}

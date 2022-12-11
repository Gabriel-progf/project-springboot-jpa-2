package com.gbProject.project2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gbProject.project2.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);
    
}

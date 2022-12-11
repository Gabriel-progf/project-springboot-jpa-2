package com.gbProject.project2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gbProject.project2.entities.Department;
import com.gbProject.project2.repositories.DepartmentRepository;
import com.gbProject.project2.services.exceptions.DataBaseException;
import com.gbProject.project2.services.exceptions.ResourceNotFoundException;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public List<Department> findAll() {
        return repository.findAll();
    }

    public Department findById(Long id) {
        Optional<Department> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Department save(Department department) {
        boolean existDepartment = repository.findByName(department.getName()).stream()
                .anyMatch(d -> !d.equals(department));

        if (existDepartment) {
            throw new DataBaseException("This department already exist.");
        }

        return repository.save(department);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public Boolean existById(Long id){
        return repository.existsById(id);
    }

}

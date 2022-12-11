package com.gbProject.project2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gbProject.project2.entities.Worker;
import com.gbProject.project2.repositories.WorkerRespository;
import com.gbProject.project2.services.exceptions.DataBaseException;
import com.gbProject.project2.services.exceptions.ResourceNotFoundException;


@Service
public class WorkerService {

    @Autowired
    private WorkerRespository repository;
    

    public List<Worker> findAll(){
        return repository.findAll();
    }

    public Worker findById(Long id){
        Optional<Worker> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Worker save(Worker worker){
        boolean existWorker = repository.findByName(worker.getName()).stream().anyMatch(w -> !w.equals(worker));

        if(existWorker){
            throw new DataBaseException("This worker already exist");
        }

        return repository.save(worker);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataBaseException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    public Boolean existById(Long id){
        return repository.existsById(id);
    }
}

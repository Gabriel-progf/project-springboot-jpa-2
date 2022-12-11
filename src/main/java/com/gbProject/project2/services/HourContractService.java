package com.gbProject.project2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.gbProject.project2.entities.HourContract;
import com.gbProject.project2.repositories.HourContractRepository;
import com.gbProject.project2.services.exceptions.DataBaseException;
import com.gbProject.project2.services.exceptions.ResourceNotFoundException;

@Service
public class HourContractService {

    @Autowired
    private HourContractRepository repository;

    public List<HourContract> findAll() {
        return repository.findAll();
    }

    public HourContract findById(Long id) {
        Optional<HourContract> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public HourContract save(HourContract hc) {
        boolean existHc = repository.findById(hc.getId()).stream().anyMatch(x -> !x.equals(hc));

        if (existHc) {
            throw new DataBaseException("This hour contract already ecxit");
        }

        return repository.save(hc);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataBaseException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public Boolean existById(Long id){
        return repository.existsById(id);
    }
}

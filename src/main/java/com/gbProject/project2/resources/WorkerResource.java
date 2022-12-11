package com.gbProject.project2.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gbProject.project2.entities.Worker;
import com.gbProject.project2.services.WorkerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/worker")
public class WorkerResource {

    @Autowired
    private WorkerService service;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {
        Worker obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Worker insert(@Valid @RequestBody Worker worker) {
        return service.save(worker);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Worker> update(@PathVariable Long id, @Valid @RequestBody Worker worker) {
        if (!service.existById(id)) {
            return ResponseEntity.notFound().build();
        }

        worker.setId(id);
        service.save(worker);

        return ResponseEntity.ok(worker);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(!service.existById(id)){
            ResponseEntity.notFound().build();
        }

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

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

import com.gbProject.project2.entities.HourContract;
import com.gbProject.project2.services.HourContractService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/hourcontract")
public class HourContractResource {

    @Autowired
    private HourContractService service;

    @GetMapping
    public ResponseEntity<List<HourContract>> findAll() {
        List<HourContract> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HourContract> findById(@PathVariable Long id) {
        HourContract obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HourContract insert(@Valid @RequestBody HourContract hc) {
        return service.save(hc);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HourContract> update(@PathVariable Long id, @Valid @RequestBody HourContract hc) {
        if (!service.existById(id)) {
            return ResponseEntity.notFound().build();
        }

        hc.setId(id);
        service.save(hc);

        return ResponseEntity.ok(hc);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.existById(id)) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}

package com.gbProject.project2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gbProject.project2.entities.Worker;

public interface WorkerRespository extends JpaRepository<Worker, Long> {

    Optional<Worker> findByName(String name);
}

package com.gbProject.project2.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.gbProject.project2.entities.HourContract;

public interface HourContractRepository extends JpaRepository<HourContract, Long> {
    
}

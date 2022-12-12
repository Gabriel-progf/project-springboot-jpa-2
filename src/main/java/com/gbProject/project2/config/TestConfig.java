package com.gbProject.project2.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gbProject.project2.entities.Department;
import com.gbProject.project2.entities.HourContract;
import com.gbProject.project2.entities.Worker;
import com.gbProject.project2.entities.enums.WorkerLevel;
import com.gbProject.project2.repositories.DepartmentRepository;
import com.gbProject.project2.repositories.HourContractRepository;
import com.gbProject.project2.repositories.WorkerRespository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private DepartmentRepository departmentRepository;

    private HourContractRepository hourContractRepository;

    private WorkerRespository workerRespository;

    @Override
    public void run(String... args) throws Exception {

       
        
        Department d1 = new Department(null, "Vendas");
        Department d2 = new Department(null, "Suporte");
        Department d3 = new Department(null, "Consultoria");
        Department d4 = new Department(null, "Atendimento");

        Worker w1 = new Worker(null, "João", WorkerLevel.SENIOR, 8000.0, d2);
        Worker w2 = new Worker(null, "Maria", WorkerLevel.MID_LEVEL, 4500.0, d1);
        Worker w3 = new Worker(null, "Carlos", WorkerLevel.JUNIOR, 3000.0, d4);
        Worker w4 = new Worker(null, "Paula", WorkerLevel.SENIOR, 9000.0, d3);

        HourContract c1 = new HourContract(null, Instant.parse("2019-06-20T19:53:07Z"), 20.0, 8);
        HourContract c2 = new HourContract(null, Instant.parse("2019-06-20T19:53:07Z"), 50.0, 6);
        HourContract c3 = new HourContract(null, Instant.parse("2019-06-20T19:53:07Z"), 80.0, 6);
        HourContract c4 = new HourContract(null, Instant.parse("2019-06-20T19:53:07Z"), 10.0, 8);

        departmentRepository.saveAll(Arrays.asList(d1,d2,d3,d4));
        workerRespository.saveAll(Arrays.asList(w1,w2,w3,w4));
        hourContractRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
        
        w1.getContracts().add(c4);
        w2.getContracts().add(c1);
        w3.getContracts().add(c3);
        w4.getContracts().add(c2);
        w4.getContracts().add(c1);
        w1.getContracts().add(c2);
        w2.getContracts().add(c3);
        
        workerRespository.saveAll(Arrays.asList(w1,w2,w3,w4));
        




        

    }

}

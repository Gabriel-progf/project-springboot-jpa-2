package com.gbProject.project2.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.gbProject.project2.entities.enums.WorkerLevel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "tb_worker")
public class Worker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;

    @EqualsAndHashCode.Include
    private @Getter @Setter String name;
    private @Getter @Setter Integer level;
    private @Getter @Setter Double baseSalary;

    @ManyToOne
    @JoinColumn(name = "id_department")
    private @Getter @Setter Department department;

    @OneToMany(mappedBy = "worker")
    private @Getter List<HourContract> contracts = new ArrayList<>();


    public Worker(Long id, String name, WorkerLevel level, Double baseSalary, Department department) {
        this.id = id;
        this.name = name;
        setWorkerLevel(level);
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public WorkerLevel getWotkerLevel() {
        return WorkerLevel.valueOf(level);
    }

    public void setWorkerLevel(WorkerLevel workerLevel) {
        level = workerLevel != null ? workerLevel.getCode() : null;
    }

   
}

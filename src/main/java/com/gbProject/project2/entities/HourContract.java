package com.gbProject.project2.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "tb_hour_contract")
public class HourContract implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private @Getter @Setter Instant date;
    private @Getter @Setter Double valuePerHour;
    private @Getter @Setter Integer hours;

    
    @ManyToMany(mappedBy = "contracts")
    private @Getter @Setter Set<Worker> workers = new HashSet<>();

    public HourContract(Long id, Instant date, Double valuePerHour, Integer hours) {
        this.id = id;
        this.date = date;
        this.valuePerHour = valuePerHour;
        this.hours = hours;
    }

    public Double getTotalValue() {
        return valuePerHour * hours;
    }
}

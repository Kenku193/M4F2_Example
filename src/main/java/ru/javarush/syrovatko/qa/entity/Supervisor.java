package ru.javarush.syrovatko.qa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "supervisors")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Supervisor {
    @Id
    public Long id;
    public String name;
    public String title;
    public int salary;
    public LocalDate hireDate;
    public int subordinates;

}

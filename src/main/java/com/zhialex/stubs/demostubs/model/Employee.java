package com.zhialex.stubs.demostubs.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Employee {
    @Id @GeneratedValue
    private Long id;
    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "salary is required")
    private String salary;
    @NotNull(message = "age is required")
    private String age;

    public Employee(String name, String salary, String age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
}

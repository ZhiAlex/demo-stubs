package com.zhialex.stubs.demostubs.repository;

import com.zhialex.stubs.demostubs.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

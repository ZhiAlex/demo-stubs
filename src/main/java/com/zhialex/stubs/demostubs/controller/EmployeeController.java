package com.zhialex.stubs.demostubs.controller;

import com.zhialex.stubs.demostubs.exceptions.EmployeeNotFoundException;
import com.zhialex.stubs.demostubs.model.Employee;
import com.zhialex.stubs.demostubs.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee emp = employeeRepository.save(employee);
        return ok(emp);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        Employee emp = employeeRepository.findById(id).map(e -> {
            e.setName(employee.getName());
            e.setAge(employee.getAge());
            e.setSalary(employee.getSalary());
            return employeeRepository.save(e);
        }).orElseThrow(() -> new EmployeeNotFoundException(id));
        return status(CREATED).body(emp);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        return ok(employeeRepository.findAll());
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return ok(emp);
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.deleteById(emp.getId());
        return noContent().build();
    }
}

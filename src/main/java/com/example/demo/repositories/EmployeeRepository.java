package com.example.demo.repositories;

import com.example.demo.models.Employee;
import com.example.demo.models.EmployeeStatus;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAll();
    Optional<Employee> findById(int employeeId); //optional por si no lo encuentra
    List<Employee> findByStatus(EmployeeStatus status);
    List<Employee> findByDepartment(String department);
}

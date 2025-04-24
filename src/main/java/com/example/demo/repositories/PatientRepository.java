package com.example.demo.repositories;

import com.example.demo.models.Employee;
import com.example.demo.models.EmployeeStatus;
import com.example.demo.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findAll();
    Optional<Patient> findById(int patientId);
    List<Patient> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);
    List<Patient> findByAdmittedBy_Department(String department); //se accede gracias al foreign key
    List<Patient> findByAdmittedBy_Status(EmployeeStatus status);
}

package com.example.demo.controller;

import com.example.demo.models.Employee;
import com.example.demo.models.EmployeeStatus;
import com.example.demo.models.Patient;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PatchExchange;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/hospital")
public class HospitalController{

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/doctors")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllDoctors() {
        return employeeRepository.findAll();
    }

    @GetMapping("/doctors/Id/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    Optional <Employee> getDoctorById(@PathVariable int employeeId){
        return employeeRepository.findById(employeeId);
    }

    @GetMapping("doctors/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    List<Employee> getDoctorsByStatus(@PathVariable String status){
        //se convierte a String el valor del enum
        EmployeeStatus employeeStatus = EmployeeStatus.valueOf(status.toUpperCase(Locale.ROOT));
        return employeeRepository.findByStatus(employeeStatus);
    }

    @GetMapping("/doctors/department/{department}")
    @ResponseStatus(HttpStatus.OK)
    List<Employee> getDoctorsByDepartment(@PathVariable String department){
        return employeeRepository.findByDepartment(department);
    }

    @GetMapping("/patients")
    @ResponseStatus(HttpStatus.OK)
    List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/patients/Id/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    Optional <Patient> getPatientById(@PathVariable int patientId){
        return patientRepository.findById(patientId);
    }

    @GetMapping("/patients/dateOfBirthBetween")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsByDateOfBirthRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return patientRepository.findByDateOfBirthBetween(startDate, endDate);
    }

    @GetMapping("/patients/department/{department}")
    @ResponseStatus(HttpStatus.OK)
    List<Patient> getPatientsByAdmittingDoctorDepartment(@PathVariable String department){
        return patientRepository.findByAdmittedBy_Department(department);
    }

    @GetMapping("/patients/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    List<Patient> getPatientsWithDoctorStatusOff(@PathVariable String status){
        EmployeeStatus employeeStatus = EmployeeStatus.valueOf(status.toUpperCase());

        return patientRepository.findByAdmittedBy_Status(employeeStatus);
    }


}

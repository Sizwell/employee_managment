package com.itech.EmployeeManagement.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public void addNewEmployee(Employee employee)
    {
        System.out.println(employee);
        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(employee.getEmail());

        if (employeeOptional.isPresent())
        {
            throw new IllegalStateException("Email already taken");
        }
        employeeRepository.saveAndFlush(employee);
    }
}

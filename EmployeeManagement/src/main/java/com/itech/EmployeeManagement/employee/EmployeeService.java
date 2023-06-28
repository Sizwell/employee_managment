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

    public void deleteEmployee(Long employeeId)
    {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists)
        {
            throw new IllegalStateException("Employee with ID " + employeeId + "does not Exist");
        }
        else employeeRepository.deleteById(employeeId);
    }

    public void searchEmployee(String name, String surname)
    {
        List<Employee> searchEmployee = employeeRepository.findByNameAndSurname
                (name, surname
        );

        if (searchEmployee.isEmpty())
        {
            System.out.println("No user found with Matching name and surname");
        }
        else employeeRepository.findByNameAndSurname(name, surname);
        System.out.println("Employee Surname ): " + surname);

        for (int i = 0; i < searchEmployee.size(); i++) {
            System.out.println("Employee: " + i);
        }

    }


    public List<Employee> searchEmployeeByNameAndSurname(String name, String surname)
    {
        System.out.println(name);
        return employeeRepository.findByNameAndSurname(name, surname);
    }
}

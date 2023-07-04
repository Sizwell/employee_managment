package com.itech.EmployeeManagement.employee;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
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

    @Transactional
    public void updateEmployee(Long id,
                               String name,
                               String surname,
                               String email,
                               String number,
                               String occupation,
                               String experience,
                               String summary,
                               String city,
                               String suburb,
                               String address,
                               String zipCode
    )
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalStateException
                ("Employee with ID " + id + "does not exist"));
        //!Objects.equals is to check if the name provided is not the same as Current one
        if (name != null && name.length() > 0 && !Objects.equals(employee.getName(), name))
        {
            employee.setName(name);
        }

        if (surname != null && surname.length() > 0 && !Objects.equals(employee.getSurname(), surname))
        {
            employee.setSurname(surname);
        }

        if (email != null && email.length() > 0 && !Objects.equals(employee.getEmail(), email))
        {
            Optional<Employee> employeeEmail = employeeRepository.findEmployeeByEmail(email);
            if (employeeEmail.isPresent())
            {
                throw new IllegalStateException("Email has already been taken");
            }
            employee.setEmail(email);
        }

        if (number != null && number.length() > 0 && !Objects.equals(employee.getNumber(), number))
        {
            Optional<Employee> employeePhoneNumber = employeeRepository.findByNumber(number);
            if (employeePhoneNumber.isPresent())
            {
                throw new IllegalStateException
                        ("Phone number record " + number + " exists in the Database. Please use another number.");
            }
            employee.setNumber(number);
        }

        if (occupation != null && occupation.length() > 0 && !Objects.equals(employee.getOccupation(), occupation))
        {
            employee.setOccupation(occupation);
        }

        if (experience != null && experience.length() > 0 && !Objects.equals(employee.getExperience(), experience))
        {
            employee.setExperience(experience);
        }

        if (summary != null && summary.length() > 0 && !Objects.equals(employee.getSummary(), summary))
        {
            employee.setSummary(summary);
        }

        if (city != null && city.length() > 0 && !Objects.equals(employee.getCity(), city))
        {
            employee.setCity(city);
        }

        if (suburb != null && suburb.length() > 0 && !Objects.equals(employee.getSuburb(), suburb))
        {
            employee.setSuburb(suburb);
        }

        if (address != null && address.length() > 0 && !Objects.equals(employee.getAddress(), address))
        {
            employee.setAddress(address);
        }

        if (zipCode != null && zipCode.length() > 0 && !Objects.equals(employee.getZipCode(), zipCode))
        {
            employee.setZipCode(zipCode);
        }

    }
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

//    public Employee update(Long id, String name, String surname)
//    {
//        Employee employee = employeeRepository.findById(id).orElseThrow(()
//                -> new IllegalArgumentException("Employee with ID " + id + " not found"));
//
//        employee.setName(name);
//        employee.setSurname(surname);
//
//
//        return employeeRepository.save(employee);
//
//    }
}

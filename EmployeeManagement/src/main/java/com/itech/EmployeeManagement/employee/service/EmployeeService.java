package com.itech.EmployeeManagement.employee.service;

import com.itech.EmployeeManagement.address.entity.Address;
import com.itech.EmployeeManagement.address.repsitory.AddressRepository;
import com.itech.EmployeeManagement.employee.entity.Employee;
import com.itech.EmployeeManagement.employee.repsitory.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public Employee addNewEmployee(Employee employee, Address address)
    {
        System.out.println(employee);
        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(employee.getEmail());

        if (employeeOptional.isPresent())
        {
            throw new IllegalStateException("Email already taken");
        }
        employeeRepository.saveAndFlush(employee);
        addressRepository.saveAndFlush(address);
        return employee;
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

    public List<Address> getAddressByEmployeeNameAndSurname(String name, String surname)
    {
        return employeeRepository.findAddressByNameAndSurname(name, surname);
    }

    public List<Employee> searchEmployeeByNameAndSurname(String name, String surname)
    {
        System.out.println(name);
        return employeeRepository.findByNameAndSurname(name, surname);
    }

    @Transactional
    public void updateEmployee(
            Long id,
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
        Address employeeAddress = employeeRepository.findById(id).orElseThrow(() -> new IllegalStateException
                        ("Employee Address for Employee with ID " + id + " not found")).getAddresses();
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

//        List<Address> addresses = getAddressByEmployeeNameAndSurname(name, surname);


        if (city != null && city.length() > 0 && !Objects.equals(employeeAddress.getCity(), city))
        {
            employeeAddress.setCity(city);
        }

        if (suburb != null && suburb.length() > 0 && !Objects.equals(employeeAddress.getSuburb(), suburb))
        {
            employeeAddress.setSuburb(suburb);
        }

        if (address != null && address.length() > 0 && !Objects.equals(employeeAddress.getEmployeeAddress(), address))
        {
            employeeAddress.setEmployeeAddress(address);
        }

        if (zipCode != null && zipCode.length() > 0 && !Objects.equals(employeeAddress.getZipCode(), zipCode))
        {
            employeeAddress.setZipCode(zipCode);
        }

    }
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

    public Employee getEmployeeWithAddress(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
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

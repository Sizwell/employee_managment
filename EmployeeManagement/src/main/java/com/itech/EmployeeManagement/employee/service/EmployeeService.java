package com.itech.EmployeeManagement.employee.service;

import com.itech.EmployeeManagement.address.entity.Address;
import com.itech.EmployeeManagement.address.repsitory.AddressRepository;
import com.itech.EmployeeManagement.employee.entity.Employee;
import com.itech.EmployeeManagement.employee.repsitory.EmployeeRepository;
import com.itech.EmployeeManagement.project.entity.Project;
import com.itech.EmployeeManagement.project.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EmployeeService {

    private static final Logger logger = Logger.getLogger(EmployeeService.class.getName());

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    private final ProjectRepository projectRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, AddressRepository addressRepository, ProjectRepository projectRepository, JdbcTemplate jdbcTemplate) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.projectRepository = projectRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    String employeeSaveText = "Employee saved to Database";
    @Transactional
    public Employee addNewEmployee(Employee employee, Address address)
    {
        addressRepository.saveAndFlush(address);
        logger.info("Employee Address saved to Database");

        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(employee.getEmail());

        if (employeeOptional.isPresent())
        {
            throw new IllegalStateException("Email already taken");
        }

        employeeRepository.saveAndFlush(employee);
        logger.info(employeeSaveText);

        return employeeRepository.saveAndFlush(employee);
    }

    public Employee addAndAssignEmployeeToProject(Employee employee, Address address, String projectName)
    {
        addressRepository.saveAndFlush(address);
        logger.info("Employee Address saved to Database");

        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(employee.getEmail());

        if (employeeOptional.isPresent())
        {
            throw new IllegalStateException("Email already taken");
        }

        employeeRepository.saveAndFlush(employee);
        logger.info(employeeSaveText);

        List<Long> employeeId = employeeRepository.findEmployeeId(employee.getName(), employee.getSurname());
        Long projectId = getProjectId(projectName);

        for (int i = 0; i < employeeId.size(); i++) {
            assignEmployeeToProject(employeeId.get(i), projectId);
        }
        logger.info("Employee saved to Database");

        return employeeRepository.saveAndFlush(employee);
    }

    //To work on a JPQL solution to add to the joining table (work_items)
    public void assignEmployeeToProject(Long employeeId, Long projectId)
    {
        String sql = "INSERT INTO work_items (employee_id, project_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, employeeId, projectId);
    }

    public Long getProjectId(String projectName)
    {
        if (projectName.isEmpty())
        {
            logger.info("No project selected");
        }
        return projectRepository.findProjectId(projectName);
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
                ("Employee with ID " + id + " does not exist"));
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

    public List<String> getAllEmployeeNames()
    {
        List<String> employeeList = employeeRepository.getAllEmployeeNames();

        for (int i = 0; i < employeeList.size(); i++) {
            logger.info(employeeList.get(i));
        }
        return employeeRepository.getAllEmployeeNames();

    }


    public List<String> getEmployeeProjects(Long employeeId)
    {
        return employeeRepository.findEmployeeProj(employeeId);
    }
}

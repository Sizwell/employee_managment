package com.itech.EmployeeManagement.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByEmail(String email);

    //@Query("SELECT emp FROM Employee emp WHERE emp.name = :name AND emp.surname = :surname")
    List<Employee> findByNameAndSurname(String name, String surname);
    Optional<Employee> findByNumber(String number);
}

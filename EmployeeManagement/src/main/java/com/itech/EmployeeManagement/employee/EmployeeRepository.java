package com.itech.EmployeeManagement.employee;

import com.itech.EmployeeManagement.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByEmail(String email);

    //@Query("SELECT emp FROM Employee emp WHERE emp.name = :name AND emp.surname = :surname")
//    @Query("SELECT emp, addr.employeeAddress FROM Employee emp " +
//            "INNER JOIN FETCH Address addr ON emp.employeeId = addr.id " +
//            "WHERE emp.name = :name AND emp.surname = :surname")
    @Query("SELECT e FROM Employee e INNER JOIN e.addresses addr WHERE e.name = :name AND e.surname = :surname")
    List<Employee> findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

    Optional<Employee> findByNumber(String number);

    /*
    The below commented-out query is what we are looking to get sorted
     */
    //@Query("SELECT emp FROM Employee emp JOIN FETCH emp.addresses adr WHERE emp.name = :name AND emp.surname = :surname")
    @Query("SELECT e.addresses FROM Employee e WHERE e.name = :name AND e.surname = :surname")
    Address findAddressByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
}

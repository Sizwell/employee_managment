package com.itech.EmployeeManagement.employee.repsitory;

import com.itech.EmployeeManagement.address.entity.Address;
import com.itech.EmployeeManagement.employee.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    List<Address> findAddressByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

    //Return All Employee Names
    @Query("SELECT e.name FROM Employee e")
    List<String> getAllEmployeeNames();

    @Query("SELECT e.employeeId FROM Employee e WHERE e.name = :name AND e.surname =:surname")
    List<Long> findEmployeeId(@Param("name") String name, String surname);

//    @Modifying
//    @Transactional
//    @Query("INSERT INTO work_items(employee_id, project_id) VALUES (:employeeId, projectId)")
//    public void insertWorkItem(Long employeeId, Long projectId);

    Set<Employee> findByEmployeeId(Long id);


}

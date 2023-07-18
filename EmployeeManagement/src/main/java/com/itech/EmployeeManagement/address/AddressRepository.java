package com.itech.EmployeeManagement.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long>
{
   // List<Address> findAddressByEmployeeAddress(String employeeName, String employeeSurname);

}

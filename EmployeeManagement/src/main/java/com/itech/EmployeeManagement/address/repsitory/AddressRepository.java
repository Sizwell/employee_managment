package com.itech.EmployeeManagement.address.repsitory;

import com.itech.EmployeeManagement.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>
{

}

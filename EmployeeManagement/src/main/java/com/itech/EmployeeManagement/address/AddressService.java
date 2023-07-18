package com.itech.EmployeeManagement.address;

import com.itech.EmployeeManagement.employee.Employee;
import com.itech.EmployeeManagement.employee.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository)
    {
        this.addressRepository = addressRepository;
    }

    public void addEmployeeAddress(Address address)
    {
        System.out.println(address);
        addressRepository.save(address);
        System.out.println("Employee Address Captured");
    }

//    public List<Address> searchAddressByEmployeeNameAndSurname(String name, String surname)
//    {
//        return addressRepository.findAddressByEmployeeAddress(name, surname);
//    }

}

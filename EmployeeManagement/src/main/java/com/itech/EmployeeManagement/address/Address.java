package com.itech.EmployeeManagement.address;

import com.itech.EmployeeManagement.employee.Employee;
import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


    private Long id;
    private String city;
    private String suburb;
    private String employeeAddress;
    private String zipCode;

    public Address()
    {

    }

    public Address(Long id, String city, String suburb, String employeeAddress, String zipCode)
    {
        this.id = id;
        this.city = city;
        this.suburb = suburb;
        this.employeeAddress = employeeAddress;
        this.zipCode = zipCode;
    }

    public Address(String city, String suburb, String employeeAddress, String zipCode)
    {
        this.city = city;
        this.suburb = suburb;
        this.employeeAddress = employeeAddress;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", suburb='" + suburb + '\'' +
                ", address='" + employeeAddress + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}

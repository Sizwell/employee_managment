package com.itech.EmployeeManagement.employee;

import com.itech.EmployeeManagement.address.Address;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "employee_id")
    private Long employeeId;
    private String name;
    private String surname;
    private String email;
    private String number;
    private String IdNumber;
    private LocalDate dob;
    private String gender;
    private String ethnicity;
    private String occupation;

    private String experience;
    private String summary;

    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }

    public Employee(Address addresses) {
        this.addresses = addresses;
    }

    //    private String city;
//    private String suburb;
//    private String address;
//    private String zipCode;

    /*
    Cascading options, such as CascadeType.ALL in the Employee entity,
    allow you to propagate save, update, and delete operations to the
    associated Address entity.
    FetchType.LAZY is used to fetch the Address entity only when needed,
    reducing unnecessary database queries.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Address addresses;


    public Employee() {
    }

    public Employee(
            Long employeeId,
            String name,
            String surname,
            String email,
            String number,
            String idNumber,
            LocalDate dob,
            String gender,
            String ethnicity,
            String occupation,
            String experience,
            String summary
//            ,
//                    String city,
//                    String suburb,
//                    String address,
//                    String zipCode
    )
    {
        this.employeeId = employeeId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.number = number;
        this.IdNumber = idNumber;
        this.dob = dob;
        this.gender = gender;
        this.ethnicity = ethnicity;
        this.occupation = occupation;
        this.experience = experience;
        this.summary = summary;
//        this.city = city;
//        this.suburb = suburb;
//        this.address = address;
//        this.zipCode = zipCode;
    }
//Constructor with no ID field as the Database will generate and populate this field
    public Employee(String name,
                    String surname,
                    String email,
                    String number,
                    String idNumber,
                    LocalDate dob,
                    String gender,
                    String ethnicity,
                    String occupation,
                    String experience,
                    String summary
//            ,
//                    String city,
//                    String suburb,
//                    String address,
//                    String zipCode
    )
    {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.number = number;
        this.IdNumber = idNumber;
        this.dob = dob;
        this.gender = gender;
        this.ethnicity = ethnicity;
        this.occupation = occupation;
        this.experience = experience;
        this.summary = summary;
//        this.city = city;
//        this.suburb = suburb;
//        this.address = address;
//        this.zipCode = zipCode;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
    public String getExperience() {
        return experience;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getSuburb() {
//        return suburb;
//    }
//
//    public void setSuburb(String suburb) {
//        this.suburb = suburb;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getZipCode() {
//        return zipCode;
//    }
//
//    public void setZipCode(String zipCode) {
//        this.zipCode = zipCode;
//    }

    @Override
    public String toString() {
        return "Employees{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", IdNumber=" + IdNumber +
                ", dob=" + dob +
                ", gender=" + gender +
                ", ethnicity='" + ethnicity + '\'' +
                ", occupation='" + occupation + '\'' +
                ", experience='" + experience + '\'' +
                ", summary='" + summary +
//                '\'' +
//                ", city='" + city + '\'' +
//                ", suburb='" + suburb + '\'' +
//                ", address='" + address + '\'' +
//                ", zipCode=" + zipCode +
                '}';
    }
}

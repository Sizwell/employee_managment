package com.itech.EmployeeManagement.employee;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table
public class Employee {
    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )

    private Long Id;
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
    private String city;
    private String suburb;
    private String address;
    private String zipCode;

    public Employee() {
    }

    public Employee(Long id,
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
                    String summary,
                    String city,
                    String suburb,
                    String address,
                    String zipCode)
    {
        Id = id;
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
        this.city = city;
        this.suburb = suburb;
        this.address = address;
        this.zipCode = zipCode;
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
                    String summary,
                    String city,
                    String suburb,
                    String address,
                    String zipCode)
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
        this.city = city;
        this.suburb = suburb;
        this.address = address;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "Id=" + Id +
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
                ", summary='" + summary + '\'' +
                ", city='" + city + '\'' +
                ", suburb='" + suburb + '\'' +
                ", address='" + address + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}

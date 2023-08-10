package com.itech.EmployeeManagement.project.entity;

import com.itech.EmployeeManagement.employee.entity.Employee;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long projectId;
    private String projectName;
    private LocalDate startDate;
    private int duration;
    private String description;

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();

    public Project()
    {

    }

    public Project(Long projectId, String projectName, LocalDate startDate, int duration,
                   String description)
    {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.duration = duration;
        this.description = description;
    }

    public Project(String projectName, LocalDate startDate, int duration, String description) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.duration = duration;
        this.description = description;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", startDate=" + startDate +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                '}';
    }
}

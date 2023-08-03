package com.itech.EmployeeManagement.project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long projectId;
    private String projectName;
    private LocalDate startDate;
    private int duration;
    private String team;
    private String description;

    public Project()
    {

    }

    public Project(Long projectId, String projectName, LocalDate startDate, int duration, String team,
                   String description)
    {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.duration = duration;
        this.team = team;
        this.description = description;
    }

    public Project(String projectName, LocalDate startDate, int duration, String team, String description) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.duration = duration;
        this.team = team;
        this.description = description;
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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
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
                ", team='" + team + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

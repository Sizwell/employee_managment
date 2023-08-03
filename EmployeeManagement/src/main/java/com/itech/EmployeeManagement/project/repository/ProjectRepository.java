package com.itech.EmployeeManagement.project.repository;

import com.itech.EmployeeManagement.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>
{
    Optional<Project> findProjectByProjectName(@Param("projectName") String projectName);
}

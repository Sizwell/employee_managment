package com.itech.EmployeeManagement.project.repository;

import com.itech.EmployeeManagement.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>
{
    List<Project> findProjectByProjectName(@Param("projectName") String projectName);

    //Return All Project Names
    @Query("SELECT p.projectName FROM Project p")
    List<String> getAllProjectNames();

    @Query("SELECT p.projectId FROM Project p WHERE p.projectName = :projectName")
    Long findProjectId(@Param("projectName") String projectName);

    @Query("SELECT DISTINCT p.projectName FROM Project p WHERE p NOT IN (SELECT DISTINCT pr FROM Project pr INNER JOIN pr.employees e WHERE e.employeeId = :employeeId)")
    List<String> findProjectNotRelatedToEmployee(@Param("employeeId") Long employeeId);

    @Query("SELECT p FROM Project p WHERE p.projectName = :projectName")
    List<Project> findProjectName (@Param("projectName") String projectName);
}

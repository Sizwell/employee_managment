package com.itech.EmployeeManagement.project.service;

import com.itech.EmployeeManagement.employee.entity.Employee;
import com.itech.EmployeeManagement.project.entity.Project;
import com.itech.EmployeeManagement.project.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProjectService {

    private static final Logger logger = Logger.getLogger(ProjectService.class.getName());
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects()
    {
        return projectRepository.findAll();
    }

    public void addProject(Project project)
    {
        List<Project> projectOptional = projectRepository.findProjectByProjectName(project.getProjectName());
        if (!projectOptional.isEmpty())
        {
            logger.info("Project with name " + project.getProjectName() + " already exists");
            throw new IllegalStateException("Project with name " + project.getProjectName() + " already exists");
        }
        logger.fine("Saving project...");
        projectRepository.saveAndFlush(project);
    }

    public List<String> getProjectNames()
    {
        List<String> projectList = projectRepository.getAllProjectNames();

        for (int i = 0; i < projectList.size(); i++) {
            logger.info(projectList.get(i));
        }
        return projectRepository.getAllProjectNames();
    }

    public List<String> getProjectsNotRelatedToEmployee(Long employeeId)
    {
        return projectRepository.findProjectNotRelatedToEmployee(employeeId);
    }

    public List<Project> getProjects()
    {
        return projectRepository.findAll();
    }

    public List<Project> searchProject(String projectName) {
        return projectRepository.findProjectName(projectName);
    }

    public String countProjects(String projectName)
    {
        return projectRepository.countEmployeesByProject(projectName);
    }

    public List<Employee> getProjectTeam(String projectName)
    {
        return projectRepository.findEmployeesByProject(projectName);
    }

    public List<Employee> techTeam(String projectName)
    {
        return projectRepository.findEmployeesNotInProject(projectName);
    }

    @Transactional
    public void updateProject(Long id, String projectName, Integer duration, String description)
    {
        Project project = projectRepository.findById(id).orElseThrow(()-> new IllegalStateException
                ("Project with ID " + id + " does not exist."));

        if (projectName != null && projectName.length() > 0 && !Objects.equals(project.getProjectName(), projectName))
        {
            project.setProjectName(projectName);
        }

        if (duration != 0 && duration != project.getDuration())
        {
            project.setDuration(duration);
        }

        if (description != null && description.length() > 0 && !Objects.equals(project.getDescription(), description))
        {
            project.setDescription(description);
        }
    }
}

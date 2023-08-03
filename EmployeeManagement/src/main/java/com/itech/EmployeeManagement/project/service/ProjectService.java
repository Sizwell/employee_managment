package com.itech.EmployeeManagement.project.service;

import com.itech.EmployeeManagement.project.entity.Project;
import com.itech.EmployeeManagement.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<Project> projectOptional = projectRepository.findProjectByProjectName(project.getProjectName());
        if (projectOptional.isPresent())
        {
            logger.info("Project with name " + project.getProjectName() + " already exists");
            throw new IllegalStateException("Project with name " + project.getProjectName() + " already exists");
        }
        logger.fine("Saving project...");
        projectRepository.saveAndFlush(project);
    }
}

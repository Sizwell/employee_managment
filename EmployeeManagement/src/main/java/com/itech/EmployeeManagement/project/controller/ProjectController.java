package com.itech.EmployeeManagement.project.controller;

import com.itech.EmployeeManagement.project.entity.Project;
import com.itech.EmployeeManagement.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
public class ProjectController {
    private static final Logger logger = Logger.getLogger(ProjectController.class.getName());

    private final ProjectService projectService;
    ModelAndView modelAndView;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/add-project")
    public ModelAndView addProject(){
        modelAndView = new ModelAndView("add-project");
        modelAndView.addObject("project", new Project());
        return modelAndView;
    }

    @PostMapping("/add-project")
    public String addProject(@ModelAttribute Project project)
    {
        projectService.addProject(project);
        return "add-project";
    }

    @GetMapping("/project-overview")
    public String projectOverview(){
        return "project-overview";
    }

    @GetMapping("/all-projects")
    public String allProjects(){
        return "all-projects";
    }
}

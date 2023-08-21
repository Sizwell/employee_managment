package com.itech.EmployeeManagement.project.controller;

import com.itech.EmployeeManagement.employee.entity.Employee;
import com.itech.EmployeeManagement.employee.service.EmployeeService;
import com.itech.EmployeeManagement.project.entity.Project;
import com.itech.EmployeeManagement.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class ProjectController {
    private static final Logger logger = Logger.getLogger(ProjectController.class.getName());

    private final ProjectService projectService;
    private final EmployeeService employeeService;
    ModelAndView modelAndView;

    @Autowired
    public ProjectController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/add-project")
    public ModelAndView addProject(Model model){
        modelAndView = new ModelAndView("add-project");
        modelAndView.addObject("project", new Project());
        List<String> employeeList = employeeService.getAllEmployeeNames();
        model.addAttribute("team", employeeList);
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
    public String allProjects(Model model){
        List<Project> projectList = projectService.getProjects();
        model.addAttribute("projects", projectList);
        return "all-projects";
    }

    @GetMapping("/find-project")
    public String findProject(@RequestParam("projectName") String projectName, Model model)
    {
        logger.info("Project name " + projectName);
        List<Project> projectList = projectService.searchProject(projectName);

        for (int i = 0; i < projectList.size(); i++) {
            logger.info("Project Details: " + projectList.get(i).getProjectName() + projectList.get(i).getDescription());
        }

        for (Project project: projectList) {
            model.addAttribute("projectName", project.getProjectName());
            model.addAttribute("startDate", project.getStartDate());
            model.addAttribute("duration", project.getDuration());
            model.addAttribute("description", project.getDescription());
            model.addAttribute("numberOfMembers", 2);
        }

        return "project-overview";
    }
}

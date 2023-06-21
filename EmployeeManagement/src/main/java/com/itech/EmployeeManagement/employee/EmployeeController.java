package com.itech.EmployeeManagement.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
//@RequestMapping(path = "/i-tech/abc-consulting")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/home")
    String dashboard(){
        return "index";
    }

    @GetMapping("/add-employee")
    public ModelAndView iEmployees()
    {
        ModelAndView modelAndView = new ModelAndView("add-employee");
        Employee employee = new Employee();
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping("/save-employee")
    String addEmployees(@ModelAttribute Employee employee)
    {
        employeeService.addNewEmployee(employee);
        return "redirect:/add-employee";
    }

    @GetMapping("/manage-techies")
    String manageTechies(){
        return "manage-techies";
    }

    @GetMapping("/all-techies")
    String allTechies(){
        return "all-techies";
    }
//To move all Project endpoints to Projects Controller
    @GetMapping("/add-project")
    String addProject(){
        return "add-project";
    }

    @GetMapping("/project-overview")
    String projectOverview(){
        return "project-overview";
    }

    @GetMapping("/all-projects")
    String allProjects(){
        return "all-projects";
    }

}

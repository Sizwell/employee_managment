package com.itech.EmployeeManagement.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
//@RequestMapping(path = "/i-tech/abc-consulting")
public class EmployeeController {

    String addEmployeeRedirect = "redirect:/add-employee";
    String manageTechiesRedirect = "redirect:/manage-techies";

    private final EmployeeService employeeService;
    Employee employee = new Employee();
    ModelAndView modelAndView;

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
        modelAndView = new ModelAndView("add-employee");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping("/save-employee")
    public String addEmployees(@ModelAttribute Employee employee)
    {
        employeeService.addNewEmployee(employee);
        return addEmployeeRedirect;
    }

    @PostMapping( "/delete-employee/{employeeId}")
    public String deleteEmployee(@RequestParam("id") Long employeeId)
    {
        employeeService.deleteEmployee(employeeId);
        return addEmployeeRedirect;
    }

    @GetMapping("/manage-techies")
    String manageTechies(){
        return "manage-techies";
    }

    @GetMapping("/find-employee")
    public String findEmployee(@RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               Model model)
    {
        List<Employee> employees = employeeService.searchEmployeeByNameAndSurname(name, surname);

        for (Employee employee_: employees)
        {
            model.addAttribute("name", employee_.getName());
            model.addAttribute("surname", employee_.getSurname());
            model.addAttribute("occupation", employee_.getOccupation());
            model.addAttribute("years_experience", employee_.getExperience());
            model.addAttribute("ethnicity", employee_.getEthnicity());
            model.addAttribute("number", employee_.getNumber());
            model.addAttribute("email", employee_.getEmail());
            model.addAttribute("summary", employee_.getSummary());

            model.addAttribute("city", employee_.getCity());
            model.addAttribute("suburb", employee_.getSuburb());
            model.addAttribute("address", employee_.getAddress());
            model.addAttribute("zip_code", employee_.getZipCode());
        }

        return "manage-techies";
    }

    @GetMapping("/employee-profile")
    public String profile()
    {
        return "manage-techies";
    }

    @GetMapping("/all-techies")
    public String allTechies(){
        return "all-techies";
    }
//To move all Project endpoints to Projects Controller
    @GetMapping("/add-project")
    public String addProject(){
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

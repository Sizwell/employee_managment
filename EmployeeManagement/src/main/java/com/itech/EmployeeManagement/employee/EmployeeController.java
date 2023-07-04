package com.itech.EmployeeManagement.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
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
            //employee = employeeService.getEmployeeById(employee_.getId());
            model.addAttribute("Id", employee_.getId());
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

    @PostMapping("/find-employee/{id}/update-techie")
    public String updateEmployee(
            @PathVariable ("id") Long employeeId,
            @RequestParam(required = false) String name,
            // Variable names here should match the names of fields in the corresponding html file
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String number,
            @RequestParam(required = false) String occupation,
            @RequestParam(required = false) String experience,
            @RequestParam(required = false) String summary,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String suburb,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String zipCode
            )
    {
        employeeService.updateEmployee(
                employeeId, name, surname, email, number, occupation,
                experience, summary, city, suburb, address, zipCode
        );
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

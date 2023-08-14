package com.itech.EmployeeManagement.employee.controller;

import com.itech.EmployeeManagement.address.entity.Address;
import com.itech.EmployeeManagement.employee.service.EmployeeService;
import com.itech.EmployeeManagement.employee.entity.Employee;
import com.itech.EmployeeManagement.project.entity.Project;
import com.itech.EmployeeManagement.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

@Controller
//@RequestMapping(path = "/i-tech/abc-consulting")
public class EmployeeController {
    private static final Logger logger = Logger.getLogger(EmployeeController.class.getName());

    String addEmployeeRedirect = "redirect:/add-employee";
    String manageTechiesRedirect = "redirect:/manage-techies";
    String manageITechies = "manage-techies";

    Employee employee = new Employee();
    private final EmployeeService employeeService;
    private final ProjectService projectService;

    ModelAndView modelAndView;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/home")
    String dashboard(){
        return "index";
    }

    List<String> projectList;
    @GetMapping("/add-employee")
    public ModelAndView iEmployees(Model model)
    {
        modelAndView = new ModelAndView("add-employee");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("address", new Address());
        projectList = projectService.getProjectNames();
        model.addAttribute("projects", projectList);
        return modelAndView;
    }

    @PostMapping("/add-employee")
    public String addEmployees(
            @RequestParam(required = false) String project,
            @ModelAttribute Employee employee,
            @ModelAttribute Address address)
    {

        logger.info("Selected project: " + project);
        if (project == null)
        {
            logger.info("No project selected");
            employeeService.addNewEmployee(employee, address);
        } else {
            logger.info("Project selected");
            employeeService.addAndAssignEmployeeToProject(employee, address, project);
        }
        return addEmployeeRedirect;
    }

    @GetMapping( "/manage-techies/delete-employee/{id}")
    public String deleteEmployee(@PathVariable("id") Long employeeId)
    {
        employeeService.deleteEmployee(employeeId);
        return manageTechiesRedirect;
    }

    @GetMapping("/manage-techies")
    String manageTechies(){
        return manageITechies;
    }

    @GetMapping("/find-employee")
    public String findEmployee(@RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               Model model)
    {
        List<Employee> employees = employeeService.searchEmployeeByNameAndSurname(name, surname);
        List<Address> addresses = employeeService.getAddressByEmployeeNameAndSurname(name, surname);
        logger.info("Employee Address " + addresses);
        logger.info("Employee Address Breakdown " + addresses.get(0).getCity());

        String streetAddress = addresses.get(0).getEmployeeAddress();
        String suburb = addresses.get(0).getSuburb();
        String city = addresses.get(0).getCity();
        String zipCode = addresses.get(0).getZipCode();

        String fullAddress = streetAddress + " \n" + suburb + " \n" + city;
        logger.info(fullAddress);

        System.out.println(employees + " " +  employee.getAddresses());

        Address address = new Address();
        address.setEmployeeAddress(streetAddress);
        address.setSuburb(suburb);
        address.setCity(city);
        address.setZipCode(zipCode);

        for (Employee employee_: employees)
        {
            //employee = employeeService.getEmployeeById(employee_.getId());
            model.addAttribute("Id", employee_.getEmployeeId());
            model.addAttribute("name", employee_.getName());
            model.addAttribute("surname", employee_.getSurname());
            model.addAttribute("occupation", employee_.getOccupation());
            model.addAttribute("years_experience", employee_.getExperience());
            model.addAttribute("ethnicity", employee_.getEthnicity());
            model.addAttribute("number", employee_.getNumber());
            model.addAttribute("email", employee_.getEmail());
            model.addAttribute("summary", employee_.getSummary());

            model.addAttribute("city", city);
            model.addAttribute("suburb", suburb);
            model.addAttribute("address", streetAddress);
            model.addAttribute("fullAddress", fullAddress);
            model.addAttribute("zip_code", zipCode);

            List<String> employeeProjects = employeeService.getEmployeeProjects(employee_.getEmployeeId());

            for (int i = 0; i < employeeProjects.size(); i++) {
                logger.info("Employee Projects >: " + employeeProjects.get(i));
            }
            model.addAttribute("myProjects_", employeeProjects);

            List<String> projects = projectService.getProjectsNotRelatedToEmployee(employee_.getEmployeeId());
//            for (int i = 0; i < projects.size(); i++) {
//                logger.info("Projects not assigned to Employee: " + projects.get(i).getProjectName());
//                model.addAttribute("otherProjects", projects.get(i).getProjectName());
//            }
            model.addAttribute("otherProjects", projects);

        }

                // return manageTechiesRedirect does not display any db records
        return manageITechies;
    }

    @GetMapping("/employee-profile")
    public String profile()
    {
        return manageITechies;
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
        return manageTechiesRedirect;
    }

    @PostMapping("/find-employee/{id}/update-employee-project")
    public String updateEmployeeProject(@PathVariable ("id") Long employeeId,
            @RequestParam(required = false) String projects)
    {
        if (projects.isEmpty())
        {
            logger.info("No project selected");
        } else {
            Long projectId = employeeService.getProjectId(projects);
            employeeService.assignEmployeeToProject(employeeId, projectId);
        }

        return manageTechiesRedirect;
    }

    @GetMapping("/all-techies")
    public String allTechies(Model model){
        List<Employee> employeeList = employeeService.getEmployees();
        model.addAttribute("employees", employeeList);
        return "all-techies";
    }

}

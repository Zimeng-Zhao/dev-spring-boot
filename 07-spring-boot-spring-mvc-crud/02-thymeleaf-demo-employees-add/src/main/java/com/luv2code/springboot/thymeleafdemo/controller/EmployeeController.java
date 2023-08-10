package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	//define a field of employee service
	private EmployeeService employeeService;
	//define a constructor for constructor injection
	public EmployeeController(EmployeeService theEmployeeService){
		employeeService = theEmployeeService;
	}


	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		//get the employee from the database
		List<Employee> theEmployees = employeeService.findAll();

		// add to the spring model,so that can be used by thymeleaf templates
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}
	//to show the form
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		//create model attribute to bind form data
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee",theEmployee);
		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel){
		//get the employee from the service
		Employee theEmployee = employeeService.findById(theId);
		//set employee in the model to prepopulate the form
		theModel.addAttribute("employee",theEmployee);
		//send over to our form
		return "employees/employee-form";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId){
		//delete the employee
		employeeService.deleteById(theId);
		//redirect to /employee/list
		return "redirect:/employees/list";
	}
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

		//save the employee
		employeeService.save(theEmployee);
		//use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
}










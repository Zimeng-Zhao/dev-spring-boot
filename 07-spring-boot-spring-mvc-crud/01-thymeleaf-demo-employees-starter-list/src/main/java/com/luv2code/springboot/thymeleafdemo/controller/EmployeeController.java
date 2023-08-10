package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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

		return "list-employees";
	}
}










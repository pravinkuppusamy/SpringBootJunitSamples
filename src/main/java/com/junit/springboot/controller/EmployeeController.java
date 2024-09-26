package com.junit.springboot.controller;

import com.junit.springboot.dto.EmployeeDto;
import com.junit.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping
	public EmployeeDto get(@RequestParam String id) {
		return employeeService.getEmployee(id);
	}

	@PostMapping
	public void create(@RequestBody EmployeeDto employeeDto) {
		employeeService.createEmployee(employeeDto);
	}

}

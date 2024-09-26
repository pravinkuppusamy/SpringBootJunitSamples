package com.junit.springboot.controller;

import com.junit.springboot.dto.AuditableEmployeeRequestDto;
import com.junit.springboot.dto.AuditableEmployeeResponseDto;
import com.junit.springboot.service.AuditableEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("emp/auditable")
public class AuditableEmployeeController {

	@Autowired
	AuditableEmployeeService employeeService;

	@GetMapping
	public AuditableEmployeeResponseDto get(@RequestParam String id) {
		return employeeService.getEmployee(id);
	}

	@PostMapping
	public void create(@RequestBody AuditableEmployeeRequestDto employeeDto) {
		employeeService.createEmployee(employeeDto);
	}

}

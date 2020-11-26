package com.bridgelabz.employeepayrollapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

	@GetMapping(value = { "", "/", "/get" })
	public ResponseEntity<String> getEmployeePayrollData() {
		return new ResponseEntity<String>("Get Call Success", HttpStatus.OK);
	}
		

	@GetMapping("/get/{empId}")
	public ResponseEntity<String> getEmployeePayrollData(@PathVariable("empId") int empId) {
		return new ResponseEntity<String>("Get Call Success for id: " + empId, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<String> addEmployeePayrollData(@RequestBody EmployeePayrollDTO employeePayrollDTO) {
		return new ResponseEntity<String>("Created Employee Payroll Data for: " + employeePayrollDTO, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateEmployeePayrollData(@RequestBody EmployeePayrollDTO employeePayrollDTO) {
		return new ResponseEntity<String>("Updated Employee Payroll Data for: " + employeePayrollDTO, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<String> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
		return new ResponseEntity<String>("Delete Call Success for id: " + empId, HttpStatus.OK);
	}

}

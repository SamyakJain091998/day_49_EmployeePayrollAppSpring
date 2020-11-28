package com.bridgelabz.employeepayrollapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.model.User;
import com.bridgelabz.employeepayrollapp.repository.UserRepository;
import com.bridgelabz.employeepayrollapp.services.IEmployeePayrollService;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests

	public @ResponseBody String addNewEmployee(@RequestParam String name, @RequestParam long salary) {
		EmployeePayrollData empPayrollData = new EmployeePayrollData();
		empPayrollData.setName(name);
		empPayrollData.setSalary(salary);

		userRepository.save(empPayrollData);
		return "Saved";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<EmployeePayrollData> getAllEmployees() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@RequestMapping(value = { "", "/", "/get" })
	public @ResponseBody Iterable<EmployeePayrollData> getEmployeePayrollData() {
		return userRepository.findAll();
	}

	@GetMapping("/get/{empId}")
	public @ResponseBody Optional<EmployeePayrollData> getEmployeePayrollData(@PathVariable("empId") int empId) {
		return userRepository.findById(empId);
	}

//	@PostMapping("/create")
//	public ResponseEntity<ResponseDTO> addEmployeePayrollData(@RequestBody EmployeePayrollDTO employeePayrollDTO) {
//		EmployeePayrollData empData = null;
//		empData = employeePayrollService.createEmployeePayrollData(employeePayrollDTO);
//		ResponseDTO respDTO = new ResponseDTO("Create Call Successfull---> ", empData);
//		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
//	}

	@PutMapping("/update/{empId}")
	public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId,
			@RequestBody EmployeePayrollDTO employeePayrollDTO) {
		Optional<EmployeePayrollData> empData = null;
		empData = userRepository.findById(empId);
		EmployeePayrollData empObj = null;
		if (empData.isPresent()) {
			empObj = empData.get();
		}
		empObj.setName(employeePayrollDTO.name);
		empObj.setSalary(employeePayrollDTO.salary);
		userRepository.save(empObj);
		ResponseDTO respDTO = new ResponseDTO("Update Call Successfull---> ", empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
		userRepository.deleteById(empId);
		ResponseDTO respDTO = new ResponseDTO("Delete Call Successfull---> ", "Delete Id: " + empId);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

}

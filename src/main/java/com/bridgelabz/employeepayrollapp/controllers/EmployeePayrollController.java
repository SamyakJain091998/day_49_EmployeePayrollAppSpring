package com.bridgelabz.employeepayrollapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.exceptions.EmployeePayrollException;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.model.User;
import com.bridgelabz.employeepayrollapp.repository.UserRepository;
import com.bridgelabz.employeepayrollapp.services.EmployeePayrollService;
import com.bridgelabz.employeepayrollapp.services.IEmployeePayrollService;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

	private List<EmployeePayrollData> employeeDataList = new ArrayList<>();

	@Autowired
	private UserRepository userRepository;

//	@PostMapping(path = "/add") // Map ONLY POST Requests
//	public @ResponseBody String addNewEmployee(@RequestParam String name, @RequestParam long salary) {
//		EmployeePayrollData empPayrollData = new EmployeePayrollData();
//		empPayrollData.setName(name);
//		empPayrollData.setSalary(salary);
//
//		userRepository.save(empPayrollData);
//		return "Saved";
//	}

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public ResponseEntity<ResponseDTO> addNewEmployee(@Valid @RequestBody EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empData = null;
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		empData = employeePayrollService.createEmployeePayrollData(empPayrollDTO);
		employeeDataList.add(empData);
		System.out.println("Updated employeeDataList is : " + employeeDataList.toString());
		ResponseDTO respDTO = new ResponseDTO("Created employee payroll data successfully", empData);
		userRepository.save(empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<EmployeePayrollData> getAllEmployees() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@GetMapping(value = { "", "/", "/get" })
	public @ResponseBody Iterable<EmployeePayrollData> getEmployeePayrollData() {
		return userRepository.findAll();
	}

	@GetMapping("/get/{empId}")
	public @ResponseBody EmployeePayrollData getEmployeePayrollData(@PathVariable("empId") int empId) {
		try {
			return userRepository.findById(empId).get();
		} catch (Exception e) {
			// TODO: handle exception
			throw new EmployeePayrollException("Employee not foundd");
		}
	}

	@PutMapping("/update/{empId}")
	public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId,
			@Valid @RequestBody EmployeePayrollDTO employeePayrollDTO) {
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

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
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;
import com.bridgelabz.employeepayrollapp.services.EmployeePayrollService;
import com.bridgelabz.employeepayrollapp.services.IEmployeePayrollService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employeepayrollservice")
@Slf4j
public class EmployeePayrollController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public ResponseEntity<ResponseDTO> addNewEmployee(@Valid @RequestBody EmployeePayrollDTO empPayrollDTO) {

		log.debug("Employee DTO ===== > " + empPayrollDTO.toString());

		EmployeePayrollData empData = null;
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		empData = employeePayrollService.createEmployeePayrollData(empPayrollDTO);
		ResponseDTO respDTO = new ResponseDTO("Created employee payroll data successfully", empData);
		employeeRepository.save(empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<EmployeePayrollData> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping(value = { "", "/", "/get" })
	public @ResponseBody Iterable<EmployeePayrollData> getEmployeePayrollData() {
		return employeeRepository.findAll();
	}

	@GetMapping("/get/{empId}")
	public @ResponseBody EmployeePayrollData getEmployeePayrollData(@PathVariable("empId") int empId) {
		try {
			return employeeRepository.findById(empId).get();
		} catch (Exception e) {
			throw new EmployeePayrollException("Employee not foundd");
		}
	}

	@GetMapping("/get/department/{department}")
	public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("department") String department) {
		List<EmployeePayrollData> empDataList = null;
		empDataList = employeeRepository.findEmployeesByDepartment(department);
		ResponseDTO respDTO = new ResponseDTO("Retrieved data successfully", empDataList);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	@PutMapping("/update/{empId}")
	public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId,
			@Valid @RequestBody EmployeePayrollDTO employeePayrollDTO) {
		Optional<EmployeePayrollData> empData = null;
		empData = employeeRepository.findById(empId);
		EmployeePayrollData empObj = null;
		if (empData.isPresent()) {
			empObj = empData.get();
		}
		empObj.setName(employeePayrollDTO.name);
		empObj.setSalary(employeePayrollDTO.salary);
		employeeRepository.save(empObj);
		ResponseDTO respDTO = new ResponseDTO("Update Call Successfull---> ", empData);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
		employeeRepository.deleteById(empId);
		ResponseDTO respDTO = new ResponseDTO("Delete Call Successfull---> ", "Delete Id: " + empId);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

}

package com.bridgelabz.employeepayrollapp.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.employeepayrollapp.dto.*;
import com.bridgelabz.employeepayrollapp.exceptions.EmployeePayrollException;
import com.bridgelabz.employeepayrollapp.model.*;
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeePayrollService implements IEmployeePayrollService {

	private List<EmployeePayrollData> employeeDataList = new ArrayList<>();

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empPayrollData = null;
		empPayrollData = new EmployeePayrollData(empPayrollDTO);
		empPayrollData = employeeRepository.save(empPayrollData);
		employeeDataList.add(empPayrollData);
		return empPayrollData;
	}

	@Override
	public List<EmployeePayrollData> getEmployeePayrollData() {
		employeeDataList.clear();
		employeeRepository.findAll().forEach(employee -> employeeDataList.add(employee));
		return (List<EmployeePayrollData>) employeeRepository.findAll();
	}

	@Override
	public EmployeePayrollData getEmployeePayrollDataById(int empId) throws EmployeePayrollException {
		try {
			return employeeRepository.findById(empId).get();
		} catch (Exception e) {
			throw new EmployeePayrollException("Employee not foundd");
		}
	}

	@Override
	public List<EmployeePayrollData> getEmployeesByDepartment(String department) {
		return employeeRepository.findEmployeesByDepartment(department);
	}

	@Override
	public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO empPayrollDTO) {
		Optional<EmployeePayrollData> empData = null;
		empData = employeeRepository.findById(empId);
		EmployeePayrollData empObj = null;
		if (empData.isPresent()) {
			empObj = empData.get();
		}
		empObj.setName(empPayrollDTO.name);
		empObj.setSalary(empPayrollDTO.salary);
		employeeRepository.save(empObj);
		return empObj;
	}

	@Override
	public void deleteEmployeePayrolllData(int empId) {
		employeeRepository.deleteById(empId);
	}

}

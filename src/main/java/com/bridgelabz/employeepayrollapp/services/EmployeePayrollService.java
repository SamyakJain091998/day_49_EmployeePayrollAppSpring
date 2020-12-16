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

	private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

	public List<EmployeePayrollData> getEmployeePayrollData() {
		return employeePayrollList;
	}

	public EmployeePayrollData getEmployeePayrollDataById(int empId) throws EmployeePayrollException {
		return employeePayrollList.stream().filter(empData -> empData.getEmployeeId() == empId).findFirst()
				.orElseThrow(() -> new EmployeePayrollException("----------EmployeeNotFound!!!----------"));
	}

	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empPayrollData = null;
		empPayrollData = new EmployeePayrollData(empPayrollDTO);
		log.debug("Emp Data: ----------> " + empPayrollData.toString());
		employeePayrollList.add(empPayrollData);
		return empPayrollData;
	}

	public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData empPayrollData = this.getEmployeePayrollDataById(empId);
		empPayrollData.setName(empPayrollDTO.name);
		empPayrollData.setSalary(empPayrollDTO.salary);
		employeePayrollList.set(empId - 1, empPayrollData);
		return empPayrollData;
	}

	public void deleteEmployeePayrolllData(int empId) {
		employeePayrollList.remove(empId - 1);
	}

	@Override
	public List<EmployeePayrollData> getEmployeesByDepartment(String department) {
		// TODO Auto-generated method stub
		return null;
	}
}

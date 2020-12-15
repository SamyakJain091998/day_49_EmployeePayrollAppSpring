package com.bridgelabz.employeepayrollapp.services;

import java.util.*;

import org.springframework.stereotype.Service;

import com.bridgelabz.employeepayrollapp.dto.*;
import com.bridgelabz.employeepayrollapp.exceptions.EmployeePayrollException;
import com.bridgelabz.employeepayrollapp.model.*;

@Service
public class EmployeePayrollService implements IEmployeePayrollService{

	private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

	public List<EmployeePayrollData> getEmployeePayrollData() {
//		List<EmployeePayrollData> empDataList = new ArrayList<>();
//		empDataList.add(new EmployeePayrollData(1, new EmployeePayrollDTO("Pankaj", 30000)));
		return employeePayrollList;
	}

	public EmployeePayrollData getEmployeePayrollDataById(int empId) throws EmployeePayrollException{
		// TODO Auto-generated method stub
//		EmployeePayrollData empPayrollData = null;
//		empPayrollData = new EmployeePayrollData(1, new EmployeePayrollDTO("Pankaj", 30000));
//		return employeePayrollList.get(empId - 1);
		return employeePayrollList.stream().filter(empData -> empData.getEmployeeId() == empId).findFirst()
				.orElseThrow(() -> new EmployeePayrollException("----------EmployeeNotFound!!!----------"));
	}

	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		// TODO Auto-generated method stub
		EmployeePayrollData empPayrollData = null;
		empPayrollData = new EmployeePayrollData(employeePayrollList.size() + 1, empPayrollDTO);
		employeePayrollList.add(empPayrollData);
		return empPayrollData;
	}

	public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO empPayrollDTO) {
		// TODO Auto-generated method stub
//		EmployeePayrollData empPayrollData = null;
//		empPayrollData = new EmployeePayrollData(1, empPayrollDTO);
//		empPayrollData = new EmployeePayrollData(1, empPayrollDTO);
		EmployeePayrollData empPayrollData = this.getEmployeePayrollDataById(empId);
		empPayrollData.setName(empPayrollDTO.name);
		empPayrollData.setSalary(empPayrollDTO.salary);
		employeePayrollList.set(empId - 1, empPayrollData);
		return empPayrollData;
	}

	public void deleteEmployeePayrolllData(int empId) {
		// TODO Auto-generated method stub
		employeePayrollList.remove(empId - 1);
	}
}

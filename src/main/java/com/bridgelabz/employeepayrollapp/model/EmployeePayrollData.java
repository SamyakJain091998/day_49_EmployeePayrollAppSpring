package com.bridgelabz.employeepayrollapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bridgelabz.employeepayrollapp.dto.*;

@Entity
public class EmployeePayrollData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer employeeId;

	private String name;
	private long salary;

	public EmployeePayrollData() {

	}

	public EmployeePayrollData(String name, long salary) {
		this.name = name;
		this.salary = salary;
	}
	
	
//	public EmployeePayrollData(Integer id, EmployeePayrollDTO empPayrollDTO) {
//		this.employeeId = id;
//		this.name = empPayrollDTO.name;
//		this.salary = empPayrollDTO.salary;
//	}

	public EmployeePayrollData(int i, EmployeePayrollDTO empPayrollDTO) {
		// TODO Auto-generated constructor stub
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

}

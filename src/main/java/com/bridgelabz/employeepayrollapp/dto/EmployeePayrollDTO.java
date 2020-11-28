package com.bridgelabz.employeepayrollapp.dto;

public class EmployeePayrollDTO {

	public String name;
	public long salary;

	public EmployeePayrollDTO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "EmployeePayrollDTO [name=" + name + ", salary=" + salary + "]";
	}

	public EmployeePayrollDTO(String name, long salary) {
		this.name = name;
		this.salary = salary;
	}
}

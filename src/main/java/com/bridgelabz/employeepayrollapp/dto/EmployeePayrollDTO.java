package com.bridgelabz.employeepayrollapp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class EmployeePayrollDTO {

	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "---------------------Employee Name invalid...!!!--------------------------")
	public String name;

	@Min(value = 500, message = "---------------------Salary invalid...(Min. salary should be 500)!!!--------------------------")
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

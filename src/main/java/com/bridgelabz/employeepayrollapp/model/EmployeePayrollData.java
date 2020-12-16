package com.bridgelabz.employeepayrollapp.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.bridgelabz.employeepayrollapp.dto.*;

import lombok.Data;

@Entity
public @Data class EmployeePayrollData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer employeeId;

	private String name;
	private long salary;

	private String gender;
	private LocalDate startDate;
	private String note;
	private String profilePic;
	private String departments;

	public EmployeePayrollData() {

	}

	private void updateEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		// TODO Auto-generated method stub
		this.name = empPayrollDTO.name;
		this.salary = empPayrollDTO.salary;
		this.gender = empPayrollDTO.gender;
		this.note = empPayrollDTO.note;
		this.startDate = empPayrollDTO.startDate;
		this.profilePic = empPayrollDTO.profilePic;
		this.departments = empPayrollDTO.departments;
	}

	public EmployeePayrollData(String name, long salary) {
		this.name = name;
		this.salary = salary;
	}

	public EmployeePayrollData(int i, EmployeePayrollDTO empPayrollDTO) {
		// TODO Auto-generated constructor stub
		this.updateEmployeePayrollData(empPayrollDTO);
	}
}

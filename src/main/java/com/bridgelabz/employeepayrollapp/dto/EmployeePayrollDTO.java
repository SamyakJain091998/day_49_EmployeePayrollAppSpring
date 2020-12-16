package com.bridgelabz.employeepayrollapp.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.ToString;

public @ToString class EmployeePayrollDTO {

	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "---------------------Employee Name invalid...!!!--------------------------")
	public String name;

	@Min(value = 500, message = "---------------------Salary invalid...(Min. salary should be 500)!!!--------------------------")
	public long salary;

	@Pattern(regexp = "male|female", message = "Gender needs to be male or female")
	public String gender;

	@JsonFormat(pattern = "dd MMM yyyy")
	@NotNull(message = "Start Date shouldn't be empty")
	@PastOrPresent(message = "Start Date should be past or today's date")
	public LocalDate startDate;

	@NotBlank(message = "Note can't be empty")
	public String note;

	@NotBlank(message = "Profile Pic can't be empty! Trust me, you look good")
	public String profilePic;

	@NotNull(message = "departments shouldn't be empty")
	public List<String> departments;

	public EmployeePayrollDTO(String name, long salary) {
		this.name = name;
		this.salary = salary;
	}
}

package com.bridgelabz.employeepayrollapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class EmployeePayrollAppApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EmployeePayrollAppApplication.class, args);
		log.info("Employee payroll app started started in {} environment",
				context.getEnvironment().getProperty("environment"));
		log.info("Employee payroll DB user {} ", context.getEnvironment().getProperty("spring.datasource.username"));
	}

}

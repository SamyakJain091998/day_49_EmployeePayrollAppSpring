package com.bridgelabz.employeepayrollapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.employeepayrollapp.model.*;

public interface EmployeeRepository extends CrudRepository<EmployeePayrollData, Integer> {

}
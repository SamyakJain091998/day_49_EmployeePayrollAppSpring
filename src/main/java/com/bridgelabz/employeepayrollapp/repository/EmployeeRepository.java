package com.bridgelabz.employeepayrollapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.employeepayrollapp.model.*;

public interface EmployeeRepository extends CrudRepository<EmployeePayrollData, Integer> {
	@Query(value = "select * from employee_payroll_data, employee_department where employee_id=id and department=:department", nativeQuery = true)
	List<EmployeePayrollData> findEmployeesByDepartment(String department);
}
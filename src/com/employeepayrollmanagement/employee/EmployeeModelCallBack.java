package com.employeepayrollmanagement.employee;

import com.employeepayrollmanagement.dto.Employee;

public interface EmployeeModelCallBack {

	void getSalary(Employee employee, int month);

}

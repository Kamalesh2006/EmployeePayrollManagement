package com.employeepayrollmanagement.employee;

import com.employeepayrollmanagement.dto.Employee;

public interface EmployeeModelCallBack {

	void getPaySlip(Employee employee, int month, int year);

}

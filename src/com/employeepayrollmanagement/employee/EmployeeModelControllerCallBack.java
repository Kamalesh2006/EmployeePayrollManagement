package com.employeepayrollmanagement.employee;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.Salary;

public interface EmployeeModelControllerCallBack {

	void notifyError(Employee employee, String string);

	void salaryOfEmployee(Employee employee, Salary salary);

}

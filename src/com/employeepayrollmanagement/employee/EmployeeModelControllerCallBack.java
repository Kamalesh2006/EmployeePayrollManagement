package com.employeepayrollmanagement.employee;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.PaySlip;
import com.employeepayrollmanagement.dto.Salary;

public interface EmployeeModelControllerCallBack {

	void notifyError(Employee employee, String string);

	void paySlipOfEmployee(Employee employee, PaySlip paySlip);

}

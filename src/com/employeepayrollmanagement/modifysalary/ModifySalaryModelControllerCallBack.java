package com.employeepayrollmanagement.modifysalary;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.PaySlip;
import com.employeepayrollmanagement.dto.Salary;

public interface ModifySalaryModelControllerCallBack {

	void obtainedEmployee(Employee employee);

	void error(String string);

	void salaryUpdatedSuccessfully(Salary salaryOfEmployee);

	void incentiveAddedSuccessfully(PaySlip paySlip);

}

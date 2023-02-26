package com.employeepayrollmanagement.modifysalary;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.Salary;

public interface ModifySalaryViewCallBack {

	void printStatus(String string);

	void employeeModifySalary(Employee employee);

	void increaseSalary(Employee employee);

	void decreaseSalary(Employee employee);

	void addIncentive(Employee employee);

	void goBackToHR();

	void goBackToLogin();

	void salaryUpdated(Salary salaryOfEmployee);

}

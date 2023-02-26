package com.employeepayrollmanagement.modifysalary;

import com.employeepayrollmanagement.dto.Employee;

public interface ModifySalaryModelCallBack {

	void getEmployee(String empID);

	void increaseSalary(Employee employee, int salaryToIncrease);

	void decreaseSalary(Employee employee, int salaryToDecrease);

	void addIncentive(Employee employee, int incentive, int month, int year);

}

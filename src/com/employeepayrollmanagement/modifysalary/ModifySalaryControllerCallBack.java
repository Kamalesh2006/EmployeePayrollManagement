package com.employeepayrollmanagement.modifysalary;

import com.employeepayrollmanagement.dto.Employee;

public interface ModifySalaryControllerCallBack {

	void searchEmployeeByID(String empID);

	void decideEmployeeOption(int option, Employee employee);

	void increaseSalary(Employee employee, int salaryToIncrease);

	void decreaseSalary(Employee employee, int salaryToDecrease);

	void addIncentive(Employee employee, int incentive, int month, int year);

}

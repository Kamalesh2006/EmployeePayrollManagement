package com.employeepayrollmanagement.employee;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.Salary;
import com.employeepayrollmanagement.repository.EmployeePayrollDB;

public class EmployeeModel implements EmployeeModelCallBack {
	private EmployeeModelControllerCallBack employeeController;
	private EmployeePayrollDB employeePayrollInstance = EmployeePayrollDB.getInstance();
	EmployeeModel(EmployeeController employeeController) {
		this.employeeController= employeeController;
	}
	@Override
	public void getSalary(Employee employee,int month) {
		Salary salary = employeePayrollInstance.getSalary(employee,month);
		if(salary==null) {
			employeeController.notifyError(employee,"Something went wrong");
		}
		else {
			employeeController.salaryOfEmployee(employee,salary);
		}
	}

}

package com.employeepayrollmanagement.employee;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.PaySlip;
import com.employeepayrollmanagement.dto.Salary;
import com.employeepayrollmanagement.repository.EmployeePayrollDB;

public class EmployeeModel implements EmployeeModelCallBack {
	private EmployeeModelControllerCallBack employeeController;
	private EmployeePayrollDB employeePayrollInstance = EmployeePayrollDB.getInstance();
	EmployeeModel(EmployeeController employeeController) {
		this.employeeController= employeeController;
	}
	@Override
	public void getPaySlip(Employee employee,int month,int year) {
		PaySlip paySlip = employeePayrollInstance.getPaySlip(employee,month,year);
		if(paySlip==null) {
			employeeController.notifyError(employee,"Something went wrong");
		}
		else {
			employeeController.paySlipOfEmployee(employee,paySlip);
		}
	}

}

package com.employeepayrollmanagement.applyleave;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.repository.EmployeePayrollDB;

public class ApplyLeaveModel implements ApplyLeaveModelCallBack {
	private ApplyLeaveModelControllerCallBack applyLeaveController;
	private EmployeePayrollDB employeePayrollInstance = EmployeePayrollDB.getInstance();
	public ApplyLeaveModel(ApplyLeaveController applyLeaveController) {
		this.applyLeaveController = applyLeaveController;
	}
	

}

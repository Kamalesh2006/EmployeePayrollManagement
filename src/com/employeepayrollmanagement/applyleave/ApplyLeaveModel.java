package com.employeepayrollmanagement.applyleave;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.LeaveTracker;
import com.employeepayrollmanagement.repository.EmployeePayrollDB;

public class ApplyLeaveModel implements ApplyLeaveModelCallBack {
	private ApplyLeaveModelControllerCallBack applyLeaveController;
	private EmployeePayrollDB employeePayrollInstance = EmployeePayrollDB.getInstance();

	public ApplyLeaveModel(ApplyLeaveController applyLeaveController) {
		this.applyLeaveController = applyLeaveController;
	}

	@Override
	public void applyLeave(Employee employee, int date) {
		LeaveTracker leaveTracker = employeePayrollInstance.getLeaveTracker(employee);
		// checking if the employee has already applied for leave and also the leave
		// days are higher than one
		if (!leaveTracker.isLeaveApplyStatus() && date > 0) {
			leaveTracker.setNoOfDaysLeaveRequired(date);
			// the employee has applied for leave
			leaveTracker.setLeaveApplyStatus(true);
			applyLeaveController.leaveAppliedSuccessfully(employee, date);
		} else {
			applyLeaveController.leaveAlreadyApplied(employee);
		}
	}

}

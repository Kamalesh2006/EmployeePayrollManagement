package com.employeepayrollmanagement.applyleave;

import com.employeepayrollmanagement.dto.Employee;

public interface ApplyLeaveViewCallBack {

	void applyLeaveAgain(String error, Employee employee);

	void leaveAppliedSuccessfully(Employee employee, int date);

	void leaveAlreadyApplied(Employee employee, String string);

}

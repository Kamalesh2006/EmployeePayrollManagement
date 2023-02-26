package com.employeepayrollmanagement.applyleave;

import com.employeepayrollmanagement.dto.Employee;

public interface ApplyLeaveModelControllerCallBack {

	void leaveAlreadyApplied(Employee employee);

	void leaveAppliedSuccessfully(Employee employee, int date);

}

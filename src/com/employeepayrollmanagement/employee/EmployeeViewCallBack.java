package com.employeepayrollmanagement.employee;

import com.employeepayrollmanagement.dto.Employee;

public interface EmployeeViewCallBack {

	void callToApplyLeave(Employee employee);

	void callToAttendance(Employee employee);

	void callBackToLogin(String msg);

	void printStatus(Employee employee, String msg);

}

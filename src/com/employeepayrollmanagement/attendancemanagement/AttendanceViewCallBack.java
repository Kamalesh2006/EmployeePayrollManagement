package com.employeepayrollmanagement.attendancemanagement;

import com.employeepayrollmanagement.dto.Employee;

public interface AttendanceViewCallBack {

	void printStatus(Employee employee, String msg);

	void callBackToEmployeeView(Employee employee);

}

package com.employeepayrollmanagement.humanresource;

import java.util.List;

import com.employeepayrollmanagement.dto.Credentials;
import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.LeaveTracker;

public interface HRViewCallBack {

	void registerEmployee();

	void printStatus(String msg);

	void employeeCheckedIn(List<Employee> employeeCheckedInList);

	void showLeaveAppliedEmployees(List<Employee> employeeList, int[] availableHolidays,
			List<LeaveTracker> leaveTrackerList);

	void statusOfEmployeeList(List<Credentials> addedEmployeeList, List<Employee> notAddedEmployeeList);

}

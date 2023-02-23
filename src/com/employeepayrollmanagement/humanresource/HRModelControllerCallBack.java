package com.employeepayrollmanagement.humanresource;

import java.util.List;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.LeaveTracker;

public interface HRModelControllerCallBack {

	void noEmployeeCheckedIn();

	void employeeCheckedIn(List<Employee> employeeCheckedInList);

	void leaveAppliedEmployees(List<LeaveTracker> leaveTrackerList);

}

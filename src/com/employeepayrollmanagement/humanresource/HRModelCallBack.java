package com.employeepayrollmanagement.humanresource;

import java.util.List;

import com.employeepayrollmanagement.dto.Credentials;
import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.LeaveTracker;

public interface HRModelCallBack {

	void getCheckedInEmployees();

	void getLeaveAppliedEmployees();

	boolean isEmailNew(String email);

	int getTotalEmployeesCount();

	Credentials addEmployeeToDB(Employee employee);

	void approveLeave(List<Employee> employeeList, List<LeaveTracker> leaveTrackerList);

}

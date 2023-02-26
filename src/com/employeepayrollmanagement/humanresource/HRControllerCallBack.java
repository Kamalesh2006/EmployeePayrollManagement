package com.employeepayrollmanagement.humanresource;

import java.util.List;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.LeaveTracker;

public interface HRControllerCallBack {

	void decideOptions(int option);

	String generateEmpID(String email, int count);

	void addEmployees(List<Employee> newEmployeeList);

	void decideWhereToGo(boolean option);

	void decideToApproveLeave(boolean leaveApproval, List<Employee> employeeList, List<LeaveTracker> leaveTrackerList);

}

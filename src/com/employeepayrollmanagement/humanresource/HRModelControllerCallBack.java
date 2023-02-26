package com.employeepayrollmanagement.humanresource;

import java.util.List;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.LeaveTracker;
import com.employeepayrollmanagement.dto.PaySlip;

public interface HRModelControllerCallBack {

	void noEmployeeCheckedIn();

	void employeeCheckedIn(List<Employee> employeeCheckedInList);

	void leaveAppliedEmployees(List<LeaveTracker> leaveTrackerList);

	void paySlipListEmployees(List<PaySlip> paySlipList);

}

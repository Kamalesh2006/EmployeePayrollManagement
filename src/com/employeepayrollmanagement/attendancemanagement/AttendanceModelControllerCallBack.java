package com.employeepayrollmanagement.attendancemanagement;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.LeaveTracker;

public interface AttendanceModelControllerCallBack {

	void checkedInSuccessfully(Employee employee);

	void checkedOutSuccessfully(Employee employee);

	void attendance(LeaveTracker attendanceList);

}

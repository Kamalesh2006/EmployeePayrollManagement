package com.employeepayrollmanagement.attendancemanagement;

import com.employeepayrollmanagement.dto.Employee;

public interface AttendanceModelControllerCallBack {

	void checkedInSuccessfully(Employee employee);

	void checkedOutSuccessfully(Employee employee);

}

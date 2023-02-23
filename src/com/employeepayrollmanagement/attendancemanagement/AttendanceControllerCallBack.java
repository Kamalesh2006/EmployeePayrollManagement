package com.employeepayrollmanagement.attendancemanagement;

import com.employeepayrollmanagement.dto.Employee;

public interface AttendanceControllerCallBack {

	void decideOptions(int option, Employee employee);

}

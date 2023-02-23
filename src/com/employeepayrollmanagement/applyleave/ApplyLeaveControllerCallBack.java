package com.employeepayrollmanagement.applyleave;

import java.time.LocalDate;

import com.employeepayrollmanagement.dto.Employee;

public interface ApplyLeaveControllerCallBack {

	void checkForAttendance(Employee employee, LocalDate fDate, LocalDate tDate);

}

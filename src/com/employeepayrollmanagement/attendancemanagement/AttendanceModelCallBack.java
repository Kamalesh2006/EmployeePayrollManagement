package com.employeepayrollmanagement.attendancemanagement;

import java.time.LocalDate;
import java.time.LocalTime;

import com.employeepayrollmanagement.dto.Employee;

public interface AttendanceModelCallBack {

	void checkIn(Employee employee, LocalDate date, LocalTime time);

	boolean isCheckedIn(Employee employee);

	void checkout(Employee employee, LocalTime time2);

	void getAttendanceList(Employee employee);

}

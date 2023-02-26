package com.employeepayrollmanagement.attendancemanagement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.LeaveTracker;
import com.employeepayrollmanagement.repository.EmployeePayrollDB;

public class AttendanceModel implements AttendanceModelCallBack {
	private AttendanceModelControllerCallBack attendanceController;
	private EmployeePayrollDB employeePayrollInstance= EmployeePayrollDB.getInstance();
	public AttendanceModel(AttendanceController attendanceController) {
		this.attendanceController = attendanceController;
	}
	@Override
	public void checkIn(Employee employee,LocalDate date, LocalTime time) {
		LeaveTracker leaveTracker = employeePayrollInstance.getLeaveTracker(employee);
		leaveTracker.setCheckInStatus(true);
		leaveTracker.getWorkingDays().add(date);
		attendanceController.checkedInSuccessfully(employee);
	}
	@Override
	public boolean isCheckedIn(Employee employee) {
		LeaveTracker leave = employeePayrollInstance.getLeaveTracker(employee);
		if(leave==null || leave.isCheckInStatus()==false) {
			return false;
		}
		else {
			return true;
		}
		
	}
	@Override
	public void checkout(Employee employee, LocalTime time2) {
		LeaveTracker checkOut = employeePayrollInstance.getLeaveTracker(employee);
		checkOut.setCheckInStatus(false);
		LocalTime inTime = checkOut.getCheckInTime();
		Duration timeDuration = Duration.between(time2, inTime);
		if(timeDuration.toHours()>=8) {
			checkOut.setNoOfDaysWorked(checkOut.getNoOfDaysWorked()+1);
		}
		else if(timeDuration.toHours()>=4) {
			checkOut.setNoOfDaysWorked(checkOut.getNoOfDaysWorked()+0.5);
		}
		else {
			//The employee is considered to be working day won't get rised
		}
		attendanceController.checkedOutSuccessfully(employee);
	}
	@Override
	public void getAttendanceList(Employee employee) {
		LeaveTracker attendanceList = employeePayrollInstance.getLeaveTracker(employee);
		attendanceController.attendance(attendanceList);
	}

}

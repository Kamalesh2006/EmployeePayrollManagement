package com.employeepayrollmanagement.attendancemanagement;

import java.time.LocalDate;
import java.time.LocalTime;

import com.employeepayrollmanagement.dto.Employee;

public class AttendanceController implements AttendanceControllerCallBack, AttendanceModelControllerCallBack {
	private AttendanceViewCallBack attendanceView;
	private AttendanceModelCallBack attendanceModel;
	public AttendanceController(AttendanceView attendanceView) {
		this.attendanceView = attendanceView;
		attendanceModel = new AttendanceModel(this);
	}
	@Override
	public void decideOptions(int option, Employee employee) {
		boolean flag = attendanceModel.isCheckedIn(employee);
		if(flag && option==1) {
			attendanceView.printStatus(employee, " has already checked in");
			return;
		}
		else if(!flag && option ==2) {
			attendanceView.printStatus(employee, " has already checked out");
			return;
		}else {
			switch(option) {
			case 1:
				LocalDate date = LocalDate.now();
				LocalTime time = LocalTime.now();
				attendanceModel.checkIn(employee,date,time);
				break;
			case 2:
				LocalTime time2 = LocalTime.now();
				attendanceModel.checkout(employee,time2);
				break;
			case 3:
				attendanceView.printStatus(employee, " has "+employee.getLeaveRemaining()+" leave days remaining");
				break;
			case 4:
				attendanceView.callBackToEmployeeView(employee);
				break;
			}
		}
	}
	@Override
	public void checkedInSuccessfully(Employee employee) {
		attendanceView.printStatus(employee,"Checked in Successfully");
	}
	@Override
	public void checkedOutSuccessfully(Employee employee) {
		attendanceView.printStatus(employee, "Checked out Successfully");
	}

}

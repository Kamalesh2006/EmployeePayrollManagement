package com.employeepayrollmanagement.attendancemanagement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.LeaveTracker;

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
			attendanceView.printStatus(employee, "Has already checked in");
			return;
		}
		else if(!flag && option ==2) {
			attendanceView.printStatus(employee, "Has already checked out");
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
				attendanceModel.getAttendanceList(employee);
				break;
			case 4:
				attendanceView.printStatus(employee, " has "+employee.getLeaveRemaining()+" leave days remaining");
				break;
			case 5:
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
	@Override
	public void attendance(LeaveTracker attendanceList) {
		String resultAttendance = "No of days worked:";
		List<LocalDate> attendDate = attendanceList.getWorkingDays();
		for(int i = attendDate.size()-1;i>=0;i--) {
			resultAttendance +="\n"+attendDate.get(i);
		}
		attendanceView.printStatus(attendanceList.getEmployee(), resultAttendance);
	}

}

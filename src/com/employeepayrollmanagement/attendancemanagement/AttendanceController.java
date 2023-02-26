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
		// checking the repository if he is already checked in
		boolean flag = attendanceModel.isCheckedIn(employee);
		// if he chose to check in
		if (flag && option == 1) {
			attendanceView.printStatus(employee, "Has already checked in");
			return;
		}
		// if he chose to check out but already checked out
		else if (!flag && option == 2) {
			attendanceView.printStatus(employee, "Has already checked out");
			return;
		} else {
			switch (option) {
			case 1:
				//taking the record of check in time and check in date
				LocalDate date = LocalDate.now();
				LocalTime time = LocalTime.now();
				attendanceModel.checkIn(employee, date, time);
				break;
			case 2:
				// for check out currently i am taking only the time 
				//because assuming that the employee will check out the same day he checked in
				LocalTime time2 = LocalTime.now();
				attendanceModel.checkout(employee, time2);
				break;
			case 3:
				//to see his attendance in the company
				attendanceModel.getAttendanceList(employee);
				break;
			case 4:
				//this shows the no of leaves remaining for the employee that he can take
				attendanceView.printStatus(employee, " has " + employee.getLeaveRemaining() + " leave days remaining");
				break;
			case 5:
				attendanceView.callBackToEmployeeView(employee);
				break;
			}
		}
	}

	@Override
	public void checkedInSuccessfully(Employee employee) {
		attendanceView.printStatus(employee, "Checked in Successfully");
	}

	@Override
	public void checkedOutSuccessfully(Employee employee) {
		attendanceView.printStatus(employee, "Checked out Successfully");
	}
	
	//this contains the attendance(Dates of employee worked) of the employee
	@Override
	public void attendance(LeaveTracker attendanceList) {
		String resultAttendance = "No of days worked:";
		List<LocalDate> attendDate = attendanceList.getWorkingDays();
		for (int i = attendDate.size() - 1; i >= 0; i--) {
			resultAttendance += "\n" + attendDate.get(i);
		}
		attendanceView.printStatus(attendanceList.getEmployee(), resultAttendance);
	}

}

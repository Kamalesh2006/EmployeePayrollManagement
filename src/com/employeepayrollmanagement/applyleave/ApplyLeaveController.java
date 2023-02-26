package com.employeepayrollmanagement.applyleave;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

import com.employeepayrollmanagement.dto.Employee;

public class ApplyLeaveController implements ApplyLeaveControllerCallBack, ApplyLeaveModelControllerCallBack {
	private ApplyLeaveViewCallBack applyLeaveView;
	private ApplyLeaveModelCallBack applyLeaveModel;

	public ApplyLeaveController(ApplyLeaveView applyLeaveView) {
		this.applyLeaveView = applyLeaveView;
		this.applyLeaveModel = new ApplyLeaveModel(this);

	}

	@Override
	public void checkForAttendance(Employee employee, LocalDate fDate, LocalDate tDate) {
		LocalDate today = LocalDate.now();
		if (fDate.isAfter(today) && tDate.isAfter(today)) {
			if (tDate.isAfter(fDate)) {
				// calculating how much days the employee want leave, ignoring sunday and
				// saturday
				int date = findNoOfWorkingDates(fDate, tDate);
				applyLeaveModel.applyLeave(employee, date);

			} else {
				applyLeaveView.applyLeaveAgain("TO date is wrong re-enter again", employee);
			}
		} else {
			applyLeaveView.applyLeaveAgain("\"FROM\" date is wrong reenter again", employee);
		}
	}

	private int findNoOfWorkingDates(LocalDate fDate, LocalDate tDate) {
		int result = 0;
		LocalDate date = fDate;
		String day = date.getDayOfWeek().name();
		// if both the from and to date is same
		// then we have to check if it is sunday or saturday
		if (date.isEqual(tDate)) {
			if (!day.equals("SUNDAY") && !day.equals("SATURDAY")) {
				return 1;
			}
		}

		// if he wants more days of leave then we have to take only the working dates
		// into consideration
		while (date.isBefore(tDate)) {
			// calculating only the no of working days in between the dates locked by
			// employee
			day = date.getDayOfWeek().name();
			if (!day.equals("SUNDAY") && !day.equals("SATURDAY")) {
				result++;
			}
			date = date.plusDays(1);
		}
		// checking for the last date if it is working date
		return result;
	}

	@Override
	public void leaveAlreadyApplied(Employee employee) {
		applyLeaveView.leaveAlreadyApplied(employee, "Employee has already applied for leave");
	}

	@Override
	public void leaveAppliedSuccessfully(Employee employee, int date) {
		applyLeaveView.leaveAppliedSuccessfully(employee, date);
	}

}

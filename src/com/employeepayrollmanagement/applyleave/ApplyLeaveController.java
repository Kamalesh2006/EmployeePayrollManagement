package com.employeepayrollmanagement.applyleave;

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
		if(fDate.isAfter(today) && tDate.isAfter(today)) {
			if(tDate.isAfter(fDate)) {
				applyLeaveView.leaveAppliedSuccessfully(employee);
			}
			else {
				applyLeaveView.applyLeaveAgain("TO date is wrong re-enter again",employee);
			}
		}
		else {
			applyLeaveView.applyLeaveAgain("\"FROM\" date is wrong reenter again",employee);
		}
	}

}

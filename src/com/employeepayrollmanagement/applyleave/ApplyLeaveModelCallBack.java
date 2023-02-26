package com.employeepayrollmanagement.applyleave;

import com.employeepayrollmanagement.dto.Employee;

public interface ApplyLeaveModelCallBack {

	void applyLeave(Employee employee, int date);

}

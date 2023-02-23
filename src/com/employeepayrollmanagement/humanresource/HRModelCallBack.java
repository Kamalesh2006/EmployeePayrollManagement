package com.employeepayrollmanagement.humanresource;

import com.employeepayrollmanagement.dto.Credentials;
import com.employeepayrollmanagement.dto.Employee;

public interface HRModelCallBack {

	void getCheckedInEmployees();

	void getLeaveAppliedEmployees();

	boolean isEmailNew(String email);

	int getTotalEmployeesCount();

	Credentials addEmployeeToDB(Employee employee);

}

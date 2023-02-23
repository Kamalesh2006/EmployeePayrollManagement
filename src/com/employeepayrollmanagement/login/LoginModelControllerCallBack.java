package com.employeepayrollmanagement.login;

import com.employeepayrollmanagement.dto.Employee;

public interface LoginModelControllerCallBack {

	void loginSuccess(Employee employee);

	void loginFailer();

	void employerLoginSuccess(Employee employee);

}

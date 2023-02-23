package com.employeepayrollmanagement.login;

import com.employeepayrollmanagement.dto.Employee;

public interface LoginViewCallBack {

	void inputagain(String error);

	void loginSuccess(Employee employee);

	void employerLoginSuccess(Employee employee);

}

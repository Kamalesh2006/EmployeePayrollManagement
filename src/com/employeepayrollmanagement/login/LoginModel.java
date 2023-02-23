package com.employeepayrollmanagement.login;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.repository.EmployeePayrollDB;

public class LoginModel implements LoginModelCallBack {
	private LoginModelControllerCallBack loginController;
	private EmployeePayrollDB employeePayrollInstance =  EmployeePayrollDB.getInstance();
	public LoginModel(LoginController loginController) {
		this.loginController = loginController;
	}
	@Override
	public void fetchUser(String emailid, String password) {
		Employee employee = employeePayrollInstance.checkValidEmployee(emailid, password);
		if(employee!=null) {
			if(employee.isEmployer()) {
				loginController.employerLoginSuccess(employee);
				return;
			}
			else {
				loginController.loginSuccess(employee);	
				return;
			}
		}
		else {
			loginController.loginFailer();
		}
	}

}

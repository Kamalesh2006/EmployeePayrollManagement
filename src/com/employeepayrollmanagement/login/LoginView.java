package com.employeepayrollmanagement.login;

import java.util.Scanner;

import com.employeepayrollmanagement.applyleave.ApplyLeaveView;
import com.employeepayrollmanagement.attendancemanagement.AttendanceView;
import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.employee.EmployeeView;
import com.employeepayrollmanagement.humanresource.HRView;

public class LoginView implements LoginViewCallBack {
	private LoginControllerCallBack loginController;
	private EmployeeView employeeView;
	private Scanner scanner = new Scanner(System.in);

	public LoginView() {
		this.loginController = new LoginController(this);
	}

	// this is the first page after application started
	public void showLogin() {
		System.out.println("Enter your email id:");
		String emailid = scanner.next();
		System.out.println("Enter your password: ");
		String password = scanner.next();
		loginController.verifyUser(emailid, password);
	}

	@Override
	public void inputagain(String error) {
		System.out.println(error);
		showLogin();
	}

	@Override
	public void loginSuccess(Employee employee) {
		employeeView = new EmployeeView();
		employeeView.employeeLogin(employee, this);
	}

	@Override
	public void employerLoginSuccess(Employee employee) {
		HRView hrView = new HRView();
		hrView.hrloginpage(this);
	}
}

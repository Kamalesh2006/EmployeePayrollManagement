package com.employeepayrollmanagement.attendancemanagement;

import java.util.Scanner;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.employee.EmployeeView;
import com.employeepayrollmanagement.login.LoginView;

public class AttendanceView implements AttendanceViewCallBack {
	private LoginView login;
	private AttendanceControllerCallBack attendanceController;
	private EmployeeView employeeView;
	private Scanner scanner = new Scanner(System.in);
	public AttendanceView() {
		this.attendanceController = new AttendanceController(this);
	}
	public void showAttendanceOptions(Employee employee,EmployeeView employeeView,LoginView login) {
		this.employeeView = employeeView;
		this.login = login;
		System.out.println("Press 1 to check-in");
		System.out.println("Press 2 to check-out");
		System.out.println("Press 3 to see available leaves");
		System.out.println("Press 4 to go back");
		int option = scanner.nextInt();
		attendanceController.decideOptions(option,employee);
	}
	@Override
	public void printStatus(Employee employee, String msg) {
		System.out.println("Employee "+employee.getEmpID() +" "+ msg);
		showAttendanceOptions(employee, employeeView,login);
	}
	@Override
	public void callBackToEmployeeView(Employee employee) {
		employeeView.employeeLogin(employee, login);
	}
}

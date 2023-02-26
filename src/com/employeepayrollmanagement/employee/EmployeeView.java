package com.employeepayrollmanagement.employee;

import java.util.Scanner;

import com.employeepayrollmanagement.applyleave.ApplyLeaveView;
import com.employeepayrollmanagement.attendancemanagement.AttendanceView;
import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.login.LoginView;

public class EmployeeView implements EmployeeViewCallBack{
	private EmployeeControllerCallBack employeeController;
	private LoginView login;
	private Scanner scanner = new Scanner(System.in);
	public EmployeeView() {
		this.employeeController = new EmployeeController(this);
	}
	public void employeeLogin(Employee employee,LoginView login) {
		this.login = login;
		System.out.println("Welcome "+employee.getFirst_name()+" "+employee.getLast_name());
		System.out.println("Press 1 to Apply Leave");
		System.out.println("Press 2 to Attendance");
		System.out.println("Press 3 to see payslip of this month (to be deposited at the end of the month)");
		System.out.println("Press 4 to Log out");
		int option = scanner.nextInt();
		employeeController.decideOption(option,employee);
	}
	@Override
	public void callToApplyLeave(Employee employee) {
		ApplyLeaveView applyLeave = new ApplyLeaveView();
		applyLeave.selectDates(employee,this,this.login);
	}
	@Override
	public void callToAttendance(Employee employee) {
		AttendanceView attendance = new AttendanceView();
		attendance.showAttendanceOptions(employee,this,login);
	}
	@Override
	public void callBackToLogin(String msg) {
		System.out.println(msg);
		login.showLogin();
	}
	@Override
	public void printStatus(Employee employee, String msg) {
		System.out.println(msg);
		employeeLogin(employee, login);
	}
}

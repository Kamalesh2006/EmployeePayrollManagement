package com.employeepayrollmanagement.applyleave;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.employee.EmployeeView;
import com.employeepayrollmanagement.login.LoginView;

public class ApplyLeaveView implements ApplyLeaveViewCallBack{
	private ApplyLeaveControllerCallBack applyLeaveController;
	private LoginView loginView;
	private EmployeeView employeeView;
	private Scanner scanner = new Scanner(System.in);
	public ApplyLeaveView() {
		this.applyLeaveController = new ApplyLeaveController(this);
	}
	public void selectDates(Employee employee,EmployeeView employeeView, LoginView loginView) {
		this.loginView = loginView;
		this.employeeView = employeeView;
		System.out.println("Enter the Date from which you want leave (dd/mm/yyyy)");
		String fromDate = scanner.next();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fDate = LocalDate.parse(fromDate,format);
		System.out.println("Enter the Date upto which you want leave (dd/mm/yyyy)");
		String toDate = scanner.next();
		LocalDate tDate = LocalDate.parse(toDate,format);
		applyLeaveController.checkForAttendance(employee,fDate,tDate);
	}
	@Override
	public void applyLeaveAgain(String error,Employee employee) {
		System.out.println(error);
		selectDates(employee,employeeView,loginView);
	}
	@Override
	public void leaveAppliedSuccessfully(Employee employee,int date) {
		System.out.println("Leave applied Successfully for (working dates) "+ date);
		employeeView.employeeLogin(employee, loginView);
	}
	@Override
	public void leaveAlreadyApplied(Employee employee, String msg) {
		System.out.println(employee.getEmpID()+" "+msg);
		employeeView.employeeLogin(employee, loginView);
	}
}

package com.employeepayrollmanagement;


import com.employeepayrollmanagement.login.LoginView;

public class EmployeePayrollManagement {

	public static void main(String[] args) {
		System.out.println("<--Welcome to Employee Attendance and Payroll Management-->\n");
		LoginView loginView = new LoginView();
		loginView.showLogin();
	}

}

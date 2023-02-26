package com.employeepayrollmanagement.modifysalary;

import java.time.LocalDate;
import java.util.Scanner;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.Salary;
import com.employeepayrollmanagement.humanresource.HRView;
import com.employeepayrollmanagement.login.LoginView;

public class ModifySalaryView implements ModifySalaryViewCallBack {
	private ModifySalaryControllerCallBack modifySalaryController;
	private HRView hrView;
	private LoginView login;
	private Scanner scanner = new Scanner(System.in);

	public ModifySalaryView() {
		this.modifySalaryController = new ModifySalaryController(this);
	}

	// this is the page that can be reached by only the employer
	public void modifySalaryPage(HRView hrView, LoginView login) {
		this.hrView = hrView;
		this.login = login;
		System.out.println("Enter the Employee id");
		String empID = scanner.next();
		modifySalaryController.searchEmployeeByID(empID);
	}

	@Override
	public void printStatus(String msg) {
		System.out.println(msg);
		modifySalaryPage(hrView, login);
	}

	@Override
	public void employeeModifySalary(Employee employee) {
		System.out.println("You have chosen Emp ID:" + employee.getEmpID() + " Emp Name" + employee.getFirst_name()
				+ " " + employee.getLast_name());
		System.out.println("Choose what to do");
		System.out.println("1) To increase salary for " + employee.getEmpID());
		System.out.println("2) To decrease salary for " + employee.getEmpID());
		System.out.println("3) To add incentives for " + employee.getEmpID());
		System.out.println("4) To go back");
		System.out.println("5) To log out");
		int option = scanner.nextInt();
		modifySalaryController.decideEmployeeOption(option, employee);
	}

	@Override
	public void increaseSalary(Employee employee) {
		System.out.println("Employee ID " + employee.getEmpID());
		System.out.println("Input the amount you want to increase (current salary + now entered amount)");
		int salaryToIncrease = scanner.nextInt();

		modifySalaryController.increaseSalary(employee, salaryToIncrease);
	}

	@Override
	public void decreaseSalary(Employee employee) {
		System.out.println("Employee ID " + employee.getEmpID());
		System.out.println("Input the amount you want to decrease (current salary- amount entered)");
		int salaryToDecrease = scanner.nextInt();
		modifySalaryController.decreaseSalary(employee, salaryToDecrease);
	}

	@Override
	public void addIncentive(Employee employee) {
		System.out.println("Enter the incentive to be added for the employee");
		int incentive = scanner.nextInt();
		modifySalaryController.addIncentive(employee, incentive, LocalDate.now().getDayOfMonth(),
				LocalDate.now().getDayOfYear());
	}

	@Override
	public void goBackToHR() {
		hrView.hrloginpage(login);
	}

	@Override
	public void goBackToLogin() {
		System.out.println("Successfully logged out");
		login.showLogin();

	}

	@Override
	public void salaryUpdated(Salary salaryOfEmployee) {
		System.out.println(
				"Employee ID" + salaryOfEmployee.getEmpID() + "\t\tEmployee Name:" + salaryOfEmployee.getFirst_name());
		System.out.println("Updated Salary:" + salaryOfEmployee.getSalaryAnnual());
		employeeModifySalary(salaryOfEmployee);
	}
}

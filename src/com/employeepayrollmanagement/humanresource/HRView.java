package com.employeepayrollmanagement.humanresource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.employeepayrollmanagement.applyleave.ApplyLeaveView;
import com.employeepayrollmanagement.dto.Credentials;
import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.LeaveTracker;
import com.employeepayrollmanagement.dto.PaySlip;
import com.employeepayrollmanagement.login.LoginView;
import com.employeepayrollmanagement.modifysalary.ModifySalaryView;

public class HRView implements HRViewCallBack {
	private Scanner scanner = new Scanner(System.in);
	private LoginView login;
	private HRControllerCallBack hrController;
	private ModifySalaryView modifySalaryView;

	public HRView() {
		hrController = new HRController(this);
	}

	// This is the first page the employer will see after login
	public void hrloginpage(LoginView login) {
		this.login = login;
		System.out.println("Press 1 to see Employees who are checked in");
		System.out.println("Press 2 to see Employees who have applied for leave");
		System.out.println("Press 3 to modify salary for an employee");
		System.out.println("Press 4 to add Employees");
		System.out.println("Press 5 to go back");
		int option = scanner.nextInt();
		hrController.decideOptions(option);
	}

	@Override
	// registering employee
	public void registerEmployee() {
		System.out.println("Welcome to Employee registration:");
		System.out.println("Enter the no of new Employees you want to add");
		int noOfEmployees = scanner.nextInt();
		List<Employee> newEmployeeList = new ArrayList<>();
		int i = 1;
		while (i++ <= noOfEmployees) {
			System.out.println("Enter employee first name");
			String firstname = scanner.next();
			System.out.println("Enter employee last name");
			String lastname = scanner.next();
			System.out.println("Enter The Date of Birth(dd/mm/yyyy) of employee:");
			String dobStr = scanner.next();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dob = LocalDate.parse(dobStr, formatter);
			System.out.println("Enter employee email id:");
			String email = scanner.next();
			LocalDate doj = LocalDate.now();
			System.out.println("Is he/she a employee?(True/False");
			boolean isEmployer = scanner.nextBoolean();
			System.out.println("Assign Holidays for employee");
			int leaveRemaining = scanner.nextInt();
			String empID = hrController.generateEmpID(email, i);
			Employee employee = new Employee(empID, firstname, lastname, dob, email, doj, isEmployer, leaveRemaining);
			newEmployeeList.add(employee);
		}
		hrController.addEmployees(newEmployeeList);
	}

	@Override
	public void printStatus(String msg) {
		System.out.println(msg);
		hrloginpage(login);
	}

	@Override
	// to see all the employee who are checked in
	public void employeeCheckedIn(List<Employee> employeeCheckedInList) {
		System.out.println("Employees Checked In Today:" + LocalDate.now() + " List");
		for (Employee employee : employeeCheckedInList) {
			System.out.println("Employee ID:" + employee.getEmpID() + " Employee Name:" + employee.getFirst_name() + " "
					+ employee.getLast_name());
		}
		hrloginpage(login);
	}

	@Override
	public void showLeaveAppliedEmployees(List<Employee> employeeList, int[] availableHolidays,
			List<LeaveTracker> leaveTrackerList) {
		System.out.println("Showing employees who have applied for leave");

		if (employeeList.size() == 0) {
			System.out.println("No Employees applied for leave");
		} else {
			System.out.println("SNo  Employee id\tEmployee Name\tEmployee Applied days\tAvailable Holidays");
			for (int i = 0; i < employeeList.size(); i++) {
				Employee employee = employeeList.get(i);
				System.out.println((i + 1) + "  " + employee.getEmpID() + "\t" + employee.getFirst_name() + " "
						+ employee.getLast_name() + "\tEmployee Applied days:"+leaveTrackerList.get(i).getNoOfDaysLeaveRequired() +"\t"+ availableHolidays[i]);
			}
			System.out.println("Approve leave?(True/False)");
			boolean leaveApproval = scanner.nextBoolean();
			hrController.decideToApproveLeave(leaveApproval, employeeList, leaveTrackerList);
		}
		hrloginpage(login);
	}

	@Override
	public void statusOfEmployeeList(List<Credentials> addedEmployeeList, List<Employee> notAddedEmployeeList) {
		System.out.println("Showing List of Employees with password added to the database");
		for (Credentials credential : addedEmployeeList) {
			System.out.println("Employee ID: " + credential.getEmpID() + " Employee Name: " + credential.getFirst_name()
					+ " " + credential.getLast_name() + " (Confidential)" + credential.getPassword());
		}
		System.out.println("Showing List of Employees whose email already is in the database:");
		for (Employee employee : notAddedEmployeeList) {
			System.out.println("Email:" + employee.getEmail());
		}
		System.out.println("Do you want to add Employee (True/False)");
		boolean option = scanner.nextBoolean();
		hrController.decideWhereToGo(option);
	}

	@Override
	public void callToLoginView() {
		System.out.println("Successfully Logged Out\n");
		login.showLogin();
	}

	@Override
	public void callToModifySalary() {
		modifySalaryView= new ModifySalaryView();
		modifySalaryView.modifySalaryPage(this, login);
	}

	@Override
	public void leaveApproved(List<PaySlip> paySlipList) {
		for (PaySlip payslip : paySlipList) {
			double monthSalary = ((payslip.getSalary().getBasicPay()) / 12)
					+ ((payslip.getSalary().getAllowance()) / 12) - ((payslip.getSalary().getTax()) / 12)
					- ((payslip.getSalary().getPf()) / 12);
			System.out.println(
					payslip.getSalary().getEmpID() + " Month:" + payslip.getMonth() + " Year:" + payslip.getYear());
			System.out.println("MonthSalary:(incentives & loss of pay excluded)" + (monthSalary) + "\tIncentives:"
					+ payslip.getIncentives());
			System.out.println("Loss of Pay:" + payslip.getLossOfPay());
		}
	}
}

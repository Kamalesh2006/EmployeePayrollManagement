package com.employeepayrollmanagement.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.employeepayrollmanagement.dto.Credentials;
import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.LeaveTracker;
import com.employeepayrollmanagement.dto.PaySlip;
import com.employeepayrollmanagement.dto.Salary;

public class EmployeePayrollDB {
	private static EmployeePayrollDB employeePayrollInstance;
	private List<Credentials> credentialList = new ArrayList<>();
	private List<LeaveTracker> leaveTrackerList = new ArrayList<>();
	private List<Salary> salaryList = new ArrayList<>();
	private List<PaySlip> paySlipList = new ArrayList<>();
	private EmployeePayrollDB() {
		
	}
	public static EmployeePayrollDB getInstance() {
		if(employeePayrollInstance==null) {
			employeePayrollInstance = new EmployeePayrollDB();
			employeePayrollInstance.init();
		}
		return employeePayrollInstance;
	}
	//default initialization for the database
	public void init() {
		Credentials credential1 = new Credentials("ZSGS0143","Kamalesh","K",LocalDate.of(2001, 6, 20),"kamalesh@zsgs.in",LocalDate.of(2023,01,03),false,12,"kamal");
		Credentials credential2 = new Credentials("ZSGS0116","Deepak","M",LocalDate.of(2001, 2, 13),"deepak@zsgs.in",LocalDate.of(2023, 01, 03),false,12,"deepak");
		Credentials credential3 = new Credentials("ZSGS0123","Rajesh","R",LocalDate.of(2000, 1, 9),"rajesh@zsgs.in",LocalDate.of(2023, 01, 03),false,12,"rajesh");
		Credentials credential4 = new Credentials("Zoho300","Ananth","K",LocalDate.of(1990, 07, 28),"ananthk@zoho.in",LocalDate.of(2015, 03, 20),true,15,"ananth");
		credentialList.add(credential1);
		credentialList.add(credential2);
		credentialList.add(credential3);
		credentialList.add(credential4);
		Salary salary1 = new Salary(credential1,560000);
		Salary salary2 = new Salary(credential2,600000);
		Salary salary3 = new Salary(credential3,750000);
		salaryList.add(salary1);
		salaryList.add(salary2);
		salaryList.add(salary3);
		LeaveTracker leaveTracker1 = new LeaveTracker(credential1);
		LeaveTracker leaveTracker2 = new LeaveTracker(credential2);
		LeaveTracker leaveTracker3 = new LeaveTracker(credential3);	
		leaveTrackerList.add(leaveTracker1);
		leaveTrackerList.add(leaveTracker2);
		leaveTrackerList.add(leaveTracker3);
		paySlipList.add(new PaySlip(salary1,0));
		paySlipList.add(new PaySlip(salary2,0));
		paySlipList.add(new PaySlip(salary3,0));
		
	}
	public Employee checkValidEmployee(String emailid, String password) {
		for(Credentials credential: credentialList) {
			if(credential.getEmail().equals(emailid) && credential.getPassword().equals(password)) {
				return credential;
			}
		}
		return null;
	}
	public LeaveTracker getLeaveTracker(Employee employee) {
		for(LeaveTracker leaveTracker: leaveTrackerList) {
			if(leaveTracker.getEmployee()==employee) {
				return leaveTracker;
			}
		}
		return null;
	}
	public List<Employee> getEmployeeCheckedInList() {
		List<Employee> employeeList = new ArrayList<>();
		for(LeaveTracker leaveTracker: leaveTrackerList) {
			if(leaveTracker.isCheckInStatus()) {
				employeeList.add(leaveTracker.getEmployee());
			}
		}
		return employeeList;
	}
	public List<LeaveTracker> getLeaveTrackerList() {
		return leaveTrackerList;
	}
	public Employee getEmployeeByEmail(String email) {
		for(Credentials credential: credentialList) {
			if(credential.getEmail().equals(email)) {
				return credential;
			}
		}
		return null;
	}
	public int getCountOfCredential() {
		return credentialList.size();
	}
	public void addCredentials(Credentials credential) {
		credentialList.add(credential);
	}
	public Employee getEmployeeByEmpID(String empID) {
		for(Credentials credential:credentialList) {
			if(credential.getEmpID().equals(empID)) {
				return credential;
			}
		}
		return null;
	}
	public Salary getSalary(Employee employee) {
		for(Salary salary: salaryList) {
			if(salary.getEmpID().equals(employee.getEmpID())) {
				return salary;
			}
		}
		return null;
	}
	public PaySlip getPaySlip(Employee employee, int month, int year) {
		for(PaySlip paySlip: paySlipList) {
			if(paySlip.getSalary().getEmpID().equals(employee.getEmpID()) && paySlip.getMonth()==month && paySlip.getYear()==year) {
				return paySlip;
			}
		}
		return null;
	}
	public List<PaySlip> getPaySlipList(List<Employee> employeeList) {
		List<PaySlip> paySlip = new ArrayList<>();
		int i =0;
		for(Employee employee: employeeList) {
			//because the payslip contains payslip for every month of employee
			//we want payslip of current month
			for(PaySlip pay: paySlipList) {
				if(pay.getSalary().getEmpID().equals(employee.getEmpID()) && pay.getMonth()==LocalDate.now().getMonthValue()) {
					paySlip.add(pay);
				}
			}
		}
		return paySlip;
	}
}

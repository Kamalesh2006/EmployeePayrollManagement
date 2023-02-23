package com.employeepayrollmanagement.humanresource;

import java.util.List;
import java.util.Random;

import com.employeepayrollmanagement.dto.Credentials;
import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.LeaveTracker;
import com.employeepayrollmanagement.repository.EmployeePayrollDB;

public class HRModel implements HRModelCallBack {
	private HRModelControllerCallBack hrController;
	private EmployeePayrollDB employeePayrollDB = EmployeePayrollDB.getInstance();
	public HRModel(HRController hrController) {
		this.hrController = hrController;
	}
	@Override
	public void getCheckedInEmployees() {
		List<Employee> employeeCheckedInList = employeePayrollDB.getEmployeeCheckedInList();
		if(employeeCheckedInList==null) {
			hrController.noEmployeeCheckedIn();
			return;
		}
		hrController.employeeCheckedIn(employeeCheckedInList);
	}
	@Override
	public void getLeaveAppliedEmployees() {
		List<LeaveTracker> leaveTrackerList = employeePayrollDB.getLeaveTrackerList();
		hrController.leaveAppliedEmployees(leaveTrackerList);
	}
	@Override
	public boolean isEmailNew(String email) {
		Employee employee = employeePayrollDB.getEmployeeByEmail(email);
		if(employee==null) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public int getTotalEmployeesCount() {
		return employeePayrollDB.getCountOfCredential();
	}
	@Override
	public Credentials addEmployeeToDB(Employee employee) {
		//we have to set password first
		//generating random password
		Random r = new Random();
		String combination = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"abcdefghijklmnopqrstuvwxyz"+"1234567890"+"@#$%";
		//assuming my password size has to be 8
		char[] password = new char[8];
		for(int i =0;i<8;i++) {
			password[i]=combination.charAt(r.nextInt(combination.length()));
		}
		Credentials credential = new Credentials(employee,new String(password));
		employeePayrollDB.addCredentials(credential);
		return credential;
	}

}

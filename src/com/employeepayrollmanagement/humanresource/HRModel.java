package com.employeepayrollmanagement.humanresource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.employeepayrollmanagement.dto.Credentials;
import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.LeaveTracker;
import com.employeepayrollmanagement.dto.PaySlip;
import com.employeepayrollmanagement.dto.Salary;
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
		List<LeaveTracker> employeeLeaveAppliedList = new ArrayList<>();
		//checking the status of the employees who have applied for leave
		for(LeaveTracker leave: leaveTrackerList) {
			if(leave.isLeaveApplyStatus()) {
				employeeLeaveAppliedList.add(leave);
			}
		}
		hrController.leaveAppliedEmployees(employeeLeaveAppliedList);
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
	@Override
	public void approveLeave(List<Employee> employeeList, List<LeaveTracker> leaveTrackerList) {
		List<PaySlip> paySlipList = employeePayrollDB.getPaySlipList(employeeList); 
		for(int i =0;i<employeeList.size();i++) {
			Salary salary = employeePayrollDB.getSalary(employeeList.get(i));
			//we have to check the no of leave available for the employee
			int lossOfPayDays =0;
			System.out.println("Loss of pay days "+leaveTrackerList.get(i).getNoOfDaysLeaveRequired());
			System.out.println("Leave remaininig"+salary.getLeaveRemaining());
			if(leaveTrackerList.get(i).getNoOfDaysLeaveRequired()>salary.getLeaveRemaining()) {
				lossOfPayDays = Math.abs(salary.getLeaveRemaining()-leaveTrackerList.get(i).getNoOfDaysLeaveRequired());
				leaveTrackerList.get(i).setLeaveApplyStatus(true);
				salary.setLeaveRemaining(0);
				
			}else {
				salary.setLeaveRemaining(salary.getLeaveRemaining()-leaveTrackerList.get(i).getNoOfDaysLeaveRequired());
			}
			//reducing the monthly salary based on the leave taken by the employee
			paySlipList.get(i).setLossOfPay(lossOfPayDays*salary.getSalaryAnnual()/365);
			
			//after setting the loss of pay days we have to reset the leave tracker to default so that the employee
			//can apply for leave once again
			leaveTrackerList.get(i).setLeaveApplyStatus(false);
			leaveTrackerList.get(i).setNoOfDaysLeaveRequired(0);
		}
		hrController.paySlipListEmployees(paySlipList);
	}

}

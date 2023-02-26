package com.employeepayrollmanagement.humanresource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.employeepayrollmanagement.dto.Credentials;
import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.LeaveTracker;
import com.employeepayrollmanagement.dto.PaySlip;

public class HRController implements HRControllerCallBack, HRModelControllerCallBack {
	private HRViewCallBack hrView;
	private HRModelCallBack hrModel;
	public HRController(HRView hrView) {
		this.hrView = hrView;
		hrModel = new HRModel(this);
	}
	@Override
	public void decideOptions(int option) {
		switch(option) {
		case 1:
			hrModel.getCheckedInEmployees();
			break;
		case 2:
			hrModel.getLeaveAppliedEmployees();
			break;
		case 3:
			hrView.callToModifySalary();
			break;
		case 4:
			hrView.registerEmployee();
			break;
		case 5:
			hrView.callToLoginView();
			break;
		}
	}
	@Override
	public void noEmployeeCheckedIn() {
		hrView.printStatus("No employee Checked in on "+LocalDate.now());
	}
	@Override
	public void employeeCheckedIn(List<Employee> employeeCheckedInList) {
		hrView.employeeCheckedIn(employeeCheckedInList);
	}
	@Override
	public void leaveAppliedEmployees(List<LeaveTracker> leaveTrackerList) {
		//calculating how much days the employees applied for leave
		int[] availableHolidays = new int[leaveTrackerList.size()];
		/*Employees list who have applied for leave*/
		List<Employee> employeeList = new ArrayList<>();
		int i=0;
		for(LeaveTracker leaveTracker: leaveTrackerList) {
			availableHolidays[i++] = leaveTracker.getEmployee().getLeaveRemaining();
			employeeList.add(leaveTracker.getEmployee());
		}
		hrView.showLeaveAppliedEmployees(employeeList,availableHolidays,leaveTrackerList);
		
	}
	// this method will generate employee id for new email id but for email that already exist in the database it will return null
	@Override
	public String generateEmpID(String email,int count) {
		if(hrModel.isEmailNew(email)) {
			//generating emp id based on coorporation name and no of employees available in the database
			int employeeCount = hrModel.getTotalEmployeesCount()+count;
			//for now the organization is zoho
			return "ZOHO"+employeeCount;
			
		}else {
			//email id already exist in the database
			return null;
		}
	}
	@Override
	public void addEmployees(List<Employee> newEmployeeList) {
		List<Employee> notAddedEmployeeList = new ArrayList<>();
		List<Credentials> addedEmployeeList = new ArrayList<>();
		for(int i =0;i<newEmployeeList.size();i++) {
			// checking if the generateID method created new employee id
			if(newEmployeeList.get(i).getEmail()==null) {
				notAddedEmployeeList.add(newEmployeeList.get(i));
			}
			else {
				addedEmployeeList.add(hrModel.addEmployeeToDB(newEmployeeList.get(i)));				
			}
		}
		hrView.statusOfEmployeeList(addedEmployeeList,notAddedEmployeeList);
	}
	@Override
	public void decideWhereToGo(boolean option) {
		if(option) {
			hrView.registerEmployee();
			return;
		}
		else {
			hrView.printStatus("Employees added successfully to the database");
		}
	}
	@Override
	public void decideToApproveLeave(boolean leaveApproval, List<Employee> employeeList, List<LeaveTracker> leaveTrackerList) {
		if(leaveApproval) {
			//leave approval is granted
			hrModel.approveLeave(employeeList,leaveTrackerList);
		}else {
			//leave approval is failed
			hrView.printStatus("Leave has not been granted");
		}
	}
	@Override
	public void paySlipListEmployees(List<PaySlip> paySlipList) {
		//this contains the updated employees paylist for those who have applied for leave
		hrView.leaveApproved(paySlipList);
	}
	
	

}

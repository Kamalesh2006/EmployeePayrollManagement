package com.employeepayrollmanagement.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LeaveTracker {
	private Employee employee;
	private List<LocalDate> workingDays;
	private double noOfDaysWorked;
	private boolean checkInStatus;
	private boolean leaveApplyStatus;
	private int noOfDaysLeaveRequired;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public List<LocalDate> getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(List<LocalDate> workingDays) {
		this.workingDays = workingDays;
	}
	public boolean isCheckInStatus() {
		return checkInStatus;
	}
	public void setCheckInStatus(boolean checkInStatus) {
		this.checkInStatus = checkInStatus;
	}
	public LocalTime getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(LocalTime checkInTime) {
		this.checkInTime = checkInTime;
	}
	private LocalTime checkInTime;
	public LeaveTracker(Employee employee) {
		super();
		this.employee = employee;
		this.workingDays =new ArrayList<>();
		this.noOfDaysWorked = 0;
		this.checkInStatus = false;
		this.checkInTime = LocalTime.now();
		this.leaveApplyStatus = false;
		this.setNoOfDaysLeaveRequired(0);
	}
	public double getNoOfDaysWorked() {
		return noOfDaysWorked;
	}
	public void setNoOfDaysWorked(double noOfDaysWorked) {
		this.noOfDaysWorked = noOfDaysWorked;
	}
	public boolean isLeaveApplyStatus() {
		return leaveApplyStatus;
	}
	public void setLeaveApplyStatus(boolean leaveApplyStatus) {
		this.leaveApplyStatus = leaveApplyStatus;
	}
	public int getNoOfDaysLeaveRequired() {
		return noOfDaysLeaveRequired;
	}
	public void setNoOfDaysLeaveRequired(int noOfDaysLeaveRequired) {
		this.noOfDaysLeaveRequired = noOfDaysLeaveRequired;
	}
	
}

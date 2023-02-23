package com.employeepayrollmanagement.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class LeaveTracker {
	private Employee employee;
	private List<LocalDate> workingDays;
	private double noOfDaysWorked;
	private boolean checkInStatus;
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
	public LeaveTracker(Employee employee, List<LocalDate> workingDays, double noOfDaysWorked,boolean checkInStatus, LocalTime checkInTime) {
		super();
		this.employee = employee;
		this.workingDays = workingDays;
		this.noOfDaysWorked = noOfDaysWorked;
		this.checkInStatus = checkInStatus;
		this.checkInTime = checkInTime;
	}
	public double getNoOfDaysWorked() {
		return noOfDaysWorked;
	}
	public void setNoOfDaysWorked(double noOfDaysWorked) {
		this.noOfDaysWorked = noOfDaysWorked;
	}
	
}

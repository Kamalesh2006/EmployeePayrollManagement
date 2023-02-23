package com.employeepayrollmanagement.dto;

import java.time.LocalDate;

public class Employee {
	private String empID;
	private String first_name;
	private String last_name;
	private LocalDate dob;
	private String email;
	private LocalDate doj;
	private boolean isEmployer;
	private int leaveRemaining;

	public Employee(String empID,String first_name, String last_name, LocalDate dob, String email, LocalDate doj,boolean isEmployer,int leaveRemaining) {
		super();
		this.empID = empID;
		this.first_name = first_name;
		this.last_name = last_name;
		this.dob = dob;
		this.email = email;
		this.doj = doj;
		this.setEmployer(isEmployer);
		this.setLeaveRemaining(leaveRemaining);
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}
	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public boolean isEmployer() {
		return isEmployer;
	}

	public void setEmployer(boolean isEmployer) {
		this.isEmployer = isEmployer;
	}

	public int getLeaveRemaining() {
		return leaveRemaining;
	}

	public void setLeaveRemaining(int leaveRemaining) {
		this.leaveRemaining = leaveRemaining;
	}
}

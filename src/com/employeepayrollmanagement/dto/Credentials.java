package com.employeepayrollmanagement.dto;

import java.time.LocalDate;

public class Credentials extends Employee {
	private String password;
	public Credentials(Employee emp,String password) {
		super(emp.getEmpID(),emp.getFirst_name(),emp.getLast_name(),emp.getDob(),emp.getEmail(),emp.getDoj(),emp.isEmployer(),emp.getLeaveRemaining());
		this.password = password;
	}
	public Credentials(String empID,String first_name, String last_name, LocalDate dob, String email, LocalDate doj,boolean isEmployer,int attendance,String password) {
		super(empID,first_name,last_name,dob,email,doj,isEmployer,attendance);
		this.password= password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

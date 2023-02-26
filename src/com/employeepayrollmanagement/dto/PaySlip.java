package com.employeepayrollmanagement.dto;

import java.time.LocalDate;

public class PaySlip {
	private int month;
	private int year;
	private Salary salary;
	private int incentives;
	private double lossOfPay;
	public PaySlip(Salary salaryEmployee,int incentives) {
		super();
		this.salary = salaryEmployee;
		this.month = LocalDate.now().getMonthValue();
		this.setYear(LocalDate.now().getYear());
		this.incentives = incentives;
		this.setLossOfPay(0);
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	
	public Salary getSalary() {
		return salary;
	}
	public void setSalary(Salary salary) {
		this.salary = salary;
	}
	public int getIncentives() {
		return incentives;
	}
	public void setIncentives(int incentives) {
		this.incentives = incentives;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getLossOfPay() {
		return lossOfPay;
	}
	public void setLossOfPay(double lossOfPay) {
		this.lossOfPay = lossOfPay;
	}
}

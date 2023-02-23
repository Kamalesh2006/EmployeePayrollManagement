package com.employeepayrollmanagement.dto;

public class Salary {
	private Employee employee;
	//contains the salary of the employee with tax and benefits 
	private double basicPay;
	private int month;
	private double tax;
	private double allowance;
	private double incentives;
	private double pf;
	
	public Salary(Employee employee, double basicPay,int month, double allowance,double incentives, double pf) {
		super();
		this.employee = employee;
		this.basicPay = basicPay;
		this.setMonth(month);
		//tax is calculated based on if salary is above 7LPA(58333 per month), if not then no tax is deducted 
		this.tax = basicPay>59000?basicPay*0.12:0;
		this.allowance = allowance;
		this.setIncentives(incentives);
		this.pf = pf;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public double getBasicPay() {
		return basicPay;
	}
	public void setBasicPay(double basicPay) {
		this.basicPay = basicPay;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getAllowance() {
		return allowance;
	}
	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}
	public double getPf() {
		return pf;
	}
	public void setPf(double pf) {
		this.pf = pf;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public double getIncentives() {
		return incentives;
	}

	public void setIncentives(double incentives) {
		this.incentives = incentives;
	}
}

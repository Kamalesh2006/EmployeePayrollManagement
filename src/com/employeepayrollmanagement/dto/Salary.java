package com.employeepayrollmanagement.dto;

public class Salary extends Employee{
	//contains the salary of the employee with tax and benefits 
	private double basicPay;
	private double tax;
	private double allowance;
	private double pf;
	private double salaryAnnual;
	
	public Salary(Employee employee,double salaryAnnual) {
		super(employee.getEmpID(),employee.getFirst_name(),employee.getLast_name(),employee.getDob(),employee.getEmail(),employee.getDoj(),employee.isEmployer(),employee.getLeaveRemaining());
		this.setSalaryAnnual(salaryAnnual);
		
		this.basicPay = salaryAnnual*0.4;
		//tax is calculated based on if salary is above 7LPA(58333 per month), if not then no tax is deducted 
		this.tax = salaryAnnual>=700000?salaryAnnual*0.12:0;
		this.allowance = salaryAnnual*0.2;
		this.pf = salaryAnnual*0.12;
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

	public double getSalaryAnnual() {
		return salaryAnnual;
	}

	public void setSalaryAnnual(double salaryAnnual) {
		this.salaryAnnual = salaryAnnual;
	}
}

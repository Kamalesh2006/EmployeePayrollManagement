package com.employeepayrollmanagement.employee;

import java.time.LocalDate;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.Salary;

public class EmployeeController implements EmployeeControllerCallBack, EmployeeModelControllerCallBack {
	private EmployeeViewCallBack employeeView; 
	private EmployeeModelCallBack employeeModel;
	EmployeeController(EmployeeView employeeView) {
		this.employeeView = employeeView;
		employeeModel = new EmployeeModel(this);
	}
	@Override
	public void decideOption(int option,Employee employee) {
		switch(option) {
		case 1: 
			employeeView.callToApplyLeave(employee);
			break;
		case 2:
			employeeView.callToAttendance(employee);
			break;
		case 3:
			employeeModel.getSalary(employee,LocalDate.now().getMonthValue());
			break;
		case 4:
			employeeView.callBackToLogin("Thank you for using our application. "+employee.getEmpID()+" is successfully logged out");
		}
	}
	@Override
	public void notifyError(Employee employee, String msg) {
		employeeView.printStatus(employee,msg);
	}
	@Override
	public void salaryOfEmployee(Employee employee, Salary salary) {
		double basicPay = salary.getBasicPay();
		double allowance = salary.getAllowance();
		double incentives = salary.getIncentives();
		double pf = salary.getPf();
		double tax = salary.getTax();
		
		String msg = "Date: "+LocalDate.now()+"\t\tEmpID: "+employee.getEmpID()+"\nBasic pay:"+basicPay+"\t\tPF Deductions:"+pf;
		msg = msg+"\nIncentives: "+incentives+"\t\tTax:"+tax+"\t\tAllowance: "+allowance;
		msg += "\nGross Earning: "+(basicPay+allowance)+"\t\tNet Pay:"+(basicPay+allowance+incentives-pf-tax);
		employeeView.printStatus(employee, msg);
	}
}

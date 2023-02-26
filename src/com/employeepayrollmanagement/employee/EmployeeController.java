package com.employeepayrollmanagement.employee;

import java.time.LocalDate;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.PaySlip;
import com.employeepayrollmanagement.dto.Salary;

public class EmployeeController implements EmployeeControllerCallBack, EmployeeModelControllerCallBack {
	private EmployeeViewCallBack employeeView;
	private EmployeeModelCallBack employeeModel;

	EmployeeController(EmployeeView employeeView) {
		this.employeeView = employeeView;
		employeeModel = new EmployeeModel(this);
	}

	@Override
	public void decideOption(int option, Employee employee) {
		switch (option) {
		case 1:
			// callingn apply leave module
			employeeView.callToApplyLeave(employee);
			break;
		case 2:
			// call to attendance module
			employeeView.callToAttendance(employee);
			break;
		case 3:
			// to get pay slip of the current working month
			employeeModel.getPaySlip(employee, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
			break;
		case 4:
			// logging out
			employeeView.callBackToLogin(
					"Thank you for using our application. " + employee.getEmpID() + " is successfully logged out");
		}
	}

	@Override
	public void notifyError(Employee employee, String msg) {
		employeeView.printStatus(employee, msg);
	}

	@Override
	public void paySlipOfEmployee(Employee employee, PaySlip paySlip) {
		double ctc = paySlip.getSalary().getSalaryAnnual();
		double monthlySalary = paySlip.getSalary().getBasicPay() / 12;
		double allowance = paySlip.getSalary().getAllowance() / 12;
		double pf = paySlip.getSalary().getPf() / 12;
		double tax = paySlip.getSalary().getTax() / 12;
		int incentives = paySlip.getIncentives();
		double lossOfPay = paySlip.getLossOfPay();

		// converting the employee pojo to UI readable format

		String msg = "Date: " + LocalDate.now() + "\t\tEmpID: " + employee.getEmpID() + "\nCTC:" + ctc;
		msg = msg + "\nBasic pay:" + monthlySalary + "\t\tDeduction PF:" + pf;
		msg = msg + "\nAllowance: " + allowance + "\tTax:" + tax + "\tLoss of Pay:" + lossOfPay;
		msg += "\nGross Earning: " + (monthlySalary + allowance) + "\t\tNet Pay:"
				+ (monthlySalary + allowance + incentives - pf - tax - lossOfPay);
		employeeView.printStatus(employee, msg);
	}
}

package com.employeepayrollmanagement.modifysalary;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.PaySlip;
import com.employeepayrollmanagement.dto.Salary;
import com.employeepayrollmanagement.repository.EmployeePayrollDB;

public class ModifySalaryModel implements ModifySalaryModelCallBack {
	private ModifySalaryModelControllerCallBack modifySalaryController;
	private EmployeePayrollDB employeePayrollInstance = EmployeePayrollDB.getInstance();
	public ModifySalaryModel(ModifySalaryController modifySalaryController) {
		this.modifySalaryController = modifySalaryController;
	}
	@Override
	public void getEmployee(String empID) {
		Employee employee = employeePayrollInstance.getEmployeeByEmpID(empID);
		modifySalaryController.obtainedEmployee(employee);
	}
	@Override
	public void increaseSalary(Employee employee, int salaryToIncrease) {
		Salary salaryOfEmployee = employeePayrollInstance.getSalary(employee);
		if(salaryOfEmployee==null) {
			modifySalaryController.error("Salary doesn't exist for this employee"+employee.getEmpID());
		}else {
			double salaryAnnual = salaryOfEmployee.getSalaryAnnual()+salaryToIncrease;
			salaryOfEmployee.setSalaryAnnual(salaryAnnual);
			salaryOfEmployee.setBasicPay(salaryAnnual*0.4);
			salaryOfEmployee.setAllowance(salaryAnnual*0.2);
			salaryOfEmployee.setPf(salaryAnnual*0.12);
			salaryOfEmployee.setTax(salaryAnnual>700000?salaryAnnual*0.12:0);
			modifySalaryController.salaryUpdatedSuccessfully(salaryOfEmployee);
		}
	}
	@Override
	public void decreaseSalary(Employee employee, int salaryToDecrease) {
		Salary salary= employeePayrollInstance.getSalary(employee);
		if(salary ==null) {
			modifySalaryController.error("Salary doesn't exist for this employee "+employee.getEmpID());
		}else {
			double salaryAnnual = salary.getSalaryAnnual();
			salary.setSalaryAnnual(salaryAnnual);
			salary.setBasicPay(salaryAnnual*0.4);
			salary.setAllowance(salaryAnnual*0.2);
			salary.setPf(salaryAnnual*0.12);
			salary.setTax(salaryAnnual>700000?salaryAnnual*0.12:0);
			modifySalaryController.salaryUpdatedSuccessfully(salary);
		}
	}
	@Override
	public void addIncentive(Employee employee, int incentive, int month, int year) {
		PaySlip paySlip = employeePayrollInstance.getPaySlip(employee, month, year);
		paySlip.setIncentives(incentive);
		modifySalaryController.incentiveAddedSuccessfully(paySlip);
				
	}

}

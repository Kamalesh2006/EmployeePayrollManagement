package com.employeepayrollmanagement.modifysalary;

import com.employeepayrollmanagement.dto.Employee;
import com.employeepayrollmanagement.dto.PaySlip;
import com.employeepayrollmanagement.dto.Salary;

public class ModifySalaryController implements ModifySalaryControllerCallBack, ModifySalaryModelControllerCallBack {
	private ModifySalaryViewCallBack modifySalaryView;
	private ModifySalaryModelCallBack modifySalaryModel;
	public ModifySalaryController(ModifySalaryView modifySalaryView) {
		this.modifySalaryView = modifySalaryView;
		this.modifySalaryModel = new ModifySalaryModel(this);
	}
	@Override
	public void searchEmployeeByID(String empID) {
		//to check if there is an employee with the empid in the database
		modifySalaryModel.getEmployee(empID);
	}
	@Override
	public void obtainedEmployee(Employee employee) {
		if(employee==null) {
			modifySalaryView.printStatus("You have entered a wrong emp ID");
		}else {
			modifySalaryView.employeeModifySalary(employee);
		}
	}
	@Override
	public void decideEmployeeOption(int option, Employee employee) {
		switch(option) {
		case 1:
			modifySalaryView.increaseSalary(employee);
			break;
		case 2:
			modifySalaryView.decreaseSalary(employee);
			break;
		case 3:
			modifySalaryView.addIncentive(employee);
			break;
		case 4:
			modifySalaryView.goBackToHR();
			break;
		case 5:
			modifySalaryView.goBackToLogin();
			break;
		}
	}
	@Override
	public void increaseSalary(Employee employee, int salaryToIncrease) {
		modifySalaryModel.increaseSalary(employee,salaryToIncrease);
	}
	@Override
	public void error(String string) {
		modifySalaryView.printStatus(string);
	}
	@Override
	public void salaryUpdatedSuccessfully(Salary salaryOfEmployee) {
		modifySalaryView.salaryUpdated(salaryOfEmployee);
	}
	@Override
	public void decreaseSalary(Employee employee, int salaryToDecrease) {
		modifySalaryModel.decreaseSalary(employee,salaryToDecrease);
	}
	@Override
	public void addIncentive(Employee employee, int incentive,int month, int year) {
		modifySalaryModel.addIncentive(employee,incentive,month, year);
	}
	@Override
	public void incentiveAddedSuccessfully(PaySlip paySlip) {
		modifySalaryView.printStatus("Incentives added successfully for "+paySlip.getSalary().getEmpID());
	}

}

package com.employeepayrollmanagement.humanresource;

import java.util.List;

import com.employeepayrollmanagement.dto.Employee;

public interface HRControllerCallBack {

	void decideOptions(int option);

	String generateEmpID(String email, int count);

	void addEmployees(List<Employee> newEmployeeList);

	void decideWhereToGo(boolean option);

}

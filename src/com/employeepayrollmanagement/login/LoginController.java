package com.employeepayrollmanagement.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.employeepayrollmanagement.dto.Employee;

public class LoginController implements LoginControllerCallBack, LoginModelControllerCallBack {
	private LoginModelCallBack loginModel;
	private LoginViewCallBack loginView;
	public LoginController(LoginView loginView) {
		this.loginModel = new LoginModel(this);
		this.loginView = loginView;
	}
	@Override
	public void verifyUser(String emailid, String password) {
		Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9]*@[a-zA-Z0-9]+[.][in]");
		Matcher m = p.matcher(emailid);
		//password validation shall be added
//		Pattern pswd = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
//		Matcher mPswd = p.matcher(password);
		if(m.find()) {
			loginModel.fetchUser(emailid,password);
		}
		else {
			loginView.inputagain("Please enter email ID in correct format:");
		}
	}
	@Override
	public void loginSuccess(Employee employee) {
		loginView.loginSuccess(employee);
	}
	@Override
	public void loginFailer() {
		loginView.inputagain("Input valid user credentials");
	}
	@Override
	public void employerLoginSuccess(Employee employee) {
		loginView.employerLoginSuccess(employee);
	}

}

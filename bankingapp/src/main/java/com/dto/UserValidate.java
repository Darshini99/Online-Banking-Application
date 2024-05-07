package com.dto;

import java.util.Scanner;
import java.util.regex.Pattern;

public class UserValidate {
	static Scanner s = new Scanner(System.in);
	public boolean validateAadhar(long aadhar) {
		String l = String.valueOf(aadhar);
		boolean pt = Pattern.matches("[0-9]{12}",l);
		if(pt) return true;
		else return false;
	}
	public boolean  validatePhone(long number) {
		String l = String.valueOf(number);
		boolean pt = Pattern.matches("[6789][0-9]{9}",l);
		if(pt) return true;
		else return false;
	}
	public String setAccountType(int age) {
		String account_type="";
		if(age>=18 && age<=100) {
			 account_type="major";
		}
		if(age>12 &age<18){
			account_type ="minor";
		}
		return account_type;
	}
}

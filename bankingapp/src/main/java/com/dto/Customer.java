package com.dto;

import java.time.LocalDateTime;

public class Customer{
	String first_name;
	String last_name;
	int age;
	String account_type;
	String gender;
	long aadhar;
	long number;
	String address;
	long account_number;
	double totalBalance;
	
	LocalDateTime lt = LocalDateTime.now();
	String accountCreatedDate = lt.getDayOfMonth()+"-"+lt.getMonthValue()+"-"+lt.getYear()+"  "+lt.getHour()+":"+lt.getMinute()+":"+lt.getSecond();
	
	public String getAccountCreatedDate() {
		return accountCreatedDate;
	}
	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public Customer() {
		super();
	}
	
	
	public Customer(String first_name, String last_name, int age, String account_type, String gender, long aadhar,
			long number, String address, long account_number, double totalBalance) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.account_type = account_type;
		this.gender = gender;
		this.aadhar = aadhar;
		this.number = number;
		this.address = address;
		this.account_number = account_number;
		this.totalBalance = totalBalance;
	}

	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getAadhar() {
		return aadhar;
	}
	public void setAadhar(long aadhar) {
		this.aadhar = aadhar;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getAccount_number() {
		return account_number;
	}
	public void setAccount_number(long account_number) {
		this.account_number = account_number;
	}

	@Override
	public String toString() {
		return "Customer [first_name=" + first_name + ", last_name=" + last_name + ", age=" + age + ", account_type="
				+ account_type + ", gender=" + gender + ", aadhar=" + aadhar + ", number=" + number + ", address="
				+ address + ", account_number=" + account_number + "]";
	}         
}

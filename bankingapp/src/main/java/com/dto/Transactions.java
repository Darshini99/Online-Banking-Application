package com.dto;

public class Transactions {
	double amount;
	double total_balance ;
	long accountNumber;

	//parameterized constructor
	public Transactions() {}

	//All getters and setters
	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTotal_balance() {
		return total_balance;
	}

	public void setTotal_balance(double total_balance) {
		this.total_balance = total_balance;
	}
	
//parameterized constructor
	public Transactions(double amount, double total_balance, long accountNumber) {
		super();
		this.amount = amount;
		this.total_balance = total_balance;
		this.accountNumber = accountNumber;
	}

}

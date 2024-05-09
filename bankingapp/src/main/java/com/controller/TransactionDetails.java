package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.dao.CustomerDAO;
import com.dao.TransactionDAO;
import com.dto.Customer;
import com.dto.Transactions;

public class TransactionDetails {
	static Scanner s = new Scanner(System.in);
	static String url = "jdbc:mysql://localhost:3306/bankapplication";
	static String username ="root";
	static String password = "root";
	
	//Functionality for depositing amount
	public static void depositAmount(long account_number) throws ClassNotFoundException, SQLException {
		Transactions t = new Transactions();
		Customer c = new Customer();
		System.out.println("Enter amount to deposit: ");
		double amount = s.nextDouble();
	    double total =CustomerDAO.customerTotalBalance(account_number);
		t.setAccountNumber(account_number);
		t.setAmount(amount);
		if(t.getAmount()>=500) {
			total = total +amount;
			t.setTotal_balance(total);
			System.out.println(TransactionDAO.depositMoney(t));
			c.setTotalBalance(total);
			System.out.println("Balance after deposit : "+total);
		}else {
			System.out.println("Insufficient balance to deposit Please deposit amount more than 500");
		}
	}
	
	//Functionality for withdraw amount
	public static void withdrawAmount(long account_number) throws ClassNotFoundException, SQLException {
		Transactions t = new Transactions();
		System.out.println("Enter Amount to Withdraw: ");
		double total = CustomerDAO.customerTotalBalance(account_number);
		double amount = s.nextDouble();
		t.setAccountNumber(account_number);
		t.setAmount(amount);
		if(total>t.getAmount()) {
			total = total - t.getAmount();
			t.setTotal_balance(total);
			System.out.println(TransactionDAO.withdrawMoney(t));
			System.out.println("Balance after Withdrwal is: "+total);
		}else {
			System.out.println("Insufficient balance to Withdraw not possible");
		}
	}
	
	//To see all the transactions(history) of a customer
	public static void allMyTransactions(long accountNumber) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, password);
		System.out.println("Your transactions are : ");
		PreparedStatement ps1 = con.prepareStatement("select * from deposit where accountNumber = ?");
		//Accessing Deposit details
		ps1.setLong(1, accountNumber);
		System.out.println("DepositId\t" + "DepositMoney\t"+"TotalBalance\t"+"\tDate\t");
		System.out.println("-------------------------------------------------------------------------");
		ResultSet rs1 = ps1.executeQuery();
		while(rs1.next()) {
			System.out.println(rs1.getInt("did")+"\t\t"+rs1.getDouble("depositMoney")+"\t\t"+rs1.getDouble("totalBalance")+"\t\t"+rs1.getString("date"));
		}
		System.out.println("--------------------------------------------------------------------------");
		//Accesssing withdraw details
		PreparedStatement ps2 = con.prepareStatement("select Wid,withdrawMoney,totalBalance,date from withdraw where accountNumber = ?");
		ps2.setLong(1, accountNumber);
		System.out.println();
		System.out.println("WithdrawId\t" + "WithdrawMoney\t"+"TotalBalance\t"+"\tDate\t");
		System.out.println("---------------------------------------------------------------------------");
		ResultSet rs2 =ps2.executeQuery();
		while(rs2.next()){
			System.out.println(rs2.getInt("Wid")+"\t\t"+rs2.getDouble("withdrawMoney")+"\t\t"+rs2.getDouble("totalBalance")+"\t\t"+rs2.getString("date"));
		}
		System.out.println("---------------------------------------------------------------------------");
	}
	
	//To delete deposit details of a customer from database
	public static boolean depositTableUpdate(long accountNumber) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, password);
		PreparedStatement ps = con.prepareStatement("delete from deposit where accountNumber=?");
		ps.setLong(1, accountNumber);
		boolean rowseffected =ps.execute();
		return rowseffected;
	}
	
	//To delete withdraw details of a customer from database
	public static boolean withdrawTableUpdate(long accountNumber) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, password);
		PreparedStatement ps = con.prepareStatement("delete from withdraw where accountNumber=?");
		ps.setLong(1, accountNumber);
		boolean rowseffected = ps.execute();
		return rowseffected;
	}
	
	//To delete/close customers account from database
	public static boolean closeMyAccount(long accountNumber) throws ClassNotFoundException, SQLException  {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, password);
		PreparedStatement ps = con.prepareStatement("delete from customer where accountNumber=?");
		ps.setLong(1, accountNumber);
		boolean rowseffected =ps.execute();
		return rowseffected;
	}
}

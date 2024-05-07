package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.controller.CustomerDetails;
import com.dto.Customer;

public class CustomerDAO {
	static String url = "jdbc:mysql://localhost:3306/bankapplication";
	static String username ="root";
	static String password = "root";
	
	public static String createAccount(Customer customer) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement ps = con.prepareStatement("insert into Customer values(?,?,?,?,?,?,?,?,?,?,?)");
		ps.setLong(1,customer.getAccount_number());
		ps.setString(2,customer.getAccount_type());
		ps.setString(3,customer.getFirst_name());
		ps.setString(4,customer.getLast_name());
		ps.setInt(5,customer.getAge());
		ps.setLong(6,customer.getAadhar());
		ps.setLong(7,customer.getNumber());
		ps.setString(8,customer.getGender());
		ps.setString(9,customer.getAddress());
		ps.setDouble(10,customer.getTotalBalance());
		ps.setString(11,customer.getAccountCreatedDate());
		ps.execute();
		con.close();
		return "created succesfully";
	}
	
	public static void ValidateAccount(Customer customer) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		try {
		PreparedStatement ps = con.prepareStatement("select * from customer where accountNumber=?");
		ps.setLong(1,customer.getAccount_number());
		ResultSet rs = ps.executeQuery();
		if(rs!=null && rs.next()) {
				CustomerDetails.TransactionOpeartions(rs.getLong(1));
		}else {
			System.out.println("Dear Customer,No matched acoounts found Please check your account Number!!!");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateCustomerBalance(double amount,long number) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		try {
		PreparedStatement ps = con.prepareStatement("update customer set totalBalance=? where accountNumber=?");
		ps.setDouble(1, amount);
		ps.setLong(2,number);
		ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateDepositBalance(double amount,long number) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		try {
		PreparedStatement ps = con.prepareStatement("update deposit set totalBalance=? where accountNumber=?");
		ps.setDouble(1, amount);
		ps.setLong(2,number);
		ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateWithdrawBalance(double amount,long number) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		try {
		PreparedStatement ps = con.prepareStatement("update withdraw set totalBalance=? where accountNumber=?");
		ps.setDouble(1, amount);
		ps.setLong(2,number);
		ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static double customerTotalBalance(long number) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		double balance=0;
		try {
		PreparedStatement ps = con.prepareStatement("select * from customer where accountNumber=?");
		ps.setLong(1,number);
		ResultSet rs = ps.executeQuery();
		if(rs!=null && rs.next()) {
			balance = rs.getDouble("totalBalance");}
	    }catch(Exception e) {e.printStackTrace();}
		return balance;
	}
}

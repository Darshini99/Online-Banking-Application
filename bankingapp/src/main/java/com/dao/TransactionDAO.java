package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;

import com.dto.Transactions;

public class TransactionDAO {
	static String url = "jdbc:mysql://localhost:3306/bankapplication";
	static String username ="root";
	static String password = "root";
	
	public static String depositMoney(Transactions t) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		LocalDateTime lt = LocalDateTime.now();
		String current_datetime = lt.getDayOfMonth()+"-"+lt.getMonthValue()+"-"+lt.getYear()+"  "+lt.getHour()+":"+lt.getMinute()+":"+lt.getSecond();
		PreparedStatement ps = con.prepareStatement("insert into deposit values(?,?,?,?,?)");
		Random r = new Random();
		ps.setInt(1,r.nextInt(100));
		ps.setLong(2,t.getAccountNumber());
		ps.setDouble(3,t.getAmount());
		ps.setString(4,current_datetime);
		ps.setDouble(5,t.getTotal_balance());
		ps.execute();
		CustomerDAO.updateCustomerBalance(t.getTotal_balance(),t.getAccountNumber());
		return "Deposited Successfully";
	}
	
	public static String withdrawMoney(Transactions t) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		LocalDateTime lt = LocalDateTime.now();
		String current_datetime = lt.getDayOfMonth()+"-"+lt.getMonthValue()+"-"+lt.getYear()+"  "+lt.getHour()+":"+lt.getMinute()+":"+lt.getSecond();
		PreparedStatement ps = con.prepareStatement("insert into withdraw values(?,?,?,?,?)");
		Random r = new Random();
		ps.setInt(1,r.nextInt(100));
		ps.setLong(2,t.getAccountNumber());
		ps.setDouble(3,t.getAmount());
		ps.setString(4,current_datetime);
		ps.setDouble(5,t.getTotal_balance());
		ps.execute();
		CustomerDAO.updateCustomerBalance(t.getTotal_balance(),t.getAccountNumber());
		return "Please collect your amount";
	}
}

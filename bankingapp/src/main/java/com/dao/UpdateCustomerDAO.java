package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dto.Customer;

public class UpdateCustomerDAO {
	static String url = "jdbc:mysql://localhost:3306/bankapplication";
	static String username ="root";
	static String password = "root";
	
	//Setting updated customer Phone number into database tables
	public static String updatePhoneNumber(Customer customer) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, password);
		PreparedStatement ps = con.prepareStatement("update customer set PhoneNumber=? where AccountNumber=?");
		ps.setLong(1,customer.getNumber());
		ps.setLong(2,customer.getAccount_number());
		ps.execute();
			return "updated successfully";
		
		}
	
	//Setting updated customer Aadhar number into database tables
	public static String updateAddress(Customer customer) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, password);
		PreparedStatement ps = con.prepareStatement("update customer set Address=? where AccountNumber=?");
		ps.setString(1,customer.getAddress());
		ps.setLong(2,customer.getAccount_number());
		ps.execute();
		return "updated successfully";
		
	}
	
	

}

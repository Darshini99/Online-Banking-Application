package com.controller;
import java.sql.SQLException;
import java.util.Scanner;

import com.dao.UpdateCustomerDAO;
import com.dto.Customer;

public class UpdateCustomerDetails {
	//Updating user data based on user choice
	public static void UpdateDetails(long accountnum) throws ClassNotFoundException, SQLException {
	Customer customer = new Customer();
	Scanner sc = new Scanner(System.in);
	int change = sc.nextInt();
	switch(change) {
	//To update customers Phone Number
	case 1:
		customer.setAccount_number(accountnum);
		System.out.println("Enter new phone number: ");
		long new_number = sc.nextLong();
		customer.setNumber(new_number);
		System.out.println(UpdateCustomerDAO.updatePhoneNumber(customer));
		break;
		//To update customers Address
	case 2:
		customer.setAccount_number(accountnum);
		System.out.println("Enter new address: ");
		String address = sc.next();
		customer.setAddress(address);
		System.out.println(UpdateCustomerDAO.updateAddress(customer));
		break;
	}
	sc.close();
	}
}

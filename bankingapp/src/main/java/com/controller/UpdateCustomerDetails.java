package com.controller;
import java.sql.SQLException;
import java.util.Scanner;

import com.dao.UpdateCustomerDAO;
import com.dto.Customer;

public class UpdateCustomerDetails {
	public static void UpdateDetails(long accountnum) throws ClassNotFoundException, SQLException {
	Customer customer = new Customer();
	Scanner sc = new Scanner(System.in);
	int change = sc.nextInt();
	switch(change) {
	case 1:
		customer.setAccount_number(accountnum);
		System.out.println("enter new phone number: ");
		long new_number = sc.nextLong();
		customer.setNumber(new_number);
		System.out.println(UpdateCustomerDAO.updatePhoneNumber(customer));
		break;
	case 2:
		customer.setAccount_number(accountnum);
		System.out.println("enter address: ");
		String address = sc.next();
		customer.setAddress(address);
		System.out.println(UpdateCustomerDAO.updateAddress(customer));
		break;
	}
	sc.close();
	}
}

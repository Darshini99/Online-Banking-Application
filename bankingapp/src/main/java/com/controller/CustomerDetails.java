package com.controller;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.dao.CustomerDAO;
import com.dto.Customer;
import com.dto.UserValidate;

public class CustomerDetails {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserValidate u = new UserValidate();
		Customer customer = new Customer();
		String first_name;
		String last_name;
		System.out.println("Welcome to our Bank!!!!");
		System.out.println("Already have an Account.Please enter 1 to Signin/Login");
		System.out.println("Don't have an Account.Please enter 2 to SignUp");
		System.out.println("Enter your choice: ");
		int in = sc.nextInt();
		switch(in){
		//Functionality for Customer Login/SignIn
			case 1:
				System.out.println("Enter Account Number: ");
				long myaccount_number = sc.nextLong();
				customer.setAccount_number(myaccount_number);
				CustomerDAO.validateAccount(customer);
				break;
			case 2:
				//For User SignUp/Register with their details
				System.out.println("Enter your First Name: ");
			    first_name = sc.next();
			    //To validate users first name
			    if(UserValidate.validateName(first_name)) {}
			    else {
			    	System.out.println("Please enter Alphabets only");
			    	System.out.println("Exiting the process......");
			    	break;
			    	}
				System.out.println("Enter your Last Name: ");
				last_name = sc.next();
				//validating users last name
				if(UserValidate.validateName(last_name)) {}
				else {
					System.out.println("Please enter Alphabets only");
					System.out.println("Exiting the process......");
			    	break;
			    }
				String account_type ="";
				System.out.println("Enter your age b/w 10 to 100: ");
				int age = sc.nextInt();
				if(( Pattern.matches("[0-9]{2}",String.valueOf(age))) && (age>=10 && age<100)){
					 account_type=u.setAccountType(age);
				}else {System.out.println("Please enter your correct age");
					   System.out.println("excitting the process.....");
					   break;
				}
				System.out.println("Enter your Gender: ");
				String gender = sc.next();
				//validating users gender
				if(UserValidate.validateGender(gender)) {}
				else {
					System.out.println("Please enter either male or female only");
					System.out.println("Exiting the process......");
			    	break;
			    }
				System.out.println("Enter your Aadhar Number: ");
				long aadhar = sc.nextLong();
				//calling validateAadhar to validate
				if(u.validateAadhar(aadhar)) {}
				else {
					System.out.println("Dear User please enter a valid Aadhar Number of 12 digits");
					System.out.println("Exiting the process......");
					System.out.println("-----------------------------------------");
					break;
				}
				System.out.println("Enter Phone Number: ");
				long number = sc.nextLong();
				//calling validatePhone to validate
				if(u.validatePhone(number)) {}
				else {
					System.out.println("Dear User please enter valid phone number of 10 digits and starts with 6/7/8/9 only");
					System.out.println("Exiting the process......");
					System.out.println("-----------------------------------------");
					break;
				}
				System.out.println("Enter your Address: ");
				String address = sc.nextLine();
				address = sc.nextLine();
				System.out.println("Thank you for all the detalis.. Just wait a moment we will generate you an account number");
				//Generating account number
				long account_number = (long)(Math.random()*900000000000L) + 100000000000L;
				if (account_number < 0) account_number *= -1;
		        sc.close();
				//setting data into customer class
				customer.setFirst_name(first_name);
				customer.setLast_name(last_name);
				customer.setAge(age);
				customer.setAccount_type(account_type);
				customer.setGender(gender);
				customer.setAadhar(aadhar);
				customer.setNumber(number);
				customer.setAddress(address);
				customer.setAccount_number(account_number);
				System.out.println(CustomerDAO.createAccount(customer));
				System.out.println("Thanks for creating an account in our bank!!!!!");
				System.out.println("Details are: ");
				System.out.println("------------------------------------------");
				System.out.println("Account HolderName: "+first_name+" "+last_name);
				System.out.println("Account Number: "+account_number);
				System.out.println("-------------------------------------------");
				break;
			default:
				System.out.println("Dear user please choose the correct choice!!!!!");
			}
		}
		
		//Transactional operations
	public static void TransactionOpeartions(long accountnum) throws ClassNotFoundException, SQLException {
		System.out.println("Welcome Thanks for choosing our bank!!!!");
		System.out.println("--------- OPTIONS ---------");
		System.out.println("Dear Customer please enter \n1 for Deposit"
					+ "\n2 for WithDrawal \n3 for Checking Balance\n"
					+ "4 for other Updates\n5 to see All Transactions \n6 to Close Account\n7 to Exit");
		int in = sc.nextInt();
			//Based on the user choice feature will be called and executed
		switch(in) {
			case 1:
				TransactionDetails.depositAmount(accountnum);
				break;
			case 2:
				TransactionDetails.withdrawAmount(accountnum);
				break;
			case 3:
				System.out.println("Please enter your Account Number: ");
				long accountNumber = sc.nextLong();
				System.out.println(CustomerDAO.customerTotalBalance(accountNumber));
				break;
			case 4:
				System.out.println("Dear customer you can update the changes here!!!");
				System.out.println("Enter your choice: ");
				System.out.println("1 - Update My Mobile Number\n"
						          + "2 - Update My Address ");
				UpdateCustomerDetails.UpdateDetails(accountnum);
				break;
			case 5:
				TransactionDetails.allMyTransactions(accountnum);
				break;
			case 6:
				System.out.println("Dear Customer are you sure you want to close your account");
				System.out.println("Enter Yes or No");
				String confirm = sc.next();
				if(confirm.equalsIgnoreCase("yes")) {
					try {
						boolean d =TransactionDetails.depositTableUpdate(accountnum);
						boolean w =TransactionDetails.withdrawTableUpdate(accountnum);
						boolean c = TransactionDetails.closeMyAccount(accountnum);
						if(c) System.out.println("Your account has been closed Successfully");
						else if(d) System.out.println("Closed Successfully");
						else System.out.println("Closed Successfully");
					}catch(Exception e) {System.out.println("not closed");}
				}else {
						System.out.println("Please proceed with your preferences......");
				}
				break;
			case 7:
				System.out.println("Exitting .............");
				System.out.println("Thank You.............");
				break;
			default:
				System.out.println("Dear customer please choose correct option.......");
		}
	}
}
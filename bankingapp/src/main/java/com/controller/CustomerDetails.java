package com.controller;
import java.sql.SQLException;
import java.util.Scanner;

import com.dao.CustomerDAO;
import com.dto.Customer;
import com.dto.UserValidate;

public class CustomerDetails {
	String first_name;
	String last_name;
	static Scanner sc = new Scanner(System.in);
		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			UserValidate u = new UserValidate();
			Customer customer = new Customer();
			
			String first_name;
			String last_name;
			System.out.println("Welcome to our Bank!!!!");
			System.out.println("Already have an account.Please enter 1 to Signin/Login");
			System.out.println("Dont have an account.Please enter 2 to SignUp");
			System.out.println("Enter your choice: ");
			int in = sc.nextInt();
			switch(in){
			case 1:
				System.out.println("enter account number: ");
				long myaccount_number = sc.nextLong();
				customer.setAccount_number(myaccount_number);
				CustomerDAO.ValidateAccount(customer);
				break;
			case 2:
				System.out.println("enter your First Name: ");
			    first_name = sc.next();
				System.out.println("enter your Last Name: ");
				last_name = sc.next();
				System.out.println("enter your age greater than 10: ");
				int age = sc.nextInt();
				String account_type=u.setAccountType(age);
				System.out.println("enter your Gender: ");
				String gender = sc.next();
				System.out.println("enter your aadhar Number: ");
				long aadhar = sc.nextLong();
				if(u.validateAadhar(aadhar)) {}
				else {
					System.out.println("Dear User please enter a valid aadhar number of 12 digits");
					System.out.println("exiting the process......");
					System.out.println("-----------------------------------------");
					break;
				}
				System.out.println("enter phone Number: ");
				long number = sc.nextLong();
				if(u.validatePhone(number)) {}
				else {
					System.out.println("Dear User please enter valid phone number of 10 digits and starts with 6/7/8/9 only");
					System.out.println("exiting the process......");
					System.out.println("-----------------------------------------");
					break;
				}
				System.out.println("enter your address: ");
				String address = sc.nextLine();
				address = sc.nextLine();
				System.out.println("Thank you for all the detalis.. Just wait a moment we will generate you an account number");
				long account_number = (long)(Math.random()*900000000000L) + 100000000000L;
				if (account_number < 0) account_number *= -1;
		        sc.close();
				
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
				System.out.println("account holder name: "+first_name);
				System.out.println("Account Number: "+account_number);
				System.out.println("-------------------------------------------");
				break;
				default:
					System.out.println("Dear user please choose the correct choice!!!!!");
			}
		}
		public static void TransactionOpeartions(long accountnum) throws ClassNotFoundException, SQLException {
			System.out.println("welcome Thanks for choosing our bank!!!!");
			System.out.println("--------- OPTIONS ---------");
			System.out.println("Dear Customer please enter \n1 for Deposit"
					+ "\n2 for WithDrawal \n3 for Checking Balance\n"
					+ "4 for other\n5 to see all transactions \n6 to close the account\n7 to exit");
			int in = sc.nextInt();
			switch(in) {
			case 1:
				TransactionDetails.depositAmount(accountnum);
				break;
			case 2:
				TransactionDetails.withdrawAmount(accountnum);
				break;
			case 3:
				System.out.println("Please enter your account Number: ");
				long accountNumber = sc.nextLong();
				System.out.println(CustomerDAO.customerTotalBalance(accountNumber));
				break;
			case 4:
				System.out.println("Dear customer you can update the changes here!!!");
				System.out.println("enter your choice: ");
				System.out.println("1 - Update My Mobile Number\n"
						          + "2 - Update My Address ");
				UpdateCustomerDetails.UpdateDetails(accountnum);
				break;
			case 5:
				TransactionDetails.allMyTransactions(accountnum);
				break;
			case 6:
				System.out.println("Dear Customer are you sure you want to close your account");
				System.out.println("enter yes or no");
				String confirm = sc.next();
					if(confirm.equalsIgnoreCase("yes")) {
						try {
							boolean d =TransactionDetails.depositTableUpdate(accountnum);
							boolean w =TransactionDetails.withdrawTableUpdate(accountnum);
							boolean c = TransactionDetails.closeMyAccount(accountnum);
							if(c) System.out.println("Your account has been closed successfully");
							else if(d) System.out.println("closed successfully");
							else System.out.println("closed successfully");
						}catch(Exception e) {System.out.println("not closed");}
					}else {
						System.out.println("Please proceed with your preferences......");
					}
				break;
			case 7:
				System.out.println("exitting .............");
				System.out.println("Thank You.............");
				break;
			default:
				System.out.println("Dear customer please choose correct option.......");
			}
	}
}
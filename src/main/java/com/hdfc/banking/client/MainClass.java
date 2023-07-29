package com.hdfc.banking.client;

import com.hdfc.banking.beans.Account;
import com.hdfc.banking.beans.Transaction;
import com.hdfc.banking.exceptions.*;
import com.hdfc.banking.services.BankingServices;
import com.hdfc.banking.services.BankingServicesImpl;

import java.util.List;
import java.util.Scanner;

public class MainClass {
	private static BankingServices bankingServices = new BankingServicesImpl();
	private static Scanner sc= new Scanner(System.in);
	public static void main(String[] args) {

		String wannaContinue="y";
		int userChoice=0;
		while(wannaContinue.equalsIgnoreCase("y")){
			try {
				System.out.println("Select any one of the following:\n1. Open an Account\n2. Deposit an Amount\n3. Withdraw an Amount\n4. Transfer to another account.\n5. Get a specific Account Details\n6. Get All Account details\n7. Get Account Transactions\n8. Get Account Status\n9. Customer Care\nSelect your choice:");
				userChoice = sc.nextInt();
				sc.nextLine();
				switch(userChoice) {
				case 1:
					System.out.println("\nOpening the Account!\nEnter the account type:");
					String accountType = sc.nextLine();
					System.out.println("Enter the balance to transfer to new account:");
					float initBalance = sc.nextFloat();
					System.out.println("Enter the pin for the new account:");
					int pinNumber = sc.nextInt();
					sc.nextLine();
					int accountNo = bankingServices.openAccount("", "", accountType, initBalance);
					System.out.println("Congratulations! The account is successfully opened. Your account number is " + accountNo);
					break;

				case 2:
					System.out.println("\nDeposit Window\nEnter the account number:");
					accountNo = sc.nextInt();
					System.out.println("Enter the amount to deposit");
					float amount = sc.nextFloat();
					float finalBalance = bankingServices.depositAmount(accountNo, amount);
					System.out.println("The amount has been deposited. The updated balance in the account is Rs. "+finalBalance);
					break;

				case 3:
					System.out.println("\nWithdraw Window\nEnter the account number:");
					accountNo = sc.nextInt();
					System.out.println("Enter the amount to be withdrawn");
					amount = sc.nextFloat();
					System.out.println("Enter your pin number:");
					pinNumber = sc.nextInt();
					finalBalance = bankingServices.withdrawAmount(accountNo, amount, pinNumber);
					System.out.println("The amount has been withdrawn. The updated balance in the account is Rs. "+finalBalance);
					break;

				case 4:
					System.out.println("\nTransfer to another Account\nEnter the account number of the receipent.");
					int accountNoTo = sc.nextInt();
					System.out.println("Enter your account number");
					int accountNoFrom = sc.nextInt();
					System.out.println("Enter the amount to transfer");
					float transferAmount = sc.nextFloat();
					System.out.println("Enter your pin number");
					pinNumber = sc.nextInt();
					if (bankingServices.fundTransfer(accountNoTo, accountNoFrom, transferAmount, pinNumber))
						System.out.println("The funds got successfully transfered");
					break;

				case 5:
					System.out.println("\nAccount Details!\nEnter your account number:");
					accountNo = sc.nextInt();
					Account account = bankingServices.getAccountDetails(accountNo);
					System.out.println("Account Details!\n");
					System.out.println(account);
					System.out.println("\nTransaction Details!\n");
					for (Transaction transaction : account.getTransactions())	System.out.println(transaction);
					break;

				case 6:
					System.out.println("\nGet all Accounts");
					List<Account> accounts = bankingServices.getAllAccountDetails();
					for (Account account2 : accounts)		System.out.println(account2 + "\n"); 
					break;

				case 7:
					System.out.println("\nGet all Account Transactions\nEnter your account number:");
					accountNo = sc.nextInt();
					List<Transaction> transactions = bankingServices.getAccountAllTransaction(accountNo);
					for (Transaction transaction : transactions)	System.out.println(transaction);
					break;

				case 8: 
					System.out.println("\nFetching Account Status...\nEnter your account number:");
					accountNo = sc.nextInt();
					System.out.println("The account status of the account is: " + bankingServices.accountStatus(accountNo));
					break;

				case 9:
					System.out.println("\nCustomer Care Window!\n How may I assist you?\n1.Change Account Pin\n2.Change Account Status\n3. Deactivate Account\nEnter your choice: ");
					userChoice = sc.nextInt();
					switch(userChoice){
					case 1:
						System.out.println("\nChange account pin..\nEnter your account number");
						accountNo = sc.nextInt();
						System.out.println("Enter the new pin");
						pinNumber = sc.nextInt();
						if (bankingServices.changeAccountPin(accountNo, pinNumber))	System.out.println("Pin Updated Successfully");
						break;

					case 2:
						System.out.println("\nUnBlock Account\nEnter your account number");
						accountNo = sc.nextInt();
						if (bankingServices.upblockAccount(accountNo))	System.out.println("Unblocking your account...\nYour account is now Active");
						break;

					case 3:
						System.out.println("\nDeactivate Account.\nEnter your account number");
						accountNo = sc.nextInt();
						System.out.println("Enter pin");
						pinNumber = sc.nextInt();
						if (bankingServices.deactivateAccount(accountNo,pinNumber))	System.out.println("Your account has been deactivated.");
						break;

					default:
						System.out.println("Enter a valid choice");
						break;
					}
					break;

				default:
					System.out.println("Enter a valid choice");
					break;
				}
			} catch (BankingServicesDownException e) { System.out.println(e.getMessage());
			} catch (AccountBlockedException e) { System.out.println(e.getMessage());
			} catch (AccountNotFoundException e) { System.out.println(e.getMessage());
			} catch (InsufficientAmountException e) { System.out.println(e.getMessage());
			} catch (InvalidAccountTypeException e) { System.out.println(e.getMessage());
			} catch (InvalidAmountException e) { System.out.println(e.getMessage());
			} catch (InvalidPinNumberException e) { System.out.println(e.getMessage()); }
			System.out.println("\nDo you wanna continue(y/n):");
			wannaContinue = sc.next();
			System.out.println();
		}
		sc.close();
	} 
	/*AssociateDAO associateDAO = new AssociateDAOImpl();
		Associate associate= new Associate(12000, "Srish", "Jain", "JFS", "Sr. Analyst", "POERT8965F", "srish.jain@gmail.com", new BankDetails(8989, "ICICI", "ICIC0005896"), new Salary(50000, 12500, 10000, 10000, 10000, 5000, 2000, 2000, 2000, 92500, 81500));
		associate = associateDAO.save(associate);
		System.out.println("Your associate Id = " + associate.getAssociateID());
		System.out.println(associateDAO.findOne(1));


		/*Associate associate = new Associate("Ayush", "Goel", "ayush.goel@capgemini.com", "Sr. Analyst");
		associate = associateDAO.save(associate);
		System.out.println("Your associate Id = " + associate.getAssociateId());
//		System.out.println(associateDAO.findOne(1));
		associate=associateDAO.findOne(1);
		associate.setEmail("ayush.goel@gmail.com");
		associateDAO.update(associate);
		/*ArrayList<Associate> list = associateDAO.findAll();
		for (Associate associate2 : list) 
			System.out.println(associate2);*/
}

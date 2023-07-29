package com.hdfc.banking.services;

import com.hdfc.banking.beans.Account;
import com.hdfc.banking.beans.Transaction;
import com.hdfc.banking.exceptions.*;

import java.util.List;

public interface BankingServices {
	int openAccount(String firstName, String lastName, String accountType, float accountBalance)
			throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException;

	float depositAmount(int accountNo,float amount)
			throws AccountNotFoundException,BankingServicesDownException, AccountBlockedException;

	float withdrawAmount(int accountNo,float amount,int pinNumber)
			throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException,
			BankingServicesDownException ,AccountBlockedException;

	boolean fundTransfer(int accountNoTo,int accountNoFrom,float transferAmount,int pinNumber)
			throws InsufficientAmountException,AccountNotFoundException,InvalidPinNumberException,
			BankingServicesDownException, AccountBlockedException;

	Account getAccountDetails(int accountNo)
			throws  AccountNotFoundException,BankingServicesDownException;

	List<Account> getAllAccountDetails()
			throws BankingServicesDownException, AccountNotFoundException;

	List<Transaction> getAccountAllTransaction(int accountNo)
			throws BankingServicesDownException, AccountNotFoundException;

	public String accountStatus(int accountNo)
			throws BankingServicesDownException, AccountNotFoundException, AccountBlockedException;
	
	boolean changeAccountPin(int accountNo, int pinNumber) 
			throws AccountNotFoundException, BankingServicesDownException;
	
	boolean upblockAccount(int accountNo)
			throws AccountNotFoundException, BankingServicesDownException;

	boolean deactivateAccount(int accountNo, int pinNumber)
			throws AccountNotFoundException, BankingServicesDownException, InvalidPinNumberException;

	void changeAccountStatus(int accountNo, String status) 
			throws BankingServicesDownException, AccountNotFoundException;
	
	Account loginAccounDetails(int accountNo) 
			throws AccountNotFoundException, AccountBlockedException;
}
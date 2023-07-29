package com.hdfc.banking.services;


import com.hdfc.banking.beans.Account;
import com.hdfc.banking.beans.Transaction;
import com.hdfc.banking.daoservices.AccountDAO;
import com.hdfc.banking.daoservices.AccountDAOImpl;
import com.capgemini.banking.exceptions.*;
import com.hdfc.banking.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class BankingServicesImpl implements BankingServices {
	private AccountDAO accountDAO = new AccountDAOImpl();
	
	/**
	 * This function is used to add details of account to the database. 
	 * @param firstName	First Name of Account Holder
	 * @param lastName	Last Name of Account Holder
	 * @param accountType	Account Type ('Savings', 'Current')
	 * @param accountBalance	The initial balance added to the account
	 * @return	int	returns the account number associated with the added account
	 * @exception InvalidAmountException    Exception thrown when the balance falls below minimum balance(1000)
	 * @exception InvalidAccountTypeException    Exception thrown when account type is not valid
	 * @exception BankingServicesDownException    Exception thrown when there is error in saving account details to database
	 */
	@Override
	public int openAccount(String firstName, String lastName, String accountType, float accountBalance)
			throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException {
			if (!(accountType.equalsIgnoreCase("Savings") || accountType.equalsIgnoreCase("Current")))
				throw new InvalidAccountTypeException("Invalid account type. Please select among (Savings / Current) account type.");
			if (accountBalance<1000)
				throw new InvalidAmountException("Insufficient initial balance. Minimum balance of Rs. 1000 must be added");
			Account account = new Account(firstName, lastName, accountType, accountBalance, 0000);
			List<Transaction> transactions = new ArrayList<>();
			transactions.add(new Transaction(accountBalance, "Deposit", account));
			account.setTransactions(transactions);
			account = accountDAO.saveAccount(account);
			if (account == null)	throw new BankingServicesDownException("Banking Services are Down. Please try again later.");
			return account.getAccountNo();
	}

	/**
	 * Deposits the amount to account.
	 * @param accountNo		account number of the account holder
	 * @param	amount		the transaction amount 
	 * @return	float	returns the updated account balance
	 * @exception AccountNotFoundException	exception thrown when there is no account associated with account no
	 */
	@Override
	public float depositAmount(int accountNo, float amount)
			throws AccountNotFoundException, BankingServicesDownException, AccountBlockedException {
			Account account = accountDAO.findOne(accountNo);
			if (account == null)	throw new AccountNotFoundException("There is no account with the provided account number. Enter a valid account number.");
			account = accountDAO.depositAmountToAccount(accountNo, amount);
			return account.getAccountBalance(); 	
	}

	/**
	 * Withdraws the amount from the account.
	 * @param accountNo		account number of the account holder
	 * @param	amount		the transaction amount 
	 * @param pinNumber	pinNumber of the account holder
	 * @return	float	returns the updated account balance
	 * @exception AccountNotFoundException	exception thrown when there is no account associated with account no
	 * @exception InvalidPinNumberException    exception thrown if illegal pin number is entered
	 * @exception AccountBlockedException	exception thrown when the account is blocked
	 * @exception InsufficientAmountException exception thrown when there are insufficient funds in account
	 * @exception BankingServicesDownException	exception thrown when there error in updating details 
	 */
	@Override
	public float withdrawAmount(int accountNo, float amount, int pinNumber) throws InsufficientAmountException,
			AccountNotFoundException, InvalidPinNumberException, BankingServicesDownException, AccountBlockedException {
			Account account = accountDAO.findOne(accountNo);
			if (account == null)	throw new AccountNotFoundException("There is no account with the provided account number. Enter a valid account number.");
			if (account.getStatus().equalsIgnoreCase("Blocked")) throw new AccountBlockedException("The account is blocked. Please contact the customer care to unblock the account");
			if (account.getPinNumber()!=pinNumber)		throw new InvalidPinNumberException("The pin entered is invalid. Please enter a valid pin");
			if (account.getAccountBalance()-amount<1000)	throw new InsufficientAmountException("The transaction got failed due to insufficient balance in the account.");
			account = accountDAO.withdrawFromAccount(accountNo, amount, pinNumber);
			return account.getAccountBalance();
	}

	/**
	 * Transfers amount from one account to another
	 * @param accountNoTo	recepient's account number
	 * @param accountNoFrom	sender's account number
	 * @param	transferAmount		the transaction amount 
	 * @param pinNumber	pinNumber of the account holder
	 * @return	boolean	if the fund transfer is successful
	 * @exception AccountNotFoundException	exception thrown when there is no account associated with account no
	 * @exception InvalidPinNumberException	exception thrown if illegal pin number is entered
	 * @exception AccountBlockedException	exception thrown when the account is blocked
	 * @exception InsufficientAmountException exception thrown when there are insufficient funds in account
	 * @exception BankingServicesDownException	exception thrown when there error in updating details 
	 */
	@Override
	public boolean fundTransfer(int accountNoTo, int accountNoFrom, float transferAmount, int pinNumber)
			throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException, BankingServicesDownException, AccountBlockedException {
			if (accountDAO.findOne(accountNoTo)==null)	throw new AccountNotFoundException("The Receipent's Account does not exists");
			if (accountDAO.findOne(accountNoFrom)==null)	throw new AccountNotFoundException("The Sender Account does not exists");
			if (accountNoTo == accountNoFrom)	throw new AccountNotFoundException("The Receipent's Account cannot be same as Sender's Account.");
			if (accountDAO.fetchAccountStatus(accountNoFrom).getStatus().equalsIgnoreCase("Blocked"))	throw new AccountBlockedException("The sender account is blocked.");
			if (accountDAO.findOne(accountNoFrom).getAccountBalance()-transferAmount<1000)	throw new InsufficientAmountException("The transaction got failed due to insufficient balance in the account.");
			withdrawAmount(accountNoFrom, transferAmount, pinNumber);
			depositAmount(accountNoTo, transferAmount);
			return true;
	}

	/**
	 * Fetch single account details from the database
	 * @param accountNo		account number of the account holder
	 * @return	Account		account object of the received data from database
	 * @exception AccountNotFoundException		exception thrown when there is no account associated with account no
	 */
	@Override
	public Account getAccountDetails(int accountNo) throws AccountNotFoundException, BankingServicesDownException {
			Account account = accountDAO.findOne(accountNo);
			if (account == null)	throw new AccountNotFoundException("There is no account with the provided account number. Enter a valid account number.");
			return account;
	}

	/**
	 * Fetch login account details
	 * @param	accountNo 	account number of the account holder
	 * @return Account		returns account object with the account details
	 * @exception AccountNotFoundException		exception thrown when there is no account associated with account no	
	 */
	@Override
	public Account loginAccounDetails(int accountNo) throws AccountNotFoundException, AccountBlockedException {
		Account account = accountDAO.findOne(accountNo);
		if (account == null)	throw new AccountNotFoundException("There is no account with the provided account number. Enter a valid account number.");
		return account;
	}
	
	/**
	 * Transmits list of all accounts
	 * @return List<Account>		returns list of account objects with the account details
	 * @exception AccountNotFoundException		exception thrown when there are no accounts	
	 */
	@Override
	public List<Account> getAllAccountDetails() throws BankingServicesDownException, AccountNotFoundException {
		List<Account> accounts = accountDAO.findAllAccounts();
		if (accounts == null)		throw new AccountNotFoundException("No Accounts Found!");
		return accounts;
	}

	/**
	 * Fetches all the transactions for a particular account
	 * @param accountNo		account number of account holder
	 * @return List<Transaction> list of all transactions
	 * @exception AccountNotFoundException	exception thrown when there are no accounts			
	 */
	@Override
	public List<Transaction> getAccountAllTransaction(int accountNo)
			throws BankingServicesDownException, AccountNotFoundException {
			List<Transaction> transactions = accountDAO.fetchAllTransactions(accountNo);
			if (transactions == null)	throw new AccountNotFoundException("There is no account with the provided account number. Enter a valid account number.");
			return transactions;
	}

	/**
	 *  returns the account status of the account
	 *  @param	accountNo	account number of account holder
	 *  @return	String		Account status
	 *  @exception AccountNotFoundException		exception thrown if not account exists with given accountno
	 */
	@Override
	public String accountStatus(int accountNo)
			throws BankingServicesDownException, AccountNotFoundException, AccountBlockedException {
			Account account =  accountDAO.fetchAccountStatus(accountNo);
			if (account == null) throw new AccountNotFoundException("There is no account with the provided account number. Enter a valid account number.");
			return account.getStatus();
	}
	
	/**
	 * Changes the account pin
	 * @param accountNo		account no of account holder
	 * @param pinNumber	existing PIN
	 * @return boolean 	true if PIN changes successfully
	 * @exception AccountNotFoundException		exception thrown if not account exists with given accountno
	 */
	@Override
	public boolean changeAccountPin(int accountNo, int pinNumber) throws AccountNotFoundException, BankingServicesDownException{
			Account account =  accountDAO.changePIN(accountNo, pinNumber);
			if (account==null)	throw new AccountNotFoundException("There is no account with the provided account number. Enter a valid account number.");	
			return true;
	}
	
	/**
	 * Changes the account status
	 * @param accountNo		account no of account holder
	 * @param status	desired status of account
	 * @exception AccountNotFoundException		exception thrown if not account exists with given accountno
	 */
	@Override
	public void changeAccountStatus(int accountNo, String status)
			throws BankingServicesDownException, AccountNotFoundException {
		Account account =  accountDAO.findOne(accountNo);
		if (account == null) throw new AccountNotFoundException("There is no account with the provided account number. Enter a valid account number.");
		account.setStatus(status);
		account = accountDAO.update(account);
	}

	/**
	 * Unblocks user account
	 * @param accountNo		account no of account holder
	 * @param boolean	true if account is unblocked
	 * @exception AccountNotFoundException		exception thrown if not account exists with given accountno
	 */
	@Override
	public boolean upblockAccount(int accountNo)
			throws AccountNotFoundException, BankingServicesDownException {
			Account account = accountDAO.setAccountStatus(accountNo, "Active");
			if (account==null)	throw new AccountNotFoundException("There is no account with the provided account number. Enter a valid account number.");
			return true;
	}

	/**
	 * Deletes user account
	 * @param accountNo		account no of account holder
	 * @param pinNumber	pin number associated with hte account
	 * @param boolean		true if account gets deleted
	 * @exception AccountNotFoundException		exception thrown if not account exists with given accountno
	 * @exception InvalidPinNumberException		exception thrown if pin number is invalid
	 */
	@Override
	public boolean deactivateAccount(int accountNo, int pinNumber)
			throws AccountNotFoundException, BankingServicesDownException, InvalidPinNumberException {
			Account account = accountDAO.findOne(accountNo); 
			if (account == null)	throw new AccountNotFoundException("There is no account with the provided account number. Enter a valid account number.");
			if (account.getPinNumber() != pinNumber)	throw new InvalidPinNumberException("The pin entered is invalid. Please enter a valid pin");
			accountDAO.deactivateAccount(accountNo);
			return true;
	}
}

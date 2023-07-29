package com.hdfc.banking.daoservices;

import com.hdfc.banking.beans.Account;
import com.hdfc.banking.beans.Transaction;

import java.util.List;
public interface AccountDAO {
	Account saveAccount(Account account);
	Account findOne(int accountNo);
	List<Account> findAllAccounts();
	List<Transaction> fetchAllTransactions(int accountNo);
	Account fetchAccountStatus(int accountNo);
	Account depositAmountToAccount(int accountNo, float amount);
	Account withdrawFromAccount(int accountNo, float amount, int pinNumber);
	Account setAccountStatus(int accountNo, String status);
	Account changePIN(int accountNo, int pin);
	boolean deactivateAccount(int accountNo);
	Account update(Account account);
	Transaction saveTransaction(int accountNo, String transactionType, float amount);
}
package com.hdfc.banking.daoservices;

import com.hdfc.banking.beans.Account;
import com.hdfc.banking.beans.Transaction;
import com.hdfc.banking.util.EntityManagerFactoryProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class AccountDAOImpl implements AccountDAO{
	private EntityManagerFactory factory = EntityManagerFactoryProvider.getEntityManagerFactory();

	@Override
	public Account saveAccount(Account account) {
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(account);
		account.setStatus("Active");
		entityManager.getTransaction().commit();
		entityManager.close();
		return account;
	}
	
	@Override
	public Transaction saveTransaction(int accountNo, String transactionType, float amount) {
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Transaction transaction = new Transaction(amount, transactionType, findOne(accountNo));
		entityManager.persist(transaction);
		entityManager.getTransaction().commit();
		entityManager.close();
		return transaction;
	}

	@Override
	public Account findOne(int accountNo) {
		EntityManager entityManager = factory.createEntityManager();
		return entityManager.find(Account.class, accountNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findAllAccounts() {
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createNamedQuery("findAllAccounts");
		List<Account> accounts = query.getResultList();
		return accounts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> fetchAllTransactions(int accountNo) {
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createNamedQuery("getAllTransactions");
		query.setParameter("accountNo", accountNo);
		List<Transaction> transactions = query.getResultList();
		return transactions;
	}

	@Override
	public Account fetchAccountStatus(int accountNo) {
		EntityManager entityManager = factory.createEntityManager();
		Query query = entityManager.createNamedQuery("queryAccountStatus");
		query.setParameter("accountNo", accountNo);
		return (Account) query.getSingleResult();
	}

	@Override
	public Account depositAmountToAccount(int accountNo, float amount) {
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Account account = findOne(accountNo);
		account.setAccountBalance(account.getAccountBalance()+amount);
		entityManager.merge(account);
		saveTransaction(accountNo, "Deposit", amount);
		entityManager.getTransaction().commit();
		entityManager.close();
		return account;
	}

	@Override
	public Account withdrawFromAccount(int accountNo, float amount, int pinNumber) {
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Account account = findOne(accountNo);
		account.setAccountBalance(account.getAccountBalance() - amount);
		saveTransaction(accountNo, "Withdraw", amount);
		entityManager.merge(account);
		entityManager.getTransaction().commit();
		entityManager.close();
		return account;
	}

	@Override
	public Account setAccountStatus(int accountNo, String status) {
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Account account = findOne(accountNo);
		account.setStatus(status);
		entityManager.merge(account);
		entityManager.getTransaction().commit();
		entityManager.close();
		return account;
	}

	@Override
	public Account changePIN(int accountNo, int pin) {
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Account account = findOne(accountNo);
		account.setPinNumber(pin);
		entityManager.merge(account);
		entityManager.getTransaction().commit();
		entityManager.close();
		return account;
	}

	@Override
	public boolean deactivateAccount(int accountNo) {
		System.out.println("hello");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		Account account = findOne(accountNo);
		account = entityManager.merge(account);
		entityManager.remove(account);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;		
	}

	@Override
	public Account update(Account account) {
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(account);
		entityManager.getTransaction().commit();
		entityManager.close();
		return account;
	}

}

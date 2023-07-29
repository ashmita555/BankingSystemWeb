package com.hdfc.banking.beans;

import javax.persistence.*;
import java.util.List;

@Entity

@NamedQueries({
	@NamedQuery(name="findAllAccounts", query="Select a from Account a"),
	@NamedQuery(name="queryAccountStatus", query="Select a from Account a where a.accountNo=:accountNo")
})
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="accountNoGenerator")
	@SequenceGenerator(name="accountNoGenerator", sequenceName="accountno_seq", initialValue=915686001, allocationSize=0)
	private int accountNo;
	private int pinNumber;
	private String firstName, lastName, accountType, status;
	private float accountBalance;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="account",orphanRemoval=true)
	private List<Transaction> transactions;
	public Account() {}
	
	public Account(int accountNo, int pinNumber, String firstName, String lastName, String accountType, String status, float accountBalance, List<Transaction> transactions) {
		super();
		this.accountNo = accountNo;
		this.pinNumber = pinNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountType = accountType;
		this.status = status;
		this.accountBalance = accountBalance;
		this.transactions = transactions;
	}
	
	public Account(String firstName, String lastName, String accountType, float accountBalance, int pinNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.pinNumber = pinNumber;
	}

	public Account(String accountType, float accountBalance, List<Transaction> transactions) {
		super();
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.transactions = transactions;
	}
	
	public Account(String accountType, float accountBalance, int pinNumber) {
		super();
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.pinNumber = pinNumber;
	}

	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public int getPinNumber() {
		return this.pinNumber;
	}
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(accountBalance);
		result = prime * result + accountNo;
		result = prime * result
				+ ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + pinNumber;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((transactions == null) ? 0 : transactions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Float.floatToIntBits(accountBalance) != Float
				.floatToIntBits(other.accountBalance))
			return false;
		if (accountNo != other.accountNo)
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (pinNumber != other.pinNumber)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", pinNumber=" + pinNumber
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", accountType=" + accountType + ", status=" + status
				+ ", accountBalance=" + accountBalance + ", transactions="
				+ transactions + "]";
	}
}

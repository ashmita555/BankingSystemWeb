package com.hdfc.banking.beans;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="getAllTransactions", query="Select t from Transaction t where t.account.accountNo=:accountNo")
})
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="transactionIdGenerator")
	@SequenceGenerator(name="transactionIdGenerator", sequenceName="transid_seq", initialValue=5001, allocationSize=0)
	private int transactionId;
	private float amount;
	private String transactionType;
	@ManyToOne(fetch = FetchType.LAZY)
	private Account account;
	public Transaction() {}
	public Transaction(int transactionId, float amount, String transactionType,
			Account account) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.transactionType = transactionType;
		this.account = account;
	}
	public Transaction(float amount, String transactionType, Account account) {
		super();
		this.amount = amount;
		this.transactionType = transactionType;
		this.account = account;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + transactionId;
		result = prime * result
				+ ((transactionType == null) ? 0 : transactionType.hashCode());
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
		Transaction other = (Transaction) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (transactionId != other.transactionId)
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transaction Id=" + transactionId + "\t\t\tTransaction Amount="
				+ amount + "\t\t\tTransaction Type=" + transactionType;
	}
}


package com.hdfc.banking.exceptions;

@SuppressWarnings("serial")
public class BankingServicesDownException extends Exception{
	public BankingServicesDownException() { super(); }
	public BankingServicesDownException(String message, Throwable cause) { super(message, cause); }
	public BankingServicesDownException(String message) { super(message); }
	public BankingServicesDownException(Throwable cause) { super(cause); }
}
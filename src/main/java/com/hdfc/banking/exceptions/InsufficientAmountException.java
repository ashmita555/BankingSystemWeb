package com.hdfc.banking.exceptions;

@SuppressWarnings("serial")
public class InsufficientAmountException extends Exception {
	public InsufficientAmountException() { super(); }
	public InsufficientAmountException(String message, Throwable cause) { super(message, cause); }
	public InsufficientAmountException(String message) { super(message); }
	public InsufficientAmountException(Throwable cause) { super(cause); }
}
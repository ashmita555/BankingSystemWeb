package com.hdfc.banking.controllers;

import com.hdfc.banking.beans.Account;
import com.hdfc.banking.exceptions.AccountBlockedException;
import com.hdfc.banking.exceptions.AccountNotFoundException;
import com.hdfc.banking.exceptions.BankingServicesDownException;
import com.hdfc.banking.services.BankingServices;
import com.hdfc.banking.services.BankingServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int ACTIVE_ACCOUNT_NUMBER;
	private static int NUMBER_OF_PIN_TRIES=0;
	private static int MANAGER_ACCOUNT_NO = 915685001;
	private static int MANAGER_PIN = 5252;
	private BankingServices bankingServices = new BankingServicesImpl();
	public LoginServlet() { super(); }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int accountNo = Integer.parseInt(request.getParameter("accountNumberText"));
			int pinNumber = Integer.parseInt(request.getParameter("passwordText"));
			if (accountNo == MANAGER_ACCOUNT_NO && pinNumber == MANAGER_PIN)
				request.getRequestDispatcher("adminPage.jsp").forward(request, response);
			if (ACTIVE_ACCOUNT_NUMBER != accountNo){
				NUMBER_OF_PIN_TRIES=0;
				ACTIVE_ACCOUNT_NUMBER = accountNo;
			}
			if (NUMBER_OF_PIN_TRIES==3) {
				bankingServices.changeAccountStatus(accountNo, "Blocked");
				request.setAttribute("errorMessage", "You have exceeded the maximum PIN tries.Your account has been blocked.Please contact the customer care to unblock the account");
				request.getRequestDispatcher("loginPage.jsp").forward(request, response);
			}
			Account account = bankingServices.loginAccounDetails(accountNo);
			if (account.getPinNumber() != pinNumber){
				request.setAttribute("errorMessage", "Enter a valid PIN number. You have " + (3 - ((++NUMBER_OF_PIN_TRIES) > 3 ? 3 : NUMBER_OF_PIN_TRIES)) + " pin tries left.");
				request.getRequestDispatcher("loginPage.jsp").forward(request, response);
			}
			request.getSession().setAttribute("account", account);
			NUMBER_OF_PIN_TRIES=0;
			request.getRequestDispatcher("welcomePage.jsp").forward(request, response);
		} catch (AccountNotFoundException | BankingServicesDownException | AccountBlockedException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("loginPage.jsp").forward(request, response);
		}
	}
}

package com.hdfc.banking.controllers;

import com.hdfc.banking.beans.Account;
import com.hdfc.banking.exceptions.*;
import com.hdfc.banking.services.BankingServices;
import com.hdfc.banking.services.BankingServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/WithdrawFromAccountServlet")
public class WithdrawFromAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankingServices bankingServices = new BankingServicesImpl();
	private static int ACTIVE_ACCOUNT_NUMBER;
	private static int NUMBER_OF_PIN_TRIES=0;
    public WithdrawFromAccountServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int accountNo = ((Account) request.getSession(false).getAttribute("account")).getAccountNo();
			float amount = Float.parseFloat(request.getParameter("amount"));
			int pinNumber = Integer.parseInt(request.getParameter("pinNumber"));
			float accountBalance = bankingServices.withdrawAmount(accountNo, amount, pinNumber);
			if (ACTIVE_ACCOUNT_NUMBER != accountNo){
				NUMBER_OF_PIN_TRIES=0;
				ACTIVE_ACCOUNT_NUMBER = accountNo;
			}
			if (NUMBER_OF_PIN_TRIES==3) {
				bankingServices.changeAccountStatus(accountNo, "Blocked");
				request.setAttribute("errorMessage", "You have exceeded the maximum PIN tries.\\nYour account has been blocked.\\nPlease contact the customer care to unblock the account");
				request.getRequestDispatcher("loginPage.jsp").forward(request, response);
			}
			request.setAttribute("resultMessage", "The updated Account Balance = Rs. " + accountBalance);
			request.getRequestDispatcher("withdrawFromAccountPage.jsp").forward(request, response);
		} catch (InsufficientAmountException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("withdrawFromAccountPage.jsp").forward(request, response);
		} catch (AccountNotFoundException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("withdrawFromAccountPage.jsp").forward(request, response);
		} catch (InvalidPinNumberException e) {
			request.setAttribute("errorMessage", "Enter a valid PIN number. You have " + (3 - (++NUMBER_OF_PIN_TRIES)) + " pin tries left.");
			request.getRequestDispatcher("withdrawFromAccountPage.jsp").forward(request, response);
		} catch (BankingServicesDownException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("withdrawFromAccountPage.jsp").forward(request, response);
		} catch (AccountBlockedException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("withdrawFromAccountPage.jsp").forward(request, response);
		}
	}
}

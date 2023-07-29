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

@WebServlet("/DepositToAccountServlet")
public class DepositToAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BankingServices bankingServices = new BankingServicesImpl();   
    public DepositToAccountServlet() { super(); }	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int accountNo = ((Account) request.getSession(false).getAttribute("account")).getAccountNo();
			float amount = Float.parseFloat(request.getParameter("amount"));
			float accountBalance = bankingServices.depositAmount(accountNo, amount);
			request.setAttribute("resultMessage", "The updated Account Balance = Rs. " + accountBalance);
			request.getRequestDispatcher("depositToAccountPage.jsp").forward(request, response);
		} catch (AccountNotFoundException | BankingServicesDownException | AccountBlockedException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("depositToAccountPage.jsp").forward(request, response);
		}
	}
}

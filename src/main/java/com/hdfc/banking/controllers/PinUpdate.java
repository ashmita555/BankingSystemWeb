package com.hdfc.banking.controllers;

import com.hdfc.banking.beans.Account;
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

@WebServlet("/PinUpdate")
public class PinUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankingServices bankingServices = new BankingServicesImpl();
    public PinUpdate() { super(); }
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int accountNo = ((Account) request.getSession(false).getAttribute("account")).getAccountNo();
			Account account = bankingServices.getAccountDetails(accountNo);
			if (Integer.parseInt(request.getParameter("newPinText")) != Integer.parseInt(request.getParameter("confirmPinText"))){
				request.setAttribute("errorMessage", "New Pin and Confirm New Pin fields do not match!");
				request.getRequestDispatcher("pinUpdationPage.jsp").forward(request, response);
			}
			bankingServices.changeAccountPin(accountNo, Integer.parseInt(request.getParameter("newPinText")));
			request.getRequestDispatcher("loginPage.jsp").forward(request, response);
		} catch (AccountNotFoundException | BankingServicesDownException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("pinUpdationPage.jsp").forward(request, response);
		}
	}
}

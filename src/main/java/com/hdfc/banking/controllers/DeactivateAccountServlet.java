package com.hdfc.banking.controllers;

import com.hdfc.banking.beans.Account;
import com.hdfc.banking.exceptions.AccountNotFoundException;
import com.hdfc.banking.exceptions.BankingServicesDownException;
import com.hdfc.banking.exceptions.InvalidPinNumberException;
import com.hdfc.banking.services.BankingServices;
import com.hdfc.banking.services.BankingServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeactivateAccountServlet")
public class DeactivateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BankingServices bankingServices = new BankingServicesImpl();
    public DeactivateAccountServlet() { super(); }
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (bankingServices.deactivateAccount(((Account)request.getSession(false).getAttribute("account")).getAccountNo(), Integer.parseInt(request.getParameter("confirmPinText")))){
				request.setAttribute("message", "Your Account has been successfully deactivated.");
				request.setAttribute("check",1);
				request.getRequestDispatcher("customerCarePage.jsp").forward(request, response);
			}
		} catch (AccountNotFoundException | BankingServicesDownException | NumberFormatException | InvalidPinNumberException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("customerCarePage.jsp").forward(request, response);
		}
	}
}

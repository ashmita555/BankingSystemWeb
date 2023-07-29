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

@WebServlet("/ChangeAccountStatusServlet")
public class ChangeAccountStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankingServices bankingServices= new BankingServicesImpl();
    public ChangeAccountStatusServlet() { super(); }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (bankingServices.accountStatus(((Account)request.getSession(false).getAttribute("account")).getAccountNo()).equalsIgnoreCase("Active"))
				request.setAttribute("message", "Your Account with account number "+((Account)request.getSession(false).getAttribute("account")).getAccountNo()+" is already active");
			else{
				bankingServices.upblockAccount(((Account)request.getSession(false).getAttribute("account")).getAccountNo());
				request.setAttribute("message", "Your request has been registered. Your account would be Active within 24 hours");
			}
			request.getRequestDispatcher("customerCarePage.jsp").forward(request, response);
		} catch (BankingServicesDownException | AccountNotFoundException | AccountBlockedException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("customerCarePage.jsp").forward(request, response);
		}
	}
}

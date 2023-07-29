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

@WebServlet("/AdminGetSingleAccountTransactionsServlet")
public class AdminGetSingleAccountTransactionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankingServices bankingServices = new BankingServicesImpl();
	public AdminGetSingleAccountTransactionsServlet() { super(); }
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Account account = bankingServices.getAccountDetails(Integer.parseInt(request.getParameter("accountNo")));
			if (account != null) {
				request.setAttribute("transactions", account.getTransactions());
				request.setAttribute("requestAccountTransactions", "1");
				request.getRequestDispatcher("adminPage.jsp").forward(request, response);
			}
		} catch (NumberFormatException | AccountNotFoundException | BankingServicesDownException e) {
			request.setAttribute("errorMessage3", e.getMessage());
			request.getRequestDispatcher("adminPage.jsp").forward(request, response);
		}
	}
}

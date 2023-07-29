package com.hdfc.banking.controllers;

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

@WebServlet("/AdminGetAllAccounts")
public class AdminGetAllAccounts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankingServices bankingServices = new BankingServicesImpl();
    public AdminGetAllAccounts() {
        super();
    }
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("accountList", bankingServices.getAllAccountDetails());
			request.setAttribute("requestAllAccounts", "1");
			request.getRequestDispatcher("adminPage.jsp").forward(request, response);
		} catch (NumberFormatException | AccountNotFoundException | BankingServicesDownException e) {
			request.setAttribute("errorMessage2", e.getMessage());
			request.getRequestDispatcher("adminPage.jsp").forward(request, response);
		}
	}
}

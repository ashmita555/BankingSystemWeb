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

@WebServlet("/SingleAssociateDetails")
public class SingleAssociateDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BankingServices bankingServices = new BankingServicesImpl();
    
    public SingleAssociateDetails() {
        super();
    }
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("account", bankingServices.getAccountDetails(((Account)request.getSession(false).getAttribute("account")).getAccountNo()));
			Account account = (Account) request.getAttribute("account");
			System.out.println(account);
			request.getRequestDispatcher("getSingleAssociateDetailsPage.jsp").forward(request, response);
		} catch (AccountNotFoundException | BankingServicesDownException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("getSingleAssociateDetailsPage.jsp").forward(request, response);
		}
	}
}

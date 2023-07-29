package com.hdfc.banking.controllers;

import com.hdfc.banking.beans.Account;
import com.hdfc.banking.exceptions.BankingServicesDownException;
import com.hdfc.banking.exceptions.InvalidAccountTypeException;
import com.hdfc.banking.exceptions.InvalidAmountException;
import com.hdfc.banking.services.BankingServices;
import com.hdfc.banking.services.BankingServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/OpenAccountServlet")
public class OpenAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankingServices bankingServices = new BankingServicesImpl();
	
    public OpenAccountServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		String firstName = request.getParameter("firstName");
        	String lastName = request.getParameter("lastName");
        	String accountType = request.getParameter("accountType");
        	float accountBalance = Float.parseFloat(request.getParameter("openingBalance"));
        	int accountNo = bankingServices.openAccount(firstName, lastName, accountType, accountBalance);
    		request.getSession(false).setAttribute("account", new Account(accountNo, 0, firstName, lastName, accountType, "Active", accountBalance, null));
    		request.getRequestDispatcher("accountOpeningSuccessfulPage.jsp").forward(request, response);
		} catch (InvalidAmountException | InvalidAccountTypeException | BankingServicesDownException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("accountOpeningPage.jsp").forward(request, response);
		} 
	}
}

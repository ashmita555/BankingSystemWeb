package com.hdfc.banking.controllers;

import com.hdfc.banking.beans.Account;
import com.capgemini.banking.exceptions.*;
import com.hdfc.banking.services.BankingServices;
import com.hdfc.banking.services.BankingServicesImpl;
import com.hdfc.banking.exceptions.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FundsTransferServlet")
public class FundsTransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BankingServices bankingServices = new BankingServicesImpl();
    private static int ACTIVE_ACCOUNT_NUMBER;
	private static int NUMBER_OF_PIN_TRIES=0;
    public FundsTransferServlet() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		int accountNoFrom = ((Account) request.getSession(false).getAttribute("account")).getAccountNo();
        	int accountNoTo = Integer.parseInt(request.getParameter("accountNoTo"));
        	float transferAmount = Float.parseFloat(request.getParameter("transferAmount"));
        	int pinNumber = Integer.parseInt(request.getParameter("pinNumber"));
        	if (ACTIVE_ACCOUNT_NUMBER != accountNoFrom){
				NUMBER_OF_PIN_TRIES=0;
				ACTIVE_ACCOUNT_NUMBER = accountNoFrom;
			}
			if (NUMBER_OF_PIN_TRIES == 3) {
				bankingServices.changeAccountStatus(accountNoFrom, "Blocked");
				request.setAttribute("errorMessage", "You have exceeded the maximum PIN tries.\\nYour account has been blocked.\\nPlease contact the customer care to unblock the account");
				request.getRequestDispatcher("loginPage.jsp").forward(request, response);
			}
        	if (bankingServices.fundTransfer(accountNoTo, accountNoFrom, transferAmount, pinNumber)) {
        		request.setAttribute("resultMessage", "The fund transfer is Successful! The updated Account Balance = Rs. " + bankingServices.getAccountDetails(accountNoFrom).getAccountBalance());
    			request.getRequestDispatcher("fundsTransferPage.jsp").forward(request, response);
        	}
		} catch (InsufficientAmountException | AccountNotFoundException | BankingServicesDownException |
                 AccountBlockedException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("fundsTransferPage.jsp").forward(request, response);
		} catch (InvalidPinNumberException e) {
			request.setAttribute("errorMessage", "Enter a valid PIN number. You have " + (3 - (++NUMBER_OF_PIN_TRIES)) + " pin tries left.");
			request.getRequestDispatcher("fundsTransferPage.jsp").forward(request, response);
		} 
    }
}

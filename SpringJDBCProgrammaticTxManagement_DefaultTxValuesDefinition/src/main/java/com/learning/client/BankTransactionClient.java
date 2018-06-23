package com.learning.client;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learning.dao.exception.InsufficientAccountBalanceException;
import com.learning.model.Account;
import com.learning.service.BankService;

public class BankTransactionClient {

	public static void main(String[] args) throws InsufficientAccountBalanceException {

		AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
		BankService bankService = applicationContext.getBean("bankService", BankService.class);
		
		//creating sample account from which amount has to be transferred
		Account fromAccount = new Account();
		fromAccount.setAccountNumber(98760L);
		
		//creating the account holder in which amount has to be transfered
		Account toAccount = new Account();
		toAccount.setAccountNumber(12345L);
		
		//calling service method for complete transaction
		bankService.transferFund(fromAccount, toAccount, 1000.0);
		
		//Clossing application context
		applicationContext.close();

	}

}

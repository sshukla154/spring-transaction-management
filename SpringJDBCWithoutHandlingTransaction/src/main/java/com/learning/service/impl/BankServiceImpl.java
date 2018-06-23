package com.learning.service.impl;

import com.learning.dao.BankDAO;
import com.learning.dao.exception.InsufficientAccountBalanceException;
import com.learning.model.Account;
import com.learning.service.BankService;

public class BankServiceImpl implements BankService {

	private BankDAO bankDAO;

	public void setBankDAO(BankDAO bankDAO) {
		this.bankDAO = bankDAO;
	}

	public void transferFund(Account fromAccount, Account toAccount, Double amount) {
		try {
			bankDAO.withdrawMoney(fromAccount, amount);
			bankDAO.depositeMoney(toAccount, amount);
		} catch (InsufficientAccountBalanceException e) {
			System.out.println("Problem occured while transaction : " + e.getMessage());
		}
	}

}

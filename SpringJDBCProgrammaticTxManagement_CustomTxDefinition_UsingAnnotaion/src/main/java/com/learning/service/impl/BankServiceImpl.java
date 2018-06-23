package com.learning.service.impl;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.learning.dao.BankDAO;
import com.learning.dao.exception.InsufficientAccountBalanceException;
import com.learning.model.Account;
import com.learning.service.BankService;

public class BankServiceImpl implements BankService {

	private BankDAO bankDAO;

	public void setBankDAO(BankDAO bankDAO) {
		this.bankDAO = bankDAO;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, timeout = 100, rollbackFor = Exception.class)
	public void transferFund(Account fromAccount, Account toAccount, Double amount)
			throws InsufficientAccountBalanceException {
		bankDAO.withdrawMoney(fromAccount, amount);
		bankDAO.depositeMoney(toAccount, amount);
	}

}

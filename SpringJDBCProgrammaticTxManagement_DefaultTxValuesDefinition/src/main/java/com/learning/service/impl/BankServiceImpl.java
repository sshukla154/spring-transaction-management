package com.learning.service.impl;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.learning.dao.BankDAO;
import com.learning.dao.exception.InsufficientAccountBalanceException;
import com.learning.model.Account;
import com.learning.service.BankService;

public class BankServiceImpl implements BankService {

	private BankDAO bankDAO;

	private TransactionTemplate transactionTemplate;

	public void setBankDAO(BankDAO bankDAO) {
		this.bankDAO = bankDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void transferFund(final Account fromAccount, final Account toAccount, final Double amount) {
		transactionTemplate.execute(new TransactionCallback<Void>() {
			public Void doInTransaction(TransactionStatus status) {
				try {
					bankDAO.withdrawMoney(fromAccount, amount);
					bankDAO.depositeMoney(toAccount, amount);
				} catch (InsufficientAccountBalanceException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

}

package com.learning.service;

import com.learning.dao.exception.InsufficientAccountBalanceException;
import com.learning.model.Account;

public interface BankService {
	
	public void transferFund(Account fromAccount, Account toAccount, Double amount) throws InsufficientAccountBalanceException;

}

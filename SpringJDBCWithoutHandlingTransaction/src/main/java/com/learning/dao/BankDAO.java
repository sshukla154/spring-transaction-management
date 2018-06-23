package com.learning.dao;

import com.learning.dao.exception.InsufficientAccountBalanceException;
import com.learning.model.Account;

public interface BankDAO {

	public void withdrawMoney(Account fromAccount, Double amount) throws InsufficientAccountBalanceException;
	public void depositeMoney(Account toAccount, Double amount);
}

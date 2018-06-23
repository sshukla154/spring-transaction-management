package com.learning.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.learning.dao.BankDAO;
import com.learning.dao.exception.InsufficientAccountBalanceException;
import com.learning.model.Account;

public class BankDAOImpl implements BankDAO {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void withdrawMoney(Account fromAccount, Double amount) throws InsufficientAccountBalanceException {
		Account accountFromDB = getAccountFromDB(fromAccount.getAccountNumber());
		Double accountBalanceAfterDeduction = accountFromDB.getAccountBalance() - amount;
		if (accountFromDB.getAccountBalance() >= amount) {
			String stringQuery = "UPDATE bank_table SET account_balance=? WHERE account_number = ?";
			int response = jdbcTemplate.update(stringQuery, accountBalanceAfterDeduction,
					fromAccount.getAccountNumber());
			if (response > 0) {
				System.out.println("Ammount RS." + amount + " is deducted from Account Number : "
						+ fromAccount.getAccountNumber());
			}
		} else {
			throw new InsufficientAccountBalanceException("Insufficient Account Balance");
		}

	}

	private Account getAccountFromDB(Long accountNumber) {
		String sqlQuery = "SELECT * FROM bank_table WHERE account_number = ?";
		return jdbcTemplate.queryForObject(sqlQuery, new AccountRowMapper(), accountNumber);

	}

	public void depositeMoney(Account toAccount, Double amount) {
		Account accountFromDB = getAccountFromDB(toAccount.getAccountNumber());
		Double accountBalance = accountFromDB.getAccountBalance() + amount;
		String stringQuery = "UPDATE bank_table SET account_balance=? WHERE account_number = ?";
		int response = jdbcTemplate.update(stringQuery, accountBalance, toAccount.getAccountNumber());
		if (response > 0) {
			System.out.println(
					"Ammount RS." + amount + " is deposited in Account Number : " + toAccount.getAccountNumber());
		}
		throw new RuntimeException();

	}

}

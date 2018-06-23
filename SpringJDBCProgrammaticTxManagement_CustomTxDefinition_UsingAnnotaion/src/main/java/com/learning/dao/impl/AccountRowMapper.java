package com.learning.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.learning.model.Account;

public class AccountRowMapper implements RowMapper<Account> {

	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account account = new Account();
		account.setAccountNumber(rs.getLong("account_number"));
		account.setAccountHolderName(rs.getString("account_holder_name"));
		account.setAccountBalance(rs.getDouble("account_balance"));
		account.setAccountType(rs.getString("account_type"));
		return account;
	}

}

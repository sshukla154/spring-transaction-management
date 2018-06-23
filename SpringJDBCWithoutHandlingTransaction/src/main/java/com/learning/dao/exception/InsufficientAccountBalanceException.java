package com.learning.dao.exception;

public class InsufficientAccountBalanceException extends Exception {

	private static final long serialVersionUID = -6969144276067071082L;

	String message = "";

	public InsufficientAccountBalanceException(String message) {
		super();
		this.message = message;
	}

}

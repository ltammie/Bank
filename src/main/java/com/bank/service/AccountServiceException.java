package com.bank.service;

public class AccountServiceException  extends Exception {
	public AccountServiceException() {
		super();
	}

	public AccountServiceException(String message) {
		super(message);
	}

	public AccountServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

package com.bank.domain;

import java.io.Serializable;

public class Account implements Serializable {
	private Integer id;
	private Integer clientId;
	private Long balance;
	private String accountNumber;

	public Account(Integer id, Integer clientId, Long balance, String accountNumber) {
		this.id = id;
		this.clientId = clientId;
		this.balance = balance;
		this.accountNumber = accountNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", clientId=" + clientId +
				", balance=" + balance +
				", accountNumber='" + accountNumber + '\'' +
				'}';
	}
}

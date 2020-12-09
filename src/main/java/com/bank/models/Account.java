package com.bank.models;

import java.util.Objects;

public class Account {
	private Long id;
	private Long clientId;
	private Long balance;
	private String accountNumber;

	public Account(Long id, Long clientId, Long balance, String accountNumber) {
		this.id = id;
		this.clientId = clientId;
		this.balance = balance;
		this.accountNumber = accountNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Account account = (Account) o;
		return id.equals(account.id) && clientId.equals(account.clientId) && balance.equals(account.balance) && accountNumber.equals(account.accountNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, clientId, balance, accountNumber);
	}
}

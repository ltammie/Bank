package com.bank.domain;

public class Account {
	private Long id;
	private Integer clientId;
	private Long balance;


	public Account(Long id, Integer clientId, Long balance) {
		this.id = id;
		this.clientId = clientId;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", clientId=" + clientId +
				", balance=" + balance +
				'}';
	}
}

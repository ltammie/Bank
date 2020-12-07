package com.bank.domain;

import java.util.Date;

public class Card {
	private Long cardNumber;
	private Integer accountId;
	private Integer clientId;
	private Date expirationDate;

	public Card(Long cardNumber, Integer accountId, Integer clientId, Date expirationDate) {
		this.cardNumber = cardNumber;
		this.accountId = accountId;
		this.clientId = clientId;
		this.expirationDate = expirationDate;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "Card{" +
				"cardNumber=" + cardNumber +
				", accountId=" + accountId +
				", clientId=" + clientId +
				", expirationDate=" + expirationDate +
				'}';
	}
}

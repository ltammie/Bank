package com.bank.domain;

import java.io.Serializable;
import java.util.Date;

public class Card implements Serializable {
	private Integer id;
	private Integer accountId;
	private Integer clientId;
	private Date expirationDate;
	private String cardNumber;


	public Card(Integer id, Integer accountId, Integer clientId, Date expirationDate, String cardNumber) {
		this.id = id;
		this.accountId = accountId;
		this.clientId = clientId;
		this.expirationDate = expirationDate;
		this.cardNumber = cardNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public String toString() {
		return "Card{" +
				"id=" + id +
				", accountId=" + accountId +
				", clientId=" + clientId +
				", expirationDate=" + expirationDate +
				", cardNumber='" + cardNumber + '\'' +
				'}';
	}
}

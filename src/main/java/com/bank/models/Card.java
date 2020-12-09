package com.bank.models;

import java.util.Date;
import java.util.Objects;

public class Card {
	private Long id;
	private Long accountId;
	private Long clientId;
	private Date expirationDate;
	private String cardNumber;


	public Card(Long id, Long accountId, Long clientId, Date expirationDate, String cardNumber) {
		this.id = id;
		this.accountId = accountId;
		this.clientId = clientId;
		this.expirationDate = expirationDate;
		this.cardNumber = cardNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return id.equals(card.id) && accountId.equals(card.accountId) && clientId.equals(card.clientId) && expirationDate.equals(card.expirationDate) && cardNumber.equals(card.cardNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, accountId, clientId, expirationDate, cardNumber);
	}
}

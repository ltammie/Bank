package com.bank.models;

import java.util.Objects;

public class CounterAgent {
	private Long id;
	private String name;
	private String inn;
	private Long balance;

	public CounterAgent(Long id, String name, String inn, Long balance) {
		this.id = id;
		this.name = name;
		this.inn = inn;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CounterAgent that = (CounterAgent) o;
		return id.equals(that.id) && name.equals(that.name) && inn.equals(that.inn) && balance.equals(that.balance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, inn, balance);
	}

	@Override
	public String toString() {
		return "CounterAgent{" +
				"id=" + id +
				", name='" + name + '\'' +
				", inn='" + inn + '\'' +
				", balance=" + balance +
				'}';
	}
}

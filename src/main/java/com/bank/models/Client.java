package com.bank.models;

import java.util.Objects;

public class Client {
	private Long id;
	private String name;
	private String phoneNumber;
	private String passport;


	public Client(Long id, String name, String phoneNumber, String passport) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.passport = passport;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	@Override
	public String toString() {
		return "Client{" +
				"id=" + id +
				", name='" + name + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", passport='" + passport + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return id.equals(client.id) && name.equals(client.name) && phoneNumber.equals(client.phoneNumber) && passport.equals(client.passport);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, phoneNumber, passport);
	}
}

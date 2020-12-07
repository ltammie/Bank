package com.bank.domain;

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
}

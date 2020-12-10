package com.bank.service;

import java.util.List;

public interface AccountService<E, K> {
	void createNewCard(K k);
	List<E> getAllCards(K k);
	void depositMoney(K k);
	E getBalance(K k);
}

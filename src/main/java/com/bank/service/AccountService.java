package com.bank.service;

import java.util.List;

public interface AccountService<E, T, K> {
	void createNewCard(T t) throws AccountServiceException;
	List<T> getAllCards(K k) throws AccountServiceException;
	void depositMoney(E e) throws AccountServiceException;
	E getBalance(K k) throws AccountServiceException;
}

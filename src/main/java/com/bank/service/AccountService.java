package com.bank.service;

import java.util.List;

public interface AccountService<E, T, K> {
	void createNewCard(T t) throws ServiceException;
	List<T> getAllCards(K k) throws ServiceException;
	void depositMoney(E e) throws ServiceException;
	E getBalance(K k) throws ServiceException;
}

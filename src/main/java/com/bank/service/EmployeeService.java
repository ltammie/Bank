package com.bank.service;

public interface EmployeeService<E, T, K> {
	void addNewClient(E e) throws ServiceException;
	void openNewAccount(T n, T p) throws ServiceException;
}

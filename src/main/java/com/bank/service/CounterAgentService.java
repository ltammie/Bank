package com.bank.service;

import java.util.List;

public interface CounterAgentService<E, T, K> {
	void addNewAgent(E t) throws ServiceException;
	List<E> getAllAgents() throws ServiceException;
	void addMoneyToAgent(K k, T t) throws ServiceException;
}

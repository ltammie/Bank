package com.bank.service;

import com.bank.dao.CounterAgentDao;
import com.bank.dao.DaoException;
import com.bank.dao.impl.CounterAgentDaoH2Impl;
import com.bank.models.Account;
import com.bank.models.Card;
import com.bank.models.CounterAgent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

public class CounterAgentServiceImpl implements CounterAgentService<CounterAgent, Long, String>{
	private static Logger Log;
	private final DataSource ds;
	private final CounterAgentDao dao;

	public CounterAgentServiceImpl(DataSource ds) {
		this.ds = ds;
		dao = new CounterAgentDaoH2Impl(ds);
		Log  = LogManager.getLogger(CounterAgentService.class.getName());
	}

	@Override
	public void addNewAgent(CounterAgent t) throws ServiceException {
		try {
			dao.save(t);
		} catch (DaoException e) {
			Log.error("Failed to add counter agent=" + t.toString(), e);
			throw new ServiceException(e.getMessage(), e);
		}

	}

	@Override
	public List<CounterAgent> getAllAgents() throws ServiceException {
		List<CounterAgent> counterAgents = new LinkedList<>();
		try {
			counterAgents = dao.findAll();
		} catch (DaoException e) {
			Log.error("Failed to get all counter agents from the database", e);
			throw new ServiceException(e.getMessage(), e);
		}
		return counterAgents;
	}

	@Override
	public void addMoneyToAgent(String s, Long money) throws ServiceException {
		try {
			CounterAgent agent = dao.getCounterAgentByInn(s);
			if (agent != null) {
				agent.setBalance(agent.getBalance() + money);
				dao.update(agent);
			} else
				throw new DaoException();
		} catch (DaoException e) {
			Log.error("Failed to deposit money for counter agent with inn=" , e);
			throw new ServiceException(e.getMessage(), e);
		}

	}
}

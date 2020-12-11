package com.bank.dao;

import com.bank.models.CounterAgent;

public interface CounterAgentDao extends Dao<CounterAgent, Long>  {
	CounterAgent getCounterAgentByInn(String inn) throws DaoException;
}

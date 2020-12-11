package com.bank.service;

import com.bank.dao.DaoException;
import com.bank.dao.impl.AccountDaoH2Impl;
import com.bank.dao.impl.CardDaoH2Impl;
import com.bank.dao.impl.ClientDaoH2Impl;
import com.bank.models.Account;
import com.bank.models.Card;
import com.bank.models.Client;
import com.bank.models.CounterAgent;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class CounterAgentServiceImplTest {
	private static DataSource ds;
	private static CounterAgentServiceImpl service;

	@BeforeAll
	static void init() {
		try {
			Properties properties = new Properties();
			properties.load(AccountServiceImplTest.class.getResourceAsStream("/db_test.properties"));
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(properties.getProperty("db.url"));
			config.setUsername(properties.getProperty("db.user"));
			config.setPassword(properties.getProperty("db.password"));
			config.setDriverClassName(properties.getProperty("db.driver.name"));
			ds = new HikariDataSource(config);
			service = new CounterAgentServiceImpl(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeEach
	public void reset(){
		try (Reader reader = new InputStreamReader(AccountServiceImplTest.class.getResourceAsStream("/schema.sql"));
			 Connection con = ds.getConnection()) {
			RunScript.execute(con, reader);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void addNewAgent() throws ServiceException {
		List<CounterAgent> counterAgents = new LinkedList<>();
		counterAgents.add(new CounterAgent(1L, "Agent 2", "1234512345", 500L));
		service.addNewAgent(counterAgents.get(0));
		assertEquals(counterAgents, service.getAllAgents());
	}

	@Test
	void getAllAgents() throws ServiceException {
		List<CounterAgent> counterAgents = new LinkedList<>();
		counterAgents.add(new CounterAgent(1L, "Agent 1", "1234512345", 500L));
		counterAgents.add(new CounterAgent(2L, "Agent 2", "0000011111", 1000L));
		for (CounterAgent agent: counterAgents) {
			service.addNewAgent(agent);
		}
		assertEquals(counterAgents, service.getAllAgents());
	}

	@Test
	void addMoneyToAgent() throws ServiceException {
		Long moneyToAdd = 600L;
		CounterAgent test = new CounterAgent(1L, "Agent 2", "1234512345", 500L);
		Long newBalance = test.getBalance() + moneyToAdd;
		service.addNewAgent(test);
		service.addMoneyToAgent(test.getInn(), moneyToAdd);
		assertEquals(newBalance, service.getAllAgents().get(0).getBalance());
	}
}
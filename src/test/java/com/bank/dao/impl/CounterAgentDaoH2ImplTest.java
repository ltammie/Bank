package com.bank.dao.impl;

import com.bank.dao.DaoException;
import com.bank.models.CounterAgent;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class CounterAgentDaoH2ImplTest {
	private static DataSource ds;
	private static CounterAgentDaoH2Impl dao;

	@BeforeAll
	static void init() {
		try {
			Properties properties = new Properties();
			properties.load(ClientDaoH2ImplTest.class.getResourceAsStream("/db_test.properties"));
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(properties.getProperty("db.url"));
			config.setUsername(properties.getProperty("db.user"));
			config.setPassword(properties.getProperty("db.password"));
			config.setDriverClassName(properties.getProperty("db.driver.name"));
			ds = new HikariDataSource(config);
			dao = new CounterAgentDaoH2Impl(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeEach
	public void reset() {
		try (Reader reader = new InputStreamReader(ClientDaoH2ImplTest.class.getResourceAsStream("/schema.sql"));
			 Connection con = ds.getConnection()) {
			RunScript.execute(con, reader);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void getCounterAgentByInn() throws DaoException {
		CounterAgent test = new CounterAgent(1L, "Agent 1", "0000011111", 1000L);
		dao.save(test);
		CounterAgent real = dao.getCounterAgentByInn("0000011111");
		assertEquals(test, real);
	}

	@Test
	void findById() throws DaoException {
		CounterAgent test = new CounterAgent(1L, "Agent 2", "1234512345", 500L);
		dao.save(test);
		CounterAgent real = dao.findById(1L);
		assertEquals(test, real);
	}

	@Test
	void findAll() throws DaoException{
		List<CounterAgent> demos = new LinkedList<>();
		demos.add(new CounterAgent(1L, "Agent 1", "0000011111", 1000L));
		demos.add(new CounterAgent(2L, "Agent 2", "1234512345", 500L));
		demos.add(new CounterAgent(3L, "Agent 3", "8888844444", 0L));

		for (CounterAgent demo: demos) {
			dao.save(demo);
		}
		assertEquals(demos, dao.findAll());
	}

	@Test
	void save() throws DaoException{
		CounterAgent test = new CounterAgent(1L, "Agent 3", "8888844444", 0L);
		dao.save(test);
		assertEquals(test, dao.findById(1L));
	}

	@Test
	void update() throws DaoException {
		CounterAgent test = new CounterAgent(1L, "Agent 3", "8888844444", 0L);
		dao.save(test);
		test.setName("SPACE-X");
		test.setInn("4444477777");
		test.setBalance(1_000_000_0L);
		dao.update(test);
		assertEquals(test, dao.findById(1L));
	}

	@Test
	void delete() throws DaoException{
		CounterAgent test = new CounterAgent(1L, "Agent 3", "8888844444", 0L);
		dao.save(test);
		dao.delete(1L);
		assertEquals(0, dao.findAll().size());
	}
}
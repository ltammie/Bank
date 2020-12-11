package com.bank.dao.impl;

import com.bank.dao.DaoException;
import com.bank.models.Account;
import com.bank.models.Client;
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

class AccountDaoH2ImplTest {
	private static DataSource ds;
	private static AccountDaoH2Impl dao;
	private static ClientDaoH2Impl clientDaoH2;

	@BeforeAll
	static void init() {
		try {
			Properties properties = new Properties();
			properties.load(AccountDaoH2ImplTest.class.getResourceAsStream("/db_test.properties"));
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(properties.getProperty("db.url"));
			config.setUsername(properties.getProperty("db.user"));
			config.setPassword(properties.getProperty("db.password"));
			config.setDriverClassName(properties.getProperty("db.driver.name"));
			ds = new HikariDataSource(config);
			dao = new AccountDaoH2Impl(ds);
			clientDaoH2 = new ClientDaoH2Impl(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeEach
	public void reset() throws DaoException {
		try (Reader reader = new InputStreamReader(AccountDaoH2ImplTest.class.getResourceAsStream("/schema.sql"));
			 Connection con = ds.getConnection()) {
			RunScript.execute(con, reader);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		clientDaoH2.save(new Client(1L, "John Wick", "8-800-555-35-35", "4132532543"));
		clientDaoH2.save(new Client(2L, "Bob Wick", "8-300-455-22-11", "6767222333"));
		clientDaoH2.save(new Client(3L, "Max Caulfield", "1-111-222-33-44", "1111222000"));
		clientDaoH2.save(new Client(4L, "The Dude", "4-333-222-11-00", "0000111000"));
		clientDaoH2.save(new Client(5L, "Bob Marlin", "6-333-444-22-99", "9999000888"));
	}

	@Test
	void findById() throws DaoException {
		Account test = new Account(1L, 1L, 1_000_000L, "12345123451234512345");
		dao.save(test);
		assertEquals(test, dao.findById(1L));
	}

	@Test
	void findAll() throws DaoException {
		List<Account> demos = new LinkedList<>();
		demos.add(new Account(1L, 1L, 1_000_000L, "12345123451234512345"));
		demos.add(new Account(2L, 2L, 0L, "00000111110000011111"));
		demos.add(new Account(3L, 3L, 300_000L, "22222333334444455555"));
		demos.add(new Account(4L, 4L, 599_300L, "12345000001234500000"));
		demos.add(new Account(5L, 5L, 123_456L, "99999000008888800000"));

		for (Account demo: demos) {
			dao.save(demo);
		}
		assertEquals(demos, dao.findAll());
	}

	@Test
	void save() throws DaoException {
		Account test = new Account(1L, 1L, 1_000_000L, "12345123451234512345");
		dao.save(test);
		assertEquals(test, dao.findById(1L));
	}

	@Test
	void update() throws DaoException {
		Account test = new Account(1L, 5L, 44L, "00000111110000011111");
		dao.save(test);
		test.setBalance(322L);
		test.setClientId(2L);
		dao.update(test);
		assertEquals(test, dao.findById(1L));
	}

	@Test
	void delete() throws DaoException {
		Account test = new Account(1L, 3L, 1_000L, "11111222223333344444");
		dao.save(test);
		dao.delete(1L);
		assertEquals(0, dao.findAll().size());
	}

	@Test
	void getAccountByNumber() throws DaoException {
		Account test = new Account(1L, 3L, 1_000L, "00000222223333344444");
		dao.save(test);
		assertEquals(test, dao.getAccountByNumber(test.getAccountNumber()));

	}
}
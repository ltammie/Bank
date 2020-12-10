package com.bank.dao.impl;

import com.bank.dao.DaoException;
import com.bank.models.Account;
import com.bank.models.Card;
import com.bank.models.Client;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CardDaoH2ImplTest {
	private static DataSource ds;
	private static CardDaoH2Impl dao;
	private static ClientDaoH2Impl clientDaoH2;
	private static AccountDaoH2Impl accountDaoH2;

	@BeforeAll
	static void init() {
		try {
			Properties properties = new Properties();
			properties.load(CardDaoH2ImplTest.class.getResourceAsStream("/db.properties"));
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(properties.getProperty("db.url"));
			config.setUsername(properties.getProperty("db.user"));
			config.setPassword(properties.getProperty("db.password"));
			config.setDriverClassName(properties.getProperty("db.driver.name"));
			ds = new HikariDataSource(config);
			dao = new CardDaoH2Impl(ds);
			clientDaoH2 = new ClientDaoH2Impl(ds);
			accountDaoH2 = new AccountDaoH2Impl(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeEach
	public void reset() throws DaoException {
		try (Reader reader = new FileReader("src/test/resources/schema.sql");
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
		accountDaoH2.save(new Account(1L, 1L, 1_000_000L, "12345123451234512345"));
		accountDaoH2.save(new Account(2L, 2L, 0L, "00000111110000011111"));
		accountDaoH2.save(new Account(3L, 3L, 300_000L, "22222333334444455555"));
		accountDaoH2.save(new Account(4L, 4L, 599_300L, "12345000001234500000"));
		accountDaoH2.save(new Account(5L, 5L, 123_456L, "99999000008888800000"));
	}

	@Test
	void findById() throws DaoException {
		Card test = new Card(1L, 2L, 2L,  new Date().toString(), "1111222233334444");
		dao.save(test);
		assertEquals(test, dao.findById(1L));
	}

	@Test
	void findAll() throws DaoException {
		List<Card> demos = new LinkedList<>();
		demos.add(new Card(1L, 1L, 1L,  new Date().toString(), "1111222233334444"));
		demos.add(new Card(2L, 2L, 2L,  new Date().toString(), "1111000022223333"));
		demos.add(new Card(3L, 3L, 3L,  new Date().toString(), "0000111122223333"));
		demos.add(new Card(4L, 4L, 4L,  new Date().toString(), "4444222233331111"));
		demos.add(new Card(5L, 5L, 5L,  new Date().toString(), "0000222200002222"));

		for (Card demo: demos) {
			dao.save(demo);
		}
		assertEquals(demos, dao.findAll());
	}

	@Test
	void save() throws DaoException {
		Card test = new Card(1L, 2L, 2L,  new Date().toString(), "1111222233334444");
		dao.save(test);
		assertEquals(test, dao.findById(1L));
	}

	@Test
	void update() throws DaoException {
		Card test = new Card(1L, 2L, 2L, new Date().toString(), "4444222233331111");
		dao.save(test);
		test.setExpirationDate("kek");
		test.setCardNumber("0000111122223333");
		dao.update(test);
		assertEquals(test, dao.findById(1L));
	}

	@Test
	void delete() throws DaoException {
		Card test = new Card(1L, 3L, 3L, new Date().toString(), "4444222233331111");
		dao.save(test);
		dao.delete(1L);
		assertEquals(0, dao.findAll().size());
	}

	@Test
	void findByAccountId() throws DaoException{
		List<Card> cards= new LinkedList<>();
		cards.add(new Card(1L, 1L, 1L,  new Date().toString(), "1111222233334444"));
		cards.add(new Card(2L, 1L, 1L,  new Date().toString(), "3333222211112222"));
		for (Card demo: cards) {
			dao.save(demo);
		}
		assertEquals(cards, dao.findByAccountId(1L));
	}
}
package com.bank.service;

import com.bank.dao.DaoException;
import com.bank.dao.factory.DaoFactory;
import com.bank.dao.factory.H2DaoFactory;
import com.bank.dao.impl.AccountDaoH2Impl;
import com.bank.dao.impl.CardDaoH2Impl;
import com.bank.dao.impl.ClientDaoH2Impl;
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
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {
	private static DataSource ds;
	private static ClientDaoH2Impl clientDaoH2;
	private static AccountDaoH2Impl accountDaoH2;
	private static CardDaoH2Impl cardDaoH2;
	private static AccountServiceImpl accountService;

	@BeforeAll
	static void init() {
		try {
			Properties properties = new Properties();
			properties.load(H2DaoFactory.class.getResourceAsStream("/db.properties"));
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(properties.getProperty("db.url"));
			config.setUsername(properties.getProperty("db.user"));
			config.setPassword(properties.getProperty("db.password"));
			config.setDriverClassName(properties.getProperty("db.driver.name"));
			ds = new HikariDataSource(config);
			DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.H2);
			assert daoFactory != null;
			clientDaoH2 = (ClientDaoH2Impl)daoFactory.getClientDao();
			accountDaoH2 = (AccountDaoH2Impl) daoFactory.getAccountDao();
			cardDaoH2 = (CardDaoH2Impl) daoFactory.getCardDao();
			accountService = new AccountServiceImpl();
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
	void createNewCard() throws AccountServiceException {
		List<Card> cards = new LinkedList<Card>();
		cards.add(new Card(1L, 3L, 3L,  new Date().toString(), "0000111122223333"));
		accountService.createNewCard(cards.get(0));

		assertEquals(cards, accountService.getAllCards(3L));
	}

	@Test
	void getAllCards() throws AccountServiceException {
		List<Card> cards = new LinkedList<Card>();
		cards.add(new Card(1L, 3L, 3L,  new Date().toString(), "0000111122223333"));
		cards.add(new Card(2L, 3L, 3L,  new Date().toString(), "2222000011114444"));
		for (Card card: cards) {
			accountService.createNewCard(card);
		}
		assertEquals(cards, accountService.getAllCards(3L));
	}

	@Test
	void depositMoney() throws DaoException, AccountServiceException {
		Long newBalance = 123L;
		Account account = accountDaoH2.findById(2L);
		account.setBalance(123L);
		accountService.depositMoney(account);
		assertEquals(newBalance, accountService.getBalance(2L).getBalance());
	}

	@Test
	void getBalance() throws DaoException, AccountServiceException {
		Account account = accountDaoH2.findById(4L);
		assertEquals(account.getBalance(), accountService.getBalance(4L).getBalance());
	}
}
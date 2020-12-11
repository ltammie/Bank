package com.bank.dao.impl;

import com.bank.dao.DaoException;
import com.bank.models.Client;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientDaoH2ImplTest {
	private static DataSource ds;
	private static ClientDaoH2Impl dao;


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
			dao = new ClientDaoH2Impl(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeEach
	public void reset() {
		try (Reader reader = new InputStreamReader(ClientDaoH2ImplTest.class.getResourceAsStream("/schema.sql"));
			 Connection con = ds.getConnection()) {
			RunScript.execute(con, reader);
		} catch (SQLException  | IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void findById() throws DaoException {
		Client test = new Client(1L, "John Wick", "8-800-555-35-35", "4132532543");
		dao.save(test);
		assertEquals(test, dao.findById(1L));
	}

	@Test
	void save() throws DaoException {
		Client test = new Client(1L, "Max Caulfield", "1-111-222-33-44", "1111222000");
		dao.save(test);
		assertEquals(test, dao.findById(1L));
	}

	@Test
	void update() throws DaoException {
		Client test = new Client(1L, "The Dude", "4-333-222-11-00", "0000111000");
		dao.save(test);
		test.setName("2B");
		test.setPhoneNumber("1-222-333-44-55");
		dao.update(test);
		assertEquals(test, dao.findById(1L));
	}

	@Test
	void delete() throws DaoException  {
		Client test = new Client(1L, "Bob Marlin", "6-333-444-22-99", "9999000888");
		dao.save(test);
		dao.delete(1L);
		assertEquals(0, dao.findAll().size());
	}

	@Test
	void findAll() throws DaoException {
		List<Client> demos = new LinkedList<>();
		demos.add(new Client(1L, "John Wick", "8-800-555-35-35", "4132532543"));
		demos.add(new Client(2L, "Bob Wick", "8-300-455-22-11", "6767222333"));
		demos.add(new Client(3L, "Max Caulfield", "1-111-222-33-44", "1111222000"));
		demos.add(new Client(4L, "The Dude", "4-333-222-11-00", "0000111000"));
		demos.add(new Client(5L, "Bob Marlin", "6-333-444-22-99", "9999000888"));

		for (Client demo: demos) {
			dao.save(demo);
		}
		assertEquals(demos, dao.findAll());
	}

	@Test
	void findClientByPassport() throws DaoException  {
		Client test = new Client(1L, "Max Caulfield", "1-111-222-33-44", "1111222000");
		dao.save(test);
		Client check = dao.findClientByPassport(test.getPassport());
		assertEquals(test, check);
	}
}
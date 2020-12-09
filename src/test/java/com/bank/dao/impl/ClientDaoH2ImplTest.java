package com.bank.dao.impl;

import com.bank.dao.ClientDao;
import com.bank.dao.Dao;
import com.bank.dao.DaoException;
import com.bank.dao.factory.DaoFactory;
import com.bank.dao.factory.H2DaoFactory;
import com.bank.models.Client;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ClientDaoH2ImplTest {
	private static DataSource ds;

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeEach
	public void reset() {
		try (Reader reader = new FileReader("src/test/resources/schema.sql");
			 Connection con = ds.getConnection()) {
			RunScript.execute(con, reader);
		} catch (SQLException  | IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void findById_Test() throws DaoException {
		DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.H2);
		assert daoFactory != null;
		ClientDaoH2Impl dao = (ClientDaoH2Impl)daoFactory.getClientDao();
		Client client = dao.findById(1L);
		assertEquals(1L, client.getId());
		assertEquals("John", client.getName());
		assertEquals("1-222-333-44-55", client.getPhoneNumber());
		assertEquals("1234321123", client.getPassport());
	}

}
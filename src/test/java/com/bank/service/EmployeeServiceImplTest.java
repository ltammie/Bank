package com.bank.service;

import com.bank.dao.DaoException;
import com.bank.dao.impl.AccountDaoH2Impl;
import com.bank.dao.impl.CardDaoH2Impl;
import com.bank.dao.impl.ClientDaoH2Impl;
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
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
	private static DataSource ds;
	private static EmployeeServiceImpl employeeService;
	private static ClientDaoH2Impl clientDaoH2;
	private static AccountDaoH2Impl accountDaoH2;


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
			employeeService = new EmployeeServiceImpl(ds);
			clientDaoH2 = new ClientDaoH2Impl(ds);
			accountDaoH2 = new AccountDaoH2Impl(ds);
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
	void addNewClient() throws ServiceException, DaoException {
		Client client = new Client(1L, "Max Caulfield", "1-111-222-33-44", "1111222000");
		employeeService.addNewClient(client);
		Client check = clientDaoH2.findClientByPassport(client.getPassport());
		assertEquals(client, check);
	}

	@Test
	void openNewAccount() throws DaoException, ServiceException {
		Client client = new Client(1L, "Max Caulfield", "1-111-222-33-44", "1111222000");
		clientDaoH2.save(client);
		Account test = new Account(1L, 1L, 0L, "99999000008888800000");
		employeeService.openNewAccount(test.getAccountNumber(), client.getPassport());
		Account check =accountDaoH2.getAccountByNumber(test.getAccountNumber());
		assertEquals(test, check);
	}
}
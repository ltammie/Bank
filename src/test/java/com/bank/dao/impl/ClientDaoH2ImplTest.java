package com.bank.dao.impl;

import com.bank.dao.factory.H2DaoFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


class ClientDaoH2ImplTest extends DBTestCase {
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
	void findById() {

	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return null;
	}
}
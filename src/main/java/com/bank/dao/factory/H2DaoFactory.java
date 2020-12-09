package com.bank.dao.factory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.bank.dao.ClientDao;
import com.bank.dao.impl.ClientDaoH2Impl;

public class H2DaoFactory extends DaoFactory{
	private static final Logger Log = LogManager.getLogger(H2DaoFactory.class.getName());
	private static final HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	static {
		try {
			Properties properties = new Properties();
			InputStream in = H2DaoFactory.class.getResourceAsStream("/db.properties");
			properties.load(in);
			config.setJdbcUrl(properties.getProperty("db.url"));
			config.setUsername(properties.getProperty("db.user"));
			config.setPassword(properties.getProperty("db.password"));
			config.setDriverClassName(properties.getProperty("db.driver.name"));
			ds = new HikariDataSource(config);
		} catch (Exception e) {
			Log.error("Failed to load database properties file!", e);
		}
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	@Override
	public ClientDao getClientDao() {
		return new ClientDaoH2Impl();
	}
}

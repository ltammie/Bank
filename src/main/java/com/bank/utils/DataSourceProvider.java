package com.bank.utils;

import com.bank.dao.factory.H2DaoFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceProvider {
	private static final Logger Log = LogManager.getLogger(H2DaoFactory.class.getName());
	private static DataSource dataSource;

	static {
		try {
			Properties properties = new Properties();
			properties.load(H2DaoFactory.class.getResourceAsStream("/WEB-INF/classes/db.properties"));
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(properties.getProperty("db.url"));
			config.setUsername(properties.getProperty("db.user"));
			config.setPassword(properties.getProperty("db.password"));
			config.setDriverClassName(properties.getProperty("db.driver.name"));
			config.setAutoCommit(false);
			dataSource = new HikariDataSource(config);
		} catch (Exception e) {
			Log.error("Failed to load database properties file!", e);
			e.printStackTrace();
		}
	}

	public static DataSource getDatasource() {
		return dataSource;
	}
}
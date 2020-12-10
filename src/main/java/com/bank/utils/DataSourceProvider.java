package com.bank.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceProvider {
	private static final Logger Log = LogManager.getLogger(DataSourceProvider.class.getName());
	private static DataSource dataSource;

	static {
		try {
//			Properties properties = new Properties();
//			properties.load(DataSourceProvider.class.getResourceAsStream("WEB-INF/classes/db.properties"));
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=runscript from 'classpath:/schema.sql';MODE=PostgreSQL;");
			config.setUsername("sa");
			config.setPassword("password");
			config.setDriverClassName("org.h2.Driver");
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

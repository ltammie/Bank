package com.bank.dao.factory;

import com.bank.dao.AccountDao;
import com.bank.dao.CardDao;
import com.bank.dao.ClientDao;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class H2DaoFactory extends DaoFactory{
	private static final Logger Log = Logger.getLogger(H2DaoFactory.class.getName() );
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	static {
		try {
			Properties properties = new Properties();
			FileInputStream in = new FileInputStream("/external/configuration/dir/db.properties");
			properties.load(in);
			in.close();

			config.setJdbcUrl(properties.getProperty("db.url"));
			config.setUsername(properties.getProperty("db.user"));
			config.setPassword(properties.getProperty("db.password"));
			config.setDriverClassName(properties.getProperty("db.driver.name"));
			ds = new HikariDataSource(config);
		} catch (IOException e) {
			Log.error("Failed to load database properties file!", e);
		}
	}

	@Override
	public ClientDao getCustomerDAO() {
		return null;
	}

	@Override
	public AccountDao getAccountDAO() {
		return null;
	}

	@Override
	public CardDao getOrderDAO() {
		return null;
	}
}

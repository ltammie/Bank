package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.dao.ClientDao;
import com.bank.dao.DaoException;
import com.bank.dao.factory.DaoFactory;
import com.bank.domain.Client;

public class ClientDaoImpl implements ClientDao<Client, Integer> {
	private static Logger Log = Logger.getLogger(ClientDaoImpl.class.getName());
	private DaoFactory daoFactory = DaoFactory.getInstance();


	@Override
	public Client findById(Integer id) throws DaoException {
		return null;
	}

	@Override
	public List<Client> findAll() throws DaoException {
		return null;
	}

	@Override
	public boolean save(Client entity) throws DaoException {
		Log.info("Saving new Client with id=" + entity.getId());
		String SQL_SAVE = "INSERT INTO client (id, name, phone_number, passport) VALUES (?, ?, ?, ?)";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = daoFactory.getConnection();
		} catch (DaoException e) {
			Log.error("Cannot create user", e);
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				Log.error("Cannot close resultset");
			}
		}
		return false;
	}

	@Override
	public boolean update(Client entity) throws DaoException {
		return false;
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		return false;
	}
}

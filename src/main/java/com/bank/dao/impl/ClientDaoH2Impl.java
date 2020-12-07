package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bank.dao.ClientDao;
import com.bank.dao.factory.H2DaoFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.bank.dao.DaoException;
import com.bank.domain.Client;

public class ClientDaoH2Impl implements ClientDao<Client, Integer> {
	private static Logger Log = LogManager.getLogger(ClientDaoH2Impl.class.getName());

	@Override
	public Client findById(Integer id) throws DaoException {
		Log.info("Finding Client with id=" +id);
		String findByIdSql = "select * from client where id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Client client = null;

		try {
			Log.trace("Get connection");
			connection = H2DaoFactory.getConnection();
			Log.trace("Create prepared statement");
			statement = connection.prepareStatement(findByIdSql);
			statement.setInt(1, id);
			Log.trace("Execute query and get result set");
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Integer clientId = resultSet.getInt("client_id");
				String name = resultSet.getString("name");
				String phone_number = resultSet.getString("phone_number");
				String passport = resultSet.getString("passport");
				client = new Client(clientId, name, phone_number, passport);
				Log.info("Clinet with id="+clientId + " found");
			}
		} catch (SQLException e) {
			Log.error("error");
		}
		return client;
	}

	@Override
	public List<Client> findAll() throws DaoException {
		return null;
	}

	@Override
	public boolean save(Client entity) throws DaoException {
//		Log.info("Saving new Client with id=" + entity.getId());
//		String SQL_SAVE = "INSERT INTO client (id, name, phone_number, passport) VALUES (?, ?, ?, ?)";
//
//		Connection connection = null;
//		PreparedStatement statement = null;
//		ResultSet resultSet = null;
//		try {
//			connection = H2DaoFactory.getConnection();
//		} catch (DaoException e) {
//			Log.error("Cannot create user", e);
//		} finally {
//			try {
//				resultSet.close();
//				statement.close();
//				connection.close();
//			} catch (SQLException e) {
//				Log.error("Cannot close resultset");
//			}
//		}
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

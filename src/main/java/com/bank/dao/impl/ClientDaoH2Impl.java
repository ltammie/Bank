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
import com.bank.models.Client;

public class ClientDaoH2Impl implements ClientDao<Client, Integer> {
	private static Logger Log = LogManager.getLogger(ClientDaoH2Impl.class.getName());

	@Override
	public Client findById(Long id) throws DaoException {
		String findByIdSql = "select * from client where id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Client client = null;

		try {
			connection = H2DaoFactory.getConnection();
			statement = connection.prepareStatement(findByIdSql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Long clientId = resultSet.getLong("client_id");
				String name = resultSet.getString("name");
				String phone_number = resultSet.getString("phone_number");
				String passport = resultSet.getString("passport");
				client = new Client(clientId, name, phone_number, passport);
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

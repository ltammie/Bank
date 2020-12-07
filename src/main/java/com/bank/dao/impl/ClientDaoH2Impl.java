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

import com.bank.dao.Dao;
import com.bank.dao.DaoException;
import com.bank.domain.Client;

public class ClientDaoH2Impl implements ClientDao<Client, Integer> {
	private static Logger Log = LogManager.getLogger(ClientDaoH2Impl.class.getName());

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

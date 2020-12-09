package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.bank.dao.ClientDao;
import com.bank.dao.factory.H2DaoFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.bank.dao.DaoException;
import com.bank.models.Client;

public class ClientDaoH2Impl implements ClientDao<Client, Long>{
	private static Logger Log = LogManager.getLogger(ClientDaoH2Impl.class.getName());
	private static final String FIND_BY_ID = "select * from client where id = ?";

	@Override
	public Optional<Client> findById(Long id) throws DaoException {
		Client client = null;
		try (Connection connection = H2DaoFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()){
				if (resultSet.next()) {
					Long clientId = resultSet.getLong("client_id");
					String name = resultSet.getString("name");
					String phone_number = resultSet.getString("phone_number");
					String passport = resultSet.getString("passport");
					client = new Client(clientId, name, phone_number, passport);
				}
			}
		} catch (SQLException e) {
			Log.error("error");
			throw new DaoException(e.getMessage(), e);
		}
		return Optional.ofNullable(client);
	}

	@Override
	public List<Client> findAll() throws DaoException {
		return null;
	}

	@Override
	public boolean save(Client client) throws DaoException {
		return false;
	}

	@Override
	public boolean update(Client client) throws DaoException {
		return false;
	}

	@Override
	public boolean delete(Long id) throws DaoException {
		return false;
	}
}

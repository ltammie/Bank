package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.bank.dao.ClientDao;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.bank.dao.DaoException;
import com.bank.models.Client;

import javax.sql.DataSource;

public class ClientDaoH2Impl implements ClientDao {
	private static final Logger Log = LogManager.getLogger(ClientDaoH2Impl.class.getName());
	private final DataSource ds;
	private static final String FIND_BY_ID = "select * from clients where client_id = ?";
	private static final String FIND_ALL = "select * from clients";
	private static final String SAVE = "INSERT INTO clients (name, phone_number, passport) VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE clients SET name = ?, phone_number = ?, passport = ? where client_id = ?";
	private static final String DELETE = "DELETE FROM clients WHERE client_id = ?";

	public ClientDaoH2Impl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Client findById(Long id) throws DaoException {
		Client client = null;
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					Long clientId = resultSet.getLong("client_id");
					String name = resultSet.getString("name");
					String phone_number = resultSet.getString("phone_number");
					String passport = resultSet.getString("passport");
					client = new Client(clientId, name, phone_number, passport);
				}
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to find client by id=" + id, e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when finding client with id=" + id, e);
			throw new DaoException(e.getMessage(), e);
		}
		return client;
	}

	@Override
	public List<Client> findAll() throws DaoException {
		List<Client> clients = new LinkedList<>();
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Long clientId = resultSet.getLong("client_id");
					String name = resultSet.getString("name");
					String phone_number = resultSet.getString("phone_number");
					String passport = resultSet.getString("passport");
					clients.add(new Client(clientId, name, phone_number, passport));
				}
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to find all clients from database", e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when finding all clients", e);
			throw new DaoException(e.getMessage(), e);
		}
		return clients;
	}

	@Override
	public void save(Client client) throws DaoException {
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SAVE)) {
			statement.setString(1, client.getName());
			statement.setString(2, client.getPhoneNumber());
			statement.setString(3, client.getPassport());
			try {
				statement.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to save " + client.toString(), e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when saving " + client.toString(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

		@Override
		public void update (Client client) throws DaoException {
			try (Connection connection = ds.getConnection();
				 PreparedStatement statement = connection.prepareStatement(UPDATE)) {
				statement.setString(1, client.getName());
				statement.setString(2, client.getPhoneNumber());
				statement.setString(3, client.getPassport());
				statement.setLong(4, client.getId());
				try {
					statement.executeUpdate();
					connection.commit();
				} catch (SQLException e) {
					Log.error("Failed to update " + client.toString(), e);
					try {
						connection.rollback();
					} catch (SQLException ex) {
						Log.error("Failed to rollback transaction", e);
					}
					throw new DaoException(e.getMessage(), e);
				}
			} catch (SQLException e) {
				Log.error("Failed to connect to database when updating " + client.toString(), e);
				throw new DaoException(e.getMessage(), e);
			}
		}

		@Override
		public void delete (Long id) throws DaoException {
			try (Connection connection = ds.getConnection();
				 PreparedStatement statement = connection.prepareStatement(DELETE)) {
				statement.setLong(1, id);
				try {
					statement.executeUpdate();
					connection.commit();
				} catch (SQLException e) {
					Log.error("Failed to delete client with id=" + id, e);
					try {
						connection.rollback();
					} catch (SQLException ex) {
						Log.error("Failed to rollback transaction", e);
					}
					throw new DaoException(e.getMessage(), e);
				}
			} catch (SQLException e) {
				Log.error("Failed to connect to database when deleting client with id=" + id, e);
				throw new DaoException(e.getMessage(), e);
			}
		}
	}

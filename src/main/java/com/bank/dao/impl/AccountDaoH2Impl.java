package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.bank.dao.AccountDao;
import com.bank.dao.DaoException;
import com.bank.models.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;

public class AccountDaoH2Impl implements AccountDao {
	private static final Logger Log = LogManager.getLogger(ClientDaoH2Impl.class.getName());
	private final DataSource ds;
	private static final String FIND_BY_ID = "select * from accounts where account_id = ?";
	private static final String FIND_ALL = "select * from accounts";
	private static final String SAVE = "INSERT INTO accounts (client_id, balance, account_number) VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE accounts SET client_id = ?, balance = ?, account_number = ? where account_id = ?";
	private static final String DELETE = "DELETE FROM accounts WHERE account_id = ?";

	public AccountDaoH2Impl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Account findById(Long id) throws DaoException {
		Account account = null;
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					Long accountId = resultSet.getLong("account_id");
					Long clientId = resultSet.getLong("client_id");
					Long balance = resultSet.getLong("balance");
					String number =resultSet.getString("account_number");
					account = new Account(accountId, clientId, balance, number);
				}
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to find account by id=" + id, e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when finding account with id=" + id, e);
			throw new DaoException(e.getMessage(), e);
		}
		return account;
	}

	@Override
	public List<Account> findAll() throws DaoException {
		List<Account> accounts = new LinkedList<>();
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Long accountId = resultSet.getLong("account_id");
					Long clientId = resultSet.getLong("client_id");
					Long balance = resultSet.getLong("balance");
					String number =resultSet.getString("account_number");
					accounts.add(new Account(accountId, clientId, balance, number));
				}
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to find all accounts from database", e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when finding all accounts", e);
			throw new DaoException(e.getMessage(), e);
		}
		return accounts;
	}

	@Override
	public void save(Account account) throws DaoException {
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SAVE)) {
			statement.setLong(1, account.getClientId());
			statement.setLong(2, account.getBalance());
			statement.setString(3, account.getAccountNumber());
			try {
				statement.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to save " + account.toString(), e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when saving " + account.toString(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public void update (Account account) throws DaoException {
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(UPDATE)) {
			statement.setLong(1, account.getClientId());
			statement.setLong(2, account.getBalance());
			statement.setString(3, account.getAccountNumber());
			statement.setLong(4, account.getId());
			try {
				statement.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to update " + account.toString(), e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when updating " + account.toString(), e);
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
				Log.error("Failed to delete account with id=" + id, e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when deleting account with id=" + id, e);
			throw new DaoException(e.getMessage(), e);
		}
	}
}

package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.bank.dao.CardDao;
import com.bank.dao.DaoException;
import com.bank.dao.factory.H2DaoFactory;
import com.bank.models.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;

public class CardDaoH2Impl implements CardDao {
	private static final Logger Log = LogManager.getLogger(ClientDaoH2Impl.class.getName());
	private final DataSource ds;
	private static final String FIND_BY_ID = "select * from cards where card_id = ?";
	private static final String FIND_ALL = "select * from cards";
	private static final String SAVE = "INSERT INTO cards (account_id, client_id, expiration_date, card_number) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE cards SET account_id = ?, client_id = ?, expiration_date = ?, card_number = ? where card_id = ?";
	private static final String DELETE = "DELETE FROM cards WHERE card_id = ?";
	private static final String FIND_FOR_ACCOUNT = "select * from cards where account_id = ?";

	public CardDaoH2Impl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Card findById(Long id) throws DaoException {
		Card card = null;
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					Long cardId = resultSet.getLong("card_id");
					Long accountId = resultSet.getLong("account_id");
					Long clientId = resultSet.getLong("client_id");
					String date = resultSet.getString("expiration_date");
					String number =resultSet.getString("card_number");
					card = new Card(cardId, accountId, clientId, date, number);
				}
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to find card by id=" + id, e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when finding card with id=" + id, e);
			throw new DaoException(e.getMessage(), e);
		}
		return card;
	}

	@Override
	public List<Card> findAll() throws DaoException {
		List<Card> cards = new LinkedList<>();
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Long cardId = resultSet.getLong("card_id");
					Long accountId = resultSet.getLong("account_id");
					Long clientId = resultSet.getLong("client_id");
					String date = resultSet.getString("expiration_date");
					String number = resultSet.getString("card_number");
					cards.add(new Card(cardId, accountId, clientId, date, number));
				}
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to find all cards from database", e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when finding all cards", e);
			throw new DaoException(e.getMessage(), e);
		}
		return cards;
	}

	@Override
	public void save(Card card) throws DaoException {
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SAVE)) {
			statement.setLong(1, card.getAccountId());
			statement.setLong(2, card.getClientId());
			statement.setString(3, card.getExpirationDate());
			statement.setString(4, card.getCardNumber());
			try {
				statement.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to save " + card.toString(), e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when saving " + card.toString(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public void update(Card card) throws DaoException {
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(UPDATE)) {
			statement.setLong(1, card.getAccountId());
			statement.setLong(2, card.getClientId());
			statement.setString(3, card.getExpirationDate());
			statement.setString(4, card.getCardNumber());
			statement.setLong(5, card.getId());
			try {
				statement.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to update " + card.toString(), e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when updating " + card.toString(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(Long id) throws DaoException {
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(DELETE)) {
			statement.setLong(1, id);
			try {
				statement.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to delete card with id=" + id, e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when deleting card with id=" + id, e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public List<Card> findByAccountId(Long id) throws DaoException {
		List<Card> cards = new LinkedList<>();
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(FIND_FOR_ACCOUNT)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Long cardId = resultSet.getLong("card_id");
					Long accountId = resultSet.getLong("account_id");
					Long clientId = resultSet.getLong("client_id");
					String date = resultSet.getString("expiration_date");
					String number = resultSet.getString("card_number");
					cards.add(new Card(cardId, accountId, clientId, date, number));
				}
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to find all cards for account from database", e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when finding all cards for account", e);
			throw new DaoException(e.getMessage(), e);
		}
		return cards;
	}
}

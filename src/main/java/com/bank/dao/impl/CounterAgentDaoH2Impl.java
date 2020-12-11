package com.bank.dao.impl;

import com.bank.dao.CounterAgentDao;
import com.bank.dao.DaoException;
import com.bank.models.Client;
import com.bank.models.CounterAgent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CounterAgentDaoH2Impl implements CounterAgentDao {
	private static final Logger Log = LogManager.getLogger(ClientDaoH2Impl.class.getName());
	private final DataSource ds;
	private static final String FIND_BY_ID = "select * from contractors where card_id = ?";
	private static final String FIND_ALL = "select * from cards";
	private static final String SAVE = "INSERT INTO cards (account_id, client_id, expiration_date, card_number) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE cards SET account_id = ?, client_id = ?, expiration_date = ?, card_number = ? where card_id = ?";
	private static final String DELETE = "DELETE FROM cards WHERE card_id = ?";
	private static final String FIND_FOR_INN = "select * from counter_agents where inn = ?";

	public CounterAgentDaoH2Impl(DataSource ds) {
		this.ds = ds;
	}



	@Override
	public CounterAgent getCounterAgentByInn(String inn) throws DaoException{
		CounterAgent counterAgent = null;
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(FIND_FOR_INN)) {
			statement.setString(1, inn);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					Long id = resultSet.getLong("counter_agent_id");
					String name = resultSet.getString("name");
					String c_inn = resultSet.getString("inn");
					Long balance = resultSet.getLong("balance");
					counterAgent = new CounterAgent(id, name, c_inn, balance);
				}
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to find counteragent by inn=" + inn, e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when finding counteragent with inn=" + inn, e);
			throw new DaoException(e.getMessage(), e);
		}
		return counterAgent;
	}

	@Override
	public CounterAgent findById(Long id) throws DaoException {
		CounterAgent counterAgent = null;
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					Long c_id = resultSet.getLong("counter_agent_id");
					String name = resultSet.getString("name");
					String inn = resultSet.getString("inn");
					Long balance = resultSet.getLong("balance");
					counterAgent = new CounterAgent(c_id, name, inn, balance);
				}
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to find counteragent by id=" + id, e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when finding counteragent with id=" + id, e);
			throw new DaoException(e.getMessage(), e);
		}
		return counterAgent;
	}

	@Override
	public List<CounterAgent> findAll() throws DaoException {
		List<CounterAgent> counterAgents = new LinkedList<>();
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
	public void save(CounterAgent counterAgent) throws DaoException {

	}

	@Override
	public void update(CounterAgent counterAgent) throws DaoException {

	}

	@Override
	public void delete(Long id) throws DaoException {

	}
}

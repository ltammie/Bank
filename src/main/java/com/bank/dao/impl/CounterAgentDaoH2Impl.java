package com.bank.dao.impl;

import com.bank.dao.CounterAgentDao;
import com.bank.dao.DaoException;
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
	private static final String FIND_BY_ID = "select * from counter_agents where counter_agent_id = ?";
	private static final String FIND_ALL = "select * from counter_agents";
	private static final String SAVE = "INSERT INTO counter_agents (name, inn, balance) VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE counter_agents SET name = ?, inn = ?, balance = ? where counter_agent_id = ?";
	private static final String DELETE = "DELETE FROM counter_agents WHERE counter_agent_id = ?";
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
					Long id = resultSet.getLong("counter_agent_id");
					String name = resultSet.getString("name");
					String inn = resultSet.getString("inn");
					Long balance = resultSet.getLong("balance");
					counterAgents.add(new CounterAgent(id, name, inn, balance));
				}
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to find all counteragents from database", e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when finding all counteragents", e);
			throw new DaoException(e.getMessage(), e);
		}
		return counterAgents;
	}

	@Override
	public void save(CounterAgent counterAgent) throws DaoException {
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SAVE)) {
			statement.setString(1, counterAgent.getName());
			statement.setString(2, counterAgent.getInn());
			statement.setLong(3, counterAgent.getBalance());
			try {
				statement.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to save " + counterAgent.toString(), e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when saving " + counterAgent.toString(), e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	@Override
	public void update(CounterAgent counterAgent) throws DaoException {
		try (Connection connection = ds.getConnection();
			 PreparedStatement statement = connection.prepareStatement(UPDATE)) {
			statement.setString(1, counterAgent.getName());
			statement.setString(2, counterAgent.getInn());
			statement.setLong(3, counterAgent.getBalance());
			statement.setLong(4, counterAgent.getId());
			try {
				statement.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				Log.error("Failed to update " + counterAgent.toString(), e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when updating " + counterAgent.toString(), e);
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
				Log.error("Failed to delete counteragent with id=" + id, e);
				try {
					connection.rollback();
				} catch (SQLException ex) {
					Log.error("Failed to rollback transaction", e);
				}
				throw new DaoException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			Log.error("Failed to connect to database when deleting counteragent with id=" + id, e);
			throw new DaoException(e.getMessage(), e);
		}

	}
}

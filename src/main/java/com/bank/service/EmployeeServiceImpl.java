package com.bank.service;

import com.bank.dao.AccountDao;
import com.bank.dao.ClientDao;
import com.bank.dao.DaoException;
import com.bank.dao.impl.AccountDaoH2Impl;
import com.bank.dao.impl.ClientDaoH2Impl;
import com.bank.models.Account;
import com.bank.models.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;

public class EmployeeServiceImpl implements EmployeeService<Client, String, Long>{
	private static Logger Log;
	private final AccountDao accountDao;
	private final ClientDao clientDao;
	private final DataSource ds;

	public EmployeeServiceImpl(DataSource ds) {
		this.ds = ds;
		accountDao = new AccountDaoH2Impl(ds);
		clientDao = new ClientDaoH2Impl(ds);
		Log  = LogManager.getLogger(AccountServiceImpl.class.getName());
	}
	@Override
	public void addNewClient(Client client) throws ServiceException {
		try {
			clientDao.save(client);
		} catch (DaoException e) {
			Log.error("Failed to create a new Client", e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void openNewAccount(String n, String p) throws ServiceException {
		try {
			Client client = clientDao.findClientByPassport(p);
			if (client != null) {
				accountDao.save(new Account(1L, client.getId(), 0L, n));
			} else
				throw new DaoException();
		} catch (DaoException e) {
			Log.error("Failed to open an account", e);
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}
}

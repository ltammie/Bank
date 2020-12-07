package com.bank.dao.impl;

import com.bank.dao.ClientDao;
import com.bank.dao.DaoException;
import com.bank.domain.Client;

import java.util.List;

public class ClientDaoImpl<Client> implements ClientDao<Client> {
	@Override
	public Client findById(Long id) throws DaoException {
		return null;
	}

	@Override
	public List<Client> findAll() throws DaoException{
		return null;
	}

	@Override
	public boolean save(Client entity) throws DaoException{
		return false;
	}

	@Override
	public boolean update(Client entity) throws DaoException {
		return false;
	}

	@Override
	public boolean delete(Long id) throws DaoException {
		return false;
	}
}

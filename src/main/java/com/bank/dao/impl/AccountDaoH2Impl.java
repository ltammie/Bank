package com.bank.dao.impl;

import java.util.List;

import com.bank.dao.AccountDao;
import com.bank.dao.DaoException;
import com.bank.domain.Account;

public class AccountDaoH2Impl implements AccountDao<Account, Integer> {

	@Override
	public Account findById(Integer id) throws DaoException {
		return null;
	}

	@Override
	public List<Account> findAll() throws DaoException {
		return null;
	}

	@Override
	public boolean save(Account entity) throws DaoException {
		return false;
	}

	@Override
	public boolean update(Account entity) throws DaoException {
		return false;
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		return false;
	}
}

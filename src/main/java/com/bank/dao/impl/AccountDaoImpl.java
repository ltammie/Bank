package com.bank.dao.impl;

import com.bank.dao.AccountDao;

import java.util.List;

public class AccountDaoImpl<Account> implements AccountDao<Account> {
	@Override
	public Account findById(Long id) {
		return null;
	}

	@Override
	public List<Account> findAll() {
		return null;
	}

	@Override
	public boolean save(Account entity) {
		return false;
	}

	@Override
	public boolean update(Account entity) {
		return false;
	}

	@Override
	public boolean delete(Long id) {
		return false;
	}
}

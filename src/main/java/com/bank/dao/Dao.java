package com.bank.dao;

public interface Dao<E, K> {
	E findById(K id) throws DaoException;
}

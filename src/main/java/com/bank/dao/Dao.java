package com.bank.dao;

public interface Dao<T> {
	T findById(Long id) throws DaoException;
}

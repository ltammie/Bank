package com.bank.dao;

import java.util.List;

public interface CardDao<E, K> extends Dao<E, K> {
	E findById(K id) throws DaoException;
	List<E> findAll() throws DaoException;
	boolean save(E entity) throws DaoException;
	boolean update(E entity) throws DaoException;
	boolean delete(K id) throws DaoException;
}

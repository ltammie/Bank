package com.bank.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<E, K> {
	E findById(K id) throws DaoException;

	List<E> findAll()  throws DaoException;

	void save(E e) throws DaoException;

	void update(E e) throws DaoException;

	void delete(K id) throws DaoException;
}

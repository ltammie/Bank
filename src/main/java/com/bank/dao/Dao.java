package com.bank.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<E, K> {
	Optional<E> findById(K id) throws DaoException;

	List<E> findAll()  throws DaoException;

	boolean save(E e) throws DaoException;

	boolean update(E e) throws DaoException;

	boolean delete(K id) throws DaoException;
}

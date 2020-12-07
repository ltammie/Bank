package com.bank.dao;

import java.util.List;

public interface ClientDao<T> extends Dao<T>{
	T findById(Long id) throws DaoException;
	List<T> findAll() throws DaoException;
	boolean save(T entity) throws DaoException;
	boolean update(T entity) throws DaoException;
	boolean delete(Long id) throws DaoException;
}

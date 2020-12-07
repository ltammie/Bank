package com.bank.dao;

import java.util.List;

public interface CardDao<T> extends Dao<T> {
	T findById(Long id);
	List<T> findAll();
	boolean save(T entity);
	boolean update(T entity);
	boolean delete(Long id);
}

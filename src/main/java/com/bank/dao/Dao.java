package com.bank.dao;

import java.util.List;

public interface Dao<E, K> {
	E findById(K id) throws DaoException;
}

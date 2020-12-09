package com.bank.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<E, K> {
	Optional<E> getById(K id);

	List<E> getAll();

	void create(E t);

	void update(E t);

	void delete(E t);
}

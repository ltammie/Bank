package com.bank.dao;

import com.bank.models.Card;

import java.util.List;

public interface CardDao extends Dao<Card, Long> {
	List<Card> findByAccountId(Long id) throws DaoException;
}

package com.bank.dao.impl;

import java.util.List;

import com.bank.dao.CardDao;
import com.bank.dao.DaoException;
import com.bank.models.Card;

public class CardDaoH2Impl implements CardDao<Card, Integer> {

	@Override
	public Card findById(Integer id) throws DaoException {
		return null;
	}

	@Override
	public List<Card> findAll() throws DaoException {
		return null;
	}

	@Override
	public boolean save(Card entity) throws DaoException {
		return false;
	}

	@Override
	public boolean update(Card entity) throws DaoException {
		return false;
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		return false;
	}
}

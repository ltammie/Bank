package com.bank.dao.impl;

import com.bank.dao.CardDao;

import java.util.List;

public class CardDaoImpl<Card> implements CardDao<Card> {
	@Override
	public Card findById(Long id) {
		return null;
	}

	@Override
	public List<Card> findAll() {
		return null;
	}

	@Override
	public boolean save(Card entity) {
		return false;
	}

	@Override
	public boolean update(Card entity) {
		return false;
	}

	@Override
	public boolean delete(Long id) {
		return false;
	}
}

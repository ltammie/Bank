package com.bank.service;

import com.bank.dao.AccountDao;
import com.bank.dao.CardDao;
import com.bank.dao.DaoException;
import com.bank.dao.factory.DaoFactory;
import com.bank.models.Account;
import com.bank.models.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class AccountServiceImpl implements AccountService<Account, Card, Long> {
	private static Logger Log;
	private final AccountDao accountDao;
	private final CardDao cardDao;

	public AccountServiceImpl() {
		DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.H2);
		accountDao = daoFactory.getAccountDao();
		cardDao = daoFactory.getCardDao();
		Log  = LogManager.getLogger(AccountServiceImpl.class.getName());
	}


	@Override
	public void createNewCard(Card card) throws AccountServiceException {
		try {
			cardDao.save(card);
		} catch (DaoException e) {
			Log.error("Failed to get create new a card", e);
			throw new AccountServiceException(e.getMessage(), e);
		}
	}

	// rewrite - find better way to get all cards associated with an account
	@Override
	public List<Card> getAllCards(Long id) throws AccountServiceException {
		List<Card> cards = new LinkedList<>();
		try {
			cards = cardDao.findByAccountId(id);
		} catch (DaoException e) {
			Log.error("Failed to get all cards for account with id=" + id, e);
			throw new AccountServiceException(e.getMessage(), e);
		}
		return cards;
	}

	@Override
	public void depositMoney(Account account) throws AccountServiceException {
		try {
			accountDao.update(account);
		} catch (DaoException e) {
			Log.error("Failed to deposit money for account with id=" + account.getId(), e);
			throw new AccountServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Account getBalance(Long id) throws AccountServiceException {
		Account account = null;
		try {
			account = accountDao.findById(id);
		} catch (DaoException e) {
			Log.error("Failed to get balance for account with id=" + id, e);
			throw new AccountServiceException(e.getMessage(), e);
		}
		return account;
	}
}

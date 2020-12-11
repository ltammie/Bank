package com.bank.service;

import com.bank.dao.AccountDao;
import com.bank.dao.CardDao;
import com.bank.dao.DaoException;
import com.bank.dao.impl.AccountDaoH2Impl;
import com.bank.dao.impl.CardDaoH2Impl;
import com.bank.models.Account;
import com.bank.models.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

public class AccountServiceImpl implements AccountService<Account, Card, Long> {
	private static Logger Log;
	private final AccountDao accountDao;
	private final CardDao cardDao;
	private final DataSource ds;

	public AccountServiceImpl(DataSource ds) {
		this.ds = ds;
		accountDao = new AccountDaoH2Impl(ds);
		cardDao = new CardDaoH2Impl(ds);
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
			Account newAcc = accountDao.getAccountByNumber(account.getAccountNumber());
			newAcc.setBalance(newAcc.getBalance() + account.getBalance());
			accountDao.update(newAcc);
		} catch (DaoException e) {
			Log.error("Failed to deposit money for account with number=" + account.getAccountNumber(), e);
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

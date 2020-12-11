package com.bank.dao;

import com.bank.models.Account;

public interface AccountDao extends Dao<Account, Long>{
	Account getAccountByNumber(String number) throws DaoException ;
}

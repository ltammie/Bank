package com.bank.dao.factory;

import com.bank.dao.AccountDao;
import com.bank.dao.CardDao;
import com.bank.dao.ClientDao;
import com.bank.models.Client;

public abstract class DaoFactory {
	public static final int H2 = 1;

	public abstract ClientDao getClientDao();

	public static DaoFactory getDaoFactory(int whichFactory) {
		switch (whichFactory) {
			case H2:
				return new H2DaoFactory();
			default:
				return null;
		}
	}

}

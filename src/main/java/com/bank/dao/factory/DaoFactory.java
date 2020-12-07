package com.bank.dao.factory;

import com.bank.dao.AccountDao;
import com.bank.dao.CardDao;
import com.bank.dao.ClientDao;

public abstract class DaoFactory {
	private static final int H2 = 1;

	public abstract ClientDao getCustomerDAO();

	public abstract AccountDao getAccountDAO();

	public abstract CardDao getOrderDAO();


	public static DaoFactory getDaoFactory(int whichFactory) {
		switch (whichFactory) {
			case H2:
				return new H2DaoFactory();
			default:
				return null;
		}
	}

}

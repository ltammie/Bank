package com.bank;

import com.bank.dao.ClientDao;
import com.bank.dao.factory.DaoFactory;

public class App {
	public static void main(String[] args) {
		DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.H2);
		ClientDao clientDao = daoFactory.getClientDao();
	}
}

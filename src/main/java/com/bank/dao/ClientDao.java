package com.bank.dao;

import com.bank.models.Client;

public interface ClientDao extends Dao<Client, Long> {
	Client findClientByPassport(String pass) throws DaoException;

}

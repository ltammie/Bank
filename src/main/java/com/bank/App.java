package com.bank;

import com.alibaba.fastjson.JSON;

import com.bank.dao.CardDao;
import com.bank.dao.ClientDao;
import com.bank.dao.Dao;
import com.bank.dao.DaoException;
import com.bank.dao.factory.DaoFactory;
import com.bank.dao.impl.AccountDaoH2Impl;
import com.bank.dao.impl.CardDaoH2Impl;
import com.bank.dao.impl.ClientDaoH2Impl;
import com.bank.models.Account;
import com.bank.models.Card;
import com.bank.models.Client;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Date;


//localhost:port
public class App {
	public static void main(String[] args) throws IOException {
//		DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.H2);
//		ClientDaoH2Impl clientDao = (ClientDaoH2Impl) daoFactory.getClientDao();
//		AccountDaoH2Impl accountDao = (AccountDaoH2Impl) daoFactory.getAccountDao();
//		CardDaoH2Impl cardDao = (CardDaoH2Impl) daoFactory.getCardDao();
//
//		try {
//			clientDao.save(new Client(1L, "John Wick", "8-800-555-35-35", "4132532543"));
//			clientDao.save(new Client(2L, "Bob Wick", "8-300-455-22-11", "6767222333"));
//			clientDao.save(new Client(3L, "Max Caulfield", "1-111-222-33-44", "1111222000"));
//			clientDao.save(new Client(4L, "The Dude", "4-333-222-11-00", "0000111000"));
//			clientDao.save(new Client(5L, "Bob Marlin", "6-333-444-22-99", "9999000888"));
//			accountDao.save(new Account(1L, 1L, 1_000_000L, "12345123451234512345"));
//			accountDao.save(new Account(2L, 2L, 0L, "00000111110000011111"));
//			accountDao.save(new Account(3L, 3L, 300_000L, "22222333334444455555"));
//			accountDao.save(new Account(4L, 4L, 599_300L, "12345000001234500000"));
//			accountDao.save(new Account(5L, 5L, 123_456L, "99999000008888800000"));
//			cardDao.save(new Card(1L, 1L, 1L,  new Date().toString(), "1111222233334444"));
//			cardDao.save(new Card(2L, 2L, 2L,  new Date().toString(), "1111000022223333"));
//			cardDao.save(new Card(3L, 3L, 3L,  new Date().toString(), "0000111122223333"));
//			cardDao.save(new Card(4L, 4L, 4L,  new Date().toString(), "4444222233331111"));
//			System.out.println(clientDao.findById(3L).toString());
//			System.out.println(accountDao.findById(5L).toString());
//			System.out.println(cardDao.findById(4L).toString());
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}

//		HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);
//		server.createContext("/test", new MyHandler());
//		server.setExecutor(null); // creates a default executor
//		server.start();
		Client client = new Client(1L, "John Wick", "8-800-555-35-35", "4132532543");
		String jsonString = JSON.toJSONString(client);
		System.out.println(jsonString);
		Client client1 = JSON.parseObject(jsonString, Client.class);
		System.out.println(client1.toString());
	}

	static class MyHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange t) throws IOException {
//			String response =
//}
//			t.sendResponseHeaders(200, response.length());
//			OutputStream os = t.getResponseBody();
//			byte[] jsonBytes = json.getBytes("UTF-8");
//			os.write(jsonBytes);
//			os.close();
		}
	}
}

package com.bank.servlets;

import com.alibaba.fastjson.JSON;
import com.bank.models.Account;
import com.bank.models.Card;
import com.bank.service.ServiceException;
import com.bank.service.AccountServiceImpl;
import com.bank.utils.DataSourceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/account")
public class AccountsServlet extends HttpServlet {
	private AccountServiceImpl accountService = null;

	public void init() throws ServletException {
		accountService = new AccountServiceImpl(DataSourceProvider.getDatasource());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String command = req.getParameter("command");

		try (PrintWriter writer = resp.getWriter()) {
			switch(command) {
				case "1":
					Long accountId =  Long.parseLong(req.getParameter("account_id"));
					Long clientId =  Long.parseLong(req.getParameter("client_id"));
					String date =  req.getParameter("expiration_date");
					String cardNumber = req.getParameter("card_number");
					accountService.createNewCard(new Card(2L, accountId, clientId, date, cardNumber));
					resp.setContentType("text/html");
					break;
				case "2":
					Long id  = Long.parseLong(req.getParameter("account_id"));
					List<Card> list = accountService.getAllCards(id);
					resp.setContentType("application/json");
					String jsonList = JSON.toJSONString(list);
					writer.println(jsonList);
					break;
				case "3":
					Long money = Long.parseLong(req.getParameter("transfer_amount"));
					String number = req.getParameter("account_number");
					accountService.depositMoney(new Account(1L, 2L, money, number));
					resp.setContentType("text/html");
					break;
				case "4":
					Account account = accountService.getBalance(Long.parseLong(req.getParameter("account_id")));
					if (account == null) {
						resp.sendError(404, "Server error");
					}
					else {
						resp.setContentType("application/json");
						String jsonString = JSON.toJSONString(account.getBalance());
						writer.println(jsonString);
					}
					break;
			}

		} catch (ServiceException e) {
			resp.sendError(404, "Server error");
		}
	}
}

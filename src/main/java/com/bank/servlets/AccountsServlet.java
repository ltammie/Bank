package com.bank.servlets;

import com.alibaba.fastjson.JSON;
import com.bank.models.Account;
import com.bank.service.AccountServiceException;
import com.bank.service.AccountServiceImpl;
import com.bank.utils.DataSourceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/account")
public class AccountsServlet extends HttpServlet {
	private AccountServiceImpl accountService = null;

	public void init() throws ServletException {
		accountService = new AccountServiceImpl(DataSourceProvider.getDatasource());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		String command = req.getParameter("command");

		try (PrintWriter writer = resp.getWriter()) {
			Account account;
			switch(command) {
				case "1":
					break;
				case "2":
					break;
				case "3":
					Long money = Long.parseLong(req.getParameter("transfer_amount"));
					String number = req.getParameter("account_number");
					accountService.depositMoney(new Account(1L, 2L, money, number));
					break;
				case "4":
					account = accountService.getBalance(Long.parseLong(req.getParameter("account_id")));
					if (account == null) {
						resp.sendError(404, "Server error/Account not found!");
					}
					else {
						String jsonString = JSON.toJSONString(account.getBalance());
						writer.println(jsonString);
					}
					break;
			}

		} catch (AccountServiceException e) {
			System.out.println("server error");
		}
	}
}

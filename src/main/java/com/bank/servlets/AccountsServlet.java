package com.bank.servlets;

import com.alibaba.fastjson.JSON;
import com.bank.models.Account;
import com.bank.service.AccountServiceException;
import com.bank.service.AccountServiceImpl;
import com.bank.utils.DataSourceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "account", urlPatterns ="/accounts.html")
public class AccountsServlet extends HttpServlet {
	private AccountServiceImpl accountService = null;

	public void init() throws ServletException {
		accountService = new AccountServiceImpl(DataSourceProvider.getDatasource());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "html/submit_form.html";
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");

		try (PrintWriter writer = resp.getWriter()) {
			Account account = accountService.getBalance(Long.parseLong(req.getParameter("account_id")));
			String jsonString = JSON.toJSONString(account);
			writer.println(jsonString);
		} catch (AccountServiceException e) {
			System.out.println("server error");
		}
	}
}

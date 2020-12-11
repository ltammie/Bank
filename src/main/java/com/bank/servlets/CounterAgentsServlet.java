package com.bank.servlets;

import com.bank.service.AccountServiceImpl;
import com.bank.service.CounterAgentServiceImpl;
import com.bank.service.ServiceException;
import com.bank.utils.DataSourceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/agents")
public class CounterAgentsServlet extends HttpServlet {
	private CounterAgentServiceImpl counterAgentService = null;

	public void init() throws ServletException {
		 counterAgentService = new CounterAgentServiceImpl(DataSourceProvider.getDatasource());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String command = req.getParameter("command");
		try (PrintWriter writer = resp.getWriter()) {
			switch (command) {
				case "1":
					break;
				case "2":
					break;
				case "3":
					break;
			}

		} catch (ServiceException e) {
			resp.sendError(404, "Server error");
		}
	}
}

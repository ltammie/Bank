package com.bank.servlets;

import com.alibaba.fastjson.JSON;
import com.bank.models.Account;
import com.bank.models.Card;
import com.bank.models.CounterAgent;
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
import java.util.List;

@WebServlet("/agent")
public class CounterAgentsServlet extends HttpServlet {
	private CounterAgentServiceImpl counterAgentService = null;

	public void init() throws ServletException {
		 counterAgentService = new CounterAgentServiceImpl(DataSourceProvider.getDatasource());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try (PrintWriter writer = resp.getWriter()){
			List<CounterAgent> list = counterAgentService.getAllAgents();
			resp.setContentType("application/json");
			String jsonList = JSON.toJSONString(list);
			writer.println(jsonList);
		} catch (ServiceException e) {
			resp.sendError(404, "Server error");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String command = req.getParameter("command");
		try (PrintWriter writer = resp.getWriter()) {
			switch (command) {
				case "1":
					String name =  req.getParameter("agent_name");
					String inn =  req.getParameter("inn");
					counterAgentService.addNewAgent(new CounterAgent(1L, name, inn, 0L));
					resp.setContentType("text/html");
					resp.sendRedirect(req.getContextPath());
					break;
				case "3":
					String number = req.getParameter("inn");
					Long money = Long.parseLong(req.getParameter("transfer_amount"));
					counterAgentService.addMoneyToAgent(number, money);
					resp.setContentType("text/html");
					resp.sendRedirect(req.getContextPath());
					break;
			}
		} catch (ServiceException e) {
			resp.sendError(404, "Server error");
		}
	}
}

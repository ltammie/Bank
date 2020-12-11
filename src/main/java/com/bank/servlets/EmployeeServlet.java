package com.bank.servlets;

import com.bank.models.Client;
import com.bank.models.CounterAgent;
import com.bank.service.CounterAgentServiceImpl;
import com.bank.service.EmployeeServiceImpl;
import com.bank.service.ServiceException;
import com.bank.utils.DataSourceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
	private EmployeeServiceImpl employeeService = null;

	public void init() throws ServletException {
		employeeService = new EmployeeServiceImpl(DataSourceProvider.getDatasource());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String command = req.getParameter("command");
		try {
			switch (command) {
				case "1":
					String name =  req.getParameter("client_name");
					String phone =  req.getParameter("phone");
					String pass =  req.getParameter("passport");
					employeeService.addNewClient(new Client(1L, name, phone, pass));
					resp.setContentType("text/html");
					resp.sendRedirect(req.getContextPath());
					break;
				case "2":
					String acc_number = req.getParameter("account_number");
					String passport = req.getParameter("passport");
					employeeService.openNewAccount(acc_number, passport);
					resp.setContentType("text/html");
					resp.sendRedirect(req.getContextPath());
					break;
			}
		} catch (ServiceException e) {
			resp.sendError(404, "Server error");
		}
}

package com.pscuderi.appengineangulardemo.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pscuderi.appengineangulardemo.model.AppUser;
import com.pscuderi.appengineangulardemo.service.ToDoService;
import com.pscuderi.appengineangulardemo.util.ServletUtils;

@SuppressWarnings("serial")
public class AdminServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		AppUser user = ServletUtils.getUserAndRedirectIfNotAuthenticated(req, resp);
		if (user.getEmail().equalsIgnoreCase("pscuderi@gmail.com")) {
			ToDoService.deleteAll();
		}
	}
}
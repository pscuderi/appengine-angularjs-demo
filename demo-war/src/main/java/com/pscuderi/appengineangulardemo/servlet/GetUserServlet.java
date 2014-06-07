package com.pscuderi.appengineangulardemo.servlet;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.pscuderi.appengineangulardemo.util.ServletUtils;

@SuppressWarnings("serial")
public class GetUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/json");
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		resp.getWriter().println(ServletUtils.toJson(user));
		
//		if (user != null) {
//		    resp.getWriter().println(ServletUtils.toJson(user));
//		}
//		else {
//			resp.getWriter().println(ServletUtils.toJson(null));
//		}
	}
}

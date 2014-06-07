package com.pscuderi.appengineangulardemo.servlet;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    if (user != null) {
	    	// return the user to home
	    	// in the future, might want to get the path and redirect accordingly
	    	resp.sendRedirect(userService.createLogoutURL("/"));
	    }
	    else {
	    	System.out.println("ERROR: user isn't logged in...why logout?");
	    }
	}
}

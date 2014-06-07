package com.pscuderi.appengineangulardemo.servlet;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.pscuderi.appengineangulardemo.util.ServletUtils;

@SuppressWarnings("serial")
public class GuestbookServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User user = ServletUtils.getUserAndRedirectIfNotAuthenticated(req, resp);
		
		// TODO: return all posts, with timestamps
		
	    System.out.println("user = " + user);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User user = ServletUtils.getUserAndRedirectIfNotAuthenticated(req, resp);
		
		// TODO: submit post, and respond with any new content (determine using a timestamp param)
		
	    System.out.println("user = " + user);
	}
}

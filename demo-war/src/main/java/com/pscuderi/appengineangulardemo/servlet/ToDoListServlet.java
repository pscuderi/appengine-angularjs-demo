package com.pscuderi.appengineangulardemo.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pscuderi.appengineangulardemo.model.AppUser;
import com.pscuderi.appengineangulardemo.model.ToDoItem;
import com.pscuderi.appengineangulardemo.service.ToDoService;
import com.pscuderi.appengineangulardemo.util.ServletUtils;

@SuppressWarnings("serial")
public class ToDoListServlet extends HttpServlet {
	@Override
	public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Create
		
		AppUser user = ServletUtils.getUserAndRedirectIfNotAuthenticated(req, resp);
	    ToDoItem item = ServletUtils.fromJson(req.getReader(), ToDoItem.class);
	    if (user != null && item != null) {
	    	resp.setContentType("application/json");
	    	resp.getWriter().println(ServletUtils.toCustomJson(ToDoService.createOrUpdate(user, item)));
	    }
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Retrieve
		
		AppUser user = ServletUtils.getUserAndRedirectIfNotAuthenticated(req, resp);
		if (user != null) {
			resp.setContentType("application/json");
			resp.getWriter().println(ServletUtils.toCustomJson(ToDoService.getToDoList(user)));
		}
	}
	
//	@Override
//	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		// Update
//		
//		AppUser user = ServletUtils.getUserAndRedirectIfNotAuthenticated(req, resp);
//	    ToDoItem item = ServletUtils.fromJson(req.getReader(), ToDoItem.class);
//	    if (user != null && item != null) {
//	    	resp.setContentType("application/json");
//	    	resp.getWriter().println(ServletUtils.toCustomJson(ToDoService.createOrUpdate(user, item)));
//	    }
//	}
//	
//	@Override
//	public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		// Delete
//		
//		AppUser user = ServletUtils.getUserAndRedirectIfNotAuthenticated(req, resp);
//	    ToDoItem item = ServletUtils.fromCustomJson(req.getReader(), ToDoItem.class);
//	    
//	    if (item != null)
//	    	System.out.println(item.getAction());
//	    else
//	    	System.out.println("NULL");
//	    
//	    if (user != null && item != null) {
//	    	ToDoService.delete(user, item);
//	    }
//	}
}
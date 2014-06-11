package com.pscuderi.appengineangulardemo.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pscuderi.appengineangulardemo.model.AppUser;
import com.pscuderi.appengineangulardemo.model.ToDoItem;
import com.pscuderi.appengineangulardemo.service.OfyService;
import com.pscuderi.appengineangulardemo.util.ServletUtils;

@SuppressWarnings("serial")
public class DeleteToDoItemServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Delete
		
		AppUser user = ServletUtils.getUserAndRedirectIfNotAuthenticated(req, resp);
	    ToDoItem item = ServletUtils.fromCustomJson(req.getReader(), ToDoItem.class);
	    
	    if (user != null && item != null) {
	    	item.setOwner(user);
	    	OfyService.ofy().delete().entity(item).now();
	    }
	}
}
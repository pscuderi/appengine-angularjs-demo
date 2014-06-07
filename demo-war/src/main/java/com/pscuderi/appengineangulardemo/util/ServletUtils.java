package com.pscuderi.appengineangulardemo.util;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;

public final class ServletUtils {
	private ServletUtils() { }

	private static Gson gson = new Gson();

	public static String toJson(Object o) {
		return gson.toJson(o);
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

	public static <T> T fromJson(BufferedReader reader, Class<T> clazz) throws IOException {
		StringBuilder sb = new StringBuilder();
		try {
	    	String line = reader.readLine();
	    	if (line != null) {
	    		sb.append(line);
		    	while ((line = reader.readLine()) != null)
		    		sb.append('\n').append(line);
	    	}
	    }
		finally {
	        reader.close();
	    }
	    return fromJson(sb.toString(), clazz);
	}
	
	public static User getUserAndRedirectIfNotAuthenticated(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    if (user == null) {
	    	// should we redirect to "/login" instead?
	    	resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
	    }
	    return user;
	}
}
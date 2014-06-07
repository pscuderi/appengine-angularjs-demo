package com.pscuderi.appengineangulardemo.servlet;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pscuderi.appengineangulardemo.util.ServletUtils;

@SuppressWarnings("serial")
public class GetUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/json");


		resp.getWriter().println(ServletUtils.toJson(new Date()));
	}
}
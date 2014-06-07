package com.pscuderi.appengineangulardemo.model;

import com.google.appengine.api.users.User;

public class AppUser {
	private User user;
	private String logoutUrl;
	
	public AppUser() {}
	
	public AppUser (User user, String logoutUrl) {
		this.user = user;
		this.logoutUrl = logoutUrl;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getLogoutUrl() {
		return logoutUrl;
	}
	
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}
}
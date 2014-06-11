package com.pscuderi.appengineangulardemo.model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Cache
@Entity
public class AppUser {
	@Id
	private String id;
	
	private String email;
	
	public AppUser() { }
	
	public AppUser(String id, String email) {
		this.id = id;
		this.email = email;
	}
	
	public Key<AppUser> getKey() {
		return Key.create(AppUser.class, id);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
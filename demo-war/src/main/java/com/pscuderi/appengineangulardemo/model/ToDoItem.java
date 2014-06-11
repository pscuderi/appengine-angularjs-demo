package com.pscuderi.appengineangulardemo.model;

import com.google.gson.annotations.Expose;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Cache
@Entity
public class ToDoItem {
	@Parent
	private Key<AppUser> ownerKey;
	
	@Id
	@Expose
	private String action;
	
	@Expose
	private boolean done;
	
	public ToDoItem() { }
	
	public ToDoItem(AppUser owner, String action, boolean done) {
		this.ownerKey = owner.getKey();
		this.action = action;
		this.done = done;
	}
	
	public Key<ToDoItem> getKey() {
		return Key.create(ToDoItem.class, action);
	}
	
	public Key<AppUser> getOwnerKey() {
		return ownerKey;
	}
	
	public void setOwner(AppUser owner) {
		this.ownerKey = owner.getKey();
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}

	public boolean getDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
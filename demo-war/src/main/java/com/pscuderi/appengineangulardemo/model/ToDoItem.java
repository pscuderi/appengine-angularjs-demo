package com.pscuderi.appengineangulardemo.model;

import com.google.gson.annotations.Expose;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Cache
@Entity
public class ToDoItem {
	@Id
	@Expose
	private Long id;
	
	@Index
	private String ownerId;
	
	@Expose
	private String action;
	
	@Expose
	private boolean done;
	
	public ToDoItem() { }
	
	public ToDoItem(String ownerId, String action, boolean done) {
		this.ownerId = ownerId;
		this.action = action;
		this.done = done;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	public String getAction() {
		return action;
	}

	public boolean getDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
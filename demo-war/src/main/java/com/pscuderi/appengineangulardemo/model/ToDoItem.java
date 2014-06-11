package com.pscuderi.appengineangulardemo.model;

import com.google.gson.annotations.Expose;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Cache
@Entity
public class ToDoItem {
	@Parent
	private Ref<AppUser> ownerRef;
	
	@Id
	@Expose
	private String action;
	
	@Expose
	private boolean done;
	
	public ToDoItem() { }
	
	public ToDoItem(AppUser owner, String action, boolean done) {
		ownerRef = Ref.create(owner.getKey());
		this.action = action;
		this.done = done;
	}
	
	public Key<ToDoItem> getKey() {
		return Key.create(ToDoItem.class, action);
	}
	
	public AppUser getOwner() {
		return ownerRef.get();
	}
	
	public void setOwner(AppUser owner) {
		this.ownerRef = Ref.create(owner.getKey());
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
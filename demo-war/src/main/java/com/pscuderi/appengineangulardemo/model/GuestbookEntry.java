package com.pscuderi.appengineangulardemo.model;

import java.util.Date;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Cache(expirationSeconds=600)
@Entity
public class GuestbookEntry {
	
	@Id
	private Long id;
	
	@Index
	private Date time;
	
	private String userId;
	
	private String text;
	
	public GuestbookEntry() {}
	
	public GuestbookEntry(Date time, String userId, String text) {
		this.time = time;
		this.userId = userId;
		this.text = text;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
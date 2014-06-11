package com.pscuderi.appengineangulardemo.service;

import java.util.List;

import com.pscuderi.appengineangulardemo.model.AppUser;
import com.pscuderi.appengineangulardemo.model.ToDoItem;

public final class ToDoService {   
    private ToDoService() {}
    
    public static List<ToDoItem> getToDoList(AppUser user) {
    	return OfyService.ofy().load().type(ToDoItem.class).ancestor(user.getKey()).list();
    }
    
    public static void delete(AppUser user, ToDoItem item) {
    	item.setOwner(user);
    	OfyService.ofy().delete().entity(item).now();
    }
    
    public static ToDoItem createOrUpdate(AppUser user, ToDoItem item) {
    	item.setOwner(user);
    	OfyService.ofy().save().entity(item).now();
    	return item;
    }
}
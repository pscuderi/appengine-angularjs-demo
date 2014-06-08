package com.pscuderi.appengineangulardemo.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.pscuderi.appengineangulardemo.model.AppUser;
import com.pscuderi.appengineangulardemo.model.ToDoItem;

public final class ToDoService {   
	private static final Logger log = Logger.getLogger(ToDoService.class.getName());
	
    private ToDoService() {}
    
    public static List<ToDoItem> getToDoList(AppUser user) {
    	return OfyService.ofy().load().type(ToDoItem.class).filter("ownerId", user.getId()).list();
    }
    
    public static ToDoItem getById(Long id) {
    	return OfyService.ofy().load().type(ToDoItem.class).id(id).now();
    }
    
    public static void persistOrUpdate(AppUser user, ToDoItem newItem) {
    	// data security
    	if (newItem.getId() != null) {
    		ToDoItem oldItem = getById(newItem.getId());
    		if (!oldItem.getOwnerId().equals(newItem.getOwnerId())) {
    			log.log(
    					Level.WARNING,
    					"user " + user.getId() + " doesn't own item " + newItem.getId());
    			return;
    		}
    	}
    	
    	// ownerId isn't serialized, so we might need to set it
    	if (newItem.getOwnerId() == null) {
    		newItem.setOwnerId(user.getId());
    	}
    	
    	OfyService.ofy().save().entity(newItem).now();
    }
}
package com.pscuderi.appengineangulardemo.service;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.pscuderi.appengineangulardemo.model.AppUser;
import com.pscuderi.appengineangulardemo.model.ToDoItem;

public final class OfyService {
    static {
    	factory().register(AppUser.class);
    	factory().register(ToDoItem.class);
    }
    
    private OfyService() {}
    
    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }
    
    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
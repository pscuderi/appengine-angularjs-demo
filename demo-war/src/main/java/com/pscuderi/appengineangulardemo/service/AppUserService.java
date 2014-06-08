package com.pscuderi.appengineangulardemo.service;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Objectify;
import com.pscuderi.appengineangulardemo.model.AppUser;

public final class AppUserService {    
    private AppUserService() {}
    
    public static AppUser getAppUser(User user) {
    	Objectify ofy = OfyService.ofy();
    	
    	AppUser appUser = ofy.load().type(AppUser.class).id(user.getUserId()).now();
    	
    	if (appUser == null) { // appUser wasn't in the datastore
    		appUser = new AppUser(user.getUserId(), user.getEmail());
    		ofy.save().entity(appUser).now();
    	}
    	else { // appUser is already in the datastore
    		
    		// update properties if they've changed
    		if (!appUser.getEmail().equalsIgnoreCase(user.getEmail())) {
    			appUser.setEmail(user.getEmail());
    			ofy.save().entity(appUser).now();
    		}
    	}
    	
        return appUser;
    }
}
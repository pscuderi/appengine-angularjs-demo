angular
	.module('auth', [])
    .service('AuthService', function($http){
        var obj = {
        	user: null
        };
        
        obj.refresh = function() {
	        $http.get('/GetUser')
		        .success(function(data) {
		            obj.user = eval(data);
		        })
	        	.error(function(error) {
	        		obj.user = null;
	        	});
    	};
        
        obj.isAuthenticated = function() {
        	return obj.user != null;
        };
        
        obj.getUser = function() {
        	return obj.user;
        };
        
        return obj;    
    });
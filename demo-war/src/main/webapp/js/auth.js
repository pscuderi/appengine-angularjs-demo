angular
	.module('auth', [])
    .service('AuthService', [function(){
        var userIsAuthenticated = true;
        
        this.setUserAuthenticated = function(value) {
            userIsAuthenticated = value;
        };
        
        this.getUserAuthenticated = function() {
            return userIsAuthenticated;
        };
    }]);
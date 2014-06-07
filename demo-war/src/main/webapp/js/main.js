var app = angular.module('appEngineAngularDemo', [
	   'ngRoute',
	   'auth'
   ]);

app.config(function($routeProvider, $locationProvider) {
	$routeProvider
		.when('/', {
			templateUrl: "templates/home.html",
			controller: 'HomeController',
			requireLogin: false
		})
		.when('/chat', {
			templateUrl: "templates/chat.html",
			controller: 'ChatController',
			requireLogin: true
		})
		.when('/traffic', {
			templateUrl: "templates/traffic.html",
			controller: 'TrafficController',
			requireLogin: false
		})
		.when('/login', {
			templateUrl: "servlets/GetUser",
			controller: 'LoginController',
			requireLogin: false
		})
		.when('/register', {
			templateUrl: "templates/register.html",
			controller: 'RegisterController',
			requireLogin: false
		})
		.otherwise({ redirectTo: '/'});
});

app.run(function($rootScope, AuthService, $location){
    $rootScope.$on("$routeChangeStart", function(event, next, current) {
        // Every time the route in our app changes check auth status
        if (next.requireLogin && !AuthService.getUserAuthenticated()) {
            // if you're logged out send to login page.
            $location.path('/login');
            event.preventDefault();
        }
    });
});

app.controller('HeaderController', function($scope, $location, AuthService) {
	$scope.isActive = function(viewLocation) { 
	    return viewLocation === $location.path();
	};
	
	$scope.isAuthenticated = function() {
		return AuthService.getUserAuthenticated();
	};
});

app.controller('HomeController', function($scope) {
	
});

app.controller('ChatController', function($scope) {
	
});

app.controller('TrafficController', function($scope) {
	
});

app.controller('LoginController', function($scope, $http) {
	//$scope.user = User;
	
//    $scope.login = function() {
//    	// TODO: post some parameters or similar
//    	$http.get("/User")
//		.success(function (data, status, headers, config) {
//			$scope.user = eval(data);
//	    })
//	    .error(function(error) {
//		 	$scope.user = {};
//	    });
//    };
//    
//    $scope.logout = function() {
//        $scope.user = {};
//    };
    
//	$scope.isAuthenticated = function() {
//		return userService.isAuthenticated();
//		//return angular.isDefined($scope.user) && angular.isDefined($scope.user.id);
//	};
});

app.controller('RegisterController', function($scope, AuthService) {
	$scope.register = function() {
		// toggle for now
		AuthService.setUserAuthenticated(!AuthService.getUserAuthenticated());
	};
});
var app = angular.module('appEngineAngularDemo', [
	   'ngRoute',
	   'auth'
   ]);

app.config(function($routeProvider, $locationProvider) {
	$routeProvider
		.when('/', {
			templateUrl: "/GetUser",
			controller: 'HomeController'
//			requireLogin: false
		})
		.when('/guestbook', {
			templateUrl: "templates/guestbook.html",
			controller: 'GuestbookController'
//			requireLogin: true
		})
		.otherwise({ redirectTo: '/'});
});

//app.run(function($rootScope, AuthService, $location){
//    $rootScope.$on("$routeChangeStart", function(event, next, current) {
//        // Every time the route in our app changes check auth status
//        if (next.requireLogin && !AuthService.getUserAuthenticated()) {
//            // if you're logged out send to login page.
//            $location.path('/login');
//            event.preventDefault();
//        }
//    });
//});

app.controller('HeaderController', function($scope, $location, AuthService) {
	$scope.isActive = function(viewLocation) { 
	    return viewLocation === $location.path();
	};
	$scope.isAuthenticated = function() {
		return AuthService.getUserAuthenticated();
	};
	$scope.loginPath = function() {
		return "/Login?path=" + $location.path();
	};
	$scope.logoutPath = function() {
		// TODO: if user is at a page that requires authorization, return them to home
		return "/Logout?path=" + $location.path();
	};
});

app.controller('HomeController', function($scope) {
	// call GetUser, and show their name
	
	
});

app.controller('GuestbookController', function($scope) {
	
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

app.controller('LogoutController', function($scope, AuthService) {
	$scope.register = function() {
		// toggle for now
		AuthService.setUserAuthenticated(!AuthService.getUserAuthenticated());
	};
});
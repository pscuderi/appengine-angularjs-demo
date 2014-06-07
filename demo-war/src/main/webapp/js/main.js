var app = angular.module('appEngineAngularDemo', [
	   'ngRoute',
	   'auth'
   ]);

app.config(function($routeProvider, $locationProvider) {
	$routeProvider
		.when('/', {
			templateUrl: "templates/home.html",
			controller: 'HomeController'
		})
		.when('/guestbook', {
			templateUrl: "templates/guestbook.html",
			controller: 'GuestbookController'
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
	AuthService.refresh();
	
	$scope.isActive = function(viewLocation) { 
	    return viewLocation === $location.path();
	};
	$scope.isAuthenticated = function() {
		return AuthService.isAuthenticated();
	};
	$scope.loginPath = function() {
		return "/Login?path=" + $location.path();
	};
	$scope.logoutPath = function() {
		return "/Logout?path=" + $location.path();
	};
});

app.controller('HomeController', function($scope, AuthService) {
	AuthService.refresh();
	
	$scope.user = AuthService.getUser();
	$scope.isAuthenticated = AuthService.isAuthenticated();
});

app.controller('GuestbookController', function($scope) {
	
});

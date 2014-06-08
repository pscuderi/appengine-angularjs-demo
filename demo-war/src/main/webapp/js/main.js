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
		.when('/todolist', {
			templateUrl: "templates/todolist.html",
			controller: 'ToDoListController'
		})
		.otherwise({ redirectTo: '/'});
});

app.controller('HeaderController', function($scope, $location, AuthService) {
	AuthService.refresh();
	
	$scope.isActive = function(viewLocation) { 
	    return viewLocation === $location.path();
	};
	
	$scope.isAuthenticated = AuthService.isAuthenticated();
	$scope.$watch(function () { return AuthService.isAuthenticated(); }, function (newVal, oldVal) {
		$scope.isAuthenticated = AuthService.isAuthenticated();
	});
	
	$scope.loginPath = function() {
		return "/Login?path=" + $location.path();
	};
	$scope.logoutPath = function() {
		return "/Logout?path=" + $location.path();
	};
});

app.controller('HomeController', function($scope, $location, AuthService) {
	AuthService.refresh();
	
	$scope.user = AuthService.getUser();
	
	$scope.loginPath = function() {
		return "/Login?path=" + $location.path();
	};
	
	$scope.$watch(function () { return AuthService.getUser(); }, function (newVal, oldVal) {
		$scope.user = AuthService.getUser();
		if ($scope.user != null) {
			$scope.name = $scope.user.email;
		}
		else {
			$scope.name = "Guest";
		}
	});
	
	$scope.isAuthenticated = AuthService.isAuthenticated();
	$scope.$watch(function () { return AuthService.isAuthenticated(); }, function (newVal, oldVal) {
		$scope.isAuthenticated = AuthService.isAuthenticated();
	});
});

app.controller('ToDoListController', function($scope) {
	
});

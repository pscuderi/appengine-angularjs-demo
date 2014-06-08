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

app.controller('ToDoListController', function($scope, $http) {
	
	$scope.items = {};
	
	$http.get("/ToDoList")
		.success(function (data, status, headers, config) {
			$scope.items = eval(data);
	    })
	    .error(function(error) {
		 	console.log(error);
		});
	
	$scope.put = function(item) {
		$http.put("/ToDoList", item)
			.error(function(error) {
				console.log(error);
			});
		
	$scope.incompleteCount = function() {
		var count = 0;
		angular.forEach($scope.items, function(item) {
			if (!item.done)
				++count;
		});
		return count;
	};

	$scope.warningLevel = function() {
		return $scope.incompleteCount() < 3 ? "label-success" : "label-warning";
	};
	
	$scope.addNewItem = function(actionText) {
		var found = false;
		angular.forEach($scope.items, function(item) {
			if (item.action.toUpperCase() === actionText.toUpperCase())
				found = true;
		});
		if (found) {
			alert(actionText + " already exists!");
		}
		else {
			var item = {action: actionText, done: false};
			$scope.items.push(item);
			$scope.put(item);
		}
	};
};
	
	
});

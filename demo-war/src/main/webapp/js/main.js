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
	});
	
	$scope.isAuthenticated = AuthService.isAuthenticated();
	$scope.$watch(function () { return AuthService.isAuthenticated(); }, function (newVal, oldVal) {
		$scope.isAuthenticated = AuthService.isAuthenticated();
	});
});

app.controller('ToDoListController', function($scope, $http) {
	
	$scope.items = {};
	$scope.showComplete = false;
	$scope.formDisabled = true;
	
	$http.get("/ToDoList")
		.success(function (data, status, headers, config) {
			$scope.items = eval(data);
			$scope.formDisabled = false;
	    })
	    .error(function(error) {
		 	console.log(error);
		});
	
	$scope.update = function(item) {
		$http.put("/ToDoList", item)
			.error(function(error) {
				console.log(error);
			});
	};
	
	$scope.del = function(item) {
		$http.post("/DeleteToDoItem", item)
			.success(function (data, status, headers, config) {
			    for (var i = 0; i < $scope.items.length; i++) {
			        if ($scope.items[i].action.toUpperCase() === item.action.toUpperCase()) {
			        	$scope.items.splice(i, 1);
			        	break;
			        }
			    };
		    })
			.error(function(error) {
				console.log(error);
			});
	};
	
	$scope.showItem = function(item) {
		return $scope.showComplete == true || item.done == false;
	};
	
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
	
	$scope.addNewItem = function() {
	    for (var i = 0; i < $scope.items.length; i++) {
	        if ($scope.items[i].action.toUpperCase() === $scope.actionText.toUpperCase()) {
	        	alert($scope.actionText + " already exists!");
	        	return;
	        }
	    };
		
		$scope.formDisabled = true;
		var item = {action: $scope.actionText, done: false};
		$http.put("/ToDoList", item)
			.success(function (data, status, headers, config) {
				$scope.items.push(item);
				$scope.actionText = "";
				$scope.formDisabled = false;
		    })
			.error(function(error) {
				console.log(error);
				$scope.formDisabled = false;
			});
	};
});

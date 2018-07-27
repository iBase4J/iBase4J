'use strict';

angular.module('app')
	.controller('loginController',[ '$rootScope', '$scope', '$http', '$state', function($rootScope, $scope, $http, $state) {
        $scope.user = {};
		$scope.login = function () {
			$.ajax({
				type: 'POST',
	            dataType: 'json',
				contentType:'application/json;charset=UTF-8',
				url : '/login',
				data: angular.toJson($scope.user)
			}).then(function(result) {
				if (result.code == 200) {
					$state.go('main.sys.user.list');
				} else {
					$scope.msg = result.msg;
					$rootScope.$apply();
				}
			});
		}
} ]);
'use strict';

angular.module('app')
	.controller('loginController',[ '$rootScope', '$scope', '$http', '$state', function($rootScope, $scope, $http, $state) {
        $scope.user = {};
		$scope.login = function () {
			$.ajax({
				url : '/login',
				data: $scope.user
			}).then(function(result) {
				if (result.httpCode == 200) {
					$state.go('main.sys.user.list');
				} else {
					$scope.msg = result.msg;
					localStorage.isLogin = true;
				}
			});
		}
} ]);
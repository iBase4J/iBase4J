'use strict';

angular.module('app')
	.controller('loginController',[ '$rootScope', '$scope', '$http', '$state', function($rootScope, $scope, $http, $state) {
        $scope.user = {};
		$scope.login = function () {
			var u = $scope.user;
			u.password = hex_md5(u.password);
			$.ajax({
				type: 'POST',
				url : '/login',
				data: u
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
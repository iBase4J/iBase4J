'use strict';

angular.module('app')
	.controller('sessionController', [ '$rootScope', '$scope', '$http', '$state',
	                                function($rootScope, $scope, $http, $state) {
		$scope.title = '会话管理';
        $scope.param = { };
        $scope.loading = false;
        
		$scope.search = function () {
	        $scope.loading = true;
			$.ajax({
				type: 'PUT',
	            dataType: 'json',
				contentType:'application/json;charset=UTF-8',
				url : '/session/read/list',
				data: angular.toJson($scope.param)
			}).then(function(result) {
		        $scope.loading = false;
				if (result.code == 200) {
					$scope.pageInfo = result;
				} else {
					$scope.msg = result.msg;
				}
				$scope.$apply();
			});
		}
		
		$scope.search();
		
		$scope.clearSearch = function() {
			$scope.param.keyword= null;
			$scope.search();
		}
		
		$scope.disableItem = function(id, account) {
			if(account != $rootScope.userInfo.account || confirm('确认要自杀？')){
				$.ajax({
					type: 'DELETE',
		            dataType: 'json',
					contentType:'application/json;charset=UTF-8',
					url : '/session',
					data: angular.toJson({'id': id})
				}).then(function(result) {
			        $scope.loading = false;
					if (result.code == 200) {
						$state.reload();
					} else {
						$scope.msg = result.msg;
					}
				});
			}
		}
		
		// 翻页
        $scope.pagination = function (page) {
            $scope.param.pageNumber=page;
            $scope.search();
        };
} ]);
'use strict';

angular.module('app')
	.controller('taskScheduledController', [ '$rootScope', '$scope', '$http', '$state',
	                                function($rootScope, $scope, $http, $state) {
		$scope.title = '调度管理';
        $scope.param = { };
        $scope.loading = false;
        
		$scope.search = function () {
	        $scope.loading = true;
			$.ajax({
				type: 'PUT',
	            dataType: 'json',
				contentType:'application/json;charset=UTF-8',
				url : '/scheduled/read/tasks',
				data: angular.toJson($scope.param)
			}).then(function(result) {
		        $scope.loading = false;
				if (result.httpCode == 200) {
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
		
		$scope.disableItem = function(group, name, enable) {
			$.ajax({
				type: 'POST',
	            dataType: 'json',
				contentType:'application/json;charset=UTF-8',
				url : enable==1? '/scheduled/open/task' : '/scheduled/close/task',
				data: angular.toJson({'taskGroup': group, 'taskName': name})
			}).then(function(result) {
				if (result.httpCode == 200) {
					$scope.search();
				} else {
					$scope.msg = result.msg;
				}
				$scope.$apply();
			});
		}
		
		$scope.runItem = function(group, name) {
			$.ajax({
				type: 'POST',
				url : '/scheduled/run/task',
				data: angular.toJson({'taskGroup': group, 'taskName': name})
			}).then(function(result) {
				if (result.httpCode == 200) {
					setTimeout(function(){
						$scope.search();
		            },300);
				} else {
					$scope.msg = result.msg;
				}
				$scope.$apply();
			});
		}
		
		$scope.delItem = function(group, name) {
			$.ajax({
				type: 'DELETE',
	            dataType: 'json',
				contentType:'application/json;charset=UTF-8',
				url : '/scheduled',
				data: angular.toJson({'taskGroup': group, 'taskName': name})
			}).then(function(result) {
				if (result.httpCode == 200) {
					setTimeout(function(){
						$scope.search();
		            },300);
				} else {
					$scope.msg = result.msg;
				}
				$scope.$apply();
			});
		}
		
		// 翻页
        $scope.pagination = function (page) {
            var params = {pageIndex: page};
            // 合并搜索字段
            if($scope.keyword) {
                angular.extend(params, $scope.user);
            }
            go(params);
        };
} ]);
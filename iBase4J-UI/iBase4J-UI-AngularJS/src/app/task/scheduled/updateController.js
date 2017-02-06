'use strict';

    angular.module('app')
        .controller('scheduledUpdateController', ['$scope', '$rootScope', '$state', '$timeout', 'toaster',
                                             function($scope, $rootScope, $state, $timeout, toaster) {
        var title = "";
        var defaultAva = $rootScope.defaultAvatar;
        $scope.record={jobType:'statefulJob', taskType:'LOCAL'};
        if($state.includes('**.scheduled.update')){
            title="编辑任务";
            $scope.record = $state.params;
        }else if($state.includes('**.scheduled.create')){
            title="添加任务";
        }
        $scope.title = $rootScope.title = title;
        $scope.loading = true;
        //初始化验证
        validate();
        
        $scope.submit= function(){
            var m = $scope.record;
            if(m){
                $scope.isDisabled = true;//提交disabled
                $.ajax({
                	type: 'POST',
    				url :'/scheduled',
    				data: angular.toJson($scope.record)
    			}).then(callback);
            }
            function callback(result) {
                if(result.httpCode ==200){//成功
                    toaster.clear('*');
                    toaster.pop('success', '', "保存成功");
                    $timeout(function(){
                        $state.go('main.task.scheduled.list');
                    },2000);
                }else{
                    toaster.clear('*');
                    toaster.pop('error', '', result.msg);
                    $scope.isDisabled = false;
                }
            }
        }

        //表单验证
        function validate(){
            jQuery('form').validate({
                rules: {
                    taskDesc: {
                        required: true
                    },
                    jobType: {
                        required: true
                    },
                    taskType: {
                        required: true
                    },
                	targetObject: {
                        required: true
                    },
                	targetMethod: {
                        required: true
                    },
                    taskCron: {
                        required: true
                    }
                },
                messages: {
                    taskDesc: {
                        required: '请填描述'
                    },
                    jobType: {
                        required: '请填任务阻塞类型'
                    },
                    taskType: {
                        required: '请填任务调用类型'
                    },
                	targetObject: {
                        required: '请填写目标对象'
                    },
                    targetMethod: {
                        required: '请填写目标方法'
                    },
                    taskCron: {
                        required: '请填执行周期'
                    }
                },
                submitHandler: function() {
                    $scope.submit();
                }
            });
        }

    }]);
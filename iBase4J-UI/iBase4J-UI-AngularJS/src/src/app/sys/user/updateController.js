'use strict';

    angular.module('app')
        .controller('userUpdateController', ['$scope', '$rootScope', '$state', '$timeout', 
                                             function($scope, $rootScope, $state, $timeout) {
        var title = "";
        var defaultAva = $rootScope.defaultAvatar;
        $scope.myImage='';
        // $scope.myCroppedImage=$scope.myCroppedImage ;
        $scope.myCroppedImage = '';
        if($state.includes('**.user.update')){
            title="编辑用户";
            var id = $state.params.id;
            activate(id);
            validate(id);
        }else if($state.includes('**.user.create')){
            title="添加用户";
            validate(null);
            setTimeout(function(){
                $scope.myCroppedImage = defaultAva;
                !$rootScope.$$phase && $scope.$apply();
            },300);

        }
        $scope.title = $rootScope.title = title;
        $scope.loading = true;
        //初始化验证
        //validate($scope);
        $scope.submit= function(){
            $scope.loading = true;
        	$.ajax({
        		type: 'POST',
				url : '/upload/imageData',
				data: {fileData:$scope.myCroppedImage},
			}).then(function(result){
                    if(result && result.code ==200){//成功
                        $scope.record['avatar'] =result.fileNames[0];
                        saveData();
                    }else if(result && result.code ==400){
                        saveData();
                    }
                });
        };

        function saveData(){
            var m = $scope.record;
            if(m){
                $scope.isDisabled = true;//提交disabled
                m.password && (m.password = hex_md5(m.password));
                $.ajax({
                	type: 'POST',
    				url : '/user',
    	            dataType: 'json',
    				contentType:'application/json;charset=UTF-8',
    				data: angular.toJson(m)
    			}).then(callback);
            }
            function callback(result){
                if(result.code ==200){//成功
                	toaster.clear('*');
                    toaster.pop('success', '', "保存成功");
                    $timeout(function(){
                        $state.go('main.sys.user.list');
                    },2000);
                }else{
                    toaster.clear('*');
                    toaster.pop('error', '', result.msg);
                    $scope.isDisabled = false;
                }
                $scope.loading = false;
            }
        }

        var handleFileSelect=function(evt) {
            var file=evt.currentTarget.files[0];
            if(!/image\/\w+/.test(file.type)){
                return false;
            }
            var reader = new FileReader();
            reader.onload = function (evt) {
                $scope.$apply(function($scope){
                    $scope.myImage=evt.target.result;
                });
            };
            reader.readAsDataURL(file);
        };
        angular.element(document.querySelector('#fileInput')).on('change',handleFileSelect);
        $('.upload-pic-box a').click(function() {
        	$('#fileInput').click();
        });

        // 初始化页面
        function activate(id) {
	        $scope.loading = true;
        	$.ajax({
        		type: 'PUT',
				url : '/user/read/detail',
	            dataType: 'json',
				contentType:'application/json;charset=UTF-8',
				data: angular.toJson({'id': id})
			}).then(function(result) {
		        $scope.loading = false;
				if (result.code == 200) {
					$scope.record = result.data;
				} else {
					$scope.msg = result.msg;
				}
				$scope.$apply();
	            $timeout(function(){
	                $scope.myCroppedImage=$scope.record.avatar || defaultAva;//初始化 预览图
	                $scope.record.avatar = null;
	            },300);
			});
        }

        //表单验证
        function validate(userId){
            //notEqual 规则
            $.validator.addMethod('notEqual', function(value, ele){
                return value != this.settings.rules[ele.name].notEqual;
            });
            jQuery('form').validate({
                rules: {
                    account: {
                        required: true,
                        stringCheck:[],
                        maxLengthB:[10]//,
                        //isExist:['/user/checkName',userId]
                    },
                    userName: {
                        required: true
                    },
                    phone: {
                        required: true,
                        isPhone:[]
                    },
                    password:{
                        maxlength: 16
                    },
                    confirmPassword:{
                       // required: true,
                        maxlength: 16,
                        equalTo: "#password"
                    }
                },
                messages: {
                    account: {
                        required: '请填写帐号',
                        maxLengthB:"帐号不得超过{0}个字符",
                        isExist:"该帐号已存在"
                    },
                    userName: {
                        required: '请填写用户名'
                    },
                    phone: {
                        required: '请填写联系方式'
                    },
                    password:{
                        //required: '请填写密码',
                        maxlength: '密码长度不可大于16位'
                    },
                    confirmPassword:{
                        //required: '请填写确认密码',
                        maxlength: '密码长度不可大于16位',
                        equalTo: '两次输入的密码不相符'
                    }
                },
                submitHandler: function() {
                    $scope.submit();
                }
            });
        }

    }]);
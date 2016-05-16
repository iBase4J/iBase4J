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
            adminService.upload({filename:$scope.myCroppedImage})
                .then(function(result){
                    if(result.data && result.data.httpCode ==200){//成功
                        toaster.clear('*');
                        toaster.pop('success', '', "头像上传成功");
                        //保存
                        $scope.admin = $scope.admin || {};
                        $scope.admin['userLogo'] =result.data.imgName;
                        saveData();
                    }else if(result.data && result.data.httpCode ==400){
                        saveData();
                    }else{
                        toaster.clear('*');
                        toaster.pop('error', '', result.data.msg);
                    }
                });
        };

        function saveData(){
            var m = $scope.admin;
            if(m){
                $scope.isDisabled = true;//提交disabled

                delete $scope.admin.createDate;
                m.userId?adminService.update(m).then(callback):adminService.create(m).then(callback)
            }
            function callback(result){
                if(result.data && result.data.httpCode ==200){//成功
                    toaster.clear('*');
                    toaster.pop('success', '', "保存成功");
                    $timeout(function(){
                        $state.go('main.user');
                    },2000);
                }else{
                    toaster.clear('*');
                    toaster.pop('error', '', result.data.msg);
                    $scope.isDisabled = false;
                }
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


        // 初始化页面
        function activate(id) {
            adminService.view(id)
                .then(success);
        }

        //成功回调
        function success(result){
            $scope.admin = result.data.user;
            $scope.loading = false;
            $timeout(function(){
                $scope.myCroppedImage=$scope.admin.userLogo || defaultAva;//初始化 预览图
                $scope.admin.userLogo = null;
            },300);
        }

        //表单验证
        function validate(userId){
            //notEqual 规则
            $.validator.addMethod('notEqual', function(value, ele){
                return value != this.settings.rules[ele.name].notEqual;
            });
            jQuery('form').validate({
                rules: {
                    userName: {
                        required: true,
                        stringCheck:[],
                        maxLengthB:[10],
                        isExist:['/user/checkName',userId]
                    },
                    realName: {
                        required: true
                    },
                    contactWay: {
                        required: true,
                        isPhone:[]
                    },
                    passWord:{
                        maxlength: 16
                    },
                    confirmPassword:{
                       // required: true,
                        maxlength: 16,
                        equalTo: "#passWord"
                    }
                },
                messages: {
                    userName: {
                        required: '请填写用户名',
                        maxLengthB:"用户名不得超过{0}个字符",
                        isExist:"该用户名已存在"
                    },
                    realName: {
                        required: '请填写真实姓名'
                    },
                    contactWay: {
                        required: '请填写联系方式'
                    },
                    passWord:{
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
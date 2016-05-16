'use strict';
// 

var app = angular.module('app')
	.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider,   $urlRouterProvider) {
            // 默认地址
            $urlRouterProvider.otherwise('/access/login');
            // 状态配置
            $stateProvider
                .state('main', {
                    abstract: true,
                    url: '',
                    templateUrl: 'src/tpl/app.html'
                })
                .state('access', {
                    url: '/access',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('access.login',{
                    url: '/login',
                    templateUrl: 'src/app/login/login.html',
                    controller: 'loginController',
                    resolve: {
                    	deps: ['uiLoad', function(uiLoad) {
                            return uiLoad.load('src/app/login/loginController.js');
                        }]
                      }
                })
                .state('main.sys', {
                    url: '/sys',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.sys.user', {
                    url: '/user',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.sys.user.list', {
                    url: '/list',
                    templateUrl: 'src/app/user/user.html',
                    controller: 'userController',
                    resolve: {
                    	deps: ['uiLoad', function(uiLoad) {
                            return uiLoad.load('src/app/user/userController.js');
                        }]
                      }
                })
                .state('main.sys.user.create', {
                    url: '/create',
                    templateUrl: 'src/app/user/userUpdate.html',
                    controller: 'userUpdateController',
                    resolve: {
                    	deps: ['uiLoad', function(uiLoad) {
                            return uiLoad.load('src/app/user/userUpdateController.js');
                        }]
                      }
                })
                .state('main.sys.user.update', {
                    url: '/update/{merchant_id}?params',
                    templateUrl: 'src/app/user/userUpdate.html',
                    controller: 'userUpdateController',
                    resolve: {
                    	deps: ['uiLoad', function(uiLoad) {
                            return uiLoad.load('src/app/user/userUpdateController.js');
                        }]
                      }
                })
                .state('main.sys.dept', {
                    url: '/dept',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.sys.dept.list', {
                    url: '/list',
                    templateUrl: 'src/app/dept/dept.html',
                    controller: 'deptController',
                    resolve: {
                    	deps: ['uiLoad', function(uiLoad) {
                            return uiLoad.load('src/app/dept/deptController.js');
                        }]
                      }
                })
                .state('main.sys.menu', {
                    url: '/menu',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.sys.menu.list', {
                    url: '/list',
                    templateUrl: 'src/app/menu/menu.html',
                    controller: 'menuController',
                    resolve: {
                    	deps: ['uiLoad', function(uiLoad) {
                            return uiLoad.load('src/app/menu/menuController.js');
                        }]
                      }
                });
    }])
    .controller("navCtrl",function($rootScope,$state) {
    	$.ajax({
			url : '/user/read/current',
			success : function(result) {
				if (result.httpCode == 200) {
					$rootScope.userInfo = result.user;
					$rootScope.menuList = result.menus;
					$rootScope.$apply();
				}
			}
		});
  	})
    .run(['$rootScope', '$state', '$stateParams','$timeout', '$templateCache',
          function ($rootScope,$state,$stateParams,$timeout, $templateCache) {
              $rootScope.$state = $state;
              $rootScope.$stateParams = $stateParams;
              $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
                  var from =  fromState.name, to = toState.name;
                  if(from && to){ // 解决 相应模块从列表进入编辑后 状态丢失问题
                      var s1= from.substring(0,from.lastIndexOf(".")),
                          g1 = from.substring(from.lastIndexOf(".")+1),
                          s2 = to.substring(0,to.lastIndexOf(".")),
                          g2 = to.substring(to.lastIndexOf(".")+1);
                      if(s1 == s2){
                          if(g1 =='list' && (g2=='update'||g2=='view')) { //进行编辑
                              toParams['params'] = window.location.hash;
                          }
                          //返回列表
                          if((g1 == "update"||g1 =='view') && g2=='list') {
                              var h = fromParams['params'];
                              if(h){
                                  $timeout(function(){
                                      window.location.hash = h;
                                  });
                              }
                          }
                      }
                  }
              });
          }
      ]);
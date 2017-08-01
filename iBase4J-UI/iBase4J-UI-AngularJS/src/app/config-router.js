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
                    templateUrl: 'src/app/sys/login/login.html',
                    controller: 'loginController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/login/loginController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.sys', {
                    url: '/sys',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                }) // 用户
                .state('main.sys.user', {
                    url: '/user',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.sys.user.list', {
                    url: '/list',
                    templateUrl: 'src/app/sys/user/user.html',
                    controller: 'userController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/user/userController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.sys.user.create', {
                    url: '/create',
                    templateUrl: 'src/app/sys/user/update.html',
                    controller: 'userUpdateController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/user/updateController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.sys.user.update', {
                    url: '/update/{id}?params',
                    templateUrl: 'src/app/sys/user/update.html',
                    controller: 'userUpdateController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/user/updateController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                }) // 部门
                .state('main.sys.dept', {
                    url: '/dept',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.sys.dept.list', {
                    url: '/list',
                    templateUrl: 'src/app/sys/dept/dept.html',
                    controller: 'deptController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/dept/deptController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.sys.dept.create', {
                    url: '/create',
                    templateUrl: 'src/app/sys/dept/update.html',
                    controller: 'deptUpdateController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/dept/updateController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.sys.dept.update', {
                    url: '/update/{id}?params',
                    templateUrl: 'src/app/sys/dept/update.html',
                    controller: 'deptUpdateController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/dept/updateController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                }) // 菜单
                .state('main.sys.menu', {
                    url: '/menu',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.sys.menu.list', {
                    url: '/list',
                    templateUrl: 'src/app/sys/menu/menu.html',
                    controller: 'menuController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/menu/menuController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.sys.menu.create', {
                    url: '/create',
                    templateUrl: 'src/app/sys/menu/update.html',
                    controller: 'menuUpdateController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/menu/updateController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.sys.menu.update', {
                    url: '/update/{id}?params',
                    templateUrl: 'src/app/sys/menu/update.html',
                    controller: 'menuUpdateController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/menu/updateController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                }) // 角色
                .state('main.sys.role', {
                    url: '/role',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.sys.role.list', {
                    url: '/list',
                    templateUrl: 'src/app/sys/role/role.html',
                    controller: 'roleController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                    		return uiLoad.load('src/app/sys/role/roleController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.sys.role.create', {
                    url: '/create',
                    templateUrl: 'src/app/sys/role/update.html',
                    controller: 'roleUpdateController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/role/updateController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.sys.role.update', {
                    url: '/update/{id}?params',
                    templateUrl: 'src/app/sys/role/update.html',
                    controller: 'roleUpdateController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/role/updateController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                }) // 会话
                .state('main.sys.session', {
                    url: '/session',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.sys.session.list', {
                    url: '/list',
                    templateUrl: 'src/app/sys/session/session.html',
                    controller: 'sessionController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/session/sessionController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                }) // 字典
                .state('main.sys.dic', {
                    url: '/dic',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.sys.dic.list', {
                    url: '/list',
                    templateUrl: 'src/app/sys/dic/dic.html',
                    controller: 'dicController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/dic/dicController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.sys.dic.create', {
                    url: '/create',
                    templateUrl: 'src/app/sys/dic/update.html',
                    controller: 'dicUpdateController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/dic/updateController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.sys.dic.update', {
                    url: '/update/{id}?params',
                    templateUrl: 'src/app/sys/dic/update.html',
                    controller: 'dicUpdateController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/dic/updateController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                }) // 参数
                .state('main.sys.param', {
                    url: '/param',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.sys.param.list', {
                    url: '/list',
                    templateUrl: 'src/app/sys/param/param.html',
                    controller: 'paramController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/param/paramController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.sys.param.create', {
                    url: '/create',
                    templateUrl: 'src/app/sys/param/update.html',
                    controller: 'paramUpdateController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/param/updateController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.sys.param.update', {
                    url: '/update/{id}?params',
                    templateUrl: 'src/app/sys/param/update.html',
                    controller: 'paramUpdateController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/sys/param/updateController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                }) // 调度
                .state('main.task', {
                    url: '/task',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.task.scheduled', {
                    url: '/scheduled',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.task.scheduled.list', {
                    url: '/list',
                    templateUrl: 'src/app/task/scheduled/scheduled.html',
                    controller: 'taskScheduledController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/task/scheduled/scheduledController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.task.scheduled.create', {
                    url: '/update',
                    templateUrl: 'src/app/task/scheduled/update.html',
                    controller: 'scheduledUpdateController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/task/scheduled/updateController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.task.scheduled.update', {
                    url: '/update',
                    params: {
                    	'taskGroup': null,'taskName': null,
                    	'taskCron': null,'taskDesc': null,
                    	'taskType': null,'jobType': null,
                    	'targetObject': null,'targetMethod': null
                    },
                    templateUrl: 'src/app/task/scheduled/update.html',
                    controller: 'scheduledUpdateController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/task/scheduled/updateController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                })
                .state('main.task.log', {
                    url: '/log',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.task.log.list', {
                    url: '/list',
                    templateUrl: 'src/app/task/scheduled/log.html',
                    controller: 'scheduledLogController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load('src/app/task/scheduled/logController.js').then(function() {
                                return $ocLazyLoad.load('toaster');
                            });
                        }]
                      }
                });
    }])
    .controller("navCtrl",function($rootScope,$state) {
    	$.ajax({
			url : '/user/read/promission',
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
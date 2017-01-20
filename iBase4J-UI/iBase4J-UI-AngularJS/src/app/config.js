// config
window.APP = { version : 'v=20160509' };

angular.module('app')
  .config(['$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
	    function ($controllerProvider,   $compileProvider,   $filterProvider,   $provide) {
	        // lazy controller, directive and service
	        app.controller = $controllerProvider.register;
	        app.directive  = $compileProvider.directive;
	        app.filter     = $filterProvider.register;
	        app.factory    = $provide.factory;
	        app.service    = $provide.service;
	        app.constant   = $provide.constant;
	        app.value      = $provide.value;
	    }
	])
	.config(function(){
        jQuery.validator.setDefaults({
            errorClass: 'help-block animation-slideDown', // You can change the animation class for a different entrance animation - check animations page
            errorElement: 'div',
            errorPlacement: function(error, e) {
                var eleErrContains = e.parents('.tdgroup');
                if(eleErrContains.length == 0){
                    eleErrContains = e.parents('.form-group > div');
                }
                eleErrContains.append(error);
            },
            highlight: function(e) {
                $(e).closest('.form-group').removeClass('has-success has-error').addClass('has-error');
                $(e).closest('.help-block').remove();
            },
            success: function(e) {
                e.closest('.form-group').removeClass('has-success has-error');
                e.closest('.help-block').remove();
            }
        });
        $.extend({'cookie':function(name, value, options) {
                if(cookieIsEnable){
                    if (typeof value != 'undefined') { // name and value given, set cookie
                        options = options || {};
                        if (value === null) {
                            value = '';
                            options.expires = -1;
                        }
                        var expires = '';
                        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
                            var date;
                            if (typeof options.expires == 'number') {
                                date = new Date();
                                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
                            } else {
                                date = options.expires;
                            }
                            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
                        }
                        var path = options.path ? '; path=' + options.path : '';
                        var domain = options.domain ? '; domain=' + options.domain : '';
                        var secure = options.secure ? '; secure' : '';
                        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
                    } else { // only name given, get cookie
                        var cookieValue = null;
                        if (document.cookie && document.cookie != '') {
                            var cookies = document.cookie.split(';');
                            for (var i = 0; i < cookies.length; i++) {
                                var cookie = jQuery.trim(cookies[i]);
                                // Does this cookie string begin with the name we want?
                                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                                    break;
                                }
                            }
                        }
                        return cookieValue;
                    }
                }else{
                    alert('cookie 禁用');
                }
                function cookieIsEnable(){
                    return window.navigator.cookieEnabled;
                }
            }
        });
        jQuery.validator.addMethod('stringCheck',function(value,element){
        	return this.optional(element)||/^[a-zA-Z0-9\u4e00-\u9fa5]*$/.test(value);
        },'只能包含字母、数字和汉字');
        jQuery.validator.addMethod('isPhone',function(value,element){
        	var mobileRgx = /^1[3-8][0-9]\d{8}$/;
        	var telRgx =  /^(\d{3,4}-){0,1}\d{7,9}$/;
        	return this.optional(element)||mobileRgx.test(value)||telRgx.test(value);
        },'请输入正确格式的手机或电话号码');
        jQuery.validator.addMethod('isExist',function(value,element,params){
        	var isTrue = false;
        	jQuery.ajax({
        		url:params[0],
        		type:'GET',
        		async:false,
        		data:{
                    userId:params[1],
                    param:value
                },
        		dataType:'json',
        		success:function(resData){
        			isTrue = resData.success;
        		}
        	});
        	return this.optional(element)||isTrue;
        });
        jQuery.validator.addMethod('maxLengthB',function(value,element,params){
        	var b=0,l=value.length;
        	for(var i=0;i<l;i++){
        		if(value.charCodeAt(i)>255){
        			b+=2;
        		}else{
        			b++;
        		}
        	}
        	return this.optional(element)||b<=params[0];
        });
    })
    .config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
      // We configure ocLazyLoad to use the lib script.js as the async loader
      $ocLazyLoadProvider.config({
          debug:false,
          events: true,
          modules: [{
              name: 'toaster',
              files: [
                  'lib/angular/toaster.js',
                  'lib/angular/toaster.css'
              ]
          }]
      });
    }])
    .filter('label', function() { // 显示为标签
		  return function(input, s) {
			  var l = input.split(s);
			  var r = '';
			  for(var i=0; i<l.length; i++) {
				  r += '<label class="label label-info">' + l[i] + '</label>\n';
			  }
			  return r;
		  }
    })
    .filter('trustHtml', function ($sce) { // 安全HTML
        return function (input) {
            return $sce.trustAsHtml(input);
        }
    })
    .directive('uiNav', ['$timeout', function($timeout) {
    return {
      restrict: 'AC',
      link: function(scope, el, attr) {
        var _window = $(window), 
        _mb = 768, 
        wrap = $('.app-aside'), 
        next, 
        backdrop = '.dropdown-backdrop';
        // unfolded
        el.on('click', 'a', function(e) {
          next && next.trigger('mouseleave.nav');
          var _this = $(this);
          _this.parent().siblings( ".active" ).toggleClass('active');
          _this.next().is('ul') &&  _this.parent().toggleClass('active') &&  e.preventDefault();
          // mobile
          _this.next().is('ul') || ( ( _window.width() < _mb ) && $('.app-aside').removeClass('show off-screen') );
        });

        // folded & fixed
        el.on('mouseenter', 'a', function(e){
          next && next.trigger('mouseleave.nav');
          $('> .nav', wrap).remove();
          if ( !$('.app-aside-fixed.app-aside-folded').length || ( _window.width() < _mb ) || $('.app-aside-dock').length) return;
          var _this = $(e.target)
          , top
          , w_h = $(window).height()
          , offset = 50
          , min = 150;

          !_this.is('a') && (_this = _this.closest('a'));
          if( _this.next().is('ul') ){
             next = _this.next();
          }else{
            return;
          }
         
          _this.parent().addClass('active');
          top = _this.parent().position().top + offset;
          next.css('top', top);
          if( top + next.height() > w_h ){
            next.css('bottom', 0);
          }
          if(top + min > w_h){
            next.css('bottom', w_h - top - offset).css('top', 'auto');
          }
          next.appendTo(wrap);

          next.on('mouseleave.nav', function(e){
            $(backdrop).remove();
            next.appendTo(_this.parent());
            next.off('mouseleave.nav').css('top', 'auto').css('bottom', 'auto');
            _this.parent().removeClass('active');
          });

          $('.smart').length && $('<div class="dropdown-backdrop"/>').insertAfter('.app-aside').on('click', function(next){
            next && next.trigger('mouseleave.nav');
          });

        });

        wrap.on('mouseleave', function(e){
          next && next.trigger('mouseleave.nav');
          $('> .nav', wrap).remove();
        });
      }
    };
}]);
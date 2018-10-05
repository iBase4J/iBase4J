window.APP = { version : '20170115' };
window.Table = null;

// Allow CSS transitions when page is loaded
$(window).on('load', function() {
    $('body').removeClass('no-transitions');
});

// 消息
window.notice = function(msg) {
	var notice = new PNotify({
        title: '操作提示',
        text: msg,
        type: 'success',
        hide: true,
        icon: 'glyphicon glyphicon-ok-circle'
    });
	notice.get().click(function(){
        notice.remove();
    });
}

window.error = function(msg) {
	new PNotify({
        title: '错误提示',
        text: msg,
        type: 'error',
        icon: 'glyphicon glyphicon-remove-circle'
    });
}
// Ajax配置
$.ajaxSetup({
    dataType: 'json',
	contentType:'application/json;charset=UTF-8',
	beforeSend: function(evt, request, settings) {
		//request.url = 'XBLK-Web' + request.url;
		$('.loading').show();
	},
	dataFilter: function(result) {
		try {
			result = JSON.parse(result);
			if(result.code == 401) {
				if(window.parent) {
					window.parent.document.location.href = '/login.html';
				} else {
					location.href = '/login.html';
				}
	            return null;
	        } else if(result.code == 303) {
	        } else if(result.code == 200) {
				
	        } else if(result.msg) {
	        	error(result.msg);
			}
			return JSON.stringify(result);
		} catch(e) {
			return result;
		}
	},
	error : function(jqXHR, textStatus, errorThrown) {
		switch (jqXHR.status) {
		case (404):
			error("未找到请求的资源");
			break;
		}
		$('.loading').hide();
	}
});

$(function() {
	bootbox.setLocale("zh_CN");
	//PNotify.prototype.options.styling = "bootstrap3";
    // 底部
    new Vue({
 		  el: '.footer',
 		  data: { footer: '&copy;2017 中和天与（北京）文化发展有限公司' }
 	});
	
	$.fn.serializeObject = function(){
		var obj = {};
		$.each(this.serializeArray(), function(index){
			var v = this['value'];
			try{
				var t = JSON.parse(v);
				if(typeof(t) == 'object') {
					v = t;
				}
			}catch(e){}
		   if(this['name'].indexOf('.') > -1) {
			   var n = this['name'].split('.');
			   if(!obj[n[0]]) {
				   obj[n[0]] = {};
			   }
			   if(obj[n[0]][n[1]]) {
				   if(obj[n[0]][n[1]] instanceof Array) {
					   obj[n[0]][n[1]].push(v);
				   } else {
					   obj[n[0]][n[1]] = [obj[n[0]][n[1]], v];
				   }
			   } else {
				   obj[n[0]][n[1]] = v;
			   }
		   } else {
			   if(obj[this['name']]) {
				   if(obj[this['name']] instanceof Array) {
					   obj[this['name']].push(v);
				   } else {
					   obj[this['name']] = [obj[this['name']], v];
				   }
			   } else {
				   obj[this['name']] = v;
			   }
		   }
	   });
		return obj;
	};
   
   // 登录
   $(document).on('submit', '#loginForm',function() {
	   if($(this).valid()) {
		   $.ajax({
	   			url : '/login',
	   			type: 'POST',
	   			data: JSON.stringify($(this).serializeObject()),
                dataType: 'json',
				contentType:'application/json;charset=UTF-8',
	   		}).then(function(result) {
	   			if (result.code == 200) {
	   				location.href = 'index.html';
	   			} else {
	   				error(result.msg);
	   			}
	   		});
	   }
	   return false;
   });
   
    // Disable CSS transitions on page load
    $('body').addClass('no-transitions');

    // ========================================
    //
    // Content area height
    //
    // ========================================


    // Calculate min height
    function containerHeight() {
        var availableHeight = $(window).height() - $('.page-container').offset().top - $('.navbar-fixed-bottom').outerHeight();

        $('.page-container').attr('style', 'min-height:' + availableHeight + 'px');
        $('.content-wrapper iframe').attr('style', 'min-height:' + availableHeight + 'px');
        
        var height = $(window).height() - $('.category-content').outerHeight() - $('.navbar').outerHeight();
        $('.sidebar-category').attr('style', 'overflow-y:scroll;height:' + height + 'px');
    }

    // Initialize
    containerHeight();

    $(document).on('click', '#logout', function() {
    	$.ajax({
    		type : "POST",
    		url : '/logout',
    		success : function(result) {
    			if (result.code == 200) {
    				location.href = '/login.html';
    			}
    		}
    	});
    });


    // ========================================
    //
    // Heading elements
    //
    // ========================================


    // Heading elements toggler
    // -------------------------

    // Add control button toggler to page and panel headers if have heading elements
    $('.panel-footer').has('> .heading-elements:not(.not-collapsible)').prepend('<a class="heading-elements-toggle"><i class="icon-more"></i></a>');
    $('.page-title, .panel-title').parent().has('> .heading-elements:not(.not-collapsible)').children('.page-title, .panel-title').append('<a class="heading-elements-toggle"><i class="icon-more"></i></a>');


    // Toggle visible state of heading elements
    $(document).on('click', '.page-title .heading-elements-toggle, .panel-title .heading-elements-toggle', function() {
        $(this).parent().parent().toggleClass('has-visible-elements').children('.heading-elements').toggleClass('visible-elements');
    });
    $(document).on('click', '.panel-footer .heading-elements-toggle', function() {
        $(this).parent().toggleClass('has-visible-elements').children('.heading-elements').toggleClass('visible-elements');
    });



    // Breadcrumb elements toggler
    // -------------------------

    // Add control button toggler to breadcrumbs if has elements
    $('.breadcrumb-line').has('.breadcrumb-elements').prepend('<a class="breadcrumb-elements-toggle"><i class="icon-menu-open"></i></a>');


    // Toggle visible state of breadcrumb elements
    $(document).on('click', '.breadcrumb-elements-toggle', function() {
        $(this).parent().children('.breadcrumb-elements').toggleClass('visible-elements');
    });

    $(document).on('click', '[data-action="checkAll"]', function() {
    	var target = $(this).attr('data-target');
    	if($(this).is(':checked')) {
        	$(target).parent().addClass('selected');
    	} else {
    		$(target).parent().removeClass('selected');
    	}
    });


    // ========================================
    //
    // Navbar
    //
    // ========================================


    // Navbar navigation
    // -------------------------

    // Prevent dropdown from closing on click
    $(document).on('click', '.dropdown-content', function (e) {
        e.stopPropagation();
    });

    // Disabled links
    $(document).on('click', '.navbar-nav .disabled a', function (e) {
        e.preventDefault();
        e.stopPropagation();
    });

    // Show tabs inside dropdowns
    $(document).on('click', '.dropdown-content a[data-toggle="tab"]', function (e) {
        $(this).tab('show');
    });

    // ========================================
    //
    // Element controls
    //
    // ========================================

    // Reload elements
    // -------------------------

    // Panels
    $(document).on('click', '.panel [data-action=reload]', function (e) {
        e.preventDefault();
        var block = $(this).parent().parent().parent().parent().parent();
        $(block).block({ 
            message: '<i class="icon-spinner2 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait',
                'box-shadow': '0 0 0 1px #ddd'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none'
            }
        });
        $(document).trigger($(this).attr('data-fnReload'));
        $(block).unblock();

        // For demo purposes
        /*window.setTimeout(function () {
           $(block).unblock();
        }, 2000); */
    });


    // Sidebar categories
    $(document).on('click', '.category-title [data-action=reload]', function (e) {
        e.preventDefault();
        var block = $(this).parent().parent().parent().parent();
        $(block).block({ 
            message: '<i class="icon-spinner2 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#000',
                opacity: 0.5,
                cursor: 'wait',
                'box-shadow': '0 0 0 1px #000'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none',
                color: '#fff'
            }
        });
        $(document).trigger($(this).attr('fnReload'));
        $(block).unblock();
        
        // For demo purposes
        /*window.setTimeout(function () {
           $(block).unblock();
        }, 2000); */
    }); 


    // Light sidebar categories
    $(document).on('click', '.sidebar-default .category-title [data-action=reload]', function (e) {
        e.preventDefault();
        var block = $(this).parent().parent().parent().parent();
        $(block).block({ 
            message: '<i class="icon-spinner2 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait',
                'box-shadow': '0 0 0 1px #ddd'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none'
            }
        });

        $(document).trigger($(this).attr('fnReload'));
        $(block).unblock();
        
        // For demo purposes
        /*window.setTimeout(function () {
            $(block).unblock();
        }, 2000);*/
    }); 


    // Collapse elements
    // -------------------------

    //
    // Sidebar categories
    //

    // Hide if collapsed by default
    $('.category-collapsed').children('.category-content').hide();


    // Rotate icon if collapsed by default
    $('.category-collapsed').find('[data-action=collapse]').addClass('rotate-180');


    // Collapse on click
    $(document).on('click', '.category-title [data-action=collapse]', function (e) {
        e.preventDefault();
        var $categoryCollapse = $(this).parent().parent().parent().nextAll();
        $(this).parents('.category-title').toggleClass('category-collapsed');
        $(this).toggleClass('rotate-180');

        containerHeight(); // adjust page height

        $categoryCollapse.slideToggle(150);
    });


    //
    // Panels
    //

    // Hide if collapsed by default
    $('.panel-collapsed').children('.panel-heading').nextAll().hide();


    // Rotate icon if collapsed by default
    $('.panel-collapsed').find('[data-action=collapse]').addClass('rotate-180');


    // Collapse on click
    $(document).on('click', '.panel [data-action=collapse]', function (e) {
        e.preventDefault();
        var $panelCollapse = $(this).parent().parent().parent().parent().nextAll();
        $(this).parents('.panel').toggleClass('panel-collapsed');
        $(this).toggleClass('rotate-180');

        containerHeight(); // recalculate page height

        $panelCollapse.slideToggle(150);
    });



    // Remove elements
    // -------------------------

    // Panels
    $(document).on('click', '.panel [data-action=close]', function (e) {
        e.preventDefault();
        var $panelClose = $(this).parent().parent().parent().parent().parent();

        containerHeight(); // recalculate page height

        $panelClose.slideUp(150, function() {
            $(this).remove();
        });
    });


    // Sidebar categories
    $(document).on('click', '.category-title [data-action=close]', function (e) {
        e.preventDefault();
        var $categoryClose = $(this).parent().parent().parent().parent();

        containerHeight(); // recalculate page height

        $categoryClose.slideUp(150, function() {
            $(this).remove();
        });
    });

    // Collapsible functionality
    // -------------------------

    // Main navigation
    $(document).on('click', '.navigation-main li:has(ul)>a', function (e) {
        e.preventDefault();

        // Collapsible
        $(this).parent('li').not('.disabled').not($('.sidebar-xs').not('.sidebar-xs-indicator').find('.navigation-main').children('li')).children('ul').slideToggle(250);

        // Accordion
        /*if ($('.navigation-main').hasClass('navigation-accordion')) {
            $(this).parent('li').not('.disabled').not($('.sidebar-xs').not('.sidebar-xs-indicator').find('.navigation-main').children('li')).siblings(':has(.has-ul)').removeClass('active').children('ul').slideUp(250);
        }*/
    });

    $(document).on('click', '.navigation-main li:not(:has(ul))>a', function (e) {
    	$('.navigation-main a').parent('li').removeClass('active');
		$(this).parents('li').addClass('active');
    });
        
    // Alternate navigation
    $('.navigation-alt').find('li').has('ul').children('a').on('click', function (e) {
        e.preventDefault();

        // Collapsible
        $(this).parent('li').not('.disabled').toggleClass('active').children('ul').slideToggle(200);

        // Accordion
        if ($('.navigation-alt').hasClass('navigation-accordion')) {
            $(this).parent('li').not('.disabled').siblings(':has(.has-ul)').removeClass('active').children('ul').slideUp(200);
        }
    });

    // ========================================
    //
    // Sidebars
    //
    // ========================================


    // Mini sidebar
    // -------------------------

    // Toggle mini sidebar
    $(document).on('click', '.sidebar-main-toggle', function (e) {
        e.preventDefault();

        // Toggle min sidebar class
        $('body').toggleClass('sidebar-xs');
    });



    // Sidebar controls
    // -------------------------

    // Disable click in disabled navigation items
    $(document).on('click', '.navigation .disabled a', function (e) {
        e.preventDefault();
    });


    // Adjust page height on sidebar control button click
    $(document).on('click', '.sidebar-control', function (e) {
        containerHeight();
    });


    // Hide main sidebar in Dual Sidebar
    $(document).on('click', '.sidebar-main-hide', function (e) {
        e.preventDefault();
        $('body').toggleClass('sidebar-main-hidden');
    });


    // Toggle second sidebar in Dual Sidebar
    $(document).on('click', '.sidebar-secondary-hide', function (e) {
        e.preventDefault();
        $('body').toggleClass('sidebar-secondary-hidden');
    });


    // Hide detached sidebar
    $(document).on('click', '.sidebar-detached-hide', function (e) {
        e.preventDefault();
        $('body').toggleClass('sidebar-detached-hidden');
    });


    // Hide all sidebars
    $(document).on('click', '.sidebar-all-hide', function (e) {
        e.preventDefault();

        $('body').toggleClass('sidebar-all-hidden');
    });



    //
    // Opposite sidebar
    //

    // Collapse main sidebar if opposite sidebar is visible
    $(document).on('click', '.sidebar-opposite-toggle', function (e) {
        e.preventDefault();

        // Opposite sidebar visibility
        $('body').toggleClass('sidebar-opposite-visible');

        // If visible
        if ($('body').hasClass('sidebar-opposite-visible')) {

            // Make main sidebar mini
            $('body').addClass('sidebar-xs');

            // Hide children lists
            $('.navigation-main').children('li').children('ul').css('display', '');
        }
        else {

            // Make main sidebar default
            $('body').removeClass('sidebar-xs');
        }
    });


    // Hide main sidebar if opposite sidebar is shown
    $(document).on('click', '.sidebar-opposite-main-hide', function (e) {
        e.preventDefault();

        // Opposite sidebar visibility
        $('body').toggleClass('sidebar-opposite-visible');
        
        // If visible
        if ($('body').hasClass('sidebar-opposite-visible')) {

            // Hide main sidebar
            $('body').addClass('sidebar-main-hidden');
        }
        else {

            // Show main sidebar
            $('body').removeClass('sidebar-main-hidden');
        }
    });


    // Hide secondary sidebar if opposite sidebar is shown
    $(document).on('click', '.sidebar-opposite-secondary-hide', function (e) {
        e.preventDefault();

        // Opposite sidebar visibility
        $('body').toggleClass('sidebar-opposite-visible');

        // If visible
        if ($('body').hasClass('sidebar-opposite-visible')) {

            // Hide secondary
            $('body').addClass('sidebar-secondary-hidden');

        }
        else {

            // Show secondary
            $('body').removeClass('sidebar-secondary-hidden');
        }
    });


    // Hide all sidebars if opposite sidebar is shown
    $(document).on('click', '.sidebar-opposite-hide', function (e) {
        e.preventDefault();

        // Toggle sidebars visibility
        $('body').toggleClass('sidebar-all-hidden');

        // If hidden
        if ($('body').hasClass('sidebar-all-hidden')) {

            // Show opposite
            $('body').addClass('sidebar-opposite-visible');

            // Hide children lists
            $('.navigation-main').children('li').children('ul').css('display', '');
        }
        else {

            // Hide opposite
            $('body').removeClass('sidebar-opposite-visible');
        }
    });


    // Keep the width of the main sidebar if opposite sidebar is visible
    $(document).on('click', '.sidebar-opposite-fix', function (e) {
        e.preventDefault();

        // Toggle opposite sidebar visibility
        $('body').toggleClass('sidebar-opposite-visible');
    });



    // Mobile sidebar controls
    // -------------------------

    // Toggle main sidebar
    $(document).on('click', '.sidebar-mobile-main-toggle', function (e) {
        e.preventDefault();
        $('body').toggleClass('sidebar-mobile-main').removeClass('sidebar-mobile-secondary sidebar-mobile-opposite sidebar-mobile-detached');
    });


    // Toggle secondary sidebar
    $(document).on('click', '.sidebar-mobile-secondary-toggle', function (e) {
        e.preventDefault();
        $('body').toggleClass('sidebar-mobile-secondary').removeClass('sidebar-mobile-main sidebar-mobile-opposite sidebar-mobile-detached');
    });


    // Toggle opposite sidebar
    $(document).on('click', '.sidebar-mobile-opposite-toggle', function (e) {
        e.preventDefault();
        $('body').toggleClass('sidebar-mobile-opposite').removeClass('sidebar-mobile-main sidebar-mobile-secondary sidebar-mobile-detached');
    });


    // Toggle detached sidebar
    $(document).on('click', '.sidebar-mobile-detached-toggle', function (e) {
        e.preventDefault();
        $('body').toggleClass('sidebar-mobile-detached').removeClass('sidebar-mobile-main sidebar-mobile-secondary sidebar-mobile-opposite');
    });



    // Mobile sidebar setup
    // -------------------------

    $(window).on('resize', function() {
        setTimeout(function() {
            containerHeight();
            
            if($(window).width() <= 768) {

                // Add mini sidebar indicator
                $('body').addClass('sidebar-xs-indicator');

                // Place right sidebar before content
                $('.sidebar-opposite').insertBefore('.content-wrapper');

                // Place detached sidebar before content
                $('.sidebar-detached').insertBefore('.content-wrapper');

                // Add mouse events for dropdown submenus
                $('.dropdown-submenu').on('mouseenter', function() {
                    $(this).children('.dropdown-menu').addClass('show');
                }).on('mouseleave', function() {
                    $(this).children('.dropdown-menu').removeClass('show');
                });
            }
            else {

                // Remove mini sidebar indicator
                $('body').removeClass('sidebar-xs-indicator');

                // Revert back right sidebar
                $('.sidebar-opposite').insertAfter('.content-wrapper');

                // Remove all mobile sidebar classes
                $('body').removeClass('sidebar-mobile-main sidebar-mobile-secondary sidebar-mobile-detached sidebar-mobile-opposite');

                // Revert left detached position
                if($('body').hasClass('has-detached-left')) {
                    $('.sidebar-detached').insertBefore('.container-detached');
                }

                // Revert right detached position
                else if($('body').hasClass('has-detached-right')) {
                    $('.sidebar-detached').insertAfter('.container-detached');
                }

                // Remove visibility of heading elements on desktop
                $('.page-header-content, .panel-heading, .panel-footer').removeClass('has-visible-elements');
                $('.heading-elements').removeClass('visible-elements');

                // Disable appearance of dropdown submenus
                $('.dropdown-submenu').children('.dropdown-menu').removeClass('show');
            }
        }, 100);
    }).resize();


    // ========================================
    //
    // Other code
    //
    // ========================================


    // Plugins
    // -------------------------

    // Popover
    $('[data-popup="popover"]').popover();


    // Tooltip
    $('[data-popup="tooltip"]').tooltip();

});

$(function () {

    // 初始化菜单
   $.ajax({
	   type: 'GET',
		url : '/user/read/promission',
		success : function(result) {
			if (result.code == 200) {
				// 菜单数据处理
				if(result.menus) {
					var href = $('#workspace').attr('src').replace('Edit.html', '.html').replace('Columns.html', '.html');
					for(var i = 0; i<result.menus.length; i++) {
						var has = false;
						var menu = result.menus[i];
						if(menu.request !== '#' && href.indexOf(menu.request) >= 0) {
							has = true;
						} else if(menu.menuBeans) {
							for(var j = 0; j<menu.menuBeans.length; j++) {
								var imenu = menu.menuBeans[j];
								if(imenu.request !== '#' && href.indexOf(imenu.request) >= 0) {
									has = true;
								} else if(imenu.menuBeans) {
									for(var k = 0; k<imenu.menuBeans.length; k++) {
										var rmenu = imenu.menuBeans[k];
										if(rmenu.request !== '#' && href.indexOf(rmenu.request) >= 0) {
											has = true;
											rmenu.className = 'active';
											break;
										}
									}
								}
								if(has) {
									imenu.className = 'active';
									break;
								}
							}
						}
						if(has) {
							menu.className = 'active';
							break;
						}
					}
				}
				// 头部
				$('.navbar-inverse').load('/main/common/header.html?' + APP.version, function(response, status, xhr) {
					$(this).html(response);
					  new Vue({
						  el: '.navbar-inverse',
						  data: result
					})
				});
				// 菜单
			    $('.sidebar-main').load('/main/common/menu.html?' + APP.version, function(response, status, xhr) {
			    	$(this).html(response);
			    	if(result.user && !result.user.avatar) {
			    		result.user['avatar'] = '/assets/images/placeholder.jpg';
			    	}
					new Vue({
						  el: '.sidebar-main',
						  data: result,
						  methods: {
							notEmpty: function(v) {
								return v && v.length > 0;
							}
						  },
						  created: function () {
							  setTimeout(function() {
								  	// ========================================
								    //
								    // Main navigation
								    //
								    // ========================================

								    // Add 'active' class to parent list item in all levels
								    $('.navigation').find('li.active').parents('li').addClass('active');

								    // Highlight children links
								    $('.navigation').find('li').has('ul').children('a').addClass('has-ul');

								    // Hide all nested lists
								    $('.navigation li:not(.active, .category-title)>ul').addClass('hidden-ul');

								    // Add active state to all dropdown parent levels
								    $('.dropdown-menu:not(.dropdown-content), .dropdown-menu:not(.dropdown-content) .dropdown-submenu').has('li.active').addClass('active').parents('.navbar-nav .dropdown:not(.language-switch), .navbar-nav .dropup:not(.language-switch)').addClass('active');

								    // Main navigation tooltips positioning
								    // -------------------------

								    // Left sidebar
								    $('.navigation-main > .navigation-header > i').tooltip({
								        placement: 'right',
								        container: 'body'
								    });
								    
							        var height = $(window).height() - $('.category-content').outerHeight() - $('.navbar').outerHeight();
							        $('.sidebar-category').attr('style', 'overflow-y:scroll;height:' + height + 'px');

									$('.loading').hide();
							  }, 100);
						  }
					})
				});
			} else {
				error(result.msg);
				$('.loading').hide();
			}
		}
	});
})
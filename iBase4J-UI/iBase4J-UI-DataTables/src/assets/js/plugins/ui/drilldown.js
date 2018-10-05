/*

 * DC jQuery Drill Down Menu - jQuery drill down ipod menu
 * Copyright (c) 2011 Design Chemical
 *
 * Dual licensed under the MIT and GPL licenses:
 * 	http://www.opensource.org/licenses/mit-license.php
 * 	http://www.gnu.org/licenses/gpl.html
 *
 */

(function($){

	//define the new for the plugin ans how to call it
	$.fn.dcDrilldown = function(options) {

		//set default options
		var defaults = {
			classWrapper	: 'dd-wrapper',
			classMenu		: 'dd-menu',
			classParent		: 'dd-parent',
			classParentLink	: 'dd-parent-a',
			classActive		: 'active-ul',
			classHeader		: 'dd-header',
			eventType		: 'click',
			hoverDelay		: 300,
			speed       	: 0,
			saveState		: false,
			showCount		: false,
			classCount		: 'dd-count',
			classIcon		: 'dd-icon',
			linkType		: 'backlink',
			resetText		: 'All',
			headerTag		: 'h6',
			defaultText		: 'Select Option',
			includeHdr		: true
		};

		//call in the default otions
		var options = $.extend(defaults, options);

		//act upon the element that is passed into the design
		return this.each(function(options){

			var $dcDrilldownObj = this;
			$($dcDrilldownObj).addClass(defaults.classMenu);
			var $wrapper = '<div class="'+defaults.classWrapper+'" />';
			$($dcDrilldownObj).wrap($wrapper);
			var $dcWrapper = $($dcDrilldownObj).parent();
			var objIndex = $($dcWrapper).index('.'+defaults.classWrapper);
			var idHeader = defaults.classHeader+'-'+objIndex;
			var idWrapper = defaults.classWrapper+'-'+objIndex;
			$($dcWrapper).attr('id',idWrapper);
			var $header = '<div id="'+idHeader+'" class="'+defaults.classHeader+'"></div>';

			setUpDrilldown();

			if(defaults.saveState == true){
				var cookieId = defaults.classWrapper+'-'+objIndex;
				checkCookie(cookieId, $dcDrilldownObj);
			}

			resetDrilldown($dcDrilldownObj, $dcWrapper);

			$('li a',$dcDrilldownObj).click(function(e){

				$link = this;
				$activeLi = $(this).parent('li').not('.disabled').stop();
				$siblingsLi = $($activeLi).siblings();

				// Drilldown action
				if($('> ul',$activeLi).length){
					if($($link).hasClass(defaults.classActive)){
						$('ul a',$activeLi).removeClass(defaults.classActive);
						resetDrilldown($dcDrilldownObj, $dcWrapper);
					} else {
						actionDrillDown($activeLi, $dcWrapper, $dcDrilldownObj);
					}
				}

				// Prevent browsing to link if has child links
				if($(this).next('ul').length > 0){
					e.preventDefault();
				}
			});

			// Set up accordion
			function setUpDrilldown(){

				$arrow = '<span class="'+defaults.classIcon+'"></span>';
				$($dcDrilldownObj).before($header);

				// Get width of menu container & height of list item
				var totalWidth = $($dcDrilldownObj).outerWidth();
				totalWidth += 'px';
				var itemHeight = $('li',$dcDrilldownObj).outerHeight(true);

				// Get height of largest sub menu
				var objUl = $('ul',$dcDrilldownObj);
				var maxItems = findMaxHeight(objUl);

				// Get level of largest sub menu
				//var maxUl = $(objUl.html()+'[rel="'+maxItems+'"]');
				var maxUl = $('[rel="'+maxItems+'"]', objUl);
				var getIndex = findMaxIndex(maxUl);

				// Set menu container height
				if(defaults.linkType == 'link'){
					menuHeight = itemHeight * (maxItems + getIndex);
				} else {
					menuHeight = itemHeight * maxItems;
				}

				// Set sub menu width and offset
				$('li',$dcDrilldownObj).each(function(){
					$('ul',this).css({marginRight: '-'+totalWidth, marginTop: '0'});
					if($('> ul',this).length){
						$(this).addClass(defaults.classParent);
						$('> a',this).addClass(defaults.classParentLink).append($arrow);
						if(defaults.showCount == true){
							var parentLink = $('a:not(.'+defaults.classParentLink+')',this);
							var countParent = parseInt($(parentLink).length);
							getCount = countParent;
							$('> a',this).append(' <span class="'+defaults.classCount+'">('+getCount+')</span>');
						}
					}
				});

				// Add css class
				$('ul',$dcWrapper).each(function(){
					$('li:last',this).addClass('last');
				});
				$('> ul > li:last',$dcWrapper).addClass('last');
				if(defaults.linkType == 'link'){
					$(objUl).css('top',itemHeight+'px');
				}

			    // Add 'active' class to parent list item in all levels
			    $('ul',$dcWrapper).find('li.active').parents('li').addClass('active');
			}

			// Breadcrumbs
			$('#'+idHeader).on('click', 'a', function(e){

				if($(this).hasClass('link-back')){
					linkIndex = $('#'+idWrapper+' .'+defaults.classParentLink+'.active-ul').length;
					linkIndex = linkIndex-2;
					$('a.'+defaults.classActive+':last', $dcDrilldownObj).removeClass(defaults.classActive);
				} else {
					// Get link index
					var linkIndex = parseInt($(this).index('#'+idHeader+' a'));
					if(linkIndex == 0){
						$('a',$dcDrilldownObj).removeClass(defaults.classActive);
					} else {
						// Select equivalent active link
						linkIndex = linkIndex-1;
						$('a.'+defaults.classActive+':gt('+linkIndex+')',$dcDrilldownObj).removeClass(defaults.classActive);
					}
				}
				resetDrilldown($dcDrilldownObj, $dcWrapper);
				e.preventDefault();
				e.stopPropagation();
			});
		});

		function findMaxHeight(element){
			var maxValue = undefined;
			$(element).each(function(){
				var val = parseInt($('> li',this).length);
				$(this).attr('rel',val);
				if (maxValue === undefined || maxValue < val){
					maxValue = val;
				}
			});
			return maxValue;
		}

		function findMaxIndex(element){
			var maxIndex = undefined;
			$(element).each(function(){
				var val = parseInt($(this).parents('li').length);
				if (maxIndex === undefined || maxIndex < val) {
					maxIndex = val;
				}
			});
			return maxIndex;
		}

		// Drill Down
		function actionDrillDown(element, wrapper, obj){
			// Declare header
			var $header = $('.'+defaults.classHeader, wrapper);

			// Get new breadcrumb and header text
			var getNewBreadcrumb = $(defaults.headerTag,$header).html();
			var getNewHeaderText = $('> a',element).html();
			//var getParentText = $(wrapper).find('.'+defaults.classMenu).data('category');



			// Add new breadcrumb
			if(defaults.linkType == 'breadcrumb'){
				if(!$('ul',$header).length){
					$($header).prepend('<ul></ul>');
				}
				if(getNewBreadcrumb == defaults.defaultText){
					$('ul',$header).append('<li><a href="#" class="first"> '+defaults.resetText+'</a></li>');
				} else {
					$('ul',$header).append('<li><a href="#"> '+getNewBreadcrumb+'</a></li>');
				}
			}
			if(defaults.linkType == 'backlink'){
				if(!$('a',$header).length){
					$($header).prepend('<a href="#" class="link-back"> '+getNewBreadcrumb+'</a>');
				} else {
					$('.link-back',$header).html(getNewBreadcrumb);
				}
			}
			if(defaults.linkType == 'link'){
				if(!$('a',$header).length){
					$($header).prepend('<ul><li><a href="#" class="first"> '+defaults.resetText+'</a></li></ul>');
				}
			}
			// Update header text
			updateHeader($header, getNewHeaderText);

			// declare child link
			var activeLink = $('> a',element);

			// add active class to link
			$(activeLink).addClass(defaults.classActive);
			$('> ul li',element).show();
			$('> ul',element).animate({"margin-right": 0}, defaults.speed);

			// Find all sibling items & hide
			var $siblingsLi = $(element).siblings();
			$($siblingsLi).hide();

			// If using breadcrumbs hide this element
			if(defaults.linkType != 'link'){
				$(activeLink).hide();
			}

			// Write cookie if save state is on
			if(defaults.saveState == true){
				var cookieId = $(wrapper).attr('id');
				createCookie(cookieId, obj);
			}
		}

		// Drill Up
		function actionDrillUp(element, obj, wrapper){
			// Declare header
			var $header = $('.'+defaults.classHeader, wrapper);

			var activeLink = $('> a',element);
			var checklength = $('.'+defaults.classActive, wrapper).length;
			var activeIndex = $(activeLink).index('.'+defaults.classActive, wrapper);

			// Get width of menu for animating right
			var totalWidth = $(obj).outerWidth(true);
			$('ul',element).css('margin-right',-totalWidth+'px');

			// Show all elements
			$(activeLink).addClass(defaults.classActive);
			$('> ul li',element).show();
			$('a',element).show();

			// Get new header text from clicked link
			var getNewHeaderText = $('> a',element).html();
			$(defaults.headerTag,$header).html(getNewHeaderText);

			if(defaults.linkType == 'breadcrumb'){
				var breadcrumbIndex = activeIndex-1;
				$('a:gt('+activeIndex+')',$header).remove();
			}
		}

		function updateHeader(obj, html){
			if(defaults.includeHdr == true){
				if($(defaults.headerTag,obj).length){
					$(defaults.headerTag,obj).html(html);
				} else {
					$(obj).append('<'+defaults.headerTag+'>'+html+'</'+defaults.headerTag+'>');
				}
			}
		}

		// Reset accordion using active links
		function resetDrilldown(obj, wrapper){
			var $header = $('.'+defaults.classHeader, wrapper);
			$('ul',$header).remove();
			$('a',$header).remove();
			$('li',obj).show();
			$('a',obj).show();
			var totalWidth = $(obj).outerWidth(true);
			if(defaults.linkType == "link"){
				if($('a.'+defaults.classActive+':last',obj).parent('li').length){
					var lastActive = $('a.'+defaults.classActive+':last',obj).parent('li');
					$('ul',lastActive).css('margin-right',-totalWidth+'px');
				}else {
				$('ul',obj).css('margin-right',-totalWidth+'px');
				}
			} else {
				$('ul',obj).css('margin-right',-totalWidth+'px');
			}
			updateHeader($header, defaults.defaultText);

			// Write cookie if save state is on
			if(defaults.saveState == true){
				var cookieId = $(wrapper).attr('id');
				createCookie(cookieId, obj);
			}

			$('a.'+defaults.classActive,obj).each(function(i){
				var $activeLi = $(this).parent('li').stop();
				actionDrillDown($activeLi, wrapper, obj);
			});
		}

		// Retrieve cookie value and set active items
		function checkCookie(cookieId, obj){
			var cookieVal = $.cookie(cookieId);
			if(cookieVal != null){
				// create array from cookie string
				var activeArray = cookieVal.split(',');
				$.each(activeArray, function(index,value){
					// mewsoft. fix firefox root menu not shown
					value = parseInt(value) + 0;
					var $cookieLi = $('li:eq('+value+')',obj);
					$('> a',$cookieLi).addClass(defaults.classActive);
				});
			}
		}

		// Write cookie
		function createCookie(cookieId, obj){
			var activeIndex = [];
			// Create array of active items index value
			$('a.'+defaults.classActive,obj).each(function(i){
				var $arrayItem = $(this).parent('li');
				var itemIndex = $('li',obj).index($arrayItem);
				activeIndex.push(itemIndex);
			});
			// Store in cookie
			$.cookie(cookieId, activeIndex, { path: '/' });
		}
	};
})(jQuery);




/*!
 * jQuery Cookie Plugin v1.4.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2006, 2014 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD
		define(['jquery'], factory);
	} else if (typeof exports === 'object') {
		// CommonJS
		factory(require('jquery'));
	} else {
		// Browser globals
		factory(jQuery);
	}
}(function ($) {

	var pluses = /\+/g;

	function encode(s) {
		return config.raw ? s : encodeURIComponent(s);
	}

	function decode(s) {
		return config.raw ? s : decodeURIComponent(s);
	}

	function stringifyCookieValue(value) {
		return encode(config.json ? JSON.stringify(value) : String(value));
	}

	function parseCookieValue(s) {
		if (s.indexOf('"') === 0) {
			// This is a quoted cookie as according to RFC2068, unescape...
			s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
		}

		try {
			// Replace server-side written pluses with spaces.
			// If we can't decode the cookie, ignore it, it's unusable.
			// If we can't parse the cookie, ignore it, it's unusable.
			s = decodeURIComponent(s.replace(pluses, ' '));
			return config.json ? JSON.parse(s) : s;
		} catch(e) {}
	}

	function read(s, converter) {
		var value = config.raw ? s : parseCookieValue(s);
		return $.isFunction(converter) ? converter(value) : value;
	}

	var config = $.cookie = function (key, value, options) {

		// Write

		if (arguments.length > 1 && !$.isFunction(value)) {
			options = $.extend({}, config.defaults, options);

			if (typeof options.expires === 'number') {
				var days = options.expires, t = options.expires = new Date();
				t.setTime(+t + days * 864e+5);
			}

			return (document.cookie = [
				encode(key), '=', stringifyCookieValue(value),
				options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
				options.path    ? '; path=' + options.path : '',
				options.domain  ? '; domain=' + options.domain : '',
				options.secure  ? '; secure' : ''
			].join(''));
		}

		// Read

		var result = key ? undefined : {};

		// To prevent the for loop in the first place assign an empty array
		// in case there are no cookies at all. Also prevents odd result when
		// calling $.cookie().
		var cookies = document.cookie ? document.cookie.split('; ') : [];

		for (var i = 0, l = cookies.length; i < l; i++) {
			var parts = cookies[i].split('=');
			var name = decode(parts.shift());
			var cookie = parts.join('=');

			if (key && key === name) {
				// If second argument (value) is a function it's a converter...
				result = read(cookie, value);
				break;
			}

			// Prevent storing a cookie that we couldn't decode.
			if (!key && (cookie = read(cookie)) !== undefined) {
				result[name] = cookie;
			}
		}

		return result;
	};

	config.defaults = {};

	$.removeCookie = function (key, options) {
		if ($.cookie(key) === undefined) {
			return false;
		}

		// Must not alter options, thus extending a fresh object...
		$.cookie(key, '', $.extend({}, options, { expires: -1 }));
		return !$.cookie(key);
	};

}));

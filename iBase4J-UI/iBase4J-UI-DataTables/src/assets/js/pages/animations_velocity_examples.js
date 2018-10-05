/* ------------------------------------------------------------------------------
*
*  # Advanced Velocity.js examples
*
*  Specific JS code additions for extension_velocity_examples.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Animation options
    // ------------------------------

	// Properties animation
	$('.velocity-properties').on('click', function (e) {
		e.preventDefault();

		$(this).parent().next().find('.panel').velocity({
			marginLeft: 20,
			marginRight: 20,
			opacity: 0.5
		}).velocity('reverse', {
			delay: 1000,
	    	complete: function() {
	    		$(this).removeAttr('style');
	    	}
		})
	});


	// Chained animation
	$('.velocity-chained').on('click', function (e) {
		e.preventDefault();

		$(this).parent().next().find('.panel').velocity({
			marginLeft: 20
		}).velocity('reverse', {
			delay: 1000
		}).velocity({
			marginRight: 20 
		}).velocity('reverse', {
			delay: 1000
		}).velocity({
			opacity: 0.5
		}).velocity('reverse', {
			delay: 1000,
	    	complete: function() {
	    		$(this).removeAttr('style');
	    	}
		})
	});


	// Stagger animation
	$('.velocity-stagger').on('click', function (e) {
		e.preventDefault();

		$(this).parent().next().find('.panel').velocity('transition.slideUpIn', {
			stagger: 500
		});
	});


	// Drag animation
	$('.velocity-drag').on('click', function (e) {
		e.preventDefault();

		$(this).parent().next().find('.panel').velocity('transition.slideUpBigIn', {
			duration: 1000,
			drag: true
		});
	});


	// Backwards animation
	$('.velocity-backwards').on('click', function (e) {
		e.preventDefault();

		$(this).parent().next().find('.panel').velocity('transition.slideDownOut', {
			stagger: 400,
			backwards: true
		})
	    .velocity({ opacity: 1 }, {
	      duration: 500,
	      display: "block"
	    });
	});



    // Animation callbacks
    // ------------------------------

	// Begin callback
	$('.velocity-begin').on('click', function (e) {
		e.preventDefault();

		$(this).parent().next('.row').velocity({
			marginLeft: 20,
			marginRight: 20,
			opacity: 0.5
		}, {
			begin: function() {
				alert('Begin callback example');
			}
		}).velocity('reverse', {
			delay: 1000,
	    	complete: function() {
	    		$(this).removeAttr('style');
	    	}
		})
	});


	// Complete callback
	$('.velocity-complete').on('click', function (e) {
		e.preventDefault();

		$(this).parent().next('.row').velocity({
			marginLeft: 20,
			marginRight: 20,
			opacity: 0.5
		}, {
			complete: function() {
				alert('Complete callback example');
			}
		}).velocity('reverse', {
			delay: 1000,
	    	complete: function() {
	    		$(this).removeAttr('style');
	    	}
		})
	});


	// Progress callback animation
	$('.velocity-progress').on('click', function (e) {
		e.preventDefault();

		var $percentComplete = $("#percentComplete"),
		    $timeRemaining = $("#timeRemaining");

		$(this).parent().next('.row').velocity({
			marginLeft: 20,
			marginRight: 20,
			opacity: 0.5
		}, {
			duration: 1000,
			progress: function(elements, percentComplete, timeRemaining, timeStart) {
				$percentComplete.html(Math.round(percentComplete * 100) + "% complete.");
				$timeRemaining.html(timeRemaining + "ms remaining.");
			}
		}).velocity('reverse', {
			delay: 1000,
	    	complete: function() {
	    		$(this).removeAttr('style');
	    	}
		})
	});



    // Animate layout on page load
    // ------------------------------

	// Hide elements first
	$(".sidebar, .navbar, .navbar-brand, .navbar-text, .navbar-nav > li, .page-header, .page-title, .page-header .heading-elements, .breadcrumb, .breadcrumb-elements > li, .content > .panel, .content .row > [class*=col-], .footer")
		.css('opacity', 0);


	// Animate when Pace loading is done
    Pace.on('done', function () {

		// Layout parts
		$(".navbar, .navbar-brand, .navbar-text, .navbar-nav > li, .page-header, .page-title, .page-header .heading-elements, .breadcrumb, .breadcrumb-elements > li, .content > .panel, .content .row > [class*=col-], .footer")
			.css('opacity', 1)
			.velocity("transition.slideDownIn", {
				stagger: 200,
				duration: 200,
				complete: function(elements) {
					$(this).removeAttr('style');
				}
		});


		// Sidebar
		$(".sidebar")
			.css({opacity: 0, borderColor: 'transparent'})
			.velocity("transition.slideUpIn", {
				delay: 100,
				duration: 500,
				display: 'table-cell',
				complete: function(elements) {
					$(this).removeAttr('style');
				}
		});


		// Navigation list on load
		$(".navigation > li")
			.css('opacity', 0)
			.velocity("transition.slideLeftIn", {
				delay: 500,
				stagger: 75,
				duration: 200,
				complete: function(elements) {
					$(this).removeAttr('style')
				}
		});


		// Navigation list on click
		$(".navigation .has-ul").on('click', function() {
			if ($(this).parent('li').hasClass('active')) {
				$(this).next('ul').children('li').css('opacity', 0).velocity("transition.fadeIn", {
					delay: 150,
					stagger: 30,
					duration: 200,
					complete: function(elements) {
						$(this).removeAttr('style')
					}
				});
			} else {
				$(this).next('ul').children('li').css('opacity', 0).velocity("transition.slideLeftOut", {
					duration: 200,
					complete: function(elements) {
						$(this).removeAttr('style')
					}
				});
			}
		});
    });

});

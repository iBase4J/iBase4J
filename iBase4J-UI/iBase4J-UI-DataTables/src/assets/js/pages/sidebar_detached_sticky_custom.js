/* ------------------------------------------------------------------------------
*
*  # Sticky sidebar with custom scrollbar
*
*  Specific JS code additions for layout_sidebar_sticky_custom.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Nice scroll
    // ------------------------------

	// Setup
	function initScroll() {
	    $(".sidebar-detached .sidebar").niceScroll({
	        mousescrollstep: 100,
	        cursorcolor: '#ccc',
	        cursorborder: '',
	        cursorwidth: 3,
	        hidecursordelay: 100,
	        autohidemode: 'scroll',
	        horizrailenabled: false,
	        preservenativescrolling: false,
	        railpadding: {
	        	right: 0.5,
	        	top: 1.5,
	        	bottom: 1.5
	        }
	    });
	}

	// Resize
	function resizeScroll() {
		$('.sidebar-detached .sidebar').getNiceScroll().resize();
	}

	// Remove
	function removeScroll() {
		$(".sidebar-detached .sidebar").getNiceScroll().remove();
		$(".sidebar-detached .sidebar").removeAttr('style').removeAttr('tabindex');
	}


    // Resize sidebar on scroll
    // ------------------------------

	// Resize detached sidebar vertically when bottom reached
    function resizeDetached() {
		$(window).on('load scroll', function() {
		  if ($(window).scrollTop() > $(document).height() - $(window).height() - 60) {
		    $('.sidebar-detached').addClass('fixed-sidebar-space');
		    resizeScroll();
		  }
		  else {
		    $('.sidebar-detached').removeClass('fixed-sidebar-space');
		    resizeScroll();
		  }
		});
    }


    // Affix detached sidebar
    // ------------------------------

    // Init nicescroll when sidebar affixed
	$('.sidebar-detached').on('affix.bs.affix', function() {
		initScroll();
		resizeDetached();
	});

    // When effixed top, remove scrollbar and its data
    $('.sidebar-detached').on('affix-top.bs.affix', function() {
		removeScroll();

        $(".sidebar-detached .sidebar").removeAttr('style').removeAttr('tabindex');
    });

	// Attach BS affix component to the sidebar
	$('.sidebar-detached').affix({
		offset: {
			top: $('.sidebar-detached').offset().top - 20 // top offset - computed line height
		}
	});


    // Remove affix and scrollbar on mobile
    $(window).on('resize', function() {
        setTimeout(function() {            
            if($(window).width() <= 768) {

                // Remove nicescroll on mobiles
                removeScroll();

                // Remove affix on mobile
                $(window).off('.affix')
                $('.sidebar-detached').removeData('affix').removeClass('affix affix-top affix-bottom');
            }
        }, 100);
    }).resize();

});

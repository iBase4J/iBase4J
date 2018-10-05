/* ------------------------------------------------------------------------------
*
*  # Velocity.js UI
*
*  Specific JS code additions for extension_velocity_ui.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Run animations
    $('.velocity-animation').on('click', function (e) {

        // Get animation class and panel
        var animation = $(this).data("animation");

        // Add animation class to panel element
        $(this).parents(".panel").velocity("callout." + animation, { stagger: 500 });
        e.preventDefault();
    });


    // Run transitions
    $('.velocity-transition').on('click', function (e) {

        // Get animation class and panel
        var transition = $(this).data("transition");

        // Add animation class to panel element
        $(this).parents(".panel").velocity("transition." + transition, { 
        	stagger: 1000,
        	duration: 1000
		});

        e.preventDefault();
    });


	// Clear styles after 2 seconds
	window.setInterval(function(){
		$('.velocity-transition').parents('.panel').removeAttr('style');
	}, 2000);

});

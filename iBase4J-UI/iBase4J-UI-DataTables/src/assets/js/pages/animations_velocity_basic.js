/* ------------------------------------------------------------------------------
*
*  # Basic Velocity.js animations
*
*  Specific JS code additions for extension_velocity_basic.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Run basic animations
    $('.velocity-property').on('click', function (e) {
        e.preventDefault();
        
        // Get animation class and panel
        var property = $(this).data("property");
        var value = $(this).data("value");

        var property2 = $(this).data("property2");
        var value2 = $(this).data("value2");

        var property3 = $(this).data("property3");
        var value3 = $(this).data("value3");

        // Add options
		var animateMap = {},
		animateOptions = {
			easing: 'easeInOut',
			duration: 250
		};
		animateMap[property] = value;
		animateMap[property2] = value2;
		animateMap[property3] = value3;


        // Add animation class to panel element
        $(this).parents(".demo-velocity-box")
        .velocity(animateMap, animateOptions)
        .velocity("reverse", {
        	delay: 1000,
        	complete: function() {
        		$(this).removeAttr('style');
        	}
        });
    });
    
});

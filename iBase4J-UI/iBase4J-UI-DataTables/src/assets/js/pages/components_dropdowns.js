/* ------------------------------------------------------------------------------
*
*  # Dropdown menus
*
*  Specific JS code additions for components_dropdowns.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

	// Styled checkboxes/radios
	$('.styled').uniform({
		radioClass: 'choice'
	});


	// Switchery toggles
	var toggle = Array.prototype.slice.call(document.querySelectorAll('.switchery'));
	
	toggle.forEach(function(html) {
		var switchery = new Switchery(html);
    });

});

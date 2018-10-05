/* ------------------------------------------------------------------------------
*
*  # Media objects and lists
*
*  Specific JS code additions for components_media.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

	// Switchery toggles
	var elems = Array.prototype.slice.call(document.querySelectorAll('.switch'));

	elems.forEach(function(html) {
		var switchery = new Switchery(html);
	});


    // Styled checkboxes/radios
	$(".styled").uniform({
		radioClass: 'choice'
    });

});

/* ------------------------------------------------------------------------------
*
*  # Thumbnails
*
*  Specific JS code additions for components_thumbnails.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

	// Switchery toggle
    var elems = Array.prototype.slice.call(document.querySelectorAll('.switch'));
    elems.forEach(function(html) {
		var switchery = new Switchery(html);
    });


    // Styled checkboxes/radios
    $('.styled').uniform();


    // Image lightbox
    $('[data-popup="lightbox"]').fancybox({
	    padding: 3
    });

});

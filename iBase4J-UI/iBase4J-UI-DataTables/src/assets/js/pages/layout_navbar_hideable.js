/* ------------------------------------------------------------------------------
*
*  # Page layout with hideable navbar
*
*  Specific JS code additions for layout_navbar_hideable.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Hide navbar with Headroom.js library
    $(".navbar-fixed-top").headroom({
        classes: {
            pinned: "headroom-top-pinned",
            unpinned: "headroom-top-unpinned"
        },
        offset: $('.navbar').outerHeight(),

        // callback when unpinned, `this` is headroom object
        onUnpin: function() {
            $('.navbar .dropdown-menu').parent().removeClass('open');
        }
    });

});

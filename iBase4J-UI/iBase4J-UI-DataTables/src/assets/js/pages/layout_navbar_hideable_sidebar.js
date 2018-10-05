/* ------------------------------------------------------------------------------
*
*  # Page layout with hideable navbar and fixed sidebar
*
*  Specific JS code additions for layout_navbar_hideable_sidebar.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Hide navbar with Headroom.js library and jQuery animation
    $(".navbar-fixed-top").headroom({
        classes: {
            pinned: "headroom-top-pinned",
            unpinned: "headroom-top-unpinned"
        },
        offset: $('.navbar').outerHeight(),

        // callback when pinned, `this` is headroom object
        onPin: function() {
            $('.sidebar-fixed .sidebar-content').animate({
                top: $('.navbar').outerHeight()
            }, 200);
        },

        // callback when unpinned, `this` is headroom object
        onUnpin: function() {
            $('.navbar .dropdown-menu').parent().removeClass('open');

            $('.sidebar-fixed .sidebar-content, .sidebar-fixed').animate({
                top: 0
            }, 200);
        }
    });    
});

/* ------------------------------------------------------------------------------
*
*  # Sticky sidebar with native scrollbar
*
*  Specific JS code additions for layout_fixed_native.html page
*
*  Version: 1.1
*  Latest update: Dec 14, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Mini sidebar
    // -------------------------

    // Setup
    function miniSidebar() {
        if ($('body').hasClass('sidebar-xs')) {
            $('.sidebar-main.sidebar-fixed .sidebar-content').on('mouseenter', function () {
                if ($('body').hasClass('sidebar-xs')) {

                    // Expand fixed navbar
                    $('body').removeClass('sidebar-xs').addClass('sidebar-fixed-expanded');
                }
            }).on('mouseleave', function () {
                if ($('body').hasClass('sidebar-fixed-expanded')) {

                    // Collapse fixed navbar
                    $('body').removeClass('sidebar-fixed-expanded').addClass('sidebar-xs');
                }
            });
        }
    }

    // Toggle mini sidebar
    $('.sidebar-main-toggle').on('click', function (e) {

        // Initialize mini sidebar 
        miniSidebar();
    });

});

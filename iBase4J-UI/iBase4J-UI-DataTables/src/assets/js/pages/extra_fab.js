/* ------------------------------------------------------------------------------
*
*  # Session timeout
*
*  Specific JS code additions for extra_session_timeout.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Add bottom spacing if reached bottom,
    // to avoid footer overlapping
    // -------------------------
    
    $(window).scroll(function() {
        if($(window).scrollTop() + $(window).height() > $(document).height() - 40) {
            $('.fab-menu-bottom-left, .fab-menu-bottom-right').addClass('reached-bottom');
        }
        else {
            $('.fab-menu-bottom-left, .fab-menu-bottom-right').removeClass('reached-bottom');
        }
    });


    // Affix
    // -------------------------

    // Left alignment
    $('#fab-menu-affixed-demo-left').affix({
        offset: {
            top: $('#fab-menu-affixed-demo-left').offset().top - 20
        }
    });

    // Right alignment
    $('#fab-menu-affixed-demo-right').affix({
        offset: {
            top: $('#fab-menu-affixed-demo-right').offset().top - 20
        }
    });

});

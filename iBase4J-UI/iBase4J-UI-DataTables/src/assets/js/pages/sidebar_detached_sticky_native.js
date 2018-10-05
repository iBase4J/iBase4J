/* ------------------------------------------------------------------------------
*
*  # Sticky sidebar with native scrollbar
*
*  Specific JS code additions for layout_sidebar_sticky_native.html blank page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {
    

    // Resize sidebar on scroll
    // ------------------------------

    // Resize detached sidebar vertically when bottom reached
    function resizeDetached() {
        $(window).on('load scroll', function() {
          if ($(window).scrollTop() > $(document).height() - $(window).height() - 40) {
            $('.sidebar-detached').addClass('fixed-sidebar-space');
          }
          else {
            $('.sidebar-detached').removeClass('fixed-sidebar-space');
          }
        });
    }


    // Affix detached sidebar
    // ------------------------------

    // Init nicescroll when sidebar affixed
    $('.sidebar-detached').on('affix.bs.affix', function() {
        resizeDetached();
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

                // Remove affix on mobile
                $(window).off('.affix')
                $('.sidebar-detached').removeData('affix').removeClass('affix affix-top affix-bottom');
            }
        }, 100);
    }).resize();

});

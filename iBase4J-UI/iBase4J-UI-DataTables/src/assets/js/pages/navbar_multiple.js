/* ------------------------------------------------------------------------------
*
*  # Multiple navbars
*
*  Specific JS code additions for multiple navbar pages
*
*  Version: 1.2
*  Latest update: Aug 10, 2016
*
* ---------------------------------------------------------------------------- */

$(function() {


    // We use Select2 for selects
    // ------------------------------

    // Single
    $('#single').select2({
        width: 180,
        minimumResultsForSearch: Infinity,
        allowClear: true,
        containerCssClass: 'bg-slate-600',
        dropdownCssClass: 'bg-slate-600'
    });


    // Multiple
    $('#multiple').select2({
        width: 180,
        minimumResultsForSearch: Infinity,
        allowClear: true,
        containerCssClass: 'bg-slate-600',
        dropdownCssClass: 'bg-slate-600'
    });



    // Operate multiple navbars
    // ------------------------------

    //
    // Single navbar
    //

    $('#single').on('change', function(){

        // Value of selected item
        var vals = $(this).val();


        // If one select is active, another one is disabled
        if ((vals == 'main_top') || (vals == 'main_bottom') || (vals == 'secondary_top')) {
            $("#multiple").prop("disabled", true);
        }
        else {
            $("#multiple").prop("disabled", false);
        }


        // Main top
        if (vals == 'main_top') {
            $('body').addClass('navbar-top');
            $('#navbar-main').addClass('navbar-fixed-top');
        }
        else {
            $('body').removeClass('navbar-top');
            $('#navbar-main').removeClass('navbar-fixed-top');
        }


        // Secondary top (with affix)
        if (vals == 'secondary_top') {
            $(window).on('resize', function() {
                setTimeout(function() {                    
                    if($(window).width() <= 768) {

                        // Remove affix
                        $('#navbar-second').removeClass('navbar-affix-xs');
                        $(window).off('.affix');
                        $('#navbar-second').removeData('bs.affix').removeClass('affix affix-top affix-bottom')
                    }
                    else {

                        // Add affix
                        $('#navbar-second').addClass('navbar-affix-xs');
                        $('.navbar-affix-xs').affix({
                            offset: {
                                top: function() {
                                    return (this.top = $('body').children('.navbar').outerHeight(true))
                                }
                            }
                        });

                        // When affixed
                        $('.navbar-affix-xs').on('affixed.bs.affix', function() {
                            $(this).next('.page-container').css('margin-top', $(this).outerHeight())
                        });

                        // When on top
                        $('.navbar-affix-xs').on('affixed-top.bs.affix', function() {
                            $(this).next('.page-container').css('margin-top', '')
                        });
                    }
                }, 100);
            }).resize();
        }
        else {
            $('#navbar-second').removeClass('navbar-affix-xs');
            $(window).off('.affix');
            $('#navbar-second').removeData('bs.affix').removeClass('affix affix-top affix-bottom')
        }


        // Main bottom
        if (vals == 'main_bottom') {
            $('#navbar-main').addClass('navbar-fixed-bottom');
            $('body').addClass('navbar-bottom');
            $('.footer').hide();
        }
        else {
            $('#navbar-main').removeClass('navbar-fixed-bottom');
            $('body').removeClass('navbar-bottom');
            $('.footer').show();
        }
    });


    //
    // Multiple navbars
    //

    $('#multiple').on('change', function(){

        // Value of selected items
        var vals = $(this).val();


        // If one select is active, another one is disabled
        if ((vals == 'multiple_top') || (vals == 'multiple_bottom')) {
            $("#single").prop("disabled", true);
        }
        else {
            $("#single").prop("disabled", false);
        }


        // Multiple top
        if (vals == 'multiple_top') {
            $('body').addClass('navbar-top-md-xs');
            $('#navbar-main, #navbar-second').wrapAll('<div class="navbar-fixed-top" />');
            
        }
        else {
            $('body').removeClass('navbar-top-md-xs');
            $('body').children('.navbar-fixed-top').children().unwrap();
        }


        // Multiple bottom
        if (vals == 'multiple_bottom') {
            $('body').addClass('navbar-bottom-md-xs');
            $('#navbar-main, #navbar-second').wrapAll('<div class="navbar-fixed-bottom" />');
            $('.footer').hide();
        }
        else {
            $('body').removeClass('navbar-bottom-md-xs');
            $('body').children('.navbar-fixed-bottom').children().unwrap();
            $('.footer').show();
        }
    });

});

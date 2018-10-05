/* ------------------------------------------------------------------------------
*
*  # Set fallback language
*
*  Specific JS code additions for internationalization_fallback.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Configuration
    // -------------------------

    // Hide sidebar category titles on load
    $('.category-title > span').css('visibility', 'hidden');

    // Add options
    i18n.init({
        resGetPath: 'assets/locales/__lng__.json',
        debug: true,
        load: 'unspecific',
        detectLngQS: 'lang',
        fallbackLng : 'en'
    },
    function () {

        // Init
        $('body').i18n();

        // Show sidebar category titles after load
        $('.category-title > span').css('visibility', 'visible');
    });



    // Change languages in dropdown
    // -------------------------

    if(i18n.lng() === "en" || "hu" || "es") {

        // Set active class
        $('.english').parent().addClass('active');

        // Change language in dropdown
        $('.language-switch').children('.dropdown-toggle').html(
            $('.language-switch').find('.english').html() + ' <i class="caret" />'
        ).children('img').addClass('position-left');
    }

});

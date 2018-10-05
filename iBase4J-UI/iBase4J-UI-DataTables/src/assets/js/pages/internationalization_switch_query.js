/* ------------------------------------------------------------------------------
*
*  # Change language with page reload
*
*  Specific JS code additions for internationalization_switch_query.html page
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
        fallbackLng: false,
        load: 'unspecific',
        detectLngQS: 'lang'
    },
    function () {
        
        // Init
        $('body').i18n();

        // Show sidebar category titles after load
        $('.category-title > span').css('visibility', 'visible');
    });



    // Change languages in dropdown
    // -------------------------

    // English
    if(i18n.lng() === "en") {

        // Set active class
        $('.english').parent().addClass('active');

        // Change language in dropdown
        $('.language-switch').children('.dropdown-toggle').html(
            $('.language-switch').find('.english').html() + ' <i class="caret" />'
        ).children('img').addClass('position-left');
    }


    // Russian
    if(i18n.lng() === "ru") {

        // Set active class
        $('.russian').parent().addClass('active');

        // Change language in dropdown
        $('.language-switch').children('.dropdown-toggle').html(
            $('.language-switch').find('.russian').html() + ' <i class="caret" />'
        ).children('img').addClass('position-left');
    }


    // Ukrainian
    if(i18n.lng() === "ua") {

        // Set active class
        $('.ukrainian').parent().addClass('active');

        // Change language in dropdown
        $('.language-switch').children('.dropdown-toggle').html(
            $('.language-switch').find('.ukrainian').html() + ' <i class="caret" />'
        ).children('img').addClass('position-left');
    }

});

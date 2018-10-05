/* ------------------------------------------------------------------------------
*
*  # Change language without page reload
*
*  Specific JS code additions for internationalization_switch_direct.html page
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
        load: 'unspecific'
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



    // Change languages in navbar
    // -------------------------

    // Define switcher container
    var switchContainer = $('.language-switch');


    // English
    $('.english').on('click', function () {

        // Set language
        $.i18n.setLng('en', function() {
            $('body').i18n();
        });

        // Change lang in dropdown
        switchContainer.children('.dropdown-toggle').html(
            $('.english').html() + ' <i class="caret" />'
        ).children('img').addClass('position-left');

        // Set active class
        switchContainer.find('li').removeClass('active');
        $('.english').parent().addClass('active');
    });


    // Russian
    $('.russian').on('click', function () {

        // Set language
        $.i18n.setLng('ru', function() {
            $('body').i18n();
        });

        // Change lang in dropdown
        switchContainer.children('.dropdown-toggle').html(
            $('.russian').html() + ' <i class="caret" />'
        ).children('img').addClass('position-left');
        
        // Set active class
        switchContainer.find('li').removeClass('active');
        $('.russian').parent().addClass('active');
    });


    // Ukrainian
    $('.ukrainian').on('click', function () {

        // Set language
        $.i18n.setLng('ua', function() {
            $('body').i18n();
        });

        // Change lang in dropdown
        switchContainer.children('.dropdown-toggle').html(
            $('.ukrainian').html() + ' <i class="caret" />'
        ).children('img').addClass('position-left');
        
        // Set active class
        switchContainer.find('li').removeClass('active');
        $('.ukrainian').parent().addClass('active');
    });

});

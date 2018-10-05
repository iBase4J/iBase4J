/* ------------------------------------------------------------------------------
*
*  # i18next callbacks
*
*  Specific JS code additions for internationalization_callbacks.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // jGrowl configuration
    // -------------------------

    // Hide closer
    $.jGrowl.defaults.closer = false


    // Add bounceIn animation
    $.jGrowl.defaults.beforeOpen = function(e,m,o) {
        $(this).velocity('transition.bounceRightIn');
    };

    // Add bounceOut animation
    $.jGrowl.defaults.beforeClose = function(e,m,o) {
        $(this).velocity('transition.bounceRightOut')
    };



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

        // Initialize translation
        $('body').i18n();

        // Initialize Growl notification on load
        $.jGrowl('Library initialized, language file loaded.', {
            theme: 'bg-blue',
            life: 4000
        });

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

        // Display Growl callback notification
        $.jGrowl('The following language has beed detected: ' + '<span class="text-semibold text-uppercase">' + i18n.lng() + '</span>', {theme: 'bg-slate', life: 4000});
    }


    // Russian
    if(i18n.lng() === "ru") {

        // Set active class
        $('.russian').parent().addClass('active');

        // Change language in dropdown
        $('.language-switch').children('.dropdown-toggle').html(
            $('.language-switch').find('.russian').html() + ' <i class="caret" />'
        ).children('img').addClass('position-left');

        // Display Growl callback notification
        $.jGrowl('The following language has beed detected: ' + '<span class="text-semibold text-uppercase">' + i18n.lng() + '</span>', {theme: 'bg-slate', life: 4000});
    }


    // Ukrainian
    if(i18n.lng() === "ua") {

        // Set active class
        $('.ukrainian').parent().addClass('active');

        // Change language in dropdown
        $('.language-switch').children('.dropdown-toggle').html(
            $('.language-switch').find('.ukrainian').html() + ' <i class="caret" />'
        ).children('img').addClass('position-left');

        // Display Growl callback notification
        $.jGrowl('The following language has beed detected: ' + '<span class="text-semibold text-uppercase">' + i18n.lng() + '</span>', {theme: 'bg-slate', life: 4000});
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

        // Display Growl callback notification
        $.jGrowl('Language has been changed to: ' + '<span class="text-semibold text-uppercase">' + i18n.lng() + '</span>', {
            theme: 'bg-slate',
            position: 'center',
            life: 4000
        });
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

        // Display Growl callback notification
        $.jGrowl('Language has been changed to: ' + '<span class="text-semibold text-uppercase">' + i18n.lng() + '</span>', {
            theme: 'bg-slate',
            position: 'center',
            life: 4000
        });
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

        // Display Growl callback notification
        $.jGrowl('Language has been changed to: ' + '<span class="text-semibold text-uppercase">' + i18n.lng() + '</span>', {
            theme: 'bg-slate',
            position: 'center',
            life: 4000
        });
    });

});

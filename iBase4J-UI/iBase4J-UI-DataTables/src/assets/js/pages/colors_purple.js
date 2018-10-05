/* ------------------------------------------------------------------------------
*
*  # Purple palette colors
*
*  Specific JS code additions for colors_purple.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Selects
    // ------------------------------

    // Basic select2
    $('.select').select2({
        minimumResultsForSearch: Infinity,
        containerCssClass: 'bg-purple-400'
    });


    // Select2 ultiselect item color
    $('.select-item-color').select2({
        containerCssClass: 'bg-purple-400'
    });


    // Select2 dropdown menu color
    $('.select-menu-color').select2({
        containerCssClass: 'bg-purple-400',
        dropdownCssClass: 'bg-purple-400'
    });


    // Multiselect
    $('.multiselect').multiselect({
        buttonClass: 'btn bg-purple-400',
        nonSelectedText: 'Select your state',
        onChange: function() {
            $.uniform.update();
        }
    });


    // SelectBoxIt
    $(".selectbox").selectBoxIt({
        autoWidth: false,
        theme: "bootstrap"
    });


    // Bootstrap select
    $.fn.selectpicker.defaults = {
        iconBase: '',
        tickIcon: 'icon-checkmark-circle'
    }
    $('.bootstrap-select').selectpicker();



    // Notifications
    // ------------------------------

    // jGrowl
    $('.growl-launch').on('click', function () {
        $.jGrowl('I am a well highlighted purple notice..', { theme: 'bg-purple-400', header: 'Well highlighted' });
    });


    // PNotify
    $('.pnotify-launch').on('click', function () {
        new PNotify({
            title: 'Info Notice',
            text: 'Check me out! I\'m a notice.',
            icon: 'icon-info22',
            animate_speed: 200,
            delay: 5000,
            addclass: 'bg-purple'
        });
    });



    // Form components
    // ------------------------------

    // Switchery toggle
    var switchery = document.querySelector('.switch');
    var init = new Switchery(switchery, {color: '#7E57C2'});


    // Checkboxes and radios
    $(".styled, .multiselect-container input").uniform({
        radioClass: 'choice',
        checkboxClass: 'checker',
        wrapperClass: "border-purple text-purple-600"
    });


    // File input
    $(".file-styled").uniform({
        fileButtonClass: 'action btn bg-purple'
    });



    // Popups
    // ------------------------------

    // Tooltip
    $('[data-popup=tooltip-custom]').tooltip({
        template: '<div class="tooltip"><div class="bg-purple-400"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div></div>'
    });


    // Popover title
    $('[data-popup=popover-custom]').popover({
        template: '<div class="popover border-purple-400"><div class="arrow"></div><h3 class="popover-title bg-purple-400"></h3><div class="popover-content"></div></div>'
    });


    // Popover background color
    $('[data-popup=popover-solid]').popover({
        template: '<div class="popover bg-purple-400"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
    });

});

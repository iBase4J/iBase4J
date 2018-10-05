/* ------------------------------------------------------------------------------
*
*  # jQuery UI navigation components
*
*  Specific JS code additions for jqueryui_navigation.html page
*
*  Version: 1.0
*  Latest update: Nov 12, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Accordion
    // -------------------------

    // Basic accordion
    $("#jui-accordion-basic").accordion();


    // Collapsible accordion
    $("#jui-accordion-collapsible").accordion({
        collapsible: true
    });


    // Fill height
    $("#jui-accordion-fill").accordion({
        heightStyle: "fill"
    });


    // Accordion on hover
    $("#jui-accordion-hover").accordion({
        event: "mouseover"
    });


    // Animated accordion
    $("#jui-accordion-animate").accordion({
        animate: false
    });


    // Sortable accordion
    $("#jui-accordion-sortable").accordion({
        header: "> div > span"
    })
    .sortable({
        axis: "y",
        handle: "span",
        stop: function(event, ui) {
            ui.item.children("span").triggerHandler("focusout");

            // Refresh accordion to handle new order
            $(this).accordion("refresh");
        }
    });



    // Menu
    // -------------------------

    // Basic example
    $(".jui-menu-basic").menu();


    // Disabled menu
    $(".jui-menu-disabled").menu({
        disabled: true
    }).find('a').on('click', function (e) {
        e.preventDefault();
    });


    // Menu with headers
    $(".jui-menu-header").menu({
        items: "> :not(.ui-menu-header)"
    });



    // Tabs
    // -------------------------

    // Basic example
    $(".jui-tabs-basic").tabs();


    // Collapsible tabs
    $(".jui-tabs-collapsible").tabs({
        collapsible: true
    });


    // Open tab on hover
    $(".jui-tabs-hover").tabs({
        event: 'mouseover'
    });


    //
    // Sortable tabs
    //

    // Initialize
    var tabs = $(".jui-tabs-sortable").tabs();

    // Add sortable functionality
    tabs.find(".ui-tabs-nav").sortable({
        containment: '#ui-tabs-sortable-container',
        stop: function() {
            tabs.tabs("refresh");
        }
    });

});

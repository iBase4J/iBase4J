/* ------------------------------------------------------------------------------
*
*  # Collapsible, accordion and other navs
*
*  Specific JS code additions for components_navs.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {

    // Accordion component sorting
    $(".accordion-sortable").sortable({
        connectWith: '.accordion-sortable',
        items: '.panel',
        helper: 'original',
        cursor: 'move',
        handle: '[data-action=move]',
        revert: 100,
        containment: '.content-wrapper',
        forceHelperSize: true,
        placeholder: 'sortable-placeholder',
        forcePlaceholderSize: true,
        tolerance: 'pointer',
        start: function(e, ui){
            ui.placeholder.height(ui.item.outerHeight());
        }
    });


    // Collapsible component sorting
    $(".collapsible-sortable").sortable({
        connectWith: '.collapsible-sortable',
        items: '.panel',
        helper: 'original',
        cursor: 'move',
        handle: '[data-action=move]',
        revert: 100,
        containment: '.content-wrapper',
        forceHelperSize: true,
        placeholder: 'sortable-placeholder',
        forcePlaceholderSize: true,
        tolerance: 'pointer',
        start: function(e, ui){
            ui.placeholder.height(ui.item.outerHeight());
        }
    });

});

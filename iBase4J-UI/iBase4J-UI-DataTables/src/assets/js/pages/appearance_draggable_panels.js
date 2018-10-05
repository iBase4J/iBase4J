/* ------------------------------------------------------------------------------
 *
 *  # Draggable panels
 *
 *  Specific JS code additions for appearance_draggable_panels.html page
 *
 *  Version: 1.0
 *  Latest update: Aug 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function() {

    // Sortable rows
    $(".row-sortable").sortable({
        connectWith: '.row-sortable',
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


    // Sortable column
    $(".column-panel-sortable").sortable({
        connectWith: '.column-sortable',
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


    // Exclude element from sort
    $(".sortable-exclude").sortable({
        connectWith: '.custom-sortable',
        items: '.panel:not(.skip-sort)',
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


    // Change sort handle
    $(".sortable-heading").sortable({
        connectWith: '.heading-sortable',
        items: '.panel',
        helper: 'original',
        cursor: 'move',
        handle: '.panel-title, [data-action=move]',
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


    // Sortable panel
    $(".sortable-panel").sortable({
        connectWith: '.panel-sortable',
        items: '.panel',
        helper: 'original',
        cursor: 'move',
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


    // Sortable elements
    $(".elements-sortable").sortable({
        connectWith: '.elements-sortable',
        items: '.panel-heading, .table-responsive',
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

/* ------------------------------------------------------------------------------
*
*  # Dual listboxes
*
*  Specific JS code additions for form_dual_listboxes.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Basic example
    $('.listbox').bootstrapDualListbox();


    // Multiple selection
    $('.listbox-no-selection').bootstrapDualListbox({
        preserveSelectionOnMove: 'moved',
        moveOnSelect: false
    });


    // Filtered results
    $('.listbox-filtered-results').bootstrapDualListbox({
        nonSelectedListLabel: 'Non-selected',
        selectedListLabel: 'Selected',
        preserveSelectionOnMove: 'moved',
        moveOnSelect: false,
        nonSelectedFilter: 'Biophysics|Econophysics|Geophysics|Thermodynamics'
    });


    // Disable filtering
    $('.listbox-filter-disabled').bootstrapDualListbox({
        showFilterInputs: false
    });


    // Control container height
    $('.listbox-tall').bootstrapDualListbox({
        selectorMinimalHeight: 300
    });


    // Custom text
    $('.listbox-custom-text').bootstrapDualListbox({
        moveOnSelect: false,
        infoText: 'Показать все {0}',
        infoTextFiltered: '<span class="label label-warning">Отфильтровано</span> {0} из {1}',
        infoTextEmpty: 'Пустой лист',
        filterPlaceHolder: 'Фильтр',
        filterTextClear: 'Показать все'
    });


    //
    // Add options dynamically
    //

    // Initialize
    $('.listbox-dynamic-options').bootstrapDualListbox({
        moveOnSelect: false
    });


    // Add options
    $(".listbox-add").click(function(){
        $('.listbox-dynamic-options').append('<option value="apples">Apples</option><option value="oranges" selected>Oranges</option>');
        $('.listbox-dynamic-options').trigger('bootstrapDualListbox.refresh');
    });


    // Add options with clearing highlights
    $(".listbox-add-clear").click(function(){
        $('.listbox-dynamic-options').append('<option value="apples">Apples</option><option value="oranges" selected>Oranges</option>');
        $('.listbox-dynamic-options').trigger('bootstrapDualListbox.refresh', true);
    });
    
});

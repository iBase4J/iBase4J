/* ------------------------------------------------------------------------------
*
*  # Sidebar components
*
*  Specific JS code additions for sidebar_components.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Pickers
    // ------------------------------

    // Flat color picker
    $(".colorpicker-flat-full").spectrum({
        flat: true,
        showInitial: true,
        showButtons: false,
        showInput: true,
        showAlpha: true,
        allowEmpty: true
    });


    // jQuery UI date picker
    $('.datepicker').datepicker();


    //
    // Date range picker
    //

    // Initialize with options
    $('#reportrange').daterangepicker(
        {
            startDate: moment().subtract('days', 29),
            endDate: moment(),
            minDate: '01/01/2015',
            maxDate: '12/31/2016',
            dateLimit: { days: 60 },
            ranges: {
                'Today': [moment(), moment()],
                'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
                'Last 7 Days': [moment().subtract('days', 6), moment()],
                'This Month': [moment().startOf('month'), moment().endOf('month')],
                'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
            },
            opens: 'right',
            buttonClasses: ['btn'],
            applyClass: 'btn-small btn-info btn-block',
            cancelClass: 'btn-small btn-default btn-block',
            locale: {
                applyLabel: 'Submit',
                fromLabel: 'From',
                toLabel: 'To',
                customRangeLabel: 'Custom Range',
                daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
                monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                firstDay: 1
            }
        },
        function(start, end) {
            $('#reportrange .daterange-custom-display').html(start.format('<i>D</i> <b><i>MMM</i> <i>YYYY</i></b>') + '<em> &#8211; </em>' + end.format('<i>D</i> <b><i>MMM</i> <i>YYYY</i></b>'));
        }
    );

    // Custom date display layout
    $('#reportrange .daterange-custom-display').html(moment().subtract('days', 29).format('<i>D</i> <b><i>MMM</i> <i>YYYY</i></b>') + '<em> &#8211; </em>' + moment().format('<i>D</i> <b><i>MMM</i> <i>YYYY</i></b>'));



    // Selects
    // ------------------------------

    // SelectBoIt selects
    $(".selectbox").selectBoxIt({
        autoWidth: false 
    });


    // Multiselect
    $('.multiselect').multiselect({
        buttonWidth: '100%',
        onChange: function() {
            $.uniform.update();
        }
    });


    // Select2 selects
    $('.select').select2({
        minimumResultsForSearch: Infinity
    });


    //
    // Bootstrap select
    //

    // Hide icons
    $.fn.selectpicker.defaults = {
        iconBase: ''
    }

    // Initialize
    $('.bootstrap-select').selectpicker();



    // jQuery UI sliders
    // ------------------------------

    // Range slider
    $( ".ui-slider-range" ).slider({
        range: true,
        min: 0,
        max: 60,
        values: [ 10, 50 ]
    });


    //
    // Add pips to horizontal slider
    //

    // First we need a slider to work with
    $(".ui-slider-labels").slider({
        max: 9,
        range: true,
        values: [ 2, 7 ]
    });

    // And then we can apply pips to it!
    $(".ui-slider-labels").slider("pips" , {
        rest: "labels"
    });
    $(".ui-slider-labels").slider("float");


    //
    // Add pips to vertical slider
    //

    // Add options
    $(".ui-slider-vertical-pips > span").each(function() {

        // Read initial values from markup and remove that
        var value = parseInt($(this).text());

        $(this).empty().slider({
            min: 0,
            max: 9,
            value: value,
            animate: true,
            range: 'min',
            orientation: "vertical"
        });
    });

    // Add pips
    $(".ui-slider-vertical-pips > span").slider("pips");



    // Styled form components
    // ------------------------------

    // Multiple switchery toggles
    if (Array.prototype.forEach) {
        var elems = Array.prototype.slice.call(document.querySelectorAll('.switchery'));
        elems.forEach(function(html) {
            var switchery = new Switchery(html);
        });
    }
    else {
        var elems = document.querySelectorAll('.switchery');
        for (var i = 0; i < elems.length; i++) {
            var switchery = new Switchery(elems[i]);
        }
    }


    // Checkboxes, radios
    $(".styled, .multiselect-container input").uniform({
        radioClass: 'choice'
    });


    // File input
    $(".file-styled").uniform({
        fileButtonClass: 'action btn bg-warning'
    });



    // Other components
    // ------------------------------

    // Sortable panels
    $(".sortable").sortable({
        connectWith: '.sortable',
        items: '.sidebar-category',
        helper: 'original',
        cursor: 'move',
        handle: '[data-action=move]',
        revert: 100,
        containment: '.sidebar-secondary',
        forceHelperSize: true,
        placeholder: 'sortable-placeholder',
        forcePlaceholderSize: true,
        tolerance: 'pointer',
        start: function(e, ui){
            ui.placeholder.height(ui.item.outerHeight());
        }
    });


    // Dual listbox
    $('.listbox-no-selection').bootstrapDualListbox({
        preserveSelectionOnMove: 'moved',
        moveOnSelect: false
    });


    // Dynamit tree
    $(".tree-default").fancytree();
    
});

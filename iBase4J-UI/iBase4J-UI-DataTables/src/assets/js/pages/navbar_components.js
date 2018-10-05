/* ------------------------------------------------------------------------------
*
*  # Navbar components
*
*  Specific JS code additions for navbar_components.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Select2 selects
    // ------------------------------

    // Fixed width
    $('.select').select2({
        minimumResultsForSearch: Infinity,
        width: '200px',
        dropdownCssClass: 'border-info-700'
    });


    // Full width
    $('.select-full').select2({
        minimumResultsForSearch: Infinity
    });



    // Date range pickers
    // ------------------------------

    //
    // Custom display
    //

    // Initialize
    $('.daterange-ranges-button').daterangepicker(
        {
            startDate: moment().subtract('days', 29),
            endDate: moment(),
            minDate: '01/01/2014',
            maxDate: '12/31/2018',
            dateLimit: {
                days: 60
            },
            ranges: {
                'Today': [moment(), moment()],
                'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
                'Last 7 Days': [moment().subtract('days', 6), moment()],
                'Last 30 Days': [moment().subtract('days', 29), moment()],
                'This Month': [moment().startOf('month'), moment().endOf('month')],
                'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
            },
            opens: 'left',
            applyClass: 'btn-small btn-primary btn-block',
            cancelClass: 'btn-small btn-default btn-block',
            format: 'MM/DD/YYYY'
        },
        function(start, end) {
            $('.daterange-ranges-button span').html(start.format('MMM D, YY') + ' - ' + end.format('MMM D, YY'));
        }
    );

    // Format results
    $('.daterange-ranges-button span').html(moment().subtract('days', 29).format('MMM D, YY') + ' - ' + moment().format('MMM D, YY'));


    //
    // Attached to button
    //

    // Initialize
    $('.daterange-ranges').daterangepicker(
        {
            startDate: moment().subtract('days', 29),
            endDate: moment(),
            minDate: '01/01/2014',
            maxDate: '12/31/2018',
            dateLimit: { days: 60 },
            ranges: {
                'Today': [moment(), moment()],
                'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
                'Last 7 Days': [moment().subtract('days', 6), moment()],
                'Last 30 Days': [moment().subtract('days', 29), moment()],
                'This Month': [moment().startOf('month'), moment().endOf('month')],
                'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
            },
            opens: 'right',
            applyClass: 'btn-small btn-primary btn-block',
            cancelClass: 'btn-small btn-default btn-block',
            format: 'MM/DD/YYYY'
        },
        function(start, end) {
            $('.daterange-ranges span').html(start.format('MMM D, YY') + ' - ' + end.format('MMM D, YY'));
        }
    );

    // Format results
    $('.daterange-ranges span').html(moment().subtract('days', 29).format('MMM D, YY') + ' - ' + moment().format('MMM D, YY'));



    // Form components
    // ------------------------------

    // Switchery toggles
    var elems = Array.prototype.slice.call(document.querySelectorAll('.navbar-switch'));
    elems.forEach(function(html) {
        var switchery = new Switchery(html, {color: '#006064', secondaryColor: '#fff'});
    });


    // Multiselect
    $('.multiselect').multiselect({
        buttonWidth: 200,
        onChange: function() {
            $.uniform.update();
        }
    });


    // Styled checkboxes, radios
    $(".styled, .multiselect-container input").uniform({
        radioClass: 'choice'
    });


    // Styled file input
    $(".file-styled").uniform({
        fileButtonClass: 'action btn btn-default btn-icon',
        fileButtonHtml: '<i class="icon-upload"></i>'
    });
    
});

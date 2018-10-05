/* ------------------------------------------------------------------------------
*
*  # Page header component
*
*  Specific JS code additions for components_page_header.html page
*
*  Version: 1.1
*  Latest update: Nov 25, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Date range pickers
    // ------------------------------

    //
    // Custom display
    //

    // Setup
    $('#reportrange').daterangepicker(
        {
            startDate: moment().subtract('days', 29),
            endDate: moment(),
            minDate: '01/01/2014',
            maxDate: '12/31/2016',
            dateLimit: {
                days: 60
            },
            ranges: {
                'Today': [moment(), moment()],
                'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
                'Last 7 Days': [moment().subtract('days', 6), moment()],
                'This Month': [moment().startOf('month'), moment().endOf('month')],
                'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
            },
            opens: 'left',
            buttonClasses: ['btn'],
            applyClass: 'btn-small btn-info btn-block',
            cancelClass: 'btn-small btn-default btn-block',
            separator: ' to ',
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

            // Format date
            $('#reportrange .daterange-custom-display').html(start.format('<i>D</i> <b><i>MMM</i> <i>YYYY</i></b>') + '<em>&#8211;</em>' + end.format('<i>D</i> <b><i>MMM</i> <i>YYYY</i></b>'));
        }
    );

    // Format date
    $('#reportrange .daterange-custom-display').html(moment().subtract('days', 29).format('<i>D</i> <b><i>MMM</i> <i>YYYY</i></b>') + '<em>&#8211;</em>' + moment().format('<i>D</i> <b><i>MMM</i> <i>YYYY</i></b>'));


    //
    // As a button
    //

    // Setup
    $('.daterange-ranges').daterangepicker(
        {
            startDate: moment().subtract('days', 29),
            endDate: moment(),
            minDate: '01/01/2014',
            maxDate: '12/31/2016',
            dateLimit: { days: 60 },
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
            cancelClass: 'btn-small btn-default btn-block'
            //format: 'MM/DD/YYYY'
        },
        function(start, end) {

            // Format date
            $('.daterange-ranges span').html(end.format('MMM D, YYYY') + ' - ' + start.format('MMM D, YYYY'));
        }
    );

    // Format date
    $('.daterange-ranges span').html(moment().format('MMM D, YYYY') + ' - ' + moment().subtract('days', 29).format('MMM D, YYYY'));



    // Form components
    // ------------------------------

    // Select2 selects
    $('.select').select2({
        minimumResultsForSearch: Infinity,
        width: 220
    });


    // Bootstrap multiselect
    $('.multiselect').multiselect({
        dropRight: true,
        buttonClass: 'btn btn-default'
    });


    // Switchery toggles
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


    // Styled checkboxes/radios
    $(".styled, .multiselect-container input").uniform({ radioClass: 'choice'});


    // Styled file input
    $(".file-styled").uniform({
        fileButtonClass: 'action btn bg-warning-400 btn-icon',
        fileButtonHtml: '<i class="icon-upload"></i>'
    });
});
/* ------------------------------------------------------------------------------
*
*  # Date and time pickers
*
*  Specific JS code additions for picker_date.html page
*
*  Version: 1.1
*  Latest update: Aug 10, 2016
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Date range picker
    // ------------------------------

    // Basic initialization
    $('.daterange-basic').daterangepicker({
        applyClass: 'bg-slate-600',
        cancelClass: 'btn-default'
    });


    // Display week numbers
    $('.daterange-weeknumbers').daterangepicker({
        showWeekNumbers: true,
        applyClass: 'bg-slate-600',
        cancelClass: 'btn-default'
    });


    // Button class options
    $('.daterange-buttons').daterangepicker({
        applyClass: 'btn-success',
        cancelClass: 'btn-danger'
    });


    // Display time picker
    $('.daterange-time').daterangepicker({
        timePicker: true,
        applyClass: 'bg-slate-600',
        cancelClass: 'btn-default',
        locale: {
            format: 'MM/DD/YYYY h:mm a'
        }
    });


    // Show calendars on left
    $('.daterange-left').daterangepicker({
        opens: 'left',
        applyClass: 'bg-slate-600',
        cancelClass: 'btn-default'
    });


    // Single picker
    $('.daterange-single').daterangepicker({ 
        singleDatePicker: true
    });


    // Display date dropdowns
    $('.daterange-datemenu').daterangepicker({
        showDropdowns: true,
        opens: "left",
        applyClass: 'bg-slate-600',
        cancelClass: 'btn-default'
    });


    // 10 minute increments
    $('.daterange-increments').daterangepicker({
        timePicker: true,
        opens: "left",
        applyClass: 'bg-slate-600',
        cancelClass: 'btn-default',
        timePickerIncrement: 10,
        locale: {
            format: 'MM/DD/YYYY h:mm a'
        }
    });


    // Localization
    $('.daterange-locale').daterangepicker({
        applyClass: 'bg-slate-600',
        cancelClass: 'btn-default',
        opens: "left",
        ranges: {
            'Сегодня': [moment(), moment()],
            'Вчера': [moment().subtract('days', 1), moment().subtract('days', 1)],
            'Последние 7 дней': [moment().subtract('days', 6), moment()],
            'Последние 30 дней': [moment().subtract('days', 29), moment()],
            'Этот месяц': [moment().startOf('month'), moment().endOf('month')],
            'Прошедший месяц': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
        },
        locale: {
            applyLabel: 'Вперед',
            cancelLabel: 'Отмена',
            startLabel: 'Начальная дата',
            endLabel: 'Конечная дата',
            customRangeLabel: 'Выбрать дату',
            daysOfWeek: ['Вс', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт','Сб'],
            monthNames: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'],
            firstDay: 1
        }
    });


    //
    // Pre-defined ranges and callback
    //

    // Initialize with options
    $('.daterange-predefined').daterangepicker(
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
            applyClass: 'btn-small bg-slate',
            cancelClass: 'btn-small btn-default'
        },
        function(start, end) {
            $('.daterange-predefined span').html(start.format('MMMM D, YYYY') + ' &nbsp; - &nbsp; ' + end.format('MMMM D, YYYY'));
            $.jGrowl('Date range has been changed', { header: 'Update', theme: 'bg-primary', position: 'center', life: 1500 });
        }
    );

    // Display date format
    $('.daterange-predefined span').html(moment().subtract('days', 29).format('MMMM D, YYYY') + ' &nbsp; - &nbsp; ' + moment().format('MMMM D, YYYY'));


    //
    // Inside button
    //

    // Initialize with options
    $('.daterange-ranges').daterangepicker(
        {
            startDate: moment().subtract('days', 29),
            endDate: moment(),
            minDate: '01/01/2012',
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
            applyClass: 'btn-small bg-slate-600',
            cancelClass: 'btn-small btn-default'
        },
        function(start, end) {
            $('.daterange-ranges span').html(start.format('MMMM D, YYYY') + ' &nbsp; - &nbsp; ' + end.format('MMMM D, YYYY'));
        }
    );

    // Display date format
    $('.daterange-ranges span').html(moment().subtract('days', 29).format('MMMM D, YYYY') + ' &nbsp; - &nbsp; ' + moment().format('MMMM D, YYYY'));


    
    // Pick-a-date picker
    // ------------------------------


    // Basic options
    $('.pickadate').pickadate();


    // Change day names
    $('.pickadate-strings').pickadate({
        weekdaysShort: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
        showMonthsShort: true
    });


    // Button options
    $('.pickadate-buttons').pickadate({
        today: '',
        close: '',
        clear: 'Clear selection'
    });


    // Accessibility labels
    $('.pickadate-accessibility').pickadate({
        labelMonthNext: 'Go to the next month',
        labelMonthPrev: 'Go to the previous month',
        labelMonthSelect: 'Pick a month from the dropdown',
        labelYearSelect: 'Pick a year from the dropdown',
        selectMonths: true,
        selectYears: true
    });


    // Localization
    $('.pickadate-translated').pickadate({
        monthsFull: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
        weekdaysShort: ['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam'],
        today: 'aujourd\'hui',
        clear: 'effacer',
        formatSubmit: 'yyyy/mm/dd'
    });


    // Format options
    $('.pickadate-format').pickadate({

        // Escape any “rule” characters with an exclamation mark (!).
        format: 'You selecte!d: dddd, dd mmm, yyyy',
        formatSubmit: 'yyyy/mm/dd',
        hiddenPrefix: 'prefix__',
        hiddenSuffix: '__suffix'
    });


    // Editable input
    var $input_date = $('.pickadate-editable').pickadate({
        editable: true,
        onClose: function() {
            $('.datepicker').focus();
        }
    });

    var picker_date = $input_date.pickadate('picker');
    $input_date.on('click', function(event) { // register events (https://github.com/amsul/pickadate.js/issues/542)
        if (picker_date.get('open')) {
            picker_date.close();
        } else {
            picker_date.open();
        }                        
        event.stopPropagation();
    });


    // Dropdown selectors
    $('.pickadate-selectors').pickadate({
        selectYears: true,
        selectMonths: true
    });


    // Year selector
    $('.pickadate-year').pickadate({
        selectYears: 4
    });


    // Set first weekday
    $('.pickadate-weekday').pickadate({
        firstDay: 1
    });


    // Date limits
    $('.pickadate-limits').pickadate({
        min: [2014,3,20],
        max: [2014,7,14]
    });


    // Disable certain dates
    $('.pickadate-disable').pickadate({
        disable: [
            [2015,8,3],
            [2015,8,12],
            [2015,8,20]
        ]
    });


    // Disable date range
    $('.pickadate-disable-range').pickadate({
        disable: [
            5,
            [2013, 10, 21, 'inverted'],
            { from: [2014, 3, 15], to: [2014, 3, 25] },
            [2014, 3, 20, 'inverted'],
            { from: [2014, 3, 17], to: [2014, 3, 18], inverted: true }
        ]
    });


    // Events
    $('.pickadate-events').pickadate({
        onStart: function() {
            console.log('Hello there :)')
        },
        onRender: function() {
            console.log('Whoa.. rendered anew')
        },
        onOpen: function() {
            console.log('Opened up')
        },
        onClose: function() {
            console.log('Closed now')
        },
        onStop: function() {
            console.log('See ya.')
        },
        onSet: function(context) {
            console.log('Just set stuff:', context)
        }
    });


    // Pick-a-time time picker
    // ------------------------------

    // Default functionality
    $('.pickatime').pickatime();


    // Clear button
    $('.pickatime-clear').pickatime({
        clear: ''
    });


    // Time formats
    $('.pickatime-format').pickatime({

        // Escape any “rule” characters with an exclamation mark (!).
        format: 'T!ime selected: h:i a',
        formatLabel: '<b>h</b>:i <!i>a</!i>',
        formatSubmit: 'HH:i',
        hiddenPrefix: 'prefix__',
        hiddenSuffix: '__suffix'
    });


    // Send hidden value
    $('.pickatime-hidden').pickatime({
        formatSubmit: 'HH:i',
        hiddenName: true
    });


    // Editable input
    var $input_time = $('.pickatime-editable').pickatime({
        editable: true,
        onClose: function() {
            $('.datepicker').focus();
        }
    });

    var picker_time = $input_time.pickatime('picker');
    $input_time.on('click', function(event) { // register events (https://github.com/amsul/pickadate.js/issues/542)
        if (picker_time.get('open')) {
            picker_time.close();
        } else {
            picker_time.open();
        }                        
        event.stopPropagation();
    });


    // Time intervals
    $('.pickatime-intervals').pickatime({
        interval: 150
    });


    // Time limits
    $('.pickatime-limits').pickatime({
        min: [7,30],
        max: [14,0]
    });


    // Using integers as hours
    $('.pickatime-limits-integers').pickatime({
        disable: [
            3, 5, 7
        ]
    })


    // Disable times
    $('.pickatime-disabled').pickatime({
        disable: [
            [0,30],
            [2,0],
            [8,30],
            [9,0]
        ]
    });


    // Disabling ranges
    $('.pickatime-range').pickatime({
        disable: [
            1,
            [1, 30, 'inverted'],
            { from: [4, 30], to: [10, 30] },
            [6, 30, 'inverted'],
            { from: [8, 0], to: [9, 0], inverted: true }
        ]
    });


    // Disable all with exeption
    $('.pickatime-disableall').pickatime({
        disable: [
            true,
            3, 5, 7,
            [0,30],
            [2,0],
            [8,30],
            [9,0]
        ]
    });


    // Events
    $('.pickatime-events').pickatime({
        onStart: function() {
            console.log('Hello there :)')
        },
        onRender: function() {
            console.log('Whoa.. rendered anew')
        },
        onOpen: function() {
            console.log('Opened up')
        },
        onClose: function() {
            console.log('Closed now')
        },
        onStop: function() {
            console.log('See ya.')
        },
        onSet: function(context) {
            console.log('Just set stuff:', context)
        }
    });



    // Anytime picker
    // ------------------------------

    // Basic usage
    $("#anytime-date").AnyTime_picker({
        format: "%W, %M %D in the Year %z %E",
        firstDOW: 1
    });


    // Time picker
    $("#anytime-time").AnyTime_picker({
        format: "%H:%i"
    });


    // Display hours only
    $("#anytime-time-hours").AnyTime_picker({
        format: "%l %p"
    });


    // Date and time
    $("#anytime-both").AnyTime_picker({
        format: "%M %D %H:%i",
    });


    // Custom display format
    $("#anytime-weekday").AnyTime_picker({
        format: "%W, %D of %M, %Z"
    });


    // Numeric date
    $("#anytime-month-numeric").AnyTime_picker({
        format: "%d/%m/%Z"
    });


    // Month and day
    $("#anytime-month-day").AnyTime_picker({
        format: "%D of %M"
    });


    // On demand picker
    $('#ButtonCreationDemoButton').click(function (e) {
        $('#ButtonCreationDemoInput').AnyTime_noPicker().AnyTime_picker().focus();
        e.preventDefault();
    });


    //
    // Date range
    //

    // Options
    var oneDay = 24*60*60*1000;
    var rangeDemoFormat = "%e-%b-%Y";
    var rangeDemoConv = new AnyTime.Converter({format:rangeDemoFormat});

    // Set today's date
    $("#rangeDemoToday").click( function (e) {
        $("#rangeDemoStart").val(rangeDemoConv.format(new Date())).change();
    });

    // Clear dates
    $("#rangeDemoClear").click( function (e) {
        $("#rangeDemoStart").val("").change();
    });

    // Start date
    $("#rangeDemoStart").AnyTime_picker({
        format: rangeDemoFormat
    });

    // On value change
    $("#rangeDemoStart").change(function(e) {
        try {
            var fromDay = rangeDemoConv.parse($("#rangeDemoStart").val()).getTime();

            var dayLater = new Date(fromDay+oneDay);
                dayLater.setHours(0,0,0,0);

            var ninetyDaysLater = new Date(fromDay+(90*oneDay));
                ninetyDaysLater.setHours(23,59,59,999);

            // End date
            $("#rangeDemoFinish")
            .AnyTime_noPicker()
            .removeAttr("disabled")
            .val(rangeDemoConv.format(dayLater))
            .AnyTime_picker({
                earliest: dayLater,
                format: rangeDemoFormat,
                latest: ninetyDaysLater
            });
        }

        catch(e) {

            // Disable End date field
            $("#rangeDemoFinish").val("").attr("disabled","disabled");
        }
    });
    
});

/* ------------------------------------------------------------------------------
*
*  # Fullcalendar time and language options
*
*  Specific JS code additions for extra_fullcalendar_formats.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Add events
    // ------------------------------

    // Default events
    var events = [
        {
            title: 'All Day Event',
            start: '2014-11-01'
        },
        {
            title: 'Long Event',
            start: '2014-11-07',
            end: '2014-11-10'
        },
        {
            id: 999,
            title: 'Repeating Event',
            start: '2014-11-09T16:00:00'
        },
        {
            id: 999,
            title: 'Repeating Event',
            start: '2014-11-16T16:00:00'
        },
        {
            title: 'Conference',
            start: '2014-11-11',
            end: '2014-11-13'
        },
        {
            title: 'Meeting',
            start: '2014-11-12T10:30:00',
            end: '2014-11-12T12:30:00'
        },
        {
            title: 'Lunch',
            start: '2014-11-12T12:00:00'
        },
        {
            title: 'Meeting',
            start: '2014-11-12T14:30:00'
        },
        {
            title: 'Happy Hour',
            start: '2014-11-12T17:30:00'
        },
        {
            title: 'Dinner',
            start: '2014-11-12T20:00:00'
        },
        {
            title: 'Birthday Party',
            start: '2014-11-13T07:00:00'
        },
        {
            title: 'Click for Google',
            url: 'http://google.com/',
            start: '2014-11-28'
        }
    ];



    // Date formats
    // ------------------------------

    $('.fullcalendar-formats').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,basicWeek,basicDay'
        },
        titleFormat: {
            month: 'LL', // September 2009
            week: "MMM Do YY", // Sep 13 2009
            day: 'dddd'  // September 8
        },
        columnFormat: {
            month: 'dddd', // January
            week: 'ddd D', // Mon 7
            day: 'dddd' // Monday
        },
        timeFormat: 'h(:mm) a', // uppercase H for 24-hour clock
        defaultDate: '2014-11-12',
        editable: true,
        events: events
    });



    // Internationalization
    // ------------------------------

    // Set default language
    var currentLangCode = 'en';


    // Build the language selector's options
    $.each($.fullCalendar.langs, function(langCode) {
        $('#lang-selector').append(
            $('<option/>')
            .attr('value', langCode)
            .prop('selected', langCode == currentLangCode)
            .text(langCode)
        );
    });


    // Re-render the calendar when the selected option changes
    $('#lang-selector').on('change', function() {
        if (this.value) {
            currentLangCode = this.value;
            $('.fullcalendar-languages').fullCalendar('destroy');
            renderCalendar();
        }
    });


    // Render calendar
    renderCalendar();
    function renderCalendar() {
        $('.fullcalendar-languages').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            defaultDate: '2014-11-12',
            lang: currentLangCode,
            buttonIcons: false, // show the prev/next text
            weekNumbers: true,
            editable: true,
            events: [
                {
                    title: 'All Day Event',
                    start: '2014-11-01'
                },
                {
                    title: 'Long Event',
                    start: '2014-11-07',
                    end: '2014-11-10'
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: '2014-11-09T16:00:00'
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: '2014-11-16T16:00:00'
                },
                {
                    title: 'Conference',
                    start: '2014-11-11',
                    end: '2014-11-13'
                },
                {
                    title: 'Meeting',
                    start: '2014-11-12T10:30:00',
                    end: '2014-11-12T12:30:00'
                },
                {
                    title: 'Lunch',
                    start: '2014-11-12T12:00:00'
                },
                {
                    title: 'Meeting',
                    start: '2014-11-12T14:30:00'
                },
                {
                    title: 'Happy Hour',
                    start: '2014-11-12T17:30:00'
                },
                {
                    title: 'Dinner',
                    start: '2014-11-12T20:00:00'
                },
                {
                    title: 'Birthday Party',
                    start: '2014-11-13T07:00:00'
                },
                {
                    title: 'Click for Google',
                    url: 'http://google.com/',
                    start: '2014-11-28'
                }
            ]
        });
    }


    // We're using Select2 for language select
    $('.select').select2({
        width: 100,
        minimumResultsForSearch: Infinity,
        containerCssClass: 'bg-slate-700',
        dropdownCssClass: 'bg-slate-700'
    });
    
});

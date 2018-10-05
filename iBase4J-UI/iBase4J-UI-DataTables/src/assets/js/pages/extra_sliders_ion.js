/* ------------------------------------------------------------------------------
*
*  # Ion Range Sliders
*
*  Specific JS code additions for extra_sliders_ion.html page
*
*  Version: 1.0
*  Latest update: Nov 20, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Basic examples
    // ------------------------------

    // Basic slider
    $("#ion-basic").ionRangeSlider();

    // Set start point
    $("#ion-start").ionRangeSlider({
        min: 100,
        max: 1000,
        from: 550
    });

    // Basic range slider
    $("#ion-range").ionRangeSlider({
        type: "double",
        min: 0,
        max: 1000,
        from: 200,
        to: 800
    });


    // Range with negative values
    $("#ion-negative").ionRangeSlider({
        type: "double",
        grid: true,
        min: -1000,
        max: 1000,
        from: -500,
        to: 500
    });

    // Custom stepping
    $("#ion-step").ionRangeSlider({
        type: "double",
        grid: true,
        min: -1000,
        max: 1000,
        from: -500,
        to: 500,
        step: 250
    });

    // Fractional step
    $("#ion-fractional").ionRangeSlider({
        type: "double",
        grid: true,
        min: -12.8,
        max: 12.8,
        from: -3.2,
        to: 3.2,
        step: 0.1
    });



    // Customizing values
    // ------------------------------

    // Custom number values
    $("#ion-custom-numbers").ionRangeSlider({
        type: "double",
        grid: true,
        from: 2,
        to: 5,
        values: [0, 5, 10, 20, 35, 50, 70, 100]
    });

    // Custom value names
    $("#ion-custom-strings").ionRangeSlider({
        grid: true,
        from: 5,
        values: [
            "zero", "one",
            "two", "three",
            "four", "five",
            "six", "seven",
            "eight", "nine",
            "ten"
        ]
    });

    // Months values
    $("#ion-custom-months").ionRangeSlider({
        grid: true,
        from: 3,
        values: [
            "Jan", "Feb", "Mar",
            "Apr", "May", "Jun",
            "Jul", "Aug", "Sep",
            "Oct", "Nov", "Dec"
        ]
    });


    // Disable number prettify
    $("#ion-numbers-no-prettify").ionRangeSlider({
        grid: true,
        min: 1000,
        max: 10000,
        from: 3000,
        step: 100,
        prettify_enabled: false
    });

    // Enable number prettify
    $("#ion-numbers-prettify").ionRangeSlider({
        grid: true,
        min: 1000,
        max: 10000,
        from: 3000,
        step: 100,
        prettify_enabled: true
    });

    // Custom number separator
    $("#ion-custom-separator").ionRangeSlider({
        grid: true,
        min: 1000,
        max: 10000,
        from: 3000,
        step: 100,
        prettify_enabled: true,
        prettify_separator: ","
    });



    // Decorating numbers
    // ------------------------------

    // Numbers with prefix
    $("#ion-custom-prefix").ionRangeSlider({
        type: "double",
        grid: true,
        min: 0,
        max: 1000,
        from: 250,
        to: 750,
        step: 50,
        prefix: "$"
    });

    // Numbers with postfix
    $("#ion-custom-postfix").ionRangeSlider({
        type: "double",
        grid: true,
        min: 0,
        max: 1000,
        from: 250,
        to: 750,
        step: 50,
        postfix: "&deg;"
    });

    // Max number without limit
    $("#ion-max-no-limit").ionRangeSlider({
        grid: true,
        min: 18,
        max: 70,
        from: 40,
        prefix: "Age ",
        max_postfix: "+"
    });


    // Decorate both values
    $("#ion-decorate-both").ionRangeSlider({
        grid: true,
        type: "double",
        min: 100,
        max: 200,
        from: 145,
        to: 155,
        prefix: "HDD: ",
        postfix: " Gb",
        decorate_both: true
    });

    // Decoration separator
    $("#ion-decorate-both-custom").ionRangeSlider({
        grid: true,
        type: "double",
        min: 100,
        max: 200,
        from: 145,
        to: 155,
        prefix: "HDD: ",
        postfix: " Gb",
        values_separator: " → "
    });

    // Remove decoration
    $("#ion-decorate-both-remove").ionRangeSlider({
        grid: true,
        type: "double",
        min: 100,
        max: 200,
        from: 145,
        to: 155,
        prefix: "HDD: ",
        postfix: " Gb",
        decorate_both: false
    });



    // Advanced examples
    // ------------------------------

    // Values inside container
    $("#ion-force-edges").ionRangeSlider({
        type: "double",
        min: 1000000,
        max: 2000000,
        grid: true,
        force_edges: true
    });

    // Disabled slider
    $("#ion-disabled").ionRangeSlider({
        grid: true,
        min: 0,
        max: 100,
        from: 30,
        disable: true
    });

    // Keyboard controls
    $("#ion-keyboard").ionRangeSlider({
        grid: true,
        type: "double",
        min: 0,
        max: 100,
        from: 30,
        to: 70,
        keyboard: true,
        keyboard_step: 5
    });


    // Grid values density
    $("#ion-grid-values").ionRangeSlider({
        type: "double",
        min: 0,
        max: 100,
        from: 30,
        to: 70,
        grid: true,
        grid_num: 20
    });

    // Attach values to steps
    $("#ion-grid-snap").ionRangeSlider({
        type: "double",
        min: 0,
        max: 1000,
        step: 100,
        grid: true,
        grid_snap: true
    });

    // Attach values to fractional steps
    $("#ion-grid-snap-fractional").ionRangeSlider({
        type: "single",
        min: 0,
        max: 10,
        from: 4.68,
        step: 2.34,
        grid: true,
        grid_snap: true
    });



    // Manipulations
    // ------------------------------

    // Minimum interval size
    $("#ion-interval-min").ionRangeSlider({
        grid: true,
        type: "double",
        min: 0,
        max: 100,
        from: 30,
        to: 70,
        min_interval: 20
    });

    // Maximum interval size
    $("#ion-interval-max").ionRangeSlider({
        grid: true,
        type: "double",
        min: 0,
        max: 100,
        from: 30,
        to: 70,
        max_interval: 50
    });

    // Dragging interval
    $("#ion-interval-drag").ionRangeSlider({
        grid: true,
        type: "double",
        min: 0,
        max: 100,
        from: 30,
        to: 70,
        drag_interval: true
    });


    // Lock left handle
    $("#ion-lock-from").ionRangeSlider({
        grid: true,
        type: "double",
        min: 0,
        max: 1000,
        from: 250,
        to: 750,
        from_fixed: true
    });

    // Lock right handle
    $("#ion-lock-to").ionRangeSlider({
        grid: true,
        type: "double",
        min: 0,
        max: 1000,
        from: 250,
        to: 750,
        to_fixed: true
    });

    // Lock both handles
    $("#ion-lock-both").ionRangeSlider({
        grid: true,
        type: "double",
        min: 0,
        max: 1000,
        from: 250,
        to: 750,
        from_fixed: true,
        to_fixed: true
    });



    // Other examples
    // ------------------------------

    // Movement limit
    $("#ion-movement-limit").ionRangeSlider({
        grid: true,
        min: 0,
        max: 1000,
        from: 500,
        from_min: 100,
        from_max: 750
    });

    // Highlight limited zone
    $("#ion-highlight-limit").ionRangeSlider({
        grid: true,
        min: 0,
        max: 1000,
        from: 500,
        from_min: 100,
        from_max: 750,
        from_shadow: true
    });

    // Highlight limited zone in range
    $("#ion-highlight-range").ionRangeSlider({
        type: "double",
        min: 0,
        max: 100,
        from: 20,
        from_min: 10,
        from_max: 30,
        from_shadow: true,
        to: 80,
        to_min: 70,
        to_max: 90,
        to_shadow: true,
        grid: true,
        grid_num: 10
    });


    // Moment.js format
    $("#ion-moment-basic").ionRangeSlider({
        grid: true,
        min: +moment().subtract(1, "years").format("X"),
        max: +moment().format("X"),
        from: +moment().subtract(6, "months").format("X"),
        force_edges: true,
        prettify: function (num) {
            return moment(num, "X").format("ll");
        }
    });

    // Time format
    $("#ion-moment-time").ionRangeSlider({
        grid: true,
        min: +moment().subtract(12, "hours").format("X"),
        max: +moment().format("X"),
        from: +moment().subtract(6, "hours").format("X"),
        force_edges: true,
        prettify: function (num) {
            return moment(num, "X").format("hh:mm A");
        }
    });

    // Localization
    $("#ion-moment-local").ionRangeSlider({
        grid: true,
        min: +moment().subtract(11, "months").format("X"),
        max: +moment().format("X"),
        from: +moment().subtract(6, "months").format("X"),
        force_edges: true,
        prettify: function (num) {
            var m = moment(num, "X").locale("ru");
            return m.format("MMMM");
        }
    });

});

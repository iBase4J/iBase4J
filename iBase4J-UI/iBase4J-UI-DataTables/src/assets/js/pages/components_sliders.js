/* ------------------------------------------------------------------------------
*
*  # Sliders
*
*  Specific JS code additions for components_sliders.html page
*
*  Version: 1.0
*  Latest update: Aug 1, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // jQuery UI sliders
    // ------------------------------

    //
    // Horizontal sliders
    //

    // Basic slider
    $( ".ui-slider" ).slider();


    // Custom start value
    $( ".ui-slider-value" ).slider({
        value: 20,
        min: 0,
        max: 40
    });


    // Snap to increments
    $( ".ui-slider-increments" ).slider({
        value:100,
        min: 0,
        max: 500,
        step: 50
    });


    // Range slider
    $( ".ui-slider-range" ).slider({
        range: true,
        min: 0,
        max: 60,
        values: [ 10, 50 ]
    });


    // Fixed minimum
    $( ".ui-slider-range-min" ).slider({
        range: "min",
        value: 37,
        min: 1,
        max: 700,
        slide: function( event, ui ) {
            $( "#amount" ).val( "$" + ui.value );
        }
    });
    $( "#amount" ).val( "$" + $( ".ui-slider-range-min" ).slider( "value" ) );


    // Fixed maximum
    $( ".ui-slider-range-max" ).slider({
        range: "max",
        min: 1,
        max: 10,
        value: 2,
        slide: function( event, ui ) {
            $( "#amount" ).val( ui.value );
        }
    });
    $( "#amount" ).val( $( ".ui-slider-range-max" ).slider( "value" ) );


    //
    // Vertical sliders
    //

    // Basic
    $( ".ui-slider-vertical-default > span" ).each(function() {

        // Read initial values from markup and remove that
        var value = parseInt( $( this ).text(), 10 );
        $( this ).empty().slider({
            value: value,
            animate: true,
            orientation: "vertical"
        });
    });


    // Range slider
    $( ".ui-slider-vertical-range-min > span" ).each(function() {

        // Read initial values from markup and remove that
        var value = parseInt( $( this ).text(), 10 );
        $( this ).empty().slider({
            value: value,
            range: "min",
            animate: true,
            orientation: "vertical"
        });
    });


    // Fixed maximum
    $( ".ui-slider-vertical-range-max > span" ).each(function() {

        // Read initial values from markup and remove that
        var value = parseInt( $( this ).text(), 10 );
        $( this ).empty().slider({
            value: value,
            range: "max",
            animate: true,
            orientation: "vertical"
        });
    });



    // Slider pips
    // ------------------------------

    //
    // Horizontal sliders
    //

    // Basic
    $(".ui-slider-pips").slider({
        max: 14,
        value: 7
    });
    $(".ui-slider-pips").slider("pips");


    // With tooltip
    $(".ui-slider-floats").slider({
        max: 14,
        value: 7
    });
    $(".ui-slider-floats").slider("pips");
    $(".ui-slider-floats").slider("float");


    // Both pips and tooltip
    $(".ui-slider-floats-labels").slider({
        max: 6,
        value: 3
    });
    $(".ui-slider-floats-labels").slider("pips");
    $(".ui-slider-floats-labels").slider("float", {
        pips: true
    });


    // Label with pips
    $(".ui-slider-labels").slider({
        max: 8,
        value: 4
    });
    $(".ui-slider-labels").slider("pips" , {
        rest: "label"
    });


    // Hide rest of the points
    $(".ui-slider-limits").slider({
        max: 20,
        range: true,
        values: [ 4, 16 ]
    });
    $(".ui-slider-limits").slider("pips" , {
        rest: false
    });


    // Display pips only
    $(".ui-slider-pips-only").slider({
        max: 20
    });
    $(".ui-slider-pips-only").slider("pips", {
        first: "pip",
        last: "pip"
    });


    // Suffix and prefix
    $(".ui-slider-suffix-prefix").slider({
        max: 4,
        value: 2
    });
    $(".ui-slider-suffix-prefix").slider("pips", {
        rest: "label", 
        prefix: "$",
        suffix: ".00"
    });


    // Custom label text
    var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

    $(".ui-slider-labels-custom").slider({
        min: 0,
        max: 11,
        value: 5
    });
    $(".ui-slider-labels-custom").slider("pips" , {
        rest: "label",
        labels: months
    });
    $(".ui-slider-labels-custom").on("slidechange", function(e,ui) {
        $("#ui-slider-labels-custom-output").html("You selected " + "<span class='text-danger'>" + months[ui.value] + "</span>");
    });


    // Label localization
    var hanziNums = ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十"];

    $(".ui-slider-local").slider({
        min: 1,
        max: 10,
        value: 5
    });
    $(".ui-slider-local").slider("pips" , {
        labels: hanziNums,
        rest: "label"
    });
    $(".ui-slider-local").slider("float" , {
        labels: hanziNums
    });


    //
    // Vertical sliders
    //

    // Basic
    $(".ui-slider-vertical-pips > span").each(function() {
        var value = parseInt($(this).text());
        $(this).empty().slider({
            min: 0,
            max: 10,
            value: value,
            animate: true,
            range: 'min',
            orientation: "vertical"
        });
    });
    $(".ui-slider-vertical-pips > span").slider("pips");


    // With labels
    $( ".ui-slider-vertical-labels > span" ).each(function() {
        var value = parseInt($(this).text());
        $(this).empty().slider({
            min: 0,
            max: 4,
            value: value,
            animate: true,
            range: 'min',
            orientation: "vertical"
        });
    });
    $(".ui-slider-vertical-labels > span").slider("pips" , {
        rest: "label"
    });


    // With tooltip and handle
    $( ".ui-slider-vertical-minmax > span" ).each(function() {
        var value = parseInt($(this).text());
        $(this).empty().slider({
            min: 0,
            max: 10,
            value: value,
            animate: true,
            range: 'min',
            orientation: "vertical"
        });
    });
    $(".ui-slider-vertical-minmax > span").slider("pips");
    $(".ui-slider-vertical-minmax > span").slider("float");



    // NoUI sliders
    // ------------------------------

    //
    // Basic options
    //

    // Basic
    $(".noui-slider-default").noUiSlider({
        start: [10, 50],
        connect: true,
        range: {
            'min': 0,
            'max': 60
        }
    });


    // Handles
    $('.noui-slider-handles').noUiSlider({
        start: [4000, 8000],
        range: {
            'min': [2000],
            'max': [10000]
        }
    });


    // Range slider
    $('.noui-slider-range').noUiSlider({
        start: [4000],
        range: {
            'min': [2000],
            'max': [10000]
        },
        serialization: {
            lower: [
                $.Link({
                    target: $("#noui-range-val")
                })
            ]
        }
    });


    // Stepping
    $('.noui-slider-stepping').noUiSlider({
        start: [4000],
        step: 1000,
        range: {
            'min': [2000],
            'max': [10000]
        },
        serialization: {
            lower: [
                $.Link({
                    target: $("#noui-stepping-val")
                })
            ]
        }
    });


    // Non-linear slider
    $('.noui-slider-nonlinear').noUiSlider({
        start: [4000],
        range: {
            'min': [2000],
            '30%': [4000],
            '70%': [8000],
            'max': [10000]
        },
        serialization: {
            lower: [
                $.Link({
                    target: $("#noui-nonlinear-val")
                })
            ]
        }
    });


    // Non-linear stepping
    $('.noui-slider-nonlinear-stepping').noUiSlider({
        start: [500],
        range: {
            'min': [0],
            '10%': [500, 500],
            '50%': [4000, 1000],
            'max': [10000]
        },
        serialization: {
            lower: [
                $.Link({
                    target: $("#noui-nonlinear-stepping-val")
                })
            ]
        }
    });


    // Snapping between steps
    $('.noui-slider-snapping').noUiSlider({
        start: [50, 800],
        snap: true,
        range: {
            'min': 0,
            '10%': 50,
            '20%': 100,
            '30%': 150,
            '40%': 500,
            '50%': 800,
            'max': 1000
        },
        serialization: {
            lower: [
                $.Link({
                    target: $("#noui-snapping-val1")
                })
            ],
            upper: [
                $.Link({
                    target: $("#noui-snapping-val2")
                })
            ]
        }
    });


    // Connect lower side
    $(".noui-slider-connect-lower").noUiSlider({
        start: 40,
        connect: "lower",
        range: {
            'min': 0,
            'max': 100
        },
        serialization: {
            lower: [ $.Link({ target: $("#noui-connect-lower-val") }) ]
        }
    });


    // Connect upper side
    $(".noui-slider-connect-upper").noUiSlider({
        start: 40,
        connect: "upper",
        range: {
            'min': 0,
            'max': 100
        },
        serialization: {
            lower: [ $.Link({ target: $("#noui-connect-upper-val") }) ]
        }
    });


    //
    // Behaviours
    //

    // Slider behaviour
    $(".noui-slider-behaviour").noUiSlider({
        start: [40, 60],
        step: 10,
        behaviour: 'drag',
        connect: true,
        range: {
            'min': 20,
            'max': 80
        }
    });


    // Tap behaviour
    $(".noui-slider-tap").noUiSlider({
        start: 40,
        behaviour: 'tap',
        connect: 'upper',
        range: {
            'min': 20,
            'max': 80
        }
    });


    // Drag behaviour
    $(".noui-slider-drag").noUiSlider({
        start: [40, 60],
        behaviour: 'drag',
        connect: true,
        range: {
            'min': 20,
            'max': 80
        }
    });


    // Fixed dragging
    $(".noui-slider-drag-fixed").noUiSlider({
        start: [40, 60],
        behaviour: 'drag-fixed',
        connect: true,
        range: {
            'min': 20,
            'max': 80
        }
    });


    // Snap behaviour
    $(".noui-slider-snap").noUiSlider({
        start: 40,
        behaviour: 'snap',
        connect: 'lower',
        range: {
            'min': 20,
            'max': 80
        }
    });


    // Combined options
    $(".noui-slider-combined").noUiSlider({
        start: [40, 60],
        behaviour: 'drag-tap',
        connect: true,
        range: {
            'min': 20,
            'max': 80
        }
    });


    //
    // Other examples
    //

    // Tooltip
    var customNouiToolTip = $.Link({
        target: '-tooltip-<div class="noui-tooltip"></div>',
        method: function (value) {
            $(this).html('<span class="text-semibold">' + value + '</span>');
        }
    });
    $(".noui-slider-tooltip").noUiSlider({
        start: [10, 50],
        connect: true,
        range: {
            'min': 0,
            'max': 60
        },
        serialization: {
            lower: [ customNouiToolTip, $.Link({ target: $("#noui-tooltip-val1") }) ],
            upper: [ customNouiToolTip, $.Link({ target: $("#noui-tooltip-val2") }) ]
        }
    });


    // Set minimum handles distance
    $('.noui-slider-margin').noUiSlider({
        start: [10, 50],
        connect: true,
        margin: 10,
        range: {
            'min': 0,
            'max': 60
        },
        serialization: {
            lower: [ $.Link({ target: $("#noui-margin-val1") }) ],
            upper: [ $.Link({ target: $("#noui-margin-val2") }) ]
        }
    });


    // Skip certain steps
    $(".noui-slider-skip-steps").noUiSlider({
        range: {
            'min': 0,
            '10%': 10,
            '20%': 20,
            '30%': 30,
            // Nope, 40 is no fun.
            '50%': 50,
            '60%': 60,
            '70%': 70,
            // I never liked 80.
            '90%': 90,
            'max': 100 
        },
        snap: true,
        connect: true,
        start: [20, 90],
        serialization: {
            lower: [ $.Link({ target: $("#noui-skip-steps-val1") }) ],
            upper: [ $.Link({ target: $("#noui-skip-steps-val2") }) ],
            format: { decimals: 0 }
        }
    });


    // RTL direction
    $(".noui-slider-direction").noUiSlider({
        start: 20,
        connect: 'lower',
        direction: "rtl",
        range: {
            'min': 0,
            'max': 100
        },
        serialization: {
            lower: [
                $.Link({
                    target: $("#noui-direction-val")
                })
            ]
        }
    });


    //
    // Vertical sliders
    //

    // Vertical direction
    $('.noui-slider-values1').noUiSlider({
        start: 20,
        orientation: 'vertical',
        range: {
            'min': [0],
            'max': [100]
        }
    });

    $('.noui-slider-values2').noUiSlider({
        start: 40,
        orientation: 'vertical',
        range: {
            'min': [0],
            'max': [100]
        }
    });

    $('.noui-slider-values3').noUiSlider({
        start: 60,
        orientation: 'vertical',
        range: {
            'min': [0],
            'max': [100]
        }
    });

    $('.noui-slider-values4').noUiSlider({
        start: 80,
        orientation: 'vertical',
        range: {
            'min': [0],
            'max': [100]
        }
    });


    // Connect upper side
    $('.noui-slider-upper1').noUiSlider({
        start: 20,
        connect: 'upper',
        orientation: 'vertical',
        range: {
            'min': [0],
            'max': [100]
        }
    });

    $('.noui-slider-upper2').noUiSlider({
        start: 40,
        connect: 'upper',
        orientation: 'vertical',
        range: {
            'min': [0],
            'max': [100]
        }
    });

    $('.noui-slider-upper3').noUiSlider({
        start: 60,
        connect: 'upper',
        orientation: 'vertical',
        range: {
            'min': [0],
            'max': [100]
        }
    });

    $('.noui-slider-upper4').noUiSlider({
        start: 80,
        connect: 'upper',
        orientation: 'vertical',
        range: {
            'min': [0],
            'max': [100]
        }
    });


    // Connect lower side
    $('.noui-slider-lower1').noUiSlider({
        start: 20,
        connect: 'lower',
        orientation: 'vertical',
        range: {
            'min': [0],
            'max': [100]
        }
    });

    $('.noui-slider-lower2').noUiSlider({
        start: 40,
        connect: 'lower',
        orientation: 'vertical',
        range: {
            'min': [0],
            'max': [100]
        }
    });

    $('.noui-slider-lower3').noUiSlider({
        start: 60,
        connect: 'lower',
        orientation: 'vertical',
        range: {
            'min': [0],
            'max': [100]
        }
    });

    $('.noui-slider-lower4').noUiSlider({
        start: 80,
        connect: 'lower',
        orientation: 'vertical',
        range: {
            'min': [0],
            'max': [100]
        }
    });
    
});

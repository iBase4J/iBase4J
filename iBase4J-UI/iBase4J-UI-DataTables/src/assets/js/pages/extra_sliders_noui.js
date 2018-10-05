/* ------------------------------------------------------------------------------
*
*  # NoUI Sliders
*
*  Specific JS code additions for extra_sliders_noui.html page
*
*  Version: 1.0
*  Latest update: Nov 20, 2015
*
* ---------------------------------------------------------------------------- */

$(function() {


    // Handles
    // ------------------------------

    // Setup
    var slider_handles = document.getElementById('noui-slider-handles');
    noUiSlider.create(slider_handles, {
        start: [40, 80],
        range: {
            'min': [20],
            'max': [100]
        }
    });

    // Display values
    var slider_handles_vals = [
        document.getElementById('noui-handles-lower-val'),
        document.getElementById('noui-handles-upper-val')
    ];
    slider_handles.noUiSlider.on('update', function( values, handle ) {
        slider_handles_vals[handle].innerHTML = values[handle];
    });


    // Ranges
    // ------------------------------

    // Setup
    var slider_range = document.getElementById('noui-slider-range');
    noUiSlider.create(slider_range, {
        start: [60],
        range: {
            'min': [20],
            'max': [100]
        }
    });

    // Display values
    var slider_range_val = document.getElementById('noui-range-val');
    slider_range.noUiSlider.on('update', function( values, handle ) {
        slider_range_val.innerHTML = values[handle];
    });


    // Stepping
    // ------------------------------

    // Setup
    var slider_stepping = document.getElementById('noui-slider-stepping');
    noUiSlider.create(slider_stepping, {
        start: [40],
        step: 10,
        range: {
            'min': [20],
            'max': [100]
        },
    });

    // Display values
    var slider_stepping_val = document.getElementById('noui-stepping-val');
    slider_stepping.noUiSlider.on('update', function( values, handle ) {
        slider_stepping_val.innerHTML = values[handle];
    });


    // Non-linear slider
    // ------------------------------

    // Define element
    var slider_nonlinear = document.getElementById('noui-slider-nonlinear');

    // Create slider
    noUiSlider.create(slider_nonlinear, {
        start: [ 40 ],
        range: {
            'min': [  20 ],
            '30%': [  40 ],
            '70%': [  80 ],
            'max': [ 100 ]
        }
    });

    // Define element for values
    var slider_nonlinear_val = document.getElementById('noui-nonlinear-val');

    // Show the value for the *last* moved handle.
    slider_nonlinear.noUiSlider.on('update', function( values, handle ) {
        slider_nonlinear_val.innerHTML = values[handle];
    });


    // Non-linear stepping
    // ------------------------------

    // Define element
    var slider_nonlinear_step = document.getElementById('noui-slider-nonlinear-stepping');

    // Create slider
    noUiSlider.create(slider_nonlinear_step, {
        start: [ 5, 40 ],
        range: {
            'min': [     0 ],
            '10%': [   5,  5 ],
            '50%': [  40, 10 ],
            'max': [ 100 ]
        }
    });

    // Define elements for values
    var slider_nonlinear_step_vals = [
        document.getElementById('noui-nonlinear-stepping-lower-val'),
        document.getElementById('noui-nonlinear-stepping-upper-val')
    ];

    // Show the values
    slider_nonlinear_step.noUiSlider.on('update', function( values, handle ) {
        slider_nonlinear_step_vals[handle].innerHTML = values[handle];
    });


    // Snapping between steps
    // ------------------------------

    // Define element
    var slider_nonlinear_snap = document.getElementById('noui-slider-snapping');

    // Create slider
    noUiSlider.create(slider_nonlinear_snap, {
        start: [50, 800],
        snap: true,
        connect: true,
        range: {
            'min': 0,
            '10%': 50,
            '20%': 100,
            '30%': 150,
            '40%': 500,
            '50%': 800,
            'max': 1000
        }
    });

    // Define elements for values
    var slider_nonlinear_snap_vals = [
        document.getElementById('noui-slider-snapping-lower-val'),
        document.getElementById('noui-slider-snapping-upper-val')
    ];

    // Show the values
    slider_nonlinear_snap.noUiSlider.on('update', function( values, handle ) {
        slider_nonlinear_snap_vals[handle].innerHTML = values[handle];
    });



    // Behaviours
    // ------------------------------

    //
    // Slider behaviour
    //

    // Define element
    slider_behaviour = document.getElementById('noui-slider-behaviour');

    // Create slider
    noUiSlider.create(slider_behaviour, {
        start: [ 40, 60 ],
        step: 10,
        behaviour: 'drag',
        connect: true,
        range: {
            'min':  20,
            'max':  80
        }
    });

    // Define elements for values
    var slider_behaviour_vals = [
        document.getElementById('noui-slider-behaviour-lower-val'),
        document.getElementById('noui-slider-behaviour-upper-val')
    ];

    // Show the values
    slider_behaviour.noUiSlider.on('update', function( values, handle ) {
        slider_behaviour_vals[handle].innerHTML = values[handle];
    });


    //
    // Tap behaviour
    //

    // Define element
    slider_tap_behaviour = document.getElementById('noui-slider-tap');

    // Create slider
    noUiSlider.create(slider_tap_behaviour, {
        start: 40,
        behaviour: 'tap',
        connect: 'upper',
        range: {
            'min':  20,
            'max':  80
        }
    });

    // Define elements for values
    var slider_tap_behaviour_val = document.getElementById('noui-slider-tap-val');

    // Show the values
    slider_tap_behaviour.noUiSlider.on('update', function( values, handle ) {
        slider_tap_behaviour_val.innerHTML = values[handle];
    });


    //
    // Drag behaviour
    //

    // Define element
    var slider_drag_behaviour = document.getElementById('noui-slider-drag');

    // Create slider
    noUiSlider.create(slider_drag_behaviour, {
        start: [ 40, 60 ],
        behaviour: 'drag',
        connect: true,
        range: {
            'min':  20,
            'max':  80
        }
    });

    // Define elements for values
    var slider_drag_behaviour_vals = [
        document.getElementById('noui-slider-drag-lower-val'),
        document.getElementById('noui-slider-drag-upper-val')
    ];

    // Show the values
    slider_drag_behaviour.noUiSlider.on('update', function( values, handle ) {
        slider_drag_behaviour_vals[handle].innerHTML = values[handle];
    });


    //
    // Fixed dragging
    //

    // Define element
    slider_drag_fixed = document.getElementById('noui-slider-drag-fixed');

    // Create slider
    noUiSlider.create(slider_drag_fixed, {
        start: [ 40, 60 ],
        behaviour: 'drag-fixed',
        connect: true,
        range: {
            'min':  20,
            'max':  80
        }
    });

    // Define elements for values
    var slider_drag_fixed_vals = [
        document.getElementById('noui-slider-fixed-lower-val'),
        document.getElementById('noui-slider-fixed-upper-val')
    ];

    // Show the values
    slider_drag_fixed.noUiSlider.on('update', function( values, handle ) {
        slider_drag_fixed_vals[handle].innerHTML = values[handle];
    });


    //
    // Snap behaviour
    //

    // Define element
    slider_snap_behaviour = document.getElementById('noui-slider-snap');

    // Create slider
    noUiSlider.create(slider_snap_behaviour, {
        start: 40,
        behaviour: 'snap',
        connect: 'lower',
        range: {
            'min':  20,
            'max':  80
        }
    });

    // Define elements for values
    var slider_snap_behaviour_val = document.getElementById('noui-slider-snap-val');

    // Show the values
    slider_snap_behaviour.noUiSlider.on('update', function( values, handle ) {
        slider_snap_behaviour_val.innerHTML = values[handle];
    });


    //
    // Combined options
    //

    // Define element
    slider_combined = document.getElementById('noui-slider-combined');

    // Create slider
    noUiSlider.create(slider_combined, {
        start: [ 40, 60 ],
        behaviour: 'drag-tap',
        connect: true,
        range: {
            'min':  20,
            'max':  80
        }
    });

    // Define elements for values
    var slider_combined_vals = [
        document.getElementById('noui-slider-combined-lower-val'),
        document.getElementById('noui-slider-combined-upper-val')
    ];

    // Show the values
    slider_combined.noUiSlider.on('update', function( values, handle ) {
        slider_combined_vals[handle].innerHTML = values[handle];
    });



    // Other examples
    // ------------------------------

    //
    // Tooltip
    //

    // Define element
    var slider_tooltip = document.getElementById('noui-slider-tooltip');

    // Create slider
    noUiSlider.create(slider_tooltip, {
        start: [20, 80],
        tooltips: true,
        connect: true,
        range: {
            'min': 0,
            'max': 100
        }
    });

    // Define elements for values
    var slider_tooltip_vals = [
        document.getElementById('noui-slider-tooltip-lower-val'),
        document.getElementById('noui-slider-tooltip-upper-val')
    ];

    // Show the values
    slider_tooltip.noUiSlider.on('update', function( values, handle ) {
        slider_tooltip_vals[handle].innerHTML = values[handle];
    });


    //
    // Set minimum handles distance
    //

    // Define element
    var slider_margin = document.getElementById('noui-slider-margin');

    // Create slider
    noUiSlider.create(slider_margin, {
        start: [ 20, 80 ],
        connect: true,
        margin: 30,
        range: {
            'min': 0,
            'max': 100
        }
    });

    // Define elements for values
    var slider_margin_vals = [
        document.getElementById('noui-slider-margin-lower-val'),
        document.getElementById('noui-slider-margin-upper-val')
    ];

    // Show the values
    slider_margin.noUiSlider.on('update', function( values, handle ) {
        slider_margin_vals[handle].innerHTML = values[handle];
    });


    //
    // RTL direction
    //

    // Define element
    var slider_direction = document.getElementById('noui-slider-direction');

    // Create slider
    noUiSlider.create(slider_direction, {
        start: 20,
        direction: 'rtl',
        connect: 'lower',
        range: {
            'min': 0,
            'max': 100
        }
    });

    // Define elements for values
    var slider_direction_val = document.getElementById('noui-slider-direction-val');

    // Show the values
    slider_direction.noUiSlider.on('update', function( values, handle ) {
        slider_direction_val.innerHTML = values[handle];
    });


    //
    // Connect lower side
    //

    // Define element
    var slider_connect_lower = document.getElementById('noui-slider-connect-lower');

    // Create slider
    noUiSlider.create(slider_connect_lower, {
        start: 40,
        connect: 'lower',
        range: {
          'min': 0,
          'max': 100
        }
    });

    // Define elements for values
    var slider_connect_lower_val = document.getElementById('noui-slider-connect-lower-val');

    // Show the values
    slider_connect_lower.noUiSlider.on('update', function( values, handle ) {
        slider_connect_lower_val.innerHTML = values[handle];
    });


    //
    // Skip certain steps
    //

    // Define element
    var slider_skip = document.getElementById('noui-slider-skip-steps');

    // Create slider
    noUiSlider.create(slider_skip, {
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
        start: [20, 70]
    });

    // Define elements for values
    var slider_skip_vals = [
        document.getElementById('noui-slider-skip-lower-val'),
        document.getElementById('noui-slider-skip-upper-val')
    ];

    // Show the values
    slider_skip.noUiSlider.on('update', function( values, handle ) {
        slider_skip_vals[handle].innerHTML = values[handle];
    });


    //
    // Connect upper side
    //

    // Define element
    var slider_connect_upper = document.getElementById('noui-slider-connect-upper');

    // Create slider
    noUiSlider.create(slider_connect_upper, {
        start: 40,
        connect: 'upper',
        range: {
          'min': 0,
          'max': 100
        }
    });

    // Define elements for values
    var slider_connect_upper_val = document.getElementById('noui-slider-connect-upper-val');

    // Show the values
    slider_connect_upper.noUiSlider.on('update', function( values, handle ) {
        slider_connect_upper_val.innerHTML = values[handle];
    });



    // Sliders with pips
    // ------------------------------

    // Demo data for ranges
    var range_all_sliders = {
        'min': [     0 ],
        '10%': [   5,  5 ],
        '50%': [  40, 10 ],
        'max': [ 100 ]
    };


    //
    // Range slider pips
    //

    // Define element
    var pips_range = document.getElementById('noui-slider-pips-range');

    // Create slider
    noUiSlider.create(pips_range, {
        range: range_all_sliders,
        start: 40,
        connect: 'lower',
        pips: {
            mode: 'range',
            density: 3
        }
    });


    //
    // Filtered pips
    //

    // Filter pips
    function filter500(value, type) {
        return value % 10 ? 2 : 1;
    }

    // Define element
    var pips_filter = document.getElementById('noui-slider-pips-filter');

    // Create slider
    noUiSlider.create(pips_filter, {
        range: range_all_sliders,
        start: 40,
        connect: 'lower',
        pips: {
            mode: 'steps',
            density: 2,
            filter: filter500
        }
    });


    //
    // RTL version
    //

    // Define element
    pips_rtl = document.getElementById('noui-slider-pips-rtl');

    // Create slider
    noUiSlider.create(pips_rtl, {
        range: range_all_sliders,
        start: 60,
        connect: 'lower',
        direction: 'rtl',
        pips: {
            mode: 'range',
            density: 3
        }
    });


    //
    // Positions mode
    //

    // Define element
    var pips_positions = document.getElementById('noui-slider-pips-positions');

    // Create slider
    noUiSlider.create(pips_positions, {
        range: range_all_sliders,
        start: 18,
        connect: 'upper',
        pips: {
            mode: 'positions',
            values: [0,25,50,75,100],
            density: 4
        }
    });


    //
    // Count mode
    //

    // Define element
    var pips_count = document.getElementById('noui-slider-pips-count');

    // Create slider
    noUiSlider.create(pips_count, {
        range: range_all_sliders,
        start: 20,
        connect: 'upper',
        pips: {
            mode: 'count',
            values: 6,
            density: 4
        }
    });


    //
    // Values mode
    //

    // Define element
    var pips_values = document.getElementById('noui-slider-pips-values');

    // Create slider
    noUiSlider.create(pips_values, {
        range: range_all_sliders,
        start: 40,
        connect: 'upper',
        pips: {
            mode: 'values',
            values: [1, 10, 26, 57, 79, 99],
            density: 4
        }
    });



    // Vertical sliders
    // ------------------------------

    //
    // Basic examples
    //

    // First
    var slider_vertical_1 = document.getElementById('noui-slider-values1');
    noUiSlider.create(slider_vertical_1, {
        start: 20,
        orientation: 'vertical',
        range: {
            'min': 0,
            'max': 100
        }
    });

    // Second
    var slider_vertical_2 = document.getElementById('noui-slider-values2');
    noUiSlider.create(slider_vertical_2, {
        start: 40,
        orientation: 'vertical',
        range: {
            'min': 0,
            'max': 100
        }
    });

    // Third
    var slider_vertical_3 = document.getElementById('noui-slider-values3');
    noUiSlider.create(slider_vertical_3, {
        start: 60,
        orientation: 'vertical',
        range: {
            'min': 0,
            'max': 100
        }
    });

    // Fourth
    var slider_vertical_4 = document.getElementById('noui-slider-values4');
    noUiSlider.create(slider_vertical_4, {
        start: 80,
        orientation: 'vertical',
        range: {
            'min': 0,
            'max': 100
        }
    });


    //
    // Connect to upper side
    //

    // First
    var slider_vertical_upper_1 = document.getElementById('noui-slider-upper1');
    noUiSlider.create(slider_vertical_upper_1, {
        start: 20,
        orientation: 'vertical',
        connect: 'upper',
        range: {
            'min': 0,
            'max': 100
        }
    });

    // Second
    var slider_vertical_upper_2 = document.getElementById('noui-slider-upper2');
    noUiSlider.create(slider_vertical_upper_2, {
        start: 40,
        orientation: 'vertical',
        connect: 'upper',
        range: {
            'min': 0,
            'max': 100
        }
    });

    // Third
    var slider_vertical_upper_3 = document.getElementById('noui-slider-upper3');
    noUiSlider.create(slider_vertical_upper_3, {
        start: 60,
        orientation: 'vertical',
        connect: 'upper',
        range: {
            'min': 0,
            'max': 100
        }
    });

    // Fourth
    var slider_vertical_upper_4 = document.getElementById('noui-slider-upper4');
    noUiSlider.create(slider_vertical_upper_4, {
        start: 80,
        orientation: 'vertical',
        connect: 'upper',
        range: {
            'min': 0,
            'max': 100
        }
    });


    //
    // Connect to lower side
    //

    // First
    var slider_vertical_lower_1 = document.getElementById('noui-slider-lower1');
    noUiSlider.create(slider_vertical_lower_1, {
        start: 20,
        orientation: 'vertical',
        connect: 'lower',
        range: {
            'min': 0,
            'max': 100
        }
    });

    // Second
    var slider_vertical_lower_2 = document.getElementById('noui-slider-lower2');
    noUiSlider.create(slider_vertical_lower_2, {
        start: 40,
        orientation: 'vertical',
        connect: 'lower',
        range: {
            'min': 0,
            'max': 100
        }
    });

    // Third
    var slider_vertical_lower_3 = document.getElementById('noui-slider-lower3');
    noUiSlider.create(slider_vertical_lower_3, {
        start: 60,
        orientation: 'vertical',
        connect: 'lower',
        range: {
            'min': 0,
            'max': 100
        }
    });

    // Fourth
    var slider_vertical_lower_4 = document.getElementById('noui-slider-lower4');
    noUiSlider.create(slider_vertical_lower_4, {
        start: 80,
        orientation: 'vertical',
        connect: 'lower',
        range: {
            'min': 0,
            'max': 100
        }
    });


    //
    // Vertical range
    //

    // First
    var slider_range_1 = document.getElementById('noui-slider-range1');
    noUiSlider.create(slider_range_1, {
        start: [ 15, 85 ],
        orientation: 'vertical',
        connect: true,
        range: {
            'min': 0,
            'max': 100
        }
    });

    // Second
    var slider_range_2 = document.getElementById('noui-slider-range2');
    noUiSlider.create(slider_range_2, {
        start: [ 30, 70 ],
        orientation: 'vertical',
        connect: true,
        range: {
            'min': 0,
            'max': 100
        }
    });


    //
    // Top to bottom pips
    //

    // First
    var slider_pips_top_1 = document.getElementById('noui-slider-top1');
    noUiSlider.create(slider_pips_top_1, {
        range: range_all_sliders,
        start: 40,
        connect: 'lower',
        orientation: 'vertical',
        pips: {
            mode: 'range',
            density: 5
        }
    });

    // Second
    var slider_pips_top_2 = document.getElementById('noui-slider-top2');
    noUiSlider.create(slider_pips_top_2, {
        range: range_all_sliders,
        start: 60,
        connect: 'lower',
        orientation: 'vertical',
        pips: {
            mode: 'range',
            density: 5
        }
    });


    //
    // Bottom to top pips
    //

    // First
    var slider_pips_bottom_1 = document.getElementById('noui-slider-bottom1');
    noUiSlider.create(slider_pips_bottom_1, {
        range: range_all_sliders,
        start: 40,
        connect: 'lower',
        orientation: 'vertical',
        direction: 'rtl',
        pips: {
            mode: 'range',
            density: 5
        }
    });

    // Second
    var slider_pips_bottom_2 = document.getElementById('noui-slider-bottom2');
    noUiSlider.create(slider_pips_bottom_2, {
        range: range_all_sliders,
        start: 60,
        connect: 'lower',
        orientation: 'vertical',
        direction: 'rtl',
        pips: {
            mode: 'range',
            density: 5
        }
    });



    // Optional styling
    // ------------------------------

    //
    // Colors
    //

    // Define elements
    var color1 = document.getElementById('noui-slider-color-demo1'),
        color2 = document.getElementById('noui-slider-color-demo2'),
        color3 = document.getElementById('noui-slider-color-demo3'),
        color4 = document.getElementById('noui-slider-color-demo4'),
        color5 = document.getElementById('noui-slider-color-demo5'),
        color6 = document.getElementById('noui-slider-color-demo6');

    // Set common options
    var color_options = {
        start: [2, 8],
        connect: true,
        range: {
            'min': 0,
            'max': 10
        }
    }

    // Create sliders
    noUiSlider.create(color1, color_options);
    noUiSlider.create(color2, color_options);
    noUiSlider.create(color3, color_options);
    noUiSlider.create(color4, color_options);
    noUiSlider.create(color5, color_options);
    noUiSlider.create(color6, color_options);


    //
    // Sizes and styles
    //

    // Define elements
    var default_size1 = document.getElementById('slider-default-lg'),
        default_size2 = document.getElementById('slider-default-md'),
        default_size3 = document.getElementById('slider-default-sm'),
        default_size4 = document.getElementById('slider-default-xs'),

        solid_size1 = document.getElementById('slider-solid-lg'),
        solid_size2 = document.getElementById('slider-solid-md'),
        solid_size3 = document.getElementById('slider-solid-sm'),
        solid_size4 = document.getElementById('slider-solid-xs'),

        white_size1 = document.getElementById('slider-white-lg'),
        white_size2 = document.getElementById('slider-white-md'),
        white_size3 = document.getElementById('slider-white-sm'),
        white_size4 = document.getElementById('slider-white-xs');

    // Create sliders
    noUiSlider.create(default_size1, color_options);
    noUiSlider.create(default_size2, color_options);
    noUiSlider.create(default_size3, color_options);
    noUiSlider.create(default_size4, color_options);

    noUiSlider.create(solid_size1, color_options);
    noUiSlider.create(solid_size2, color_options);
    noUiSlider.create(solid_size3, color_options);
    noUiSlider.create(solid_size4, color_options);

    noUiSlider.create(white_size1, color_options);
    noUiSlider.create(white_size2, color_options);
    noUiSlider.create(white_size3, color_options);
    noUiSlider.create(white_size4, color_options);

});

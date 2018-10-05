/* ------------------------------------------------------------------------------
 *
 *  # C3.js - advanced examples
 *
 *  Demo setup of chart transformations, zoom, pan and brushing features
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {
    

    // Chart transforms
    // ------------------------------

    // Generate chart
    var transform = c3.generate({
        bindto: '#c3-transform',
        size: { height: 400 },
        data: {
            columns: [
                ['data1', 30, 200, 100, 400, 150, 250],
                ['data2', 130, 100, 140, 200, 150, 50]
            ]
        },
        grid: {
            y: {
                show: true
            }
        }
    });

    // Update data
    function update() {
        transform.transform('donut');
        setTimeout(function () {
            transform.transform('area');
        }, 1500);
        setTimeout(function () {
            transform.transform('bar', 'data1');
        }, 3000);
        setTimeout(function () {
            transform.transform('scatter');
        }, 4500);
        setTimeout(function () {
            transform.transform('bar');
        }, 6000);
        setTimeout(function () {
            transform.transform('step');
        }, 7500);
        setTimeout(function () {
            transform.transform('line');
            $('#btn-transform').removeClass('disabled');
        }, 11500);
    }

    // Run update on click
    $('#btn-transform').click(function () {
        $(this).addClass('disabled');
        update();
    });



    // Zoomable chart
    // ------------------------------

    // Generate chart
    var zoomable_chart = c3.generate({
        bindto: '#c3-chart-zoomable',
        size: { height: 400 },
        data: {
            columns: [
                ['sample', 30, 120, 320, 180, 50, 250, 167, 279, 290, 400, 214, 190, 40, 400, 162, 289, 300, 200, 120, 320, 390, 110, 130, 400, 240, 189, 250, 30, 100, 200, 300, 250, 50, 100, 50, 300, 250, 20, 90, 150, 400, 320, 220, 150, 190, 270, 190, 350, 90, 300, 150, 220, 170, 40]
            ]
        },
        color: {
            pattern: ['#1E88E5']
        },
        zoom: {
            enabled: true
        },
        grid: {
            x: {
                show: true
            },
            y: {
                show: true
            }
        }
    });



    // Subchart (brushing)
    // ------------------------------

    // Generate chart
    var subchart = c3.generate({
        bindto: '#c3-subchart',
        size: { height: 400 },
        data: {
            columns: [
                ['sample', 30, 200, 100, 400, 150, 250, 150, 200, 170, 240, 350, 150, 100, 400, 150, 250, 150, 200, 170, 240, 100, 150, 250, 150, 200, 170, 240, 30, 200, 100, 400, 150, 250, 150, 200, 170, 240, 350, 150, 100, 400, 350, 220, 250, 300, 270, 140, 150, 90, 150, 50, 120, 70, 40]
            ]
        },
        color: {
            pattern: ['#00ACC1']
        },
        subchart: {
            show: true
        },
        grid: {
            y: {
                show: true
            }
        }
    });



    // Label format
    // ------------------------------

    // Generate chart
    var label_format = c3.generate({
        bindto: '#c3-label-format',
        size: { height: 400 },
        data: {
            columns: [
                ['data1', 30, -200, -100, 400, 150, 250, 100, 120, 150],
                ['data2', -50, 150, 150, -150, -50, -150, -120, -100, -120],
                ['data3', -100, 100, -40, 100, -150, -50, 90, -40, 100]
            ],
            groups: [
                ['data1', 'data2']
            ],
            type: 'bar',
            labels: {
                format: {
                    y: d3.format('$')
                }
            }
        },
        color: {
            pattern: ['#4CAF50', '#F4511E', '#1E88E5']
        },
        bar: {
            width: {
                ratio: 1
            }
        },
        grid: {
            y: {
                lines: [{value: 0}]
            }
        }
    });



    // Data colors
    // ------------------------------

    // Generate chart
    var data_color = c3.generate({
        bindto: '#c3-data-color',
        size: { height: 400 },
        data: {
            columns: [
                ['data1', 30, 20, 50, 40, 60, 50],
                ['data2', 200, 130, 90, 240, 130, 220],
                ['data3', 300, 200, 160, 400, 250, 250]
            ],
            type: 'bar',
            colors: {
                data1: '#4DB6AC',
                data2: '#009688',
                data3: '#00796B'
            }
        },
        grid: {
            y: {
                show: true
            }
        }
    });



    // Resize chart on sidebar width change
    $(".sidebar-control").on('click', function() {
        transform.resize();
        zoomable_chart.resize();
        subchart.resize();
        label_format.resize();
        data_color.resize();
    });
});
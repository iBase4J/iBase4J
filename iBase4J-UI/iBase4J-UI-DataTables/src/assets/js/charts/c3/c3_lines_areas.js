/* ------------------------------------------------------------------------------
 *
 *  # C3.js - lines and areas
 *
 *  Demo setup of line, step and area charts
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {
    

    // Line chart
    // ------------------------------

    // Generate chart
    var line_chart = c3.generate({
        bindto: '#c3-line-chart',
        point: { 
            r: 4   
        },
        size: { height: 400 },
        color: {
            pattern: ['#4CAF50', '#F4511E', '#1E88E5']
        },
        data: {
            columns: [
                ['data1', 30, 200, 100, 400, 150, 250],
                ['data2', 50, 20, 10, 40, 15, 25]
            ],
            type: 'spline'
        },
        grid: {
            y: {
                show: true
            }
        }
    });

    // Change data
    setTimeout(function () {
        line_chart.load({
            columns: [
                ['data1', 230, 190, 300, 500, 300, 400]
            ]
        });
    }, 3000);
    setTimeout(function () {
        line_chart.load({
            columns: [
                ['data3', 130, 150, 200, 300, 200, 100]
            ]
        });
    }, 6000);
    setTimeout(function () {
        line_chart.unload({
            ids: 'data1'
        });
    }, 9000);



    // Line chart with regions
    // ------------------------------

    // Generate chart
    var chart_line_regions = c3.generate({
        bindto: '#c3-line-regions-chart',
        size: { height: 400 },
        point: {
            r: 4
        },
        color: {
            pattern: ['#E53935', '#5E35B1']
        },
        data: {
            columns: [
                ['data1', 30, 200, 100, 400, 150, 250],
                ['data2', 50, 20, 10, 40, 15, 25]
            ],
            regions: {
                'data1': [{'start':1, 'end':2, 'style':'dashed'},{'start':3}],
                'data2': [{'end':3}]
            }
        },
        grid: {
            y: {
                show: true
            }
        }
    });



    // Area chart
    // ------------------------------

    // Generate chart
    var area_chart = c3.generate({
        bindto: '#c3-area-chart',
        size: { height: 400 },
        point: {
            r: 4
        },
        color: {
            pattern: ['#E53935', '#3949AB']
        },
        data: {
            columns: [
                ['data1', 300, 350, 300, 0, 0, 0],
                ['data2', 130, 100, 140, 200, 150, 50]
            ],
            types: {
                data1: 'area-spline',
                data2: 'area-spline'
            }
        },
        grid: {
            y: {
                show: true
            }
        }
    });



    // Stacked area chart
    // ------------------------------

    // Generate chart
    var area_stacked_chart = c3.generate({
        bindto: '#c3-area-stacked-chart',
        size: { height: 400 },
        color: {
            pattern: ['#1E88E5', '#F4511E']
        },
        point: {
            r: 4
        },
        data: {
            columns: [
                ['data1', 300, 350, 300, 0, 0, 120],
                ['data2', 130, 100, 140, 200, 150, 50]
            ],
            types: {
                data1: 'area-spline',
                data2: 'area-spline'
            },
            groups: [['data1', 'data2']]
        },
        grid: {
            y: {
                show: true
            }
        }
    });



    // Step chart
    // ------------------------------

    // Generate chart
    var step_chart = c3.generate({
        bindto: '#c3-step-chart',
        size: { height: 400 },
        color: {
            pattern: ['#6D4C41', '#039BE5']
        },
        data: {
            columns: [
                ['data1', 300, 350, 300, 0, 0, 100],
                ['data2', 130, 100, 140, 200, 150, 50]
            ],
            types: {
                data1: 'step',
                data2: 'area-step'
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
        line_chart.resize();
        chart_line_regions.resize();
        area_chart.resize();
        area_stacked_chart.resize();
        step_chart.resize();
    });
});
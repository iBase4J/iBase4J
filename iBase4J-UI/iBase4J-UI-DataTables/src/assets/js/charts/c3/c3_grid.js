/* ------------------------------------------------------------------------------
 *
 *  # C3.js - chart grid
 *
 *  Demo setup of chart grid with options
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {
    

    // Grid lines
    // ------------------------------

    // Generate chart
    var grid_lines = c3.generate({
        bindto: '#c3-grid-lines',
        size: { height: 400 },
        color: {
            pattern: ['#2196F3']
        },
        data: {
            columns: [
                ['sample', 30, 200, 100, 400, 150, 250, 120, 200]
            ]
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



    // Optional X grid lines
    // ------------------------------

    // Generate chart
    var grid_lines_x = c3.generate({
        bindto: '#c3-grid-lines-x',
        size: { height: 400 },
        color: {
            pattern: ['#4CAF50']
        },
        data: {
            columns: [
                ['sample', 30, 200, 100, 400, 150, 250]
            ]
        },
        grid: {
            x: {
                lines: [{value: 3, text: 'Label 3'}, {value: 4.5, text: 'Label 4.5'}]
            }
        }
    });



    // Optional Y grid lines
    // ------------------------------

    // Generate chart
    var grid_lines_y = c3.generate({
        bindto: '#c3-grid-lines-y',
        size: { height: 400 },
        data: {
            columns: [
                ['sample', 30, 200, 100, 400, 150, 250],
                ['sample2', 1300, 1200, 1100, 1400, 1500, 1250],
            ],
            axes: {
                sample2: 'y2'
            }
        },
        color: {
            pattern: ['#607D8B', '#FF9800']
        },
        axis: {
            y2: {
                show: true
            }
        },
        grid: {
            y: {
                lines: [{value: 50, text: 'Label 50'}, {value: 1300, text: 'Label 1300', axis: 'y2'}]
            }
        }
    });



    // Rects on chart
    // ------------------------------

    // Generate chart
    var grid_region = c3.generate({
        bindto: '#c3-grid-region',
        size: { height: 400 },
        data: {
            columns: [
                ['data1', 30, 200, 100, 400, 150, 250, 400],
                ['data2', 830, 1200, 1100, 1400, 1150, 1250, 1500],
            ],
            axes: {
                data2: 'y2'
            }
        },
        color: {
            pattern: ['#607D8B', '#FF9800']
        },
        axis: {
            y2: {
                show: true
            }
        },
        regions: [
            {axis: 'x', end: 1, class: 'regionX'},
            {axis: 'x', start: 2, end: 4, class: 'regionX'},
            {axis: 'x', start: 5, class: 'regionX'},
            {axis: 'y', end: 50, class: 'regionY'},
            {axis: 'y', start: 80, end: 140, class: 'regionY'},
            {axis: 'y', start: 400, class: 'regionY'},
            {axis: 'y2', end: 900, class: 'regionY2'},
            {axis: 'y2', start: 1150, end: 1250, class: 'regionY2'},
            {axis: 'y2', start: 1300, class: 'regionY2'},
        ]
    });



    // Data regions
    // ------------------------------

    // Generate chart
    var grid_region_chart = c3.generate({
        bindto: '#c3-grid-chart-region',
        size: { height: 400 },
        color: {
            pattern: ['#009688', '#9C27B0']
        },
        data: {
            columns: [
                ['data1', 30, 200, 100, 400, 150, 250],
                ['data2', 50, 20, 10, 40, 15, 25]
            ],
            regions: {
                'data1': [{'start':1, 'end':2, 'style':'dashed'},{'start':3}], // currently 'dashed' style only
                'data2': [{'end':3}]
            }
        }
    });



    // Resize chart on sidebar width change
    $(".sidebar-control").on('click', function() {
        grid_lines.resize();
        grid_lines_x.resize();
        grid_lines_y.resize();
        grid_region.resize();
        grid_region_chart.resize();
    });
});
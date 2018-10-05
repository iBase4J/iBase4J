/* ------------------------------------------------------------------------------
 *
 *  # Echarts - lines and areas
 *
 *  Lines and areas chart configurations
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function() {


    // Set paths
    // ------------------------------

    require.config({
        paths: {
            echarts: 'assets/js/plugins/visualization/echarts'
        }
    });


    // Configuration
    // ------------------------------

    require(
        [
            'echarts',
            'echarts/theme/limitless',
            'echarts/chart/bar',
            'echarts/chart/line'
        ],


        // Charts setup
        function (ec, limitless) {


            // Initialize charts
            // ------------------------------

            var basic_lines = ec.init(document.getElementById('basic_lines'), limitless);
            var stacked_lines = ec.init(document.getElementById('stacked_lines'), limitless);
            var inverted_axes = ec.init(document.getElementById('inverted_axes'), limitless);
            var line_point = ec.init(document.getElementById('line_point'), limitless);
            var basic_area = ec.init(document.getElementById('basic_area'), limitless);
            var stacked_area = ec.init(document.getElementById('stacked_area'), limitless);
            var reversed_value = ec.init(document.getElementById('reversed_value'), limitless);



            // Charts setup
            // ------------------------------

            //
            // Basic lines options
            //

            basic_lines_options = {

                // Setup grid
                grid: {
                    x: 40,
                    x2: 40,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis'
                },

                // Add legend
                legend: {
                    data: ['Maximum', 'Minimum']
                },

                // Add custom colors
                color: ['#EF5350', '#66BB6A'],

                // Enable drag recalculate
                calculable: true,

                // Horizontal axis
                xAxis: [{
                    type: 'category',
                    boundaryGap: false,
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} °C'
                    }
                }],

                // Add series
                series: [
                    {
                        name: 'Maximum',
                        type: 'line',
                        data: [11, 11, 15, 13, 12, 13, 10],
                        markLine: {
                            data: [{
                                type: 'average',
                                name: 'Average'
                            }]
                        }
                    },
                    {
                        name: 'Minimum',
                        type: 'line',
                        data: [1, -2, 2, 5, 3, 2, 0],
                        markLine: {
                            data: [{
                                type: 'average',
                                name: 'Average'
                            }]
                        }
                    }
                ]
            };


            //
            // Stacked lines options
            //

            stacked_lines_options = {

                // Setup grid
                grid: {
                    x: 40,
                    x2: 20,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis'
                },

                // Add legend
                legend: {
                    data: ['Internet Explorer', 'Opera', 'Safari', 'Firefox', 'Chrome']
                },

                // Enable drag recalculate
                calculable: true,

                // Hirozontal axis
                xAxis: [{
                    type: 'category',
                    boundaryGap: false,
                    data: [
                        'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'
                    ]
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value'
                }],

                // Add series
                series: [
                    {
                        name: 'Internet Explorer',
                        type: 'line',
                        stack: 'Total',
                        data: [120, 132, 101, 134, 90, 230, 210]
                    },
                    {
                        name: 'Opera',
                        type: 'line',
                        stack: 'Total',
                        data: [220, 182, 191, 234, 290, 330, 310]
                    },
                    {
                        name: 'Safari',
                        type: 'line',
                        stack: 'Total',
                        data: [150, 232, 201, 154, 190, 330, 410]
                    },
                    {
                        name: 'Firefox',
                        type: 'line',
                        stack: 'Total',
                        data: [320, 332, 301, 334, 390, 330, 320]
                    },
                    {
                        name: 'Chrome',
                        type: 'line',
                        stack: 'Total',
                        data: [820, 932, 901, 934, 1290, 1330, 1320]
                    }
                ]
            };


            //
            // Inverted axes options
            //

            inverted_axes_options = {

                // Setup grid
                grid: {
                    x: 40,
                    x2: 20,
                    y: 35,
                    y2: 25
                },

                // Add legend
                legend: {
                    data: ['Altitude(km) and temperature(°C)']
                },


                // Enable drag recalculate
                calculable: true,

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    formatter: 'Temperature: <br/>{b}km: {c}°C'
                },

                // Horizontal axis
                xAxis: [{
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} °C'
                    }
                }],

                // Vertical axis
                yAxis: [{
                    type: 'category',
                    axisLine: {
                        onZero: false
                    },
                    axisLabel: {
                        formatter: '{value} km'
                    },
                    boundaryGap: false,
                    data: [
                        0, 10, 20, 30, 40, 50, 60, 70, 80
                    ]
                }],

                // Add series
                series: [
                    {
                        name: 'Altitude(km) and temperature(°C)',
                        type: 'line',
                        smooth: true,
                        itemStyle: {
                            normal: {
                                lineStyle: {
                                    shadowColor: 'rgba(0,0,0,0.4)'
                                }
                            }
                        },
                        data: [
                            15, -50, -56.5, -46.5, -22.1, -2.5, -27.7, -55.7, -76.5
                        ]
                    }
                ]
            };


            //
            // Line and point options
            //

            line_point_options = {

                // Setup grid
                grid: {
                    x: 35,
                    x2: 35,
                    y: 60,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        show: true,
                        type: 'cross',
                        lineStyle: {
                            type: 'dashed',
                            width: 1
                        }
                    },
                    formatter: function (params) {
                        return params.seriesName + ': [ '
                        + params.value[0] + ', ' 
                        + params.value[1] + ' ]';
                    }
                },

                // Add legend
                legend: {
                    x: 'left',
                    data: ['Data set 1', 'Data set 2']
                },

                // Display toolbox
                toolbox: {
                    show: true,
                    feature: {
                        mark: {
                            show: true,
                            title: {
                                mark: 'Markline switch',
                                markUndo: 'Undo markline',
                                markClear: 'Clear markline'
                            }
                        },
                        dataZoom: {
                            show: true,
                            title: {
                                dataZoom: 'Data zoom',
                                dataZoomReset: 'Reset zoom'
                            }
                        },
                        dataView: {
                            show: true,
                            readOnly: false,
                            title: 'View data',
                            lang: ['View chart data', 'Close', 'Update']
                        },
                        magicType: {
                            show: true,
                            title: {
                                line: 'Switch to line chart',
                                bar: 'Switch to bar chart',
                            },
                            type: ['line', 'bar']
                        },
                        restore: {
                            show: true,
                            title: 'Restore'
                        },
                        saveAsImage: {
                            show: true,
                            title: 'Same as image',
                            lang: ['Save']
                        }
                    }
                },

                // Enable drag recalculate
                calculable: true,

                // Horizontal axis
                xAxis: [{
                type: 'value'
                }],

                // Vertical axis
                yAxis: [
                    {
                        type: 'value',
                        axisLine: {
                            lineStyle: {
                                color: '#dc143c'
                            }
                        }
                    }
                ],

                // Add series
                series: [
                    {
                        name: 'Data set 1',
                        type: 'line',
                        data: [
                            [1.5, 10], [5, 7], [8, 8], [12, 6], [11, 12], [16, 9], [14, 6], [17, 4], [19, 9]
                        ],
                        markPoint: {
                            data: [

                                // Vertical
                                {type: 'max', name: 'Maximum',symbol: 'emptyCircle', itemStyle: {normal: {color: '#EF5350',label: {position: 'top'}}}},
                                {type: 'min', name: 'Minimum',symbol: 'emptyCircle', itemStyle: {normal: {color: '#EF5350',label: {position: 'bottom'}}}},

                                // Horizontal
                                {type: 'max', name: 'Maximum', valueIndex: 0, symbol: 'emptyCircle', itemStyle: {normal: {color: '#42A5F5',label: {position: 'right'}}}},
                                {type: 'min', name: 'Minimum', valueIndex: 0, symbol: 'emptyCircle', itemStyle: {normal: {color: '#42A5F5',label: {position: 'left'}}}}
                            ]
                        },
                        markLine: {
                            data: [

                                // Vertical
                                {type: 'max', name: 'Maximum', itemStyle: {normal: {color: '#EF5350'}}},
                                {type: 'min', name: 'Minimum', itemStyle: {normal: {color: '#EF5350'}}},
                                {type: 'average', name: 'Average', itemStyle: {normal: {color: '#EF5350'}}},

                                // Horizontal
                                {type: 'max', name: 'Maximum', valueIndex: 0, itemStyle: {normal: {color: '#42A5F5'}}},
                                {type: 'min', name: 'Minimum', valueIndex: 0, itemStyle: {normal: {color: '#42A5F5'}}},
                                {type: 'average', name: 'Average', valueIndex: 0, itemStyle: {normal: {color: '#42A5F5'}}}
                            ]
                        }
                    },
                    {
                        name: 'Data set 2',
                        type: 'line',
                        data: [
                            [1, 2], [2, 3], [4, 2], [7, 5], [11, 2], [18, 3]
                        ]
                    }
                ]
            };


            //
            // Basic area options
            //

            basic_area_options = {

                // Setup grid
                grid: {
                    x: 40,
                    x2: 20,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis'
                },

                // Add legend
                legend: {
                    data: ['New orders', 'In progress', 'Closed deals']
                },


                // Enable drag recalculate
                calculable: true,

                // Horizontal axis
                xAxis: [{
                    type: 'category',
                    boundaryGap: false,
                    data: [
                        'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'
                    ]
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value'
                }],

                // Add series
                series: [
                    {
                        name: 'Closed deals',
                        type: 'line',
                        smooth: true,
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: [10, 12, 21, 54, 260, 830, 710]
                    },
                    {
                        name: 'In progress',
                        type: 'line',
                        smooth: true,
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: [30, 182, 434, 791, 390, 30, 10]
                    },
                    {
                        name: 'New orders',
                        type: 'line',
                        smooth: true,
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: [1320, 1132, 601, 234, 120, 90, 20]
                    }
                ]
            };


            //
            // Stacked area options
            //

            stacked_area_options = {

                // Setup grid
                grid: {
                    x: 40,
                    x2: 20,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis'
                },

                // Add legend
                legend: {
                    data: ['Internet Explorer', 'Safari', 'Firefox', 'Chrome']
                },

                // Enable drag recalculate
                calculable: true,

                // Add horizontal axis 
                xAxis: [{
                    type: 'category',
                    boundaryGap: false,
                    data: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
                }],

                // Add vertical axis
                yAxis: [{
                    type: 'value'
                }],

                // Add series
                series: [
                    {
                        name: 'Internet Explorer',
                        type: 'line',
                        stack: 'Total',
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: [120, 132, 101, 134, 490, 230, 210]
                    },
                    {
                        name: 'Safari',
                        type: 'line',
                        stack: 'Total',
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: [150, 1232, 901, 154, 190, 330, 810]
                    },
                    {
                        name: 'Firefox',
                        type: 'line',
                        stack: 'Total',
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: [320, 1332, 1801, 1334, 590, 830, 1220]
                    },
                    {
                        name: 'Chrome',
                        type: 'line',
                        stack: 'Total',
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: [820, 1632, 1901, 2234, 1290, 1330, 1320]
                    }
                ]
            };


            //
            // Reversed value axis options
            //

            reversed_value_options = {

                // Setup grid
                grid: {
                    x: 40,
                    x2: 40,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    formatter: function(params) {
                        return params[0].name + '<br/>'
                        + params[0].seriesName + ': ' + params[0].value + ' (m^3/s)<br/>'
                        + params[1].seriesName + ': ' + -params[1].value + ' (mm)';
                    }
                },

                // Add legend
                legend: {
                    data: ['Flow', 'Rainfall']
                },

                // Add horizontal axis
                xAxis: [{
                    type: 'category',
                    boundaryGap: false,
                    axisLine: {
                        onZero: false
                    },
                    data: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
                }],

                // Add vertical axis
                yAxis: [
                    {
                        name: 'Flow(m^3/s)',
                        type: 'value',
                        max: 500
                    },
                    {
                        name: 'Rainfall(mm)',
                        type: 'value',
                        axisLabel: {
                            formatter: function(v) {
                                return - v;
                            }
                        }
                    }
                ],

                // Add series
                series: [
                    {
                        name: 'Flow',
                        type: 'line',
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data:[100, 200, 240, 180, 90, 200, 130]
                    },
                    {
                        name: 'Rainfall',
                        type: 'line',
                        yAxisIndex: 1,
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: (function() {
                            var oriData = [
                                1, 2, 1.5, 7.4, 3.1, 4, 2
                            ];
                            var len = oriData.length;
                            while(len--) {
                                oriData[len] *= -1;
                            }
                            return oriData;
                        })()
                    }
                ]
            };



            // Apply options
            // ------------------------------

            basic_lines.setOption(basic_lines_options);
            stacked_lines.setOption(stacked_lines_options);
            inverted_axes.setOption(inverted_axes_options);
            line_point.setOption(line_point_options);
            basic_area.setOption(basic_area_options);
            stacked_area.setOption(stacked_area_options);
            reversed_value.setOption(reversed_value_options);



            // Resize charts
            // ------------------------------

            window.onresize = function () {
                setTimeout(function () {
                    basic_lines.resize();
                    stacked_lines.resize();
                    inverted_axes.resize();
                    line_point.resize();
                    basic_area.resize();
                    stacked_area.resize();
                    reversed_value.resize();
                }, 200);
            }
        }
    );
});

/* ------------------------------------------------------------------------------
 *
 *  # Echarts - bars and tornados
 *
 *  Bars and tornados chart configurations
 *
 *  Version: 1.0
 *  Latest update: August 1, 2015
 *
 * ---------------------------------------------------------------------------- */

$(function () {

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

            var basic_bars = ec.init(document.getElementById('basic_bars'), limitless);
            var stacked_bars = ec.init(document.getElementById('stacked_bars'), limitless);
            var stacked_clustered_bars = ec.init(document.getElementById('stacked_clustered_bars'), limitless);
            var floating_bars = ec.init(document.getElementById('floating_bars'), limitless);
            var irregular_bars = ec.init(document.getElementById('irregular_bars'), limitless);
            var tornado_bars_negative = ec.init(document.getElementById('tornado_bars_negative'), limitless);
            var tornado_bars_staggered = ec.init(document.getElementById('tornado_bars_staggered'), limitless);



            // Charts setup
            // ------------------------------

            //
            // Basic bars options
            //

            basic_bars_options = {

                // Setup grid
                grid: {
                    x: 75,
                    x2: 35,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },

                // Add legend
                legend: {
                    data: ['Year 2013', 'Year 2014']
                },

                // Enable drag recalculate
                calculable: true,

                // Horizontal axis
                xAxis: [{
                    type: 'value',
                    boundaryGap: [0, 0.01]
                }],

                // Vertical axis
                yAxis: [{
                    type: 'category',
                    data: ['Germany','France','Spain','Netherlands','Belgium']
                }],

                // Add series
                series: [
                    {
                        name: 'Year 2013',
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                color: '#EF5350'
                            }
                        },
                        data: [38203, 73489, 129034, 204970, 331744]
                    },
                    {
                        name: 'Year 2014',
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                color: '#66BB6A'
                            }
                        },
                        data: [39325, 83438, 131000, 221594, 334141]
                    }
                ]
            };


            //
            // Stacked bars options
            //

            stacked_bars_options = {

                // Setup grid
                grid: {
                    x: 75,
                    x2: 25,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },

                // Add legend
                legend: {
                    data:['Internet Explorer','Opera','Safari','Firefox','Chrome']
                },

                // Enable drag recalculate
                calculable: true,

                // Horizontal axis
                xAxis: [{
                    type: 'value'
                }],

                // Vertical axis
                yAxis: [{
                    type: 'category',
                    data: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
                }],

                // Add series
                series: [
                    {
                        name: 'Internet Explorer',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: {
                            normal: {
                                color: '#42A5F5',
                                label: {
                                    show: true,
                                    position: 'insideRight'
                                }
                            },
                            emphasis: {
                                color: '#42A5F5',
                                label: {
                                    show: true
                                }
                            }
                        },
                        data:[320, 302, 301, 334, 390, 330, 320]
                    },
                    {
                        name: 'Opera',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: {
                            normal: {
                                color: '#ef5350',
                                label: {
                                    show: true,
                                    position: 'insideRight'
                                }
                            },
                            emphasis: {
                                color: '#ef5350',
                                label: {
                                    show: true
                                }
                            }
                        },
                        data:[120, 132, 101, 134, 90, 230, 210]
                    },
                    {
                        name: 'Safari',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: {
                            normal: {
                                color: '#66bb6a',
                                label: {
                                    show: true,
                                    position: 'insideRight'
                                }
                            },
                            emphasis: {
                                color: '#66bb6a',
                                label: {
                                    show: true
                                }
                            }
                        },
                        data:[220, 182, 191, 234, 290, 330, 310]
                    },
                    {
                        name: 'Firefox',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: {
                            normal: {
                                color: '#ff7043',
                                label: {
                                    show: true,
                                    position: 'insideRight'
                                }
                            },
                            emphasis: {
                                color: '#ff7043',
                                label: {
                                    show: true
                                }
                            }
                        },
                        data:[150, 212, 201, 154, 190, 330, 410]
                    },
                    {
                        name: 'Chrome',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: {
                            normal: {
                                color: '#26a69a',
                                label: {
                                    show: true,
                                    position: 'insideRight'
                                }
                            },
                            emphasis: {
                                color: '#26a69a',
                                label: {
                                    show: true
                                }
                            }
                        },
                        data:[820, 832, 901, 934, 1290, 1330, 1320]
                    }
                ]
            };


            //
            // Stacked clustered bars options
            //

            stacked_clustered_bars_options = {

                // Setup grid
                grid: {
                    x: 45,
                    x2: 45,
                    y: 65,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },

                // Add legends
                legend: {
                    data: [
                        'Version 1.7 - 2k data','Version 1.7 - 2w data','Version 1.7 - 20w data','',
                        'Version 2.0 - 2k data','Version 2.0 - 2w data','Version 2.0 - 20w data'
                    ]
                },

                // Enable drag recalculate
                calculable: true,

                // Vertical axis
                yAxis: [
                    {
                        type: 'category',
                        data: ['Line','Bar','Scatter','Pies']
                    },
                    {
                        type: 'category',
                        axisLine: {show: false},
                        axisTick: {show: false},
                        axisLabel: {show: false},
                        splitArea: {show: false},
                        splitLine: {show: false},
                        data: ['Line','Bar','Scatter','Pies']
                    }
                ],

                // Horizontal axis
                xAxis: [{
                    type: 'value',
                    axisLabel: {formatter: '{value} ms'}
                }],

                // Add series
                series: [
                    {
                        name: 'Version 2.0 - 2k data',
                        type: 'bar',
                        yAxisIndex: 1,
                        itemStyle: {
                            normal: {
                                color: '#F44336',
                                label: {
                                    show: true,
                                    textStyle:{
                                        color: '#fff'
                                    }
                                }
                            },
                            emphasis: {
                                color: '#F44336',
                                label: {
                                    show: true
                                }
                            }
                        },
                        data: [247, 187, 95, 175]
                    },
                    {
                        name: 'Version 2.0 - 2w data',
                        type: 'bar',
                        yAxisIndex: 1,
                        itemStyle: {
                            normal: {
                                color: '#4CAF50',
                                label: {
                                    show: true,
                                    textStyle: {
                                        color: '#fff'
                                    }
                                }
                            },
                            emphasis: {
                                color: '#4CAF50',
                                label: {
                                    show: true
                                }
                            }
                        },
                        data: [488, 415, 405, 340]
                    },
                    {
                        name: 'Version 2.0 - 20w data',
                        type: 'bar',
                        yAxisIndex: 1,
                        itemStyle: {
                            normal: {
                                color: '#2196F3',
                                label: {
                                    show: true,
                                    textStyle: {
                                        color: '#fff'
                                    }
                                }
                            },
                            emphasis: {
                                color: '#2196F3',
                                label: {
                                    show: true
                                }
                            }
                        },
                        data: [906, 911, 908, 778]
                    },

                    {
                        name: 'Version 1.7 - 2k data',
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                color: '#E57373',
                                label: {
                                    show: true
                                }
                            },
                            emphasis: {
                                color: '#E57373',
                                label: {
                                    show: true
                                }
                            }
                        },
                        data: [680, 819, 564, 724]
                    },
                    {
                        name: 'Version 1.7 - 2w data',
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                color: '#81C784',
                                label: {
                                    show: true
                                }
                            },
                            emphasis: {
                                color: '#81C784',
                                label: {
                                    show: true
                                }
                            }
                        },
                        data: [1212, 2035, 1620, 955]
                    },
                    {
                        name: 'Version 1.7 - 20w data',
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                color: '#64B5F6',
                                label: {
                                    show: true,
                                    formatter: function(p) {return p.value > 0 ? (p.value +'+'):'';}
                                }
                            },
                            emphasis: {
                                color: '#64B5F6',
                                label: {
                                    show: false
                                }
                            }
                        },
                        data: [2200, 3000, 2500, 3000]
                    }
                ]
            };


            //
            // Floating bars options
            //

            var placeHoledStyle = {
                normal: {
                    barBorderColor: 'rgba(0,0,0,0)',
                    color: 'rgba(0,0,0,0)'
                },
                emphasis: {
                    barBorderColor: 'rgba(0,0,0,0)',
                    color: 'rgba(0,0,0,0)'
                }
            };
            var dataStyle = { 
                normal: {
                    label: {
                        show: true,
                        position: 'insideLeft',
                        formatter: '{c}%'
                    }
                },
                emphasis: {
                    label: {
                        show: true
                    }
                }
            };

            floating_bars_options = {

                // Setup grid
                grid: {
                    x: 75,
                    x2: 10,
                    y: 35,
                    y2: 10
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: '{b}<br/>{a0}: {c0}%<br/>{a2}: {c2}%<br/>{a4}: {c4}%<br/>{a6}: {c6}%'
                },

                // Add legend
                legend: {
                    itemGap: document.getElementById('floating_bars').offsetWidth / 8,
                    data: ['GML', 'PYP','WTC', 'ZTW']
                },

                // Horizontal axis
                xAxis: [{
                    type: 'value',
                    position: 'top',
                    splitLine: {show: false},
                    axisLabel: {show: false}
                }],

                // Vertical axis
                yAxis: [{
                    type: 'category',
                    data: ['Paris', 'Berlin', 'Amsterdam', 'Madrid']
                }],

                // Add series
                series: [
                    {
                        name: 'GML',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: dataStyle,
                        data: [38, 50, 33, 72]
                    },
                    {
                        name: 'GML',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: placeHoledStyle,
                        data: [62, 50, 67, 28]
                    },
                    {
                        name: 'PYP',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: dataStyle,
                        data: [61, 41, 42, 30]
                    },
                    {
                        name: 'PYP',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: placeHoledStyle,
                        data: [39, 59, 58, 70]
                    },
                    {
                        name: 'WTC',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: dataStyle,
                        data: [37, 35, 44, 60]
                    },
                    {
                        name: 'WTC',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: placeHoledStyle,
                        data: [63, 65, 56, 40]
                    },
                    {
                        name: 'ZTW',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: dataStyle,
                        data: [71, 50, 31, 39]
                    },
                    {
                        name: 'ZTW',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: placeHoledStyle,
                        data: [29, 50, 69, 61]
                    }
                ]
            };


            //
            // Irregular bars options
            //

            irregular_bars_options = {

                // Setup grid
                grid: {
                    x: 25,
                    x2: 70,
                    y: 35,
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
                    data: ['Data set 1','Data set 2']
                },

                // Add toolbox
                toolbox: {
                    show: true,
                    x: 'right',
                    y: 'center',
                    orient: 'vertical',
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
                yAxis: [{
                    type: 'value'
                }],

                // Add series
                series: [
                    {
                        name: 'Data set 1',
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                color: '#29B6F6'
                            }
                        },
                        data: [
                            [1.5, 10], [5, 7], [8, 8], [12, 6], [11, 12], [16, 9], [14, 6], [17, 4], [19, 9]
                        ],
                        markPoint: {
                            data: [

                                // Default vertical axis
                                {type: 'max', name: 'Maximum', symbol: 'emptyCircle', itemStyle: {normal: {color: '#FF7043',label: {position: 'top'}}}},
                                {type: 'min', name: 'Minimum', symbol: 'emptyCircle', itemStyle: {normal: {color: '#FF7043',label: {position: 'top'}}}},

                                // Horizontal axis
                                {type: 'max', name: 'Maximum', valueIndex: 0, symbol: 'emptyCircle', itemStyle: {normal: {color: '#29B6F6',label: {position: 'right'}}}},
                                {type: 'min', name: 'Minimum', valueIndex: 0, symbol: 'emptyCircle', itemStyle: {normal: {color: '#29B6F6',label: {position: 'left'}}}}
                            ]
                        },
                        markLine: {
                            data: [

                                // Default vertical axis
                                {type: 'max', name: 'Maximum', itemStyle: {normal: {color: '#FF7043'}}},
                                {type: 'min', name: 'Minimum', itemStyle: {normal: {color: '#FF7043'}}},
                                {type: 'average', name: 'Average', itemStyle: {normal: {color: '#FF7043'}}},

                                // Horizontal axis
                                {type: 'max', name: 'Maximum', valueIndex: 0, itemStyle: {normal: {color: '#29B6F6'}}},
                                {type: 'min', name: 'Minimum', valueIndex: 0, itemStyle: {normal: {color: '#29B6F6'}}},
                                {type: 'average', name: 'Average', valueIndex: 0, itemStyle: {normal: {color: '#29B6F6'}}}
                            ]
                        }
                    },
                    {
                        name: 'Data set 2',
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                color: '#FF7043'
                            }
                        },
                        barHeight: 10,
                        data: [
                            [1, 2], [2, 3], [4, 4], [7, 5], [11, 11], [18, 15]
                        ]
                    }
                ]
            };


            //
            // Tornado with negative stacks options
            //

            tornado_bars_negative_options = {

                // Setup grid
                grid: {
                    x: 75,
                    x2: 25,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },

                // Add legend
                legend: {
                    data: ['Profit', 'Expenses', 'Income']
                },

                // Enable drag recalculate
                calculable: true,

                // Horizontal axis
                xAxis: [{
                    type: 'value'
                }],

                // Vertical axis
                yAxis: [{
                    type: 'category',
                    axisTick: {
                        show: false
                    },
                    data: ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday']
                }],

                // Add series
                series: [
                    {
                        name: 'Profit',
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    position: 'inside'
                                }
                            }
                        },
                        data: [200, 170, 240, 244, 200, 220, 210]
                    },
                    {
                        name: 'Income',
                        type: 'bar',
                        stack: 'Total',
                        barWidth: 5,
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true
                                }
                            }
                        },
                        data: [320, 302, 341, 374, 390, 450, 420]
                    },
                    {
                        name: 'Expenses',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    position: 'left'
                                }
                            }
                        },
                        data: [-120, -132, -101, -134, -190, -230, -210]
                    }
                ]
            };


            //
            // Tornado with staggered labels options
            //

            var labelRight = {
                normal: {
                    color: '#FF7043',
                    label: {
                        position: 'right'
                    }
                }
            };

            tornado_bars_staggered_options = {

                // Setup grid
                grid: {
                    x: 25,
                    x2: 25,
                    y: 25,
                    y2: 10
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: function(params) {
                        return params[0].seriesName + ': ' + params[0].value + ' €';
                    }
                },

                // Horizontal axis
                xAxis: [{
                    type: 'value',
                    position: 'top',
                    splitLine: {
                        lineStyle: {
                            type: 'dashed'
                        }
                    },
                }],

                // Vertical axis
                yAxis: [{
                    type: 'category',
                    axisLine: {show: false},
                    axisLabel: {show: false},
                    axisTick: {show: false},
                    splitLine: {show: false},
                    data: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']
                }],

                // Add series
                series: [
                    {
                        name: 'Cost of living',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: {
                            normal: {
                                color: '#66BB6A',
                                barBorderRadius: 3,
                                label: {
                                    show: true,
                                    position: 'left',
                                    formatter: '{b}'
                                }
                            },
                            emphasis: {
                                barBorderRadius: 3
                            }
                        },
                        data: [
                            {value: -680, itemStyle: labelRight},
                            {value: -300, itemStyle: labelRight},
                            690,
                            900, 
                            {value: -390, itemStyle: labelRight},
                            600,
                            {value: -120, itemStyle: labelRight},
                            700,
                            {value: -120, itemStyle: labelRight},
                            900,
                            580,
                            {value: -720, itemStyle: labelRight}
                        ]
                    }
                ]
            };



            // Apply options
            // ------------------------------

            basic_bars.setOption(basic_bars_options);
            stacked_bars.setOption(stacked_bars_options);
            stacked_clustered_bars.setOption(stacked_clustered_bars_options);
            floating_bars.setOption(floating_bars_options);
            irregular_bars.setOption(irregular_bars_options);
            tornado_bars_negative.setOption(tornado_bars_negative_options);
            tornado_bars_staggered.setOption(tornado_bars_staggered_options);



            // Resize charts
            // ------------------------------

            window.onresize = function () {
                setTimeout(function (){
                    basic_bars.resize();
                    stacked_bars.resize();
                    stacked_clustered_bars.resize();
                    floating_bars.resize();
                    irregular_bars.resize();
                    tornado_bars_negative.resize();
                    tornado_bars_staggered.resize();
                }, 200);
            }
        }
    );
});

/* ------------------------------------------------------------------------------
 *
 *  # Echarts - columns and waterfalls
 *
 *  Columns and waterfalls chart configurations
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

            var basic_columns = ec.init(document.getElementById('basic_columns'), limitless);
            var stacked_columns = ec.init(document.getElementById('stacked_columns'), limitless);
            var stacked_clustered_columns = ec.init(document.getElementById('stacked_clustered_columns'), limitless);
            var thermometer_columns = ec.init(document.getElementById('thermometer_columns'), limitless);
            var compositive_waterfall = ec.init(document.getElementById('compositive_waterfall'), limitless);
            var change_waterfall = ec.init(document.getElementById('change_waterfall'), limitless);
            var columns_timeline = ec.init(document.getElementById('columns_timeline'), limitless);



            // Charts setup
            // ------------------------------


            //
            // Basic columns options
            //

            basic_columns_options = {

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
                    data: ['Evaporation', 'Precipitation']
                },

                // Enable drag recalculate
                calculable: true,

                // Horizontal axis
                xAxis: [{
                    type: 'category',
                    data: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value'
                }],

                // Add series
                series: [
                    {
                        name: 'Evaporation',
                        type: 'bar',
                        data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    textStyle: {
                                        fontWeight: 500
                                    }
                                }
                            }
                        },
                        markLine: {
                            data: [{type: 'average', name: 'Average'}]
                        }
                    },
                    {
                        name: 'Precipitation',
                        type: 'bar',
                        data: [2.6, 5.9, 9.0, 26.4, 58.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    textStyle: {
                                        fontWeight: 500
                                    }
                                }
                            }
                        },
                        markLine: {
                            data: [{type: 'average', name: 'Average'}]
                        }
                    }
                ]
            };


            //
            // Stacked columns options
            //

            stacked_columns_options = {

                // Setup grid
                grid: {
                    x: 40,
                    x2: 47,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow' // 'line' | 'shadow'
                    }
                },

                // Add legend
                legend: {
                    data: ['Direct', 'Email', 'Prints', 'Videos', 'Television', 'Yahoo', 'Google', 'Bing', 'Other']
                },

                // Add toolbox
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    x: 'right',
                    y: 'center',
                    itemGap: 15,
                    showTitle: false,
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
                    type: 'category',
                    data: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value'
                }],

                // Add series
                series: [
                    {
                        name: 'Direct',
                        type: 'bar',
                        data: [320, 332, 301, 334, 390, 330, 320]
                    },
                    {
                        name: 'Email',
                        type: 'bar',
                        stack: 'Advertising',
                        data: [120, 132, 101, 134, 90, 230, 210]
                    },
                    {
                        name: 'Prints',
                        type: 'bar',
                        stack: 'Advertising',
                        data: [220, 182, 191, 234, 290, 330, 310]
                    },
                    {
                        name: 'Videos',
                        type: 'bar',
                        stack: 'Advertising',
                        data: [150, 232, 201, 154, 190, 330, 410]
                    },
                    {
                        name: 'Television',
                        type: 'bar',
                        data: [862, 1018, 964, 1026, 1679, 1600, 1570],
                        markLine: {
                            itemStyle: {
                                normal: {
                                    lineStyle: {
                                        type: 'dashed'
                                    }
                                }
                            },
                            data: [
                                [{type: 'min'}, {type: 'max'}]
                            ]
                        }
                    },
                    {
                        name: 'Yahoo',
                        type: 'bar',
                        barWidth: 10,
                        stack: 'Television',
                        data: [620, 732, 701, 734, 1090, 1130, 1120]
                    },
                    {
                        name: 'Google',
                        type: 'bar',
                        stack: 'Television',
                        data: [120, 132, 101, 134, 290, 230, 220]
                    },
                    {
                        name: 'Bing',
                        type: 'bar',
                        stack: 'Television',
                        data: [60, 72, 71, 74, 190, 130, 110]
                    },
                    {
                        name: 'Other',
                        type: 'bar',
                        stack: 'Television',
                        data: [62, 82, 91, 84, 109, 110, 120]
                    }
                ]
            };


            //
            // Thermometer options
            //

            thermometer_columns_options = {

                // Setup grid
                grid: {
                    x: 35,
                    x2: 10,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow' // 'line' | 'shadow'
                    },
                    formatter: function (params) {
                        return params[0].name + '<br/>'
                        + params[0].seriesName + ': ' + params[0].value + '<br/>'
                        + params[1].seriesName + ': ' + (params[1].value + params[0].value);
                    }
                },

                // Add legend
                legend: {
                    selectedMode: false,
                    data: ['Actual', 'Forecast']
                },

                // Enable drag recalculate
                calculable: true,

                // Horizontal axis
                xAxis: [{
                    type: 'category',
                    data: ['Cosco', 'CMA', 'APL', 'OOCL', 'Wanhai', 'Zim', 'Maersk', 'Hanjin', 'Nyk']
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value',
                    boundaryGap: [0, 0.1]
                }],

                // Add series
                series: [
                    {
                        name: 'Actual',
                        type: 'bar',
                        stack: 'sum',
                        barCategoryGap: '50%',
                        itemStyle: {
                            normal: {
                                color: '#FF7043',
                                barBorderColor: '#FF7043',
                                barBorderWidth: 6,
                                label: {
                                    show: true,
                                    position: 'insideTop'
                                }
                            },
                            emphasis: {
                                color: '#FF7043',
                                barBorderColor: '#FF7043',
                                barBorderWidth: 6,
                                label: {
                                    show: true,
                                    textStyle: {
                                        color: '#fff'
                                    }
                                }
                            }
                        },
                        data: [260, 200, 220, 120, 100, 80, 130, 230, 90]
                    },
                    {
                        name: 'Forecast',
                        type: 'bar',
                        stack: 'sum',
                        itemStyle: {
                            normal: {
                                color: '#fff',
                                barBorderColor: '#FF7043',
                                barBorderWidth: 6,
                                barBorderRadius: 0,
                                label: {
                                    show: true, 
                                    position: 'top',
                                    formatter: function (params) {
                                        for (var i = 0, l = thermometer_columns_options.xAxis[0].data.length; i < l; i++) {
                                            if (thermometer_columns_options.xAxis[0].data[i] == params.name) {
                                                return thermometer_columns_options.series[0].data[i] + params.value;
                                            }
                                        }
                                    },
                                    textStyle: {
                                        color: '#FF7043'
                                    }
                                }
                            },
                            emphasis: {
                                barBorderColor: '#FF7043',
                                barBorderWidth: 6,
                                label: {
                                    show: true,
                                    textStyle: {
                                        color: '#FF7043'
                                    }
                                }
                            }
                        },
                        data:[40, 80, 50, 80,80, 70, 60, 90, 120]
                    }
                ]
            };


            //
            // Compositive waterfall options
            //

            compositive_waterfall_options = {

                // Setup grid
                grid: {
                    x: 45,
                    x2: 10,
                    y: 10,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: function (params) {
                        var tar = params[0];
                        return tar.name + '<br/>' + tar.seriesName + ': ' + tar.value;
                    }
                },

                // Horizontal axis
                xAxis: [{
                    type: 'category',
                    data: ['Total cost', 'Rent', 'Utilities', 'Transport', 'Meals', 'Commodity', 'Taxes', 'Travel']
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value'
                }],

                // Add series
                series: [
                    {
                        name: 'Aid',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: {
                            normal: {
                                barBorderColor: 'rgba(0,0,0,0)',
                                color: 'rgba(0,0,0,0)'
                            },
                            emphasis: {
                                barBorderColor: 'rgba(0,0,0,0)',
                                color: 'rgba(0,0,0,0)'
                            }
                        },
                        data:[0, 3500, 3000, 2300, 1700, 900, 400, 0]
                    },
                    {
                        name: 'Cost of living',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: {
                            normal: {
                                barBorderRadius: 3,
                                color: '#42A5F5',
                                label: {
                                    show: true,
                                    position: 'inside'
                                }
                            },
                            emphasis: {
                                color: '#42A5F5',
                            }
                        },
                        data: [4500, 1000, 500, 700, 600, 800, 500, 400]
                    }
                ]
            };


            //
            // Change waterfall options
            //

            change_waterfall_options = {

                // Setup grid
                grid: {
                    x: 45,
                    x2: 10,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: function (params) {
                        var tar;
                        if (params[1].value != '-') {
                            tar = params[1];
                        }
                        else {
                            tar = params[0];
                        }
                        return tar.name + '<br/>' + tar.seriesName + ': ' + tar.value;
                    }
                },

                // Add legend
                legend: {
                    data: ['Expenses','Income']
                },

                // Horizontal axis
                xAxis: [{
                    type: 'category',
                    data: ['January','February','March','April','May','June','July','August','September','October','November','December']
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value'
                }],

                // Add series
                series: [
                    {
                        name: 'Aid',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: {
                            normal: {
                                barBorderColor: 'rgba(0,0,0,0)',
                                color: 'rgba(0,0,0,0)'
                            },
                            emphasis: {
                                barBorderColor: 'rgba(0,0,0,0)',
                                color: 'rgba(0,0,0,0)'
                            }
                        },
                        data: [0, 900, 1245, 1530, 1376, 1376, 1511, 1689, 1856, 1495, 1292, 992]
                    },
                    {
                        name: 'Income',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: { normal: {label: {show: true, position: 'top'}}},
                        data: [900, 345, 393, '-', '-', 135, 178, 286, '-', '-', '-']
                    },
                    {
                        name: 'Expenses',
                        type: 'bar',
                        stack: 'Total',
                        itemStyle: { normal: {label: {show: true, position: 'bottom'}}},
                        data: ['-', '-', '-', 108, 154, '-', '-', '-', 119, 361, 203,300]
                    }
                ]
            };


            //
            // Timeline options
            //

            columns_timeline_options = {

                // Setup timeline
                timeline: {
                    data: ['2010-01-01', '2011-01-01', '2012-01-01', '2013-01-01', '2014-01-01'],
                    x: 10,
                    x2: 10,
                    label: {
                        formatter: function(s) {
                            return s.slice(0, 4);
                        }
                    },
                    autoPlay: true,
                    playInterval: 3000
                },

                // Main options
                options: [
                    {

                        // Setup grid
                        grid: {
                            x: 55,
                            x2: 110,
                            y: 35,
                            y2: 100
                        },

                        // Add tooltip
                        tooltip: {
                            trigger: 'axis'
                        },

                        // Add legend
                        legend: {
                            data: ['GDP','Financial','Real Estate','Primary industry','Secondary industry','Third industry']
                        },

                        // Add toolbox
                        toolbox: {
                            show: true,
                            orient: 'vertical',
                            x: 'right', 
                            y: 70,
                            feature: {
                                mark: {
                                    show: true,
                                    title: {
                                        mark: 'Markline switch',
                                        markUndo: 'Undo markline',
                                        markClear: 'Clear markline'
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
                                        stack: 'Switch to stack',
                                        tiled: 'Switch to tiled'
                                    },
                                    type: ['line', 'bar', 'stack', 'tiled']
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
                            type: 'category',
                            axisLabel: {
                                interval: 0
                            },
                            data: ['Paris','Budapest','Prague','Madrid','Amsterdam','Berlin','Bratislava','Munich','Hague','Rome','Milan']
                        }],

                        // Vertical axis
                        yAxis: [
                            {
                                type: 'value',
                                name: 'GDP（million)',
                                max: 53500
                            },
                            {
                                type: 'value',
                                name: 'Other（million)'
                            }
                        ],

                        // Add series
                        series: [
                            {
                                name: 'GDP',
                                type: 'bar',
                                markLine: {
                                    symbol: ['arrow','none'],
                                    symbolSize: [4, 2],
                                    itemStyle: {
                                        normal: {
                                            lineStyle: {color: 'orange'},
                                            barBorderColor: 'orange',
                                            label: {
                                                position: 'left',
                                                formatter: function(params) {
                                                    return Math.round(params.value);
                                                },
                                                textStyle: {color: 'orange'}
                                            }
                                        }
                                    },
                                    data: [{type: 'average', name: 'Average'}]
                                },
                                data: dataMap.dataGDP['2010']
                            },
                            {
                                name: 'Financial',
                                yAxisIndex: 1,
                                type: 'bar',
                                data: dataMap.dataFinancial['2010']
                            },
                            {
                                name: 'Real Estate',
                                yAxisIndex: 1,
                                type: 'bar',
                                data: dataMap.dataEstate['2010']
                            },
                            {
                                name: 'Primary industry',
                                yAxisIndex: 1,
                                type: 'bar',
                                data: dataMap.dataPI['2010']
                            },
                            {
                                name: 'Secondary industry',
                                yAxisIndex: 1,
                                type: 'bar',
                                data: dataMap.dataSI['2010']
                            },
                            {
                                name: 'Third industry',
                                yAxisIndex: 1,
                                type: 'bar',
                                data: dataMap.dataTI['2010']
                            }
                        ]
                    },

                    // 2011 data
                    {
                        series: [
                            {data: dataMap.dataGDP['2011']},
                            {data: dataMap.dataFinancial['2011']},
                            {data: dataMap.dataEstate['2011']},
                            {data: dataMap.dataPI['2011']},
                            {data: dataMap.dataSI['2011']},
                            {data: dataMap.dataTI['2011']}
                        ]
                    },

                    // 2012 data
                    {
                        series: [
                            {data: dataMap.dataGDP['2012']},
                            {data: dataMap.dataFinancial['2012']},
                            {data: dataMap.dataEstate['2012']},
                            {data: dataMap.dataPI['2012']},
                            {data: dataMap.dataSI['2012']},
                            {data: dataMap.dataTI['2012']}
                        ]
                    },

                    // 2013 data
                    {
                        series: [
                            {data: dataMap.dataGDP['2013']},
                            {data: dataMap.dataFinancial['2013']},
                            {data: dataMap.dataEstate['2013']},
                            {data: dataMap.dataPI['2013']},
                            {data: dataMap.dataSI['2013']},
                            {data: dataMap.dataTI['2013']}
                        ]
                    },

                    // 2014 data
                    {
                        series: [
                            {data: dataMap.dataGDP['2014']},
                            {data: dataMap.dataFinancial['2014']},
                            {data: dataMap.dataEstate['2014']},
                            {data: dataMap.dataPI['2014']},
                            {data: dataMap.dataSI['2014']},
                            {data: dataMap.dataTI['2014']}
                        ]
                    }
                ]
            };


            //
            // Stacked clustered columns options
            //

            stacked_clustered_columns_options = {

                // Setup grid
                grid: {
                    x: 65,
                    x2: 20,
                    y: 85,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis'
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

                // Horizontal axis
                xAxis: [
                    {
                        type: 'category',
                        data: ['Line','Bar','Scatter','Pies','Map']
                    },
                    {
                        type: 'category',
                        axisLine: {show:false},
                        axisTick: {show:false},
                        axisLabel: {show:false},
                        splitArea: {show:false},
                        splitLine: {show:false},
                        data: ['Line','Bar','Scatter','Pies','Map']
                    }
                ],

                // Vertical axis
                yAxis: [{
                    type: 'value',
                    axisLabel: {formatter:'{value} ms'},
                    axisLine: {
                        lineStyle: {
                            color: '#dc143c'
                        }
                    }
                }],

                // Add series
                series: [
                    {
                        name: 'Version 2.0 - 2k data',
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                color: '#F44336',
                                label: {
                                    show: true,
                                    textStyle: {
                                        color: '#fff'
                                    }
                                }
                            },
                            emphasis: {
                                label: {
                                    show: true
                                }
                            }
                        },
                        data: [247, 187, 95, 175, 270]
                    },
                    {
                        name: 'Version 2.0 - 2w data',
                        type: 'bar',
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
                                label: {
                                    show: true
                                }
                            }
                        },
                        data: [488, 415, 405, 340, 328]
                    },
                    {
                        name: 'Version 2.0 - 20w data',
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                color: '#2196F3',
                                label: {
                                    show: true,
                                    textStyle: {
                                        color:'#fff'
                                    }
                                }
                            },
                            emphasis: {
                                label: {
                                    show: true
                                }
                            }
                        },
                        data: [906, 911, 908, 778, 550]
                    },
                    {
                        name: 'Version 1.7 - 2k data',
                        type: 'bar',
                        xAxisIndex: 1,
                        itemStyle: {
                            normal: {
                                color: '#E57373',
                                label: {
                                    show: true,
                                    formatter: function(p) {return p.value > 0 ? (p.value +'\n'):'';}
                                }
                            },
                            emphasis: {
                                label: {
                                    show: true
                                }
                            }
                        },
                        data: [680, 819, 564, 724, 890]
                    },
                    {
                        name: 'Version 1.7 - 2w data',
                        type: 'bar',
                        xAxisIndex: 1,
                        itemStyle: {
                            normal: {
                                color: '#81C784',
                                label: {
                                    show: true
                                }
                            },
                            emphasis: {
                                label: {
                                    show: true
                                }
                            }
                        },
                        data: [1212, 2035, 1620, 955, 1300]
                    },
                    {
                        name: 'Version 1.7 - 20w data',
                        type: 'bar',
                        xAxisIndex: 1,
                        itemStyle: {
                            normal: {
                                color: '#64B5F6',
                                label: {
                                    show: true,
                                    formatter: function(p){return p.value > 0 ? (p.value +'+'):'';}
                                }
                            },
                            emphasis: {
                                label: {
                                    show: false
                                }
                            }
                        },
                        data: [2200, 3000, 2500, 3000, 2000]
                    }
                ]
            };



            // Apply options
            // ------------------------------

            basic_columns.setOption(basic_columns_options);
            stacked_columns.setOption(stacked_columns_options);
            stacked_clustered_columns.setOption(stacked_clustered_columns_options);
            thermometer_columns.setOption(thermometer_columns_options);
            compositive_waterfall.setOption(compositive_waterfall_options);
            change_waterfall.setOption(change_waterfall_options);
            columns_timeline.setOption(columns_timeline_options);



            // Resize charts
            // ------------------------------

            window.onresize = function () {
                setTimeout(function () {
                    basic_columns.resize();
                    stacked_columns.resize();
                    stacked_clustered_columns.resize();
                    thermometer_columns.resize();
                    compositive_waterfall.resize();
                    change_waterfall.resize();
                    columns_timeline.resize();
                }, 200);
            }
        }
    );
});

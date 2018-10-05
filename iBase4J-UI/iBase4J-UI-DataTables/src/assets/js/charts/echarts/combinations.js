/* ------------------------------------------------------------------------------
 *
 *  # Echarts - chart combinations
 *
 *  Chart combination configurations
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

        // Add necessary charts
        [
          'echarts',
          'echarts/theme/limitless',
          'echarts/chart/line',
          'echarts/chart/bar',
          'echarts/chart/pie',


          'echarts/chart/scatter',
          'echarts/chart/k',
          'echarts/chart/radar',
          'echarts/chart/gauge'
        ],


        // Charts setup
        function (ec, limitless) {


            // Initialize charts
            // ------------------------------

            var line_bar = ec.init(document.getElementById('line_bar'), limitless);
            var column_pie = ec.init(document.getElementById('column_pie'), limitless);
            var scatter_pie = ec.init(document.getElementById('scatter_pie'), limitless);
            var scatter_line = ec.init(document.getElementById('scatter_line'), limitless);

            var connect_pie = ec.init(document.getElementById('connect_pie'), limitless);
            var connect_column = ec.init(document.getElementById('connect_column'), limitless);

            var candlestick_scatter = ec.init(document.getElementById('candlestick_scatter'), limitless);



            // Charts options
            // ------------------------------


            //
            // Line and bar combination
            //

            line_bar_options = {

                // Setup grid
                grid: {
                    x: 55,
                    x2: 45,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis'
                },

                // Enable drag recalculate
                calculable: true,

                // Add legend
                legend: {
                    data: ['Evaporation','Precipitation','Temperature']
                },

                // Horizontal axis
                xAxis: [{
                    type: 'category',
                    data: ['January','February','March','April','May','June','July','August','September','October','November','December']
                }],

                // Vertical axis
                yAxis: [
                    {
                        type: 'value',
                        name: 'Water',
                        axisLabel: {
                            formatter: '{value} ml'
                        }
                    },
                    {
                        type: 'value',
                        name: 'Temp',
                        axisLabel: {
                            formatter: '{value} °C'
                        }
                    }
                ],

                // Add series
                series: [
                    {
                        name: 'Evaporation',
                        type: 'bar',
                        data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                    },
                    {
                        name: 'Precipitation',
                        type: 'bar',
                        data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                    },
                    {
                        name: 'Temperature',
                        type: 'line',
                        yAxisIndex: 1,
                        data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
                    }
                ]
            };
                    


            //
            // Column and pie combination
            //

            column_pie_options = {

                // Setup grid
                grid: {
                    x: 10,
                    x2: 45,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis'
                },

                // Enable drag recalculate
                calculable: true,

                // Add legend
                legend: {
                    data: ['Direct','Email','Print','Video','Search engine','Google','Bing','Yahoo','Other']
                },

                // Horizontal axis
                xAxis: [{
                    type: 'category',
                    splitLine: {show: false},
                    data: ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday']
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value',
                    position: 'right'
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
                        tooltip: {trigger: 'item'},
                        stack: 'Advertisement',
                        data: [120, 132, 101, 134, 90, 230, 210]
                    },
                    {
                        name: 'Print',
                        type: 'bar',
                        tooltip: {trigger: 'item'},
                        stack: 'Advertisement',
                        data: [220, 182, 191, 234, 290, 330, 310]
                    },
                    {
                        name: 'Video',
                        type: 'bar',
                        tooltip: {trigger: 'item'},
                        stack: 'Advertisement',
                        data: [150, 232, 201, 154, 190, 330, 410]
                    },
                    {
                        name: 'Search engine',
                        type: 'line',
                        data: [862, 1018, 964, 1026, 1679, 1600, 1570]
                    },

                    // Pie
                    {
                        name: 'Search engine',
                        type: 'pie',
                        tooltip: {
                            trigger: 'item',
                            formatter: '{a} <br/>{b}: {c} ({d}%)'
                        },
                        center: [150, 130],
                        radius: [0, 50],
                        itemStyle:　{
                            normal: {
                                labelLine: {
                                    length: 20
                                }
                            }
                        },
                        data: [
                            {value: 1048, name: 'Google'},
                            {value: 251, name: 'Bing'},
                            {value: 147, name: 'Yahoo'},
                            {value: 102, name: 'Other'}
                        ]
                    }
                ]
            };



            //
            // Scatter and pie combination
            //

            // Data set 1
            var sData1 = (function () {
                var d = [];
                var len = 40;
                var value;
                while (len--) {
                    d.push([
                        Math.round(Math.random()*10) * (Math.round(Math.random()*10) > 5 ? 1: -1),
                        Math.round(Math.random()*10) * (Math.round(Math.random()*10) > 5 ? 1: -1),
                        Math.round(Math.random()*20)
                    ]);
                }
                return d;
            })();

            // Data set 2
            var sData2 = (function () {
                var d = [];
                var len = sData1.length;
                for (var i = 0; i < len; i++) {
                    d.push([
                        sData1[i][0],
                        sData1[i][1],
                        Math.round(Math.random()*15)
                    ]);
                }
                return d;
            })();

            // Setup
            scatter_pie_options = {

                // Setup grid
                grid: {
                    x: 35,
                    x2: 35,
                    y: 35,
                    y2: 35
                },

                // Set custo, colors
                color: ['rgba(255, 69, 0, 0.5)', 'rgba(30, 144, 255, 0.5)'],

                // Add tooltip
                tooltip: {
                    trigger: 'item',
                    formatter: "{b}: {c} ({d}%)"
                },

                // Horizontal axis
                xAxis: [{
                    type: 'value',
                    splitNumber: 2
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value',
                    splitNumber: 2
                }],

                //animation: false,

                // Add series
                series: [
                    {
                        type: 'scatter',
                        symbol: 'none',
                        data: sData1
                    },
                    {
                        type: 'scatter',
                        symbol: 'none',
                        data: sData2
                    }
                ]
            };

            // Generate pie series
            function buildPieSeries() {
                var xAxis = scatter_pie.component.xAxis.getAxis(0);
                var yAxis = scatter_pie.component.yAxis.getAxis(0);
                var len = sData1.length;
                scatter_pie_options.series = scatter_pie_options.series.slice(0,2);
                scatter_pie_options.legend = {
                    data: ['Summer', 'Winter']
                };
                while (len--) {
                    scatter_pie_options.series.push({
                        type: 'pie',
                        itemStyle: {
                            normal: {
                                label: {
                                    show: false
                                },
                                labelLine: {
                                    show: false
                                }
                            }
                        },
                        radius: sData1[len][2] + sData2[len][2],
                        center: [
                            xAxis.getCoord(sData1[len][0]), 
                            yAxis.getCoord(sData1[len][1])
                        ],
                        data: [
                            {name: 'Summer', value: sData1[len][2]},
                            {name: 'Winter', value: sData2[len][2]}
                        ]
                    })
                }
                scatter_pie_options.animation = true;
                scatter_pie.setOption(scatter_pie_options, true);
            }

            // Build pie charts instead of the original scatter
            setTimeout(buildPieSeries, 100);



            //
            // Scatter and line combination
            //

            scatter_line_options = {

                // Setup grid
                grid: {
                    x: 35,
                    x2: 35,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis'
                },

                // Add data range
                dataRange: {
                    min: 0,
                    max: 100,
                    orient: 'horizontal',
                    y: 'top',
                    x: 'center',
                    color: ['#FF8A65','#FBE9E7'],
                    splitNumber: 5
                },

                // Horizontal axis
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: function () {
                            var list = [];
                            for (var i = 1; i <= 15; i++) {
                                list.push('2013-03-' + i);
                            }
                            return list;
                        }()
                    },
                    {
                        type: 'value',
                        scale: true,
                        splitNumber: 15,
                        axisLabel: {show: false},
                        splitLine: {show: false}
                    }
                ],

                // Vertical axis
                yAxis: [
                    {
                        type: 'value'
                    },
                    {
                        type: 'value'
                    }
                ],

                // Add series
                series: [
                    {
                        name: 'Scatter value',
                        type: 'scatter',
                        tooltip: {
                            trigger: 'item',
                            formatter: function (params) {
                                return '2013-03-' + params.value[0] + '<br/>'
                                       + params.seriesName + ': ' 
                                       + params.value[1] + ', ' 
                                       + params.value[2]; 
                            }
                        },
                        yAxisIndex: 1,
                        xAxisIndex: 1,
                        symbol: 'circle',
                        symbolSize: function (value){
                            return Math.round(value[2]/10);
                        },
                        data: (function () {
                            var d = [];
                            var len = 200;
                            var value;
                            while (len--) {
                                d.push([
                                    Math.round(Math.random()*29) + 1,
                                    (Math.random()*30).toFixed(2) - 0,
                                    (Math.random()*100).toFixed(2) - 0
                                ]);
                            }
                            return d;
                        })()
                    },
                    {
                        name: 'Line value',
                        type: 'line',
                        data: function (){
                            var list = [];
                            for (var i = 1; i <= 30; i++) {
                                list.push(Math.round(Math.random()* 30));
                            }
                            return list;
                        }()
                    }
                ]
            };



            //
            // Column and pie connection
            //

            // Pie options
            connect_pie_options = {

                // Add title
                title: {
                    text: 'Browser popularity',
                    subtext: 'Open source data',
                    x: 'center'
                },

                // Add tooltip
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },

                // Add legend
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: ['Internet Explorer','Opera','Safari','Firefox','Chrome']
                },

                // Enable drag recalculate
                calculable: true,

                // Add series
                series: [{
                    name: 'Browser',
                    type: 'pie',
                    radius: '75%',
                    center: ['50%', '57.5%'],
                    data: [
                        {value: 335, name: 'Internet Explorer'},
                        {value: 310, name: 'Opera'},
                        {value: 234, name: 'Safari'},
                        {value: 135, name: 'Firefox'},
                        {value: 1548, name: 'Chrome'}
                    ]
                }]
            };

            // Column options
            connect_column_options = {

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
                        type: 'shadow'
                    }
                },

                // Add legend
                legend: {
                    data: ['Internet Explorer','Opera','Safari','Firefox','Chrome']
                },

                // Add toolbox
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    x: 'right', 
                    y: 35,
                    feature: {
                        mark: {
                            show: true,
                            title: {
                                mark: 'Markline switch',
                                markUndo: 'Undo markline',
                                markClear: 'Clear markline'
                            }
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
                    data: ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday']
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value',
                    splitArea: {show: true}
                }],

                // Add series
                series: [
                    {
                        name: 'Internet Explorer',
                        type: 'bar',
                        stack: 'Total',
                        data: [320, 332, 301, 334, 390, 330, 320]
                    },
                    {
                        name: 'Opera',
                        type: 'bar',
                        stack: 'Total',
                        data: [120, 132, 101, 134, 90, 230, 210]
                    },
                    {
                        name: 'Safari',
                        type: 'bar',
                        stack: 'Total',
                        data: [220, 182, 191, 234, 290, 330, 310]
                    },
                    {
                        name: 'Firefox',
                        type: 'bar',
                        stack: 'Total',
                        data: [150, 232, 201, 154, 190, 330, 410]
                    },
                    {
                        name: 'Chrome',
                        type: 'bar',
                        stack: 'Total',
                        data: [820, 932, 901, 934, 1290, 1330, 1320]
                    }
                ]
            };

            // Connect charts
            connect_pie.connect(connect_column);
            connect_column.connect(connect_pie);



            //
            // Dynamic data (scatter and candlestick combination)
            //

            candlestick_scatter_options = {

                // Setup grid
                grid: {
                    x: 45,
                    x2: 45,
                    y: 60,
                    y2: 90
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis'
                },

                // Add legend
                legend: {
                    data:['Composite index', 'Turnover (million)']
                },

                // Display data zoom
                dataZoom: {
                    show: true,
                    realtime: true,
                    start: 50,
                    end: 100,
                    height: 40
                },

                // Horizontal axis
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: true,
                        data: [
                            "2013/1/24", "2013/1/25", "2013/1/28", "2013/1/29", "2013/1/30",
                            "2013/1/31", "2013/2/1", "2013/2/4", "2013/2/5", "2013/2/6", 
                            "2013/2/7", "2013/2/8", "2013/2/18", "2013/2/19", "2013/2/20", 
                            "2013/2/21", "2013/2/22", "2013/2/25", "2013/2/26", "2013/2/27", 
                            "2013/2/28", "2013/3/1", "2013/3/4", "2013/3/5", "2013/3/6", 
                            "2013/3/7", "2013/3/8", "2013/3/11", "2013/3/12", "2013/3/13", 
                            "2013/3/14", "2013/3/15", "2013/3/18", "2013/3/19", "2013/3/20", 
                            "2013/3/21", "2013/3/22", "2013/3/25", "2013/3/26", "2013/3/27", 
                            "2013/3/28", "2013/3/29", "2013/4/1", "2013/4/2", "2013/4/3", 
                            "2013/4/8", "2013/4/9", "2013/4/10", "2013/4/11", "2013/4/12", 
                            "2013/4/15", "2013/4/16", "2013/4/17", "2013/4/18", "2013/4/19", 
                            "2013/4/22", "2013/4/23", "2013/4/24", "2013/4/25", "2013/4/26", 
                            "2013/5/2", "2013/5/3", "2013/5/6", "2013/5/7", "2013/5/8", 
                            "2013/5/9", "2013/5/10", "2013/5/13", "2013/5/14", "2013/5/15", 
                            "2013/5/16", "2013/5/17", "2013/5/20", "2013/5/21", "2013/5/22", 
                            "2013/5/23", "2013/5/24", "2013/5/27", "2013/5/28", "2013/5/29", 
                            "2013/5/30", "2013/5/31", "2013/6/3", "2013/6/4", "2013/6/5", 
                            "2013/6/6", "2013/6/7", "2013/6/13"
                        ]
                    },
                    {
                        type: 'value',
                        max: 100,
                        scale: true
                    }
                ],

                // Vertical axis
                yAxis: [
                    {
                        type: 'value',
                        scale: true,
                        splitNumber: 5,
                        boundaryGap: [0.05, 0.05]
                    },
                    {
                        type: 'value',
                        splitNumber: 5,
                        scale: true
                    }
                ],

                // Add series
                series: [
                    {
                        name: 'Composite index',
                        type: 'k',
                        data: [
                            [2320.26,2302.6,2287.3,2362.94],
                            [2300,2291.3,2288.26,2308.38],
                            [2295.35,2346.5,2295.35,2346.92],
                            [2347.22,2358.98,2337.35,2363.8],
                            [2360.75,2382.48,2347.89,2383.76],
                            [2383.43,2385.42,2371.23,2391.82],
                            [2377.41,2419.02,2369.57,2421.15],
                            [2425.92,2428.15,2417.58,2440.38],
                            [2411,2433.13,2403.3,2437.42],
                            [2432.68,2434.48,2427.7,2441.73],
                            [2430.69,2418.53,2394.22,2433.89],
                            [2416.62,2432.4,2414.4,2443.03],
                            [2441.91,2421.56,2415.43,2444.8],
                            [2420.26,2382.91,2373.53,2427.07],
                            [2383.49,2397.18,2370.61,2397.94],
                            [2378.82,2325.95,2309.17,2378.82],
                            [2322.94,2314.16,2308.76,2330.88],
                            [2320.62,2325.82,2315.01,2338.78],
                            [2313.74,2293.34,2289.89,2340.71],
                            [2297.77,2313.22,2292.03,2324.63],
                            [2322.32,2365.59,2308.92,2366.16],
                            [2364.54,2359.51,2330.86,2369.65],
                            [2332.08,2273.4,2259.25,2333.54],
                            [2274.81,2326.31,2270.1,2328.14],
                            [2333.61,2347.18,2321.6,2351.44],
                            [2340.44,2324.29,2304.27,2352.02],
                            [2326.42,2318.61,2314.59,2333.67],
                            [2314.68,2310.59,2296.58,2320.96],
                            [2309.16,2286.6,2264.83,2333.29],
                            [2282.17,2263.97,2253.25,2286.33],
                            [2255.77,2270.28,2253.31,2276.22],
                            [2269.31,2278.4,2250,2312.08],
                            [2267.29,2240.02,2239.21,2276.05],
                            [2244.26,2257.43,2232.02,2261.31],
                            [2257.74,2317.37,2257.42,2317.86],
                            [2318.21,2324.24,2311.6,2330.81],
                            [2321.4,2328.28,2314.97,2332],
                            [2334.74,2326.72,2319.91,2344.89],
                            [2318.58,2297.67,2281.12,2319.99],
                            [2299.38,2301.26,2289,2323.48],
                            [2273.55,2236.3,2232.91,2273.55],
                            [2238.49,2236.62,2228.81,2246.87],
                            [2229.46,2234.4,2227.31,2243.95],
                            [2234.9,2227.74,2220.44,2253.42],
                            [2232.69,2225.29,2217.25,2241.34],
                            [2196.24,2211.59,2180.67,2212.59],
                            [2215.47,2225.77,2215.47,2234.73],
                            [2224.93,2226.13,2212.56,2233.04],
                            [2236.98,2219.55,2217.26,2242.48],
                            [2218.09,2206.78,2204.44,2226.26],
                            [2199.91,2181.94,2177.39,2204.99],
                            [2169.63,2194.85,2165.78,2196.43],
                            [2195.03,2193.8,2178.47,2197.51],
                            [2181.82,2197.6,2175.44,2206.03],
                            [2201.12,2244.64,2200.58,2250.11],
                            [2236.4,2242.17,2232.26,2245.12],
                            [2242.62,2184.54,2182.81,2242.62],
                            [2187.35,2218.32,2184.11,2226.12],
                            [2213.19,2199.31,2191.85,2224.63],
                            [2203.89,2177.91,2173.86,2210.58],
                            [2170.78,2174.12,2161.14,2179.65],
                            [2179.05,2205.5,2179.05,2222.81],
                            [2212.5,2231.17,2212.5,2236.07],
                            [2227.86,2235.57,2219.44,2240.26],
                            [2242.39,2246.3,2235.42,2255.21],
                            [2246.96,2232.97,2221.38,2247.86],
                            [2228.82,2246.83,2225.81,2247.67],
                            [2247.68,2241.92,2231.36,2250.85],
                            [2238.9,2217.01,2205.87,2239.93],
                            [2217.09,2224.8,2213.58,2225.19],
                            [2221.34,2251.81,2210.77,2252.87],
                            [2249.81,2282.87,2248.41,2288.09],
                            [2286.33,2299.99,2281.9,2309.39],
                            [2297.11,2305.11,2290.12,2305.3],
                            [2303.75,2302.4,2292.43,2314.18],
                            [2293.81,2275.67,2274.1,2304.95],
                            [2281.45,2288.53,2270.25,2292.59],
                            [2286.66,2293.08,2283.94,2301.7],
                            [2293.4,2321.32,2281.47,2322.1],
                            [2323.54,2324.02,2321.17,2334.33],
                            [2316.25,2317.75,2310.49,2325.72],
                            [2320.74,2300.59,2299.37,2325.53],
                            [2300.21,2299.25,2294.11,2313.43],
                            [2297.1,2272.42,2264.76,2297.1],
                            [2270.71,2270.93,2260.87,2276.86],
                            [2264.43,2242.11,2240.07,2266.69],
                            [2242.26,2210.9,2205.07,2250.63],
                            [2190.1,2148.35,2126.22,2190.1]
                        ]
                    },
                    {
                        name: 'Turnover (million)',
                        type: 'scatter',
                        xAxisIndex: 1,
                        yAxisIndex: 1,
                        symbolSize: function (value) {
                            return Math.round(value[2] / 4);
                        },
                        data: (function () {
                            var d = [];
                            var len = 100;
                            while (len--) {
                                d.push([
                                    (Math.random()*100).toFixed(2) - 0,
                                    (Math.random()*100).toFixed(2) - 0,
                                    (Math.random()*100).toFixed(2) - 0
                                ]);
                            }
                            return d;
                        })()
                    }
                ]
            };

            var lastIndex = 0;
            var len = candlestick_scatter_options.series[0].data.length;
            clearInterval(random_data);
            var random_data = setInterval(function (){
                lastIndex += 1;
                candlestick_scatter.addData([
                    [
                        0,
                        candlestick_scatter_options.series[0].data[lastIndex%len],
                        false,
                        false,
                        candlestick_scatter_options.xAxis[0].data[lastIndex%len]
                    ],
                    [
                        1,
                        [
                            (Math.random()*100).toFixed(2) - 0,
                            (Math.random()*100).toFixed(2) - 0,
                            (Math.random()*100).toFixed(2) - 0
                        ],
                        false,
                        false
                    ]
                ]);
            }, 2000);



            // Apply options
            // ------------------------------

            line_bar.setOption(line_bar_options);
            column_pie.setOption(column_pie_options);
            scatter_pie.setOption(scatter_pie_options);
            scatter_line.setOption(scatter_line_options);
            candlestick_scatter.setOption(candlestick_scatter_options);
            
            connect_pie.setOption(connect_pie_options);
            connect_column.setOption(connect_column_options);



            // Resize charts
            // ------------------------------

            window.onresize = function () {
                setTimeout(function (){
                    line_bar.resize();
                    column_pie.resize();
                    scatter_pie.resize();
                    buildPieSeries();
                    scatter_line.resize();
                    candlestick_scatter.resize();
                    connect_pie.resize();
                    connect_column.resize();
                }, 200);
            }
        }
    );
});

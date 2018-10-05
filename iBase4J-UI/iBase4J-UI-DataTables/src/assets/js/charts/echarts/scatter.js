/* ------------------------------------------------------------------------------
 *
 *  # Echarts - scatter charts
 *
 *  Scatter chart configurations
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
            'echarts/chart/scatter'
        ],


        // Charts setup
        function (ec, limitless) {


            // Initialize charts
            // ------------------------------

            var basic_scatter = ec.init(document.getElementById('basic_scatter'), limitless);
            var bubble_size = ec.init(document.getElementById('bubble_size'), limitless);
            var large_scale = ec.init(document.getElementById('large_scale'), limitless);
            var scale_roaming = ec.init(document.getElementById('scale_roaming'), limitless);
            var category_scatter = ec.init(document.getElementById('category_scatter'), limitless);
            var time_data = ec.init(document.getElementById('time_data'), limitless);
            var timeline_scatter = ec.init(document.getElementById('timeline_scatter'), limitless);



            // Charts setup
            // ------------------------------                    

            //
            // Basic scatter chart options
            //

            basic_scatter_options = {

                // Setup grid
                grid: {
                    x: 50,
                    x2: 45,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    showDelay: 0,
                    formatter: function (params) {
                        if (params.value.length > 1) {
                            return params.seriesName + ':<br/>'
                            + params.value[0] + 'cm ' 
                            + params.value[1] + 'kg ';
                        }
                        else {
                            return params.seriesName + ':<br/>'
                            + params.name + ': '
                            + params.value + 'kg ';
                        }
                    },  
                    axisPointer: {
                        show: true,
                        type: 'cross',
                        lineStyle: {
                            type: 'dashed',
                            width: 1
                        }
                    }
                },

                // Add legend
                legend: {
                    data: ['Women','Men']
                },

                // Horizontal axis
                xAxis: [{
                    type: 'value',
                    scale: true,
                    axisLabel: {
                        formatter: '{value} cm'
                    }
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value',
                    scale: true,
                    axisLabel: {
                        formatter: '{value} kg'
                    }
                }],

                // Add series
                series: [
                    {
                        name: 'Women',
                        type: 'scatter',
                        symbolSize: 5,
                        data: [
                            [161.2, 51.6], [167.5, 59.0], [159.5, 49.2], [157.0, 63.0], [155.8, 53.6],
                            [170.0, 59.0], [159.1, 47.6], [166.0, 69.8], [176.2, 66.8], [160.2, 75.2],
                            [172.5, 55.2], [170.9, 54.2], [172.9, 62.5], [153.4, 42.0], [160.0, 50.0],
                            [147.2, 49.8], [168.2, 49.2], [175.0, 73.2], [157.0, 47.8], [167.6, 68.8],
                            [159.5, 50.6], [175.0, 82.5], [166.8, 57.2], [176.5, 87.8], [170.2, 72.8],
                            [174.0, 54.5], [173.0, 59.8], [179.9, 67.3], [170.5, 67.8], [160.0, 47.0],
                            [154.4, 46.2], [162.0, 55.0], [176.5, 83.0], [160.0, 54.4], [152.0, 45.8],
                            [162.1, 53.6], [170.0, 73.2], [160.2, 52.1], [161.3, 67.9], [166.4, 56.6],
                            [168.9, 62.3], [163.8, 58.5], [167.6, 54.5], [160.0, 50.2], [161.3, 60.3],
                            [167.6, 58.3], [165.1, 56.2], [160.0, 50.2], [170.0, 72.9], [157.5, 59.8],
                            [167.6, 61.0], [160.7, 69.1], [163.2, 55.9], [152.4, 46.5], [157.5, 54.3],
                            [168.3, 54.8], [180.3, 60.7], [165.5, 60.0], [165.0, 62.0], [164.5, 60.3],
                            [156.0, 52.7], [160.0, 74.3], [163.0, 62.0], [165.7, 73.1], [161.0, 80.0],
                            [162.0, 54.7], [166.0, 53.2], [174.0, 75.7], [172.7, 61.1], [167.6, 55.7],
                            [151.1, 48.7], [164.5, 52.3], [163.5, 50.0], [152.0, 59.3], [169.0, 62.5],
                            [164.0, 55.7], [161.2, 54.8], [155.0, 45.9], [170.0, 70.6], [176.2, 67.2],
                            [170.0, 69.4], [162.5, 58.2], [170.3, 64.8], [164.1, 71.6], [169.5, 52.8],
                            [163.2, 59.8], [154.5, 49.0], [159.8, 50.0], [173.2, 69.2], [170.0, 55.9],
                            [161.4, 63.4], [169.0, 58.2], [166.2, 58.6], [159.4, 45.7], [162.5, 52.2],
                            [159.0, 48.6], [162.8, 57.8], [159.0, 55.6], [179.8, 66.8], [162.9, 59.4],
                            [161.0, 53.6], [151.1, 73.2], [168.2, 53.4], [168.9, 69.0], [173.2, 58.4],
                            [171.8, 56.2], [178.0, 70.6], [164.3, 59.8], [163.0, 72.0], [168.5, 65.2],
                            [166.8, 56.6], [172.7, 105.2], [163.5, 51.8], [169.4, 63.4], [167.8, 59.0],
                            [159.5, 47.6], [167.6, 63.0], [161.2, 55.2], [160.0, 45.0], [163.2, 54.0],
                            [162.2, 50.2], [161.3, 60.2], [149.5, 44.8], [157.5, 58.8], [163.2, 56.4],
                            [172.7, 62.0], [155.0, 49.2], [156.5, 67.2], [164.0, 53.8], [160.9, 54.4],
                            [162.8, 58.0], [167.0, 59.8], [160.0, 54.8], [160.0, 43.2], [168.9, 60.5],
                            [158.2, 46.4], [156.0, 64.4], [160.0, 48.8], [167.1, 62.2], [158.0, 55.5],
                            [167.6, 57.8], [156.0, 54.6], [162.1, 59.2], [173.4, 52.7], [159.8, 53.2],
                            [170.5, 64.5], [159.2, 51.8], [157.5, 56.0], [161.3, 63.6], [162.6, 63.2],
                            [160.0, 59.5], [168.9, 56.8], [165.1, 64.1], [162.6, 50.0], [165.1, 72.3],
                            [166.4, 55.0], [160.0, 55.9], [152.4, 60.4], [170.2, 69.1], [162.6, 84.5],
                            [170.2, 55.9], [158.8, 55.5], [172.7, 69.5], [167.6, 76.4], [162.6, 61.4],
                            [167.6, 65.9], [156.2, 58.6], [175.2, 66.8], [172.1, 56.6], [162.6, 58.6],
                            [160.0, 55.9], [165.1, 59.1], [182.9, 81.8], [166.4, 70.7], [165.1, 56.8],
                            [177.8, 60.0], [165.1, 58.2], [175.3, 72.7], [154.9, 54.1], [158.8, 49.1],
                            [172.7, 75.9], [168.9, 55.0], [161.3, 57.3], [167.6, 55.0], [165.1, 65.5],
                            [175.3, 65.5], [157.5, 48.6], [163.8, 58.6], [167.6, 63.6], [165.1, 55.2],
                            [165.1, 62.7], [168.9, 56.6], [162.6, 53.9], [164.5, 63.2], [176.5, 73.6],
                            [168.9, 62.0], [175.3, 63.6], [159.4, 53.2], [160.0, 53.4], [170.2, 55.0],
                            [162.6, 70.5], [167.6, 54.5], [162.6, 54.5], [160.7, 55.9], [160.0, 59.0],
                            [157.5, 63.6], [162.6, 54.5], [152.4, 47.3], [170.2, 67.7], [165.1, 80.9],
                            [172.7, 70.5], [165.1, 60.9], [170.2, 63.6], [170.2, 54.5], [170.2, 59.1],
                            [161.3, 70.5], [167.6, 52.7], [167.6, 62.7], [165.1, 86.3], [162.6, 66.4],
                            [152.4, 67.3], [168.9, 63.0], [170.2, 73.6], [175.2, 62.3], [175.2, 57.7],
                            [160.0, 55.4], [165.1, 104.1], [174.0, 55.5], [170.2, 77.3], [160.0, 80.5],
                            [167.6, 64.5], [167.6, 72.3], [167.6, 61.4], [154.9, 58.2], [162.6, 81.8],
                            [175.3, 63.6], [171.4, 53.4], [157.5, 54.5], [165.1, 53.6], [160.0, 60.0],
                            [174.0, 73.6], [162.6, 61.4], [174.0, 55.5], [162.6, 63.6], [161.3, 60.9],
                            [156.2, 60.0], [149.9, 46.8], [169.5, 57.3], [160.0, 64.1], [175.3, 63.6],
                            [169.5, 67.3], [160.0, 75.5], [172.7, 68.2], [162.6, 61.4], [157.5, 76.8],
                            [176.5, 71.8], [164.4, 55.5], [160.7, 48.6], [174.0, 66.4], [163.8, 67.3]
                        ],
                        markLine: {
                            data: [{
                                type: 'average',
                                name: 'Average'
                            }]
                        }
                    },
                    {
                        name: 'Men',
                        type: 'scatter',
                        symbolSize: 5,
                        data: [
                            [174.0, 65.6], [175.3, 71.8], [193.5, 80.7], [186.5, 72.6], [187.2, 78.8],
                            [181.5, 74.8], [184.0, 86.4], [184.5, 78.4], [175.0, 62.0], [184.0, 81.6],
                            [180.0, 76.6], [177.8, 83.6], [192.0, 90.0], [176.0, 74.6], [174.0, 71.0],
                            [184.0, 79.6], [192.7, 93.8], [171.5, 70.0], [173.0, 72.4], [176.0, 85.9],
                            [176.0, 78.8], [180.5, 77.8], [172.7, 66.2], [176.0, 86.4], [173.5, 81.8],
                            [178.0, 89.6], [180.3, 82.8], [180.3, 76.4], [164.5, 63.2], [173.0, 60.9],
                            [183.5, 74.8], [175.5, 70.0], [188.0, 72.4], [189.2, 84.1], [172.8, 69.1],
                            [170.0, 59.5], [182.0, 67.2], [170.0, 61.3], [177.8, 68.6], [184.2, 80.1],
                            [186.7, 87.8], [171.4, 84.7], [172.7, 73.4], [175.3, 72.1], [180.3, 82.6],
                            [182.9, 88.7], [188.0, 84.1], [177.2, 94.1], [172.1, 74.9], [167.0, 59.1],
                            [169.5, 75.6], [174.0, 86.2], [172.7, 75.3], [182.2, 87.1], [164.1, 55.2],
                            [163.0, 57.0], [171.5, 61.4], [184.2, 76.8], [174.0, 86.8], [174.0, 72.2],
                            [177.0, 71.6], [186.0, 84.8], [167.0, 68.2], [171.8, 66.1], [182.0, 72.0],
                            [167.0, 64.6], [177.8, 74.8], [164.5, 70.0], [192.0, 101.6], [175.5, 63.2],
                            [171.2, 79.1], [181.6, 78.9], [167.4, 67.7], [181.1, 66.0], [177.0, 68.2],
                            [174.5, 63.9], [177.5, 72.0], [170.5, 56.8], [182.4, 74.5], [197.1, 90.9],
                            [180.1, 93.0], [175.5, 80.9], [180.6, 72.7], [184.4, 68.0], [175.5, 70.9],
                            [180.6, 72.5], [177.0, 72.5], [177.1, 83.4], [181.6, 75.5], [176.5, 73.0],
                            [175.0, 70.2], [174.0, 73.4], [165.1, 70.5], [177.0, 68.9], [192.0, 102.3],
                            [176.5, 68.4], [169.4, 65.9], [182.1, 75.7], [179.8, 84.5], [175.3, 87.7],
                            [184.9, 86.4], [177.3, 73.2], [167.4, 53.9], [178.1, 72.0], [168.9, 55.5],
                            [157.2, 58.4], [180.3, 83.2], [170.2, 72.7], [177.8, 64.1], [172.7, 72.3],
                            [165.1, 65.0], [186.7, 86.4], [165.1, 65.0], [174.0, 88.6], [175.3, 84.1],
                            [185.4, 66.8], [177.8, 75.5], [180.3, 93.2], [180.3, 82.7], [177.8, 58.0],
                            [177.8, 79.5], [177.8, 78.6], [177.8, 71.8], [177.8, 116.4], [163.8, 72.2],
                            [188.0, 83.6], [198.1, 85.5], [175.3, 90.9], [166.4, 85.9], [190.5, 89.1],
                            [166.4, 75.0], [177.8, 77.7], [179.7, 86.4], [172.7, 90.9], [190.5, 73.6],
                            [185.4, 76.4], [168.9, 69.1], [167.6, 84.5], [175.3, 64.5], [170.2, 69.1],
                            [190.5, 108.6], [177.8, 86.4], [190.5, 80.9], [177.8, 87.7], [184.2, 94.5],
                            [176.5, 80.2], [177.8, 72.0], [180.3, 71.4], [171.4, 72.7], [172.7, 84.1],
                            [172.7, 76.8], [177.8, 63.6], [177.8, 80.9], [182.9, 80.9], [170.2, 85.5],
                            [167.6, 68.6], [175.3, 67.7], [165.1, 66.4], [185.4, 102.3], [181.6, 70.5],
                            [172.7, 95.9], [190.5, 84.1], [179.1, 87.3], [175.3, 71.8], [170.2, 65.9],
                            [193.0, 95.9], [171.4, 91.4], [177.8, 81.8], [177.8, 96.8], [167.6, 69.1],
                            [167.6, 82.7], [180.3, 75.5], [182.9, 79.5], [176.5, 73.6], [186.7, 91.8],
                            [188.0, 84.1], [188.0, 85.9], [177.8, 81.8], [174.0, 82.5], [177.8, 80.5],
                            [171.4, 70.0], [185.4, 81.8], [185.4, 84.1], [188.0, 90.5], [188.0, 91.4],
                            [182.9, 89.1], [176.5, 85.0], [175.3, 69.1], [175.3, 73.6], [188.0, 80.5],
                            [188.0, 82.7], [175.3, 86.4], [170.5, 67.7], [179.1, 92.7], [177.8, 93.6],
                            [175.3, 70.9], [182.9, 75.0], [170.8, 93.2], [188.0, 93.2], [180.3, 77.7],
                            [177.8, 61.4], [185.4, 94.1], [168.9, 75.0], [185.4, 83.6], [180.3, 85.5],
                            [174.0, 73.9], [167.6, 66.8], [182.9, 87.3], [160.0, 72.3], [180.3, 88.6],
                            [167.6, 75.5], [186.7, 101.4], [175.3, 91.1], [175.3, 67.3], [175.9, 77.7],
                            [175.3, 81.8], [179.1, 75.5], [181.6, 84.5], [177.8, 76.6], [182.9, 85.0],
                            [177.8, 102.5], [184.2, 77.3], [179.1, 71.8], [176.5, 87.9], [188.0, 94.3],
                            [174.0, 70.9], [167.6, 64.5], [170.2, 77.3], [167.6, 72.3], [188.0, 87.3],
                            [174.0, 80.0], [176.5, 82.3], [180.3, 73.6], [167.6, 74.1], [188.0, 85.9],
                            [180.3, 73.2], [167.6, 76.3], [183.0, 65.9], [183.0, 90.9], [179.1, 89.1],
                            [170.2, 62.3], [177.8, 82.7], [179.1, 79.1], [190.5, 98.2], [177.8, 84.1],
                            [180.3, 83.2], [180.3, 83.2]
                        ],
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
            // Bubble size control options
            //

            function random(){
                var r = Math.round(Math.random() * 100);
                return (r * (r % 2 == 0 ? 1: -1));
            }

            function randomDataArray() {
                var d = [];
                var len = 100;
                while (len--) {
                    d.push([
                        random(),
                        random(),
                        Math.abs(random()),
                    ]);
                }
                return d;
            }

            bubble_size_options = {

                // Setup grid
                grid: {
                    x: 40,
                    x2: 60,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    showDelay: 0,
                    axisPointer: {
                        show: true,
                        type: 'cross',
                        lineStyle: {
                            type: 'dashed',
                            width: 1
                        }
                    }
                },

                // Add legend
                legend: {
                    data: ['Scatter1','Scatter2']
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
                        dataView: {
                            show: true,
                            readOnly: false,
                            title: 'View data',
                            lang: ['View chart data', 'Close', 'Update']
                        },
                        dataZoom: {
                            show: true,
                            title: {
                                dataZoom: 'Data zoom',
                                dataZoomReset: 'Reset zoom'
                            }
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

                // Horizontal axis
                xAxis: [{
                    type: 'value',
                    splitNumber: 4,
                    scale: true
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value',
                    splitNumber: 4,
                    scale: true
                }],

                // Add series
                series: [
                    {
                        name: 'Scatter1',
                        type: 'scatter',
                        symbolSize: function (value) {
                            return Math.round(value[2] / 5);
                        },
                        data: randomDataArray()
                    },
                    {
                        name: 'Scatter2',
                        type: 'scatter',
                        symbolSize: function (value) {
                            return Math.round(value[2] / 5);
                        },
                        data: randomDataArray()
                    }
                ]
            };


            //
            // Large scale scatter options
            //

            large_scale_options = {

                // Setup grid
                grid: {
                    x: 25,
                    x2: 47,
                    y: 35,
                    y2: 25
                },

                // Add tooltip
                tooltip: {
                    trigger: 'axis',
                    showDelay: 0,
                    axisPointer: {
                        show: true,
                        type: 'cross',
                        lineStyle: {
                            type: 'dashed',
                            width: 1
                        }
                    }
                },

                // Add legend
                legend: {
                    data: ['sin', 'cos']
                },

                // Display toolbox
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
                        dataView: {
                            show: true,
                            readOnly: false,
                            title: 'View data',
                            lang: ['View chart data', 'Close', 'Update']
                        },
                        dataZoom: {
                            show: true,
                            title: {
                                dataZoom: 'Data zoom',
                                dataZoomReset: 'Reset zoom'
                            }
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

                // Horizontal axis
                xAxis: [{
                    type: 'value',
                    scale: true
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value',
                    scale: true
                }],

                // Add series
                series: [
                    {
                        name: 'sin',
                        type: 'scatter',
                        large: true,
                        data: (function () {
                            var d = [];
                            var len = 10000;
                            var x = 0;
                            while (len--) {
                                x = (Math.random() * 10).toFixed(3) - 0;
                                d.push([
                                    x,
                                    (Math.sin(x) - x * (len % 2 ? 0.1: -0.1) * Math.random()).toFixed(3) - 0
                                ]);
                            }
                            return d;
                        })()
                    },

                    {
                        name: 'cos',
                        type: 'scatter',
                        large: true,
                        data: (function () {
                            var d = [];
                            var len = 10000;
                            var x = 0;
                            while (len--) {
                                x = (Math.random() * 10).toFixed(3) - 0;
                                d.push([
                                    x,
                                    (Math.cos(x) - x * (len % 2 ? 0.1: -0.1) * Math.random()).toFixed(3) - 0
                                ]);
                            }
                            return d;
                        })()
                    }
                ]
            };


            //
            // Scale roaming options
            //

            scale_roaming_options = {

                // Setup grid
                grid: {
                    x: 47,
                    x2: 35,
                    y: 10,
                    y2: 75
                },

                // Add tooltip
                tooltip: {
                    trigger: 'item'
                },

                // Add toolbox
                toolbox: {
                    show: true,
                    x: 'left',
                    y: 70,
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
                        dataView: {
                            show: true,
                            readOnly: false,
                            title: 'View data',
                            lang: ['View chart data', 'Close', 'Update']
                        },
                        dataZoom: {
                            show: true,
                            title: {
                                dataZoom: 'Data zoom',
                                dataZoomReset: 'Reset zoom'
                            }
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

                // Display data range
                dataRange: {
                    min: 0,
                    max: 100,
                    y: 'bottom',
                    x: 'center',
                    text: ['High','Low'],
                    color: ['#FB8C00','#FFE0B2'],
                    calculable: true,
                    itemWidth: 30,
                    orient: 'horizontal'
                },

                // Horizontal axis
                xAxis: [{
                    type: 'value',
                    scale: true
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value',
                    position: 'right',
                    scale: true
                }],

                // Add series
                series: [
                    {
                        name: 'Scatter1',
                        type: 'scatter',
                        symbolSize: 5,
                        data: (function () {
                            var d = [];
                            var len = 500;
                            var value;
                            while (len--) {
                                value = (Math.random()*100).toFixed(2) - 0;
                                d.push([
                                    (Math.random()*value + value).toFixed(2) - 0,
                                    (Math.random()*value).toFixed(2) - 0,
                                    value
                                ]);
                            }
                            return d;
                        })()
                    }
                ]
            };


            //
            // Category scatter options
            //

            category_scatter_options = {

                // Setup grid
                grid: {
                    x: 35,
                    x2: 30,
                    y: 70,
                    y2: 90
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
                    }
                },

                // Add data zoom
                dataZoom: {
                    show: true,
                    start: 40,
                    end: 60,
                    height: 40
                },

                // Add legend
                legend: {
                    data: ['Series1', 'Series2']
                },

                // Display data range
                dataRange: {
                    min: 0,
                    max: 100,
                    orient: 'horizontal',
                    y: 35,
                    x: 'center',
                    splitNumber: 5
                },

                // Horizontal axis
                xAxis: [{
                    type: 'category',
                    data: function () {
                        var list = [];
                        var len = 0;
                        while (len++ < 500) {
                            list.push(len);
                        }
                        return list;
                    }()
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value'
                }],

                // Add series
                series: [
                    {
                        name: 'Series1',
                        type: 'scatter',
                        tooltip: {
                            trigger: 'item',
                            formatter: function (params) {
                                return params.seriesName + ' （'  + 'Category' + params.value[0] + '）<br/>'
                                + params.value[1] + ', ' 
                                + params.value[2]; 
                            },
                            axisPointer: {
                                show: true
                            }
                        },
                        symbolSize: function (value) {
                            return Math.round(value[2]/10);
                        },
                        data: (function () {
                            var d = [];
                            var len = 0;
                            var value;
                            while (len++ < 500) {
                                d.push([
                                    len,
                                    (Math.random()*30).toFixed(2) - 0,
                                    (Math.random()*100).toFixed(2) - 0
                                ]);
                            }
                            return d;
                        })()
                    },

                    {
                        name: 'Series2',
                        type: 'scatter',
                        tooltip: {
                            trigger: 'item',
                            formatter: function (params) {
                                return params.seriesName + ' （'  + 'Category' + params.value[0] + '）<br/>'
                                + params.value[1] + ', ' 
                                + params.value[2]; 
                            },
                            axisPointer:{
                                show: true
                            }
                        },
                        symbolSize: function (value) {
                            return Math.round(value[2]/10);
                        },
                        data: (function () {
                            var d = [];
                            var len = 0;
                            var value;
                            while (len++ < 500) {
                                d.push([
                                    len,
                                    (Math.random()*30).toFixed(2) - 0,
                                    (Math.random()*100).toFixed(2) - 0
                                ]);
                            }
                            return d;
                        })()
                    }
                ]
            };


            //
            // Time data options
            //

            time_data_options = {

                // Setup grid
                grid: {
                    x: 45,
                    x2: 45,
                    y: 70,
                    y2: 100
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
                    }
                },

                // Add data zoom
                dataZoom: {
                    show: true,
                    start: 40,
                    end: 60,
                    height: 40
                },

                // Add legend
                legend: {
                    data: ['Series1']
                },

                // Display data range
                dataRange: {
                    min: 0,
                    max: 100,
                    orient: 'horizontal',
                    y: 35,
                    x: 'center',
                    color: ['#8BC34A','#F1F8E9'],
                    splitNumber: 5
                },

                // Horizontal axis
                xAxis: [{
                    type: 'time',
                    splitNumber: 10
                }],

                // Vertical axis
                yAxis: [{
                    type: 'value'
                }],

                // Add series
                series: [
                    {
                        name: 'Series1',
                        type: 'scatter',
                        tooltip: {
                            trigger: 'axis',
                            formatter: function (params) {
                                var date = new Date(params.value[0]);
                                return params.seriesName 
                                + ' （'
                                + date.getFullYear() + '-'
                                + (date.getMonth() + 1) + '-'
                                + date.getDate() + ' '
                                + date.getHours() + ':'
                                + date.getMinutes()
                                +  '）<br/>'
                                + params.value[1] + ', ' 
                                + params.value[2];
                            },
                            axisPointer: {
                                type: 'cross',
                                lineStyle: {
                                    type: 'dashed',
                                    width: 1
                                }
                            }
                        },
                        symbolSize: function (value) {
                            return Math.round(value[2]/10);
                        },
                        data: (function () {
                            var d = [];
                            var len = 0;
                            var now = new Date();
                            var value;
                            while (len++ < 1500) {
                                d.push([
                                    new Date(2014, 9, 1, 0, Math.round(Math.random()*10000)),
                                    (Math.random()*30).toFixed(2) - 0,
                                    (Math.random()*100).toFixed(2) - 0
                                ]);
                            }
                            return d;
                        })()
                    }
                ]
            };


            //
            // Timeline options
            //

            timeline_scatter_options = {

                // Setup timeline
                timeline: {
                    data: ['2010-01-01','2011-01-01','2012-01-01','2013-01-01','2014-01-01'],
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

                // Add options
                options: [
                    {

                        // Setup grid
                        grid: {
                            x: 55,
                            x2: 75,
                            y: 25,
                            y2: 80
                        },

                        // Add tooltip
                        tooltip: {
                            trigger: 'axis',
                            showDelay: 0,
                            axisPointer: {
                                show: true,
                                type: 'cross',
                                lineStyle: {
                                    type: 'dashed',
                                    width: 1
                                }
                            }
                        },

                        // Horizontal axis
                        xAxis: [{
                            type: 'value',
                            name: 'Real estate',
                            max: 3400
                        }],

                        // Vertical axis
                        yAxis: [{
                            type: 'value',
                            name: 'GDP',
                            max: 53500
                        }],

                        // Add series
                        series: [{
                            name: 'GDP',
                            type: 'scatter',
                            markLine: {
                                data: [
                                    {
                                        type: 'average',
                                        name: 'y average',
                                        itemStyle: {
                                            normal: {
                                                color: '#FF5722'
                                            }
                                        }
                                    },
                                    {
                                        type: 'average',
                                        name: 'x average',
                                        valueIndex: 0,
                                        itemStyle: {
                                            normal: {
                                                color: '#FF5722'
                                            }
                                        }
                                    }
                                ]
                            },
                            symbolSize: 5,
                            itemStyle: {
                                normal: {
                                    color: ['#607D8B'],
                                    label: {
                                        show: true,
                                        formatter: '{b}'
                                    }
                                }
                            },
                            data: dataMap.dataGDP_Estate['2010']
                        }]
                    },

                    {
                        series: [
                            {data: dataMap.dataGDP_Estate['2011']}
                        ]
                    },
                    {
                        series: [
                            {data: dataMap.dataGDP_Estate['2012']}
                        ]
                    },
                    {
                        series: [
                            {data: dataMap.dataGDP_Estate['2013']}
                        ]
                    },
                    {
                        series: [
                            {data: dataMap.dataGDP_Estate['2014']}
                        ]
                    }
                ]
            };



            // Apply options
            // ------------------------------

            basic_scatter.setOption(basic_scatter_options);
            bubble_size.setOption(bubble_size_options);
            large_scale.setOption(large_scale_options);
            scale_roaming.setOption(scale_roaming_options);
            category_scatter.setOption(category_scatter_options);
            time_data.setOption(time_data_options);
            timeline_scatter.setOption(timeline_scatter_options);



            // Resize charts
            // ------------------------------

            window.onresize = function () {
                setTimeout(function (){
                    basic_scatter.resize();
                    bubble_size.resize();
                    large_scale.resize();
                    scale_roaming.resize();
                    category_scatter.resize();
                    time_data.resize();
                    timeline_scatter.resize();
                }, 200);
            }
        }
    );
});

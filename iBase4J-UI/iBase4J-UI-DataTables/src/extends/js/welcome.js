$(function () {
    //图表插件
    var n = echarts.init(document.getElementById("mainChart")), t;
    n.setOption({
        backgroundColor: "#fff",
        title: {
            text: "月度销售趋势图",
            textStyle: {color: "#666666", fontSize: 24, fontWeight: 100, fontFamily: "微软雅黑"},
            subtext: "昨日订单：298 | 累计订单：897 | 昨日交易额：99840 | 累计交易额：1890894",
            subtextStyle: {color: "#878787", fontSize: 14, fontWeight: 100, fontFamily: "微软雅黑"},
            itemGap: 20
        },
        tooltip: {trigger: "axis", axisPointer: {type: "shadow", textStyle: {color: "#fff"}}},
        legend: {data: ["新增订单", "新增客户", "其它"], x: 15, y: 95, textStyle: {color: "#878787"}},
        grid: {borderWidth: 0, top: 130, bottom: 2, x: 15, y: 0, x2: 0, y2: 0, textStyle: {color: "#fff"}},
        xAxis: [{
            type: "category",
            boundaryGap: !1,
            data: ["1号", "2号", "3号", "4号", "5号", "6号", "7号", "8号", "9号", "10号", "11号", "12号", "13号", "14号", "15号", "16号", "17号", "18号", "19号", "20号", "21号", "22号", "23号", "24号", "25号", "26号", "27号", "28号", "29号", "30号", "31号"],
            splitLine: !1,
            show: !1
        }],
        yAxis: [{type: "value", splitLine: !1, show: !1}],
        series: [{
            name: "其它",
            type: "bar",
            itemStyle: {normal: {color: "#DDE0E5"}},
            data: [160, 80, 120, 150, 200, 150, 320, 220, 150, 140, 154, 220, 150, 232, 201, 154, 190, 250, 200, 150, 232, 201, 154, 190, 150, 232, 201, 154, 190, 160, 120]
        }, {
            name: "新增订单",
            type: "line",
            smooth: !0,
            areaStyle: {normal: {color: "#7b93e0"}},
            lineStyle: {normal: {color: "#7b93e0", width: 0}},
            data: [0, 0, 80, 100, 150, 160, 170, 200, 220, 240, 200, 180, 120, 100, 160, 180, 200, 210, 250, 200, 180, 170, 200, 240, 280, 300, 330, 260, 190, 150, 0, 0],
            symbol: "none"
        }, {
            name: "新增客户",
            type: "line",
            smooth: !0,
            areaStyle: {normal: {color: "#2AE8DE"}},
            lineStyle: {normal: {color: "#2AE8DE", width: 0}},
            data: [0, 0, 100, 220, 290, 190, 100, 80, 100, 110, 133, 150, 100, 80, 111, 130, 160, 144, 110, 90, 80, 100, 110, 120, 140, 190, 270, 200, 160, 100, 0, 0],
            symbol: "none"
        }]
    });
    myChart1 = echarts.init(document.getElementById("echart1"));
    t = {
        backgroundColor: "#fff",
        title: {
            text: "一周销售走势",
            textStyle: {color: "#666666", fontSize: 24, fontWeight: 100, fontFamily: "微软雅黑"}
        },
        tooltip: {trigger: "axis", axisPointer: {type: "shadow"}},
        legend: {data: ["新增订单", "新增客户", "其它"], x: "right", y: 30, padding: [5, 35, 5, 5]},
        grid: {borderWidth: 0, top: 100, left: "3%", right: "4%", bottom: "3%", containLabel: !0},
        xAxis: [{
            type: "category",
            data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
            splitLine: !1,
            axisLine: !1,
            axisTick: !1
        }],
        yAxis: [{type: "value", splitLine: !1, axisLine: !1, axisTick: !1}],
        series: [{
            name: "新增客户",
            type: "bar",
            itemStyle: {normal: {color: "#2AE8DE"}},
            data: [840, 900, 1e3, 800, 880, 1200, 1e3]
        }, {
            name: "新增订单",
            type: "bar",
            itemStyle: {normal: {color: "#7b93e0"}},
            data: [700, 800, 1100, 900, 600, 1e3, 800]
        }, {
            name: "其它",
            type: "bar",
            itemStyle: {normal: {color: "#DDE0E5"}},
            data: [400, 500, 600, 700, 500, 400, 310]
        }]
    };
    myChart1.setOption(t);
    myChart2 = echarts.init(document.getElementById("echart2"));
    option2 = {
        backgroundColor: "#fff",
        title: {
            text: "客户区域分布",
            textStyle: {color: "#666666", fontSize: 24, fontWeight: 100, fontFamily: "微软雅黑"}
        },
        tooltip: {trigger: "item", formatter: "{a} <br/>{b}: {c} ({d}%)"},
        toolbox: {
            show: !0,
            feature: {
                restore: {show: !0, title: "还原"},
                saveAsImage: {show: !0, title: "保存为图片", type: "png", lang: ["点击保存"]}
            },
            padding: [25, 25, 5, 5]
        },
        legend: {x: "center", y:"bottom", data: ["上海", "苏州", "山东", "其它"], padding: [5, 5, 35, 20]},
        series: [{
            name: "访问来源",
            type: "pie",
            radius: ["45%", "65%"],
            avoidLabelOverlap: !1,
            selectedMode: "single",
            label: {
                normal: {show: !1, position: "center"},
                emphasis: {show: !0, textStyle: {fontSize: "30", fontWeight: "bold"}}
            },
            labelLine: {normal: {show: !1}},
            data: [{value: 335, name: "其它", itemStyle: {normal: {color: "#2AE8DE"}}}, {
                value: 234,
                name: "山东",
                itemStyle: {normal: {color: "#f06995"}}
            }, {value: 135, name: "苏州", itemStyle: {normal: {color: "#F2B459"}}}, {
                value: 1548,
                name: "上海",
                itemStyle: {normal: {color: "#7b93e0"}}
            }]
        }]
    };
    myChart2.setOption(option2);
    window.onresize = function (t) {
        n.resize(t);
        myChart1.resize(t);
        myChart2.resize(t)
    }
})
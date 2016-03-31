// JavaScript Document
$(function(){
    var href = window.location.href;
    $.each($(".navbar-nav-custom li"),function(){
        var url = $(this).find("a").attr("href");
        if(href.indexOf(url)>=0){
            $(this).addClass("open");
        }else{
            $(this).removeClass("open");
        }

    })//
    if(href.indexOf("clientNo")<0){
        $("#clientNo").val("");
        $("#clientNo").focus();
    }
    $.validator.setDefaults({
        errorClass: 'help-block animation-slideDown', // You can change the animation class for a different entrance animation - check animations page
        errorElement: 'div',
        errorPlacement: function(error, e) {
            var eleErrContains = e.parents('.tdgroup');
            if(eleErrContains.length == 0){
                eleErrContains = e.parents('.form-group > div');
            }
            eleErrContains.append(error);
        },
        highlight: function(e) {
            $(e).closest('.form-group').removeClass('has-success has-error').addClass('has-error');
            $(e).closest('.help-block').remove();
        },
        success: function(e) {
            e.closest('.form-group').removeClass('has-success has-error');
            e.closest('.help-block').remove();
        }
    });
    jQuery.validator.addMethod("stringCheck", function(value, element) {
        return this.optional(element) || /(^\d+$)/.test(value);
    }, "只能输入数字");
    jQuery.validator.addMethod("stringCheck1", function(value, element) {
        return this.optional(element) || /^\d+(\.\d{1,2})?$/.test(value);
    }, "只能输入数字或保留两位小数");
    jQuery.validator.addMethod("check", function() {
        var paid = $("#paid").val(),
            paidPos = $("#paidPos").val();
        if(paid != "" || paidPos != ""){
            return true;
        }else{
            return false;
        }
    }, "应收款金额和Pos金额必须一个有值！");
    $('#form-validation').validate({
        rules: {
            clientNo: {
                required: true,
                stringCheck: true,
                remote: {
                    url: "/shop/valid",
                    type: "post",
                    dataType: "json",
                    data: {
                        clientNo: function () {
                            return $("#clientNo").val();
                        }
                    },
                    dataFilter: function (data) {
                        var dataJson = jQuery.parseJSON(data);
                        if (dataJson.code != "0x001") {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
            },
            salesOrderNo: {
                required: true,
                stringCheck: true,
                remote: {
                    url: "/order/check",
                    type: "post",
                    dataType: "json",
                    data: {
                        salesOrderNo: function () {
                            return $("#salesOrderNo").val();
                        }
                    },
                    dataFilter: function (data) {
                        var dataJson = jQuery.parseJSON(data);
                        if (dataJson.code == "0x004") {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
            },
            paid: {
                check:true,
                stringCheck1:true
            },
            paidPos: {
                check:true,
                stringCheck1:true
            }
        },
        messages: {
            clientNo:{
                required: "请输入商户编号",
                stringCheck: "只能输入数字",
                remote: "商户编号不存在"
            },
            salesOrderNo: {
                required: "请输入订单编号",
                stringCheck: "只能输入数字",
                remote:"订单编号已存在"
            },
            paid: {
                check:"现金和POS金额至少得填写一个",
                stringCheck1:"只能输入数字或保留两位小数"
            },
            paidPos: {
                check:"现金和POS金额至少得填写一个",
                stringCheck1:"只能输入数字或保留两位小数"

            }
        },
        submitHandler: function(form) {
                $.ajax({
                    url:'/paid',
                    type:'post',
                    dataType:'json',
                    data:{
                        'clientNo':$("#clientNo").val(),
                        'salesOrderNo':$("#salesOrderNo").val(),
                        'paid':$("#paid").val(),
                        'paidPos':$("#paidPos").val()
                    },
                    success:function(data){
                        if(data.code == '0x004'){
                            $.ajax({
                                url:'/rest/paid/list',
                                type:'post',
                                dataType:'json',
                                data:"",
                                success:function(data){
                                    var listTpl = "";
                                    $("#clientNo").val("");
                                    $("#salesOrderNo").val("");
                                    $("#paid").val("");
                                    $("#paidPos").val("");
                                    $("#branchName").html("");
                                    $("#totalMoney").val("");
                                    $('input:text:first').focus();
                                    $.each(data,function(key,val){
                                        if(val.paid){
                                            var paid = val.paid;
                                        }else{
                                            var paid = 0;
                                        }
                                        if(val.paidPos){
                                            var paidPos = val.paidPos;
                                        }else{
                                            var paidPos = 0;
                                        }
                                        if(val.tbTmpEmployee){
                                            var employeeName = val.tbTmpEmployee.employeeName;
                                        }else{
                                            var employeeName = "";
                                        }
                                        var totalM = Math.round(Math.round(paid*100)+Math.round(paidPos*100))/100;
                                        listTpl += '<tr> \
											<td class="text-center">'+val.clientNo+'</td> \
											<td class="text-center">'+val.tbClient.clientName+'</td> \
										<td class="text-center">'+val.salesOrderNo+'</td> \
											<td class="text-center">'+paid+'</td> \
										<td class="text-center">'+paidPos+'</td> \
										<td class="text-center">'+totalM+'</td> \
										<td class="text-center">'+employeeName+'</td> \
											<td class="text-center">'+getDate(val.createDate)+'</td> \
										</tr>';
                                    })
                                    $(".employee-list table tbody").html(listTpl);
                                    $(".employee-list table tbody tr:first").css("background","#F0FC89");
                                    setTimeout(function(){
                                        $(".employee-list table tbody tr:first").css("background","none");
                                    },500)
                                }
                            })
                        }
                    }
                });
        }
    });//blur
    $("#paid").keyup(function(){
        var paid = $(this).val(),
            paidPos = $("#paidPos").val(),
            totalMoney = Math.round(Math.round(paid*100)+Math.round(paidPos*100))/100;
        $("#totalMoney").val(totalMoney);
    })
    $("#paidPos").keyup(function(){
        var paidPos = $(this).val(),
            paid = $("#paid").val(),
            totalMoney = Math.round(Math.round(paid*100)+Math.round(paidPos*100))/100;
        $("#totalMoney").val(totalMoney);
    })
    $("#clientNo").keyup(function(){
        var clientNo = $(this).val();
        if(clientNo.length>=7){
            brandData(clientNo);
        }
    })
    if($("#clientNo").val() != "" && $("#clientNo").val().length>=7 ){
        brandData($("#clientNo").val());
    }
    $("#totalMoney").blur(function(){
        $("#totalMoney").css("background","none");
    })
    $('input:text:first').focus();
    var $inp = $('input:text');
    $inp.bind('keydown', function (e) {
        var key = e.which;
        if (key == 13) {
            e.preventDefault();
            var nxtIdx = $inp.index(this) + 1;
            if($inp.length == nxtIdx){
                $("#form-validation").submit();
            }else{
                if($inp.length - 1 == nxtIdx ){
                    $(":input:text:eq(" + nxtIdx + ")").focus();
                    $(":input:text:eq(" + nxtIdx + ")").css("background","#F0FC89");

                }else{
                    $(":input:text:eq(" + nxtIdx + ")").focus();
                }

            }
            setTimeout(function(){
                $.each($(".form-group"),function(){
                    if($(this).hasClass("has-error")){
                        $(this).find("input").focus();
                    }
                })
            },50)

        }
    });
})
function clickBut(){
    $("#form-validation").submit();
}
function brandData(data){
    $.ajax({
        url: '/shop/valid',
        type: 'post',
        dataType: 'json',
        data: {"clientNo":data},
        success: function (data) {
            if(data.code != "0x001"){
                if(data.code){
                    $("#branchName").html(data.code);
                }else{
                    $("#branchName").html("");
                }
            }else{
                console.log(data);
            }
        }
    })
}
function getDate(tm) {
    var date = new Date(tm);
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate() + ' ';
    if (date.getHours() < 10) {
        h = "0" + date.getHours() + ':';
    } else {
        h = date.getHours() + ':';
    }
    if (date.getMinutes() < 10) {
        m = "0" + date.getMinutes() + ':';
    } else {
        m = date.getMinutes() + ':';
    }
    if (date.getSeconds() < 10) {
        s = "0" + date.getSeconds();
    } else {
        s = date.getSeconds();
    }
    return Y + M + D + h + m + s;
}

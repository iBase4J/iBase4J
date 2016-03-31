// JavaScript Document
$(function(){
    var href = window.location.href,
        isIgnore = "",
        promptText = "";
    $.each($(".navbar-nav-custom li"),function(){
        var url = $(this).find("a").attr("href");
        if(href.indexOf(url)>=0){
            $(this).addClass("open");
        }else{
            $(this).removeClass("open");
        }

    })
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
    if(href.indexOf("shop/append/view?clientNo=")>=0){
        isIgnore = "#clientNo";
        $("#clientNo").attr("readonly","readonly");
        promptText = "编辑成功！";
        $(".block-title").find("strong").html("编辑商户");
    }else{
        promptText = "添加成功！";
        $(".block-title").find("strong").html("新增商户");
    }
    $('#form-validation').validate({
        ignore:isIgnore,
        rules: {
            clientNo: {
                required: true,
                stringCheck: true,
                rangelength:[7,7],
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
                        if (dataJson.code == "0x001") {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
            },
            clientName: {
                required: true
            },
            personName: {
                required: true
            },
            clientPhone: {
                required: true
            }
        },
        messages: {
            clientNo:{
                required: "请输入商户编号",
                stringCheck: "只能输入数字",
                rangelength:"商户编号为7位数字",
                remote: "商户编号已存在"
            },
            clientName: {
                required: "请输入品牌"
            },
            personName: {
                required: "请输入联系人"
            },
            clientPhone: {
                required: "请输入联系人电话"
            }
        },
        submitHandler: function(form) {
            if($("input[type='checkbox']").is(":checked")){
                var clientStatus = 1
            }else{
                var clientStatus = 0
            }
            $.ajax({
                url:'/shop/update',
                type:'post',
                dataType:'json',
                data:{
                    'clientNo':$("#clientNo").val(),
                    'clientName':$("#clientName").val(),
                    'bankAccountName':$("#bankAccountName").val(),
                    'bank':$("#bank").val(),
                    'bankAccount':$("#bankAccount").val(),
                    'personName':$("#personName").val(),
                    'clientPhone':$("#clientPhone").val(),
                    'tradeType':$("#tradeType option:selected").val(),
                    'floor' : $("#val_floor option:selected").val(),
                    'clientStatus':clientStatus
                },
                success:function(data){
                    if(data.code == '0x004'){
                        $("#alert-layer").find("strong").html(promptText);
                        $("#alert-layer").show();
                        setTimeout(function(){
                            $("#alert-layer").hide();
                            window.location.href = "/client/list";
                        },1000)
                    }
                }
            });
        }
    });//blur
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
                $(":input:text:eq(" + nxtIdx + ")").focus();
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
$("#submit").click(function(){
    $("#form-validation").submit();
});


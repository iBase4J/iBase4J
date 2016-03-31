// JavaScript Document
$(function(){
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
    $('#form-login').validate({
        rules: {
            employeeCode: {
                required: true
            },
            employeePassword: {
                required: true
            }
        },
        messages: {
            employeeCode:'请输入工号',
            employeePassword: '请输入密码'
        },
        submitHandler: function() {
            $.ajax({
                url:"/login",
                type:'post',
                dataType:'json',
                data:{
                    'employeeCode':$("#employeeCode").val(),
                    'employeePassword':$("#employeePassword").val()
                },
                success:function(data){
                    if(data.code){
                        window.location.href = "/client/list";
                    }else{
                        $("#employeePassword").parent().after('<div id="employeePassword-error" style=" color: #e74c3c;" class="help-block animation-slideDown">工号或密码错误！</div>');
                    }
                }
            });
        }
    });

})
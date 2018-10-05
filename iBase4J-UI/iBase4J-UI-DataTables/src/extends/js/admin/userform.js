$(function(){
    //日期
	$('.date-picker').datepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
    }).on('changeDate', function () {
        $(this).datepicker('hide');
    });

    // 隶属部门
    $(document).trigger('initTree', {
    	parentEle: 'department',
		url: '/dept/read/list',
    	checkParent: true,
		data: JSON.stringify({keyword:''}),
		resetData: function(data) {
		   for(var i=0;i<data.length;i++) {
			   data[i]['open'] = true;
			   data[i]['pId'] = data[i].parentId;
			   data[i]['name'] = data[i].deptName;
		   }
		   return data;
    	}
    });

    function init() {
        var id = localStorage.getItem('id');
        if(id) {
        	$('.OPT').text('编辑');
        	 loadData('/user/read/detail', {id : id}, function(data) {
    			$('#userEdit').autofill(data);
    			if($('[name="sex"][value="' + data.sex + '"]').length > 0) {
        			$('[name="sex"][value="' + data.sex + '"]').get(0).checked = true;
        			$('[name="sex"][value="' + data.sex + '"]').parents('span').addClass('checked');
    			}
    			data.enable == '1' && ($('[name="enable"]').bootstrapSwitch('state',true)) || $('[name="enable"]').bootstrapSwitch('state',false);
    			$('.loading').hide();
        	 });
        } else {
        	$('[name="enable"]').bootstrapSwitch('toggleState');
			$('.loading').hide();
        }
    }
    init();
    // 重新加载
    $(document).on('refreshData', function() {
        init();
    });
    
    var validator = $('#userEdit').validate({
    	rules : {
    		account : {
    			required: true
    		}, userName : {
    			required: true
    		}, password: {
    			required: true,
    			minlength: 6
    		}, repeat_password: {
    			required: true,
    			equalTo: '[name="password"]'
    		}, phone: {
    			required: true,
    			phone: true
    		}, sex: {
    			required: true
    		}, email: {
    			required: true,
    			email: true
    		}, position: {
    			required: true
    		}, birthDay: {
    			required: true
    		}, deptId: {
    			required: true
    		}
    	}, messages : {
    		account : {
    			required: '请输入用户账号'
    		}, userName : {
    			required: '请输入真实姓名'
    		}, password: {
    			required: '请输入用户密码',
    			minlength: '密码最少6个字符'
    		}, repeat_password: {
       			required: '请确认密码',
    			equalTo: '两次输入密码不一致'
    		}, phone : {
    			required: '请输入手机号'
    		}, sex : {
    			required: '请选择性别'
    		}, email: {
    			required: '请输入邮箱',
    			email: '请输入有效的电子邮箱'
    		}, position: {
    			required: '请输入职位'
    		}, birthDay: {
    			required: '请选择出生日期'
    		}, deptId: {
    			required: '请选择部门'
    		}
    	}
    });
    $('#reset').on('click', function() {
    	validator && validator.resetForm();
    });
    // 保存用户信息
    $('#userEdit .submit').on('click', function() {
    	$(document).trigger('submit', {form: $('#userEdit'), url: '/user', href: 'user.html'});
    	return false;
    });
});


$(function(){
	//日期
	$('.date-picker').datepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
    }).on('changeDate', function () {
        $(this).datepicker('hide');
    });
	
    //性别
    $(".styled").uniform({ radioClass: 'choice' });

    //隶属部门
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
        $.ajax({
        	url: '/user/read/current',
    		success : function(result) {
    			if (result.code == 200) {
    				if(result.data.birthDay && result.data.birthDay.length>10) {
    					result.data.birthDay = result.data.birthDay.substr(0, 10);
    				}
    				$('form').autofill(result.data);
    				if($('[name="sex"][value="' + result.data.sex + '"]').length > 0) {
    					$('[name="sex"][value="' + result.data.sex + '"]').get(0).checked = true;
    				}
    				$('[name="sex"][value="' + result.data.sex + '"]').parents('span').addClass('checked');
    			} else {
    				error(result.msg);
    			}
				$('.loading').hide();
    		}
        });
    }
    init();
    $(document).on('refreshData', function() {
	    init();
    });
    
    var validator = $('#personalData').validate({
    	rules : {
    		account : {
    			required: true
    		}, userName : {
    			required: true
    		}, sex: {
    			required: true
    		}, position: {
    			required: true
    		}, birthDay: {
    			required: true
    		}, deptId: {
    			required: true
    		}, roleId: {
    			required: true
    		}
    	}, messages : {
    		account : {
    			required: '请输入用户账号'
    		}, userName : {
    			required: '请输入真实姓名'
    		}, sex : {
    			required: '请选择性别'
    		}, position: {
    			required: '请输入职位'
    		}, birthDay: {
    			required: '请选择出生日期'
    		}, deptId: {
    			required: '请选择部门'
    		}, roleId: {
    			required: '请选择角色'
    		}
    	}
    });
    $('#reset').on('click', function() {
    	validator && validator.resetForm();
    });
    // 保存用户信息
    $('#personalData .submit').on('click', function() {
    	$(document).trigger('submit', {form: $('#personalData'), url: '/user/update/person', href: 'user.html'});
    });
    
    $('#contactWay').validate({
    	rules : {
    		phone: {
    			required: true,
    			phone: true
    		}, email: {
    			required: true,
    			email: true
    		}
    	}, messages : {
    		phone : {
    			required: '请输入手机号'
    		}, email: {
    			required: '请输入邮箱',
    			email: '请输入有效的电子邮箱'
    		}
    	}
    });
    // 保存用户信息
    $('#contactWay .submit').on('click', function() {
    	$(document).trigger('submit', {form: $('#contactWay'), url: '/user/update/person', href: 'user.html'});
    });
})


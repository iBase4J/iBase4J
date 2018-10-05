$(function() {
	var validator = $('#passwordData').validate({
		   rules : {
	   		oldPassword : {
	   			required: true
	   		}, password: {
	   			required: true,
	   			minlength: 6
	   		}, repeat_password: {
	   			required: true,
	   			equalTo: '[name="password"]'
	   		}
	   	}, messages : {
	   		oldPassword : {
	   			required: '请输入原密码'
	   		}, password: {
	   			required: '请输入新密码',
	   			minlength: '密码最少6个字符'
	   		}, repeat_password: {
	   			required: '请确认新密码',
	   			equalTo: '两次输入密码不一致'
	   		}
	   	}
	   });
	    $('#reset').on('click', function() {
	    	validator && validator.resetForm();
	    });
	   $('#passwordData .submit').on('click', function() {
		   	$(document).trigger('submit', {form: $('#passwordData'), url: '/user/update/password', href: 'user.html'});
		});
});
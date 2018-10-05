$(function(){
    function init() {
        var id = localStorage.getItem('id');
        if(id) {
        	$('.OPT').text('编辑');
        	 loadData('/unit/read/detail', {id : id}, function(data) {
    			$('form').autofill(data);
    			$('.loading').hide();
        	 });
        }
    }
    init();
    // 重新加载
    $(document).on('refreshData', function() {
        init();
    });
    
    var validator = $('form').validate({
    	rules : {
    		unitName : {
    			required: true
    		}, principal : {
    			required: true
    		}, phone: {
    			required: true,
    			phone: true
    		}, address: {
    			required: true
    		}
    	}, messages : {
    		unitName : {
    			required: '请输入单位名称'
    		}, principal : {
    			required: '请输入负责人'
    		}, phone : {
    			required: '请输入联系电话'
    		}, address: {
    			required: '请输入地址'
    		}
    	}
    });
    $('#reset').on('click', function() {
    	validator && validator.resetForm();
    });
    // 保存用户信息
    $('form .submit').on('click', function() {
    	$(document).trigger('submit', {form: $('form'), url: '/unit', href: 'unit.html'});
    	return false;
    });
});


$(function(){
    // 隶属部门
    $(document).trigger('initTree', {
    	checkParent: true,
    	parentEle: 'department',
		url: '/dept/read/list',
		data: {keyword:''},
		resetData: function(data) {
		   for(var i=0;i<data.length;i++) {
			   if(data[i].leaf == 0) {
				   data[i]['open'] = true;
			   }
			   data[i]['pId'] = data[i].parentId;
			   data[i]['name'] = data[i].deptName;
		   }
		   return data;
    	}
    });
    // 初始化
    function init() {
        var id = localStorage.getItem('id');
        if(id) {
        	$('.OPT').text('编辑');
        	 loadData('/role/read/detail', {id : id}, function(data) {
    			$('form').autofill(data);
    			$('.loading').hide();
        	 });
        } else {
    		$('.loading').hide();
        }
    }
    init();
    // 重新加载
    $(document).on('refreshData', function() {
        init();
    });
    
    var validator = $('form').validate({
    	rules : {
    		roleName : {
    			required: true
    		}, deptName : {
    			required: true
    		}
    	}, messages : {
    		roleName : {
    			required: '请输入角色名称'
    		}, deptName : {
    			required: '请输入所属部门'
    		}
    	}
    });
    $('#reset').on('click', function() {
    	validator && validator.resetForm();
    });
    // 保存用户信息
    $('form .submit').on('click', function() {
    	$(document).trigger('submit', {form: $('form'), url: '/role', href: 'role.html'});
    });
});


$(function(){
	// Setting datatable defaults
    $.extend($.fn.dataTable.defaults, {
        autoWidth: false,
        dom: '<"datatable-header"fBl><"datatable-scroll-wrap"t><"datatable-footer"ip>',
        language: {
            search: '<span>搜索：</span> _INPUT_',
            lengthMenu: '<span>显示：</span> _MENU_',
            paginate: {'first': 'First', 'last': 'Last', 'next': '&rarr;', 'previous': '&larr;'}
        }
    });
    // Basic initialization
    $(".selectbox").selectBoxIt({
        autoWidth: false
    });
    
    // 表格初始化
    var option = {
    	target: '.emailConfig-list',
    	url: '/emailConfig/read/list',
    	params: {keyword: ''},
    	searchable : true,
    	addButton: '<button class="btn bg-teal add-btn" data-toggle="modal" data-target=".emailSendEdit"> <i class="icon-plus2"></i>新增 </button>',
    	filterHolder: '邮件名称/使用发送',
    	columns: [{ "data": "senderName" },
           { "data": "senderAccount" },
           { "data": "smtpHost" },
           { "data": "smtpPort" },
           { "data": "sendMethod" },
           { 'data' : null }
       ],
       columnDefs: [{
    	   render: function(v, type, row) {
    		   switch(v) {
    		   case '0':
    			   return '默认';
    		   case '1':
    			   return 'ssl';
    		   }
       		}, targets: 4
       }, {
      	render: function(v, type, row) {
      		return '<ul class="icons-list">'
      		+ '<li class="text-primary-600"><a href="javascript:void(0)" onclick="update(\''+row.id+'\')" data-toggle="modal" data-target=".emailSendEdit" title="编辑"><i '
           	+ 'class="icon-pencil7"></i></a></li>'
    		+ '<li class="text-danger-600"><a href="javascript:void(0)" onclick="deleteRecord(\'/newsConfig\', \''+row.id+'\')" data-popup="tooltip" title="删除"><i '
            + 'class="icon-trash"></i></a></li>li>'
    		+ '</ul>';
       	   },
       	   orderable: false,
           targets: 5
       }],
       //导出
       buttons: {
           dom: {
               button: {
                   className: 'btn btn-default'
               }
           },
           buttons: [
               'excelHtml5',
               'csvHtml5',
               'pdfHtml5'
           ]}
    };
    $(document).trigger('searchDatatables', option);
    // 重新加载
    $(document).on('refreshData', function() {
    	option['refresh'] = true;
    	$(document).trigger('searchDatatables', option);
    });

    $('.add-btn').on('click', function() {
    	$('.modal-title').text('新增邮件发送设置');
    	$('#emailSendEdit')[0].reset();
    });
    
    var validator = $('#emailSendEdit').validate({
    	messages: {
    		content : {
    			smtpHost: '请输入SMTP服务器'
    		}, 
    		smtpPort : {
    			required: '请输入SMTP服务器端口'
    		}, 
    		sendMethod : {
    			required: '发送方式'
    		},
    		senderName : {
    			required: '请输入发送者名称'
    		}, 
    		senderAccount : {
    			required: '请输入邮箱账号'
    		}, 
    		senderPassword : {
    			required: '请输入邮箱密码'
    		}
    	}
    });
    $('#reset').on('click', function() {
    	validator && validator.resetForm();
    });
    $('#emailSendEdit .submit').on('click', function() {
    	$(document).trigger('submit', {form: $('#emailSendEdit'), url: '/emailConfig', callback: function() {
    		$(document).trigger('refreshData');
    		$('[data-dismiss="modal"]').click();
    	}});
    });
});

// 获取数据
function update(id) {
	$('.modal-title').text('编辑邮件发送设置');
    $.ajax({
    	type: 'GET',
    	url: '/emailConfig/read/detail',
    	data: {id: id},
		success : function(result) {
			if (result.code == 200) {
				$('#emailSendEdit').autofill(result.data);
			} else {
				error(result.msg);
			}
		}
    });
}
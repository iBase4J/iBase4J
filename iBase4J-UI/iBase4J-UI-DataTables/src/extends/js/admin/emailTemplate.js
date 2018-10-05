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
    	target: '.emailTemplate-list',
    	url: '/emailTemplate/read/list',
    	params: {keyword: ''},
    	searchable : true,
    	addButton: '<button class="btn bg-teal add-btn" data-toggle="modal" data-target=".emailTemplateEdit"> <i class="icon-plus2"></i>新增 </button>',
    	filterHolder: '发送者名称/邮箱账号',
    	columns: [{ "data": "senderName" },
           { "data": "senderAccount" },
           { "data": "sort" },
           { "data": "title" },
           { "data": "template" },
           { 'data' : null }
       ],
       columnDefs: [{
      	render: function(v, type, row) {
      		return '<ul class="icons-list">'
      		+ '<li class="text-primary-600"><a href="javascript:void(0)" onclick="update(\''+row.id+'\')" data-toggle="modal" data-target=".emailTemplateEdit" title="编辑"><i '
           	+ 'class="icon-pencil7"></i></a></li>'
    		+ '<li class="text-danger-600"><a href="javascript:void(0)" onclick="deleteRecord(\'/newsTemplate\', \''+row.id+'\')" data-popup="tooltip" title="删除"><i '
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
    	$('.modal-title').text('新增邮件模版');
    	$('#emailTemplateEdit #reset').click();
    });
    
    var validator = $('#emailTemplateEdit').validate({
    	messages: {
    		senderName : {
    			required: '请输入发送人名称'
    		}, 
    		senderAccount : {
    			required: '请输入邮件帐号'
    		}, 
    		title : {
    			required: '请输入邮件标题模版'
    		}, 
    		content : {
    			required: '请输入邮件内容模版'
    		}
    	}
    });
    $('#reset').on('click', function() {
    	validator && validator.resetForm();
    });
    $('#emailTemplateEdit .submit').on('click', function() {
    	$(document).trigger('submit', {form: $('#emailTemplateEdit'), url: '/emailTemplate', callback: function() {
    		$(document).trigger('refreshData');
    		$('[data-dismiss="modal"]').click();
    	}});
    });
});

//获取数据
function update(id) {
	$('.modal-title').text('编辑邮件模版');
    $.ajax({
    	type: 'PUT',
    	url: '/emailTemplate/read/detail',
    	data: JSON.stringify({id: id}),
		dataType: 'json',
	    contentType:'application/json;charset=UTF-8',
		success : function(result) {
			if (result.code == 200) {
				$('#emailTemplateEdit').autofill(result.data);
			} else {
				error(result.msg);
			}
		}
    });
}
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
    
    // 表格初始化
    var option = {
    	target: '.dic-list',
    	url: '/dic/read/page',
    	params: {keyword: ''},
    	searchable : true,
    	addButton: '<button class="btn bg-teal add-btn" data-toggle="modal" data-target=".dicEdit"> <i class="icon-plus2"></i>新增 </button>'
    		+' <button class="btn bg-danger-400 add-btn reflush"><i class="icon-spinner4"></i>同步缓存</button>',
    	filterHolder: '类型/选项',
    	columns: [{ "data": "type" },
               { "data": "codeText" },
               { "data": "code" },
               { "data": "sortNo" },
               { "data": "updateTime" },
               { 'data' : null }
           ],
           columnDefs: [{
          	render: function(v, type, row) {
          		return '<ul class="icons-list">'
          		+ '<li class="text-primary-600"><a href="javascript:void(0)" onclick="update(\''+row.id+'\')" data-toggle="modal" data-target=".dicEdit" title="编辑"><i '
               	+ 'class="icon-pencil7"></i></a></li>'
        		+ '<li class="text-danger-600"><a href="javascript:void(0)" onclick="deleteRecord(\'/dic\', \''+row.id+'\')" data-popup="tooltip" title="删除"><i '
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
    
    $(document).on('click', '.add-btn', function() {
    	$('.modal-title').text('新增字典');
    	$('#dicEdit')[0].reset();
    });
    
    $('#dicEdit').validate({
    	messages : {
    		type : {
    			required: '请输入类型'
    		}, codeText : {
    			required: '请输入选项描述'
    		}, code : {
    			required: '请输入选项值'
    		}
    	}
    });
    // 保存信息
    $('#dicEdit .submit').on('click', function() {
    	$(document).trigger('submit', {form: $('#dicEdit'), url: '/dic', callback: function() {
    		$(document).trigger('refreshData');
    		$('[data-dismiss="modal"]').click();
    	}});
    });

    $(document).on('click', 'button.reflush', function() {
    	$.ajax({
    		type: 'POST',
    		url: '/cache/update',
    		data: JSON.stringify({key: 'sysDic'}),
			success : function(result) {
				if (result.code == 200) {
					notice('同步成功.');
				} else {
					error(result.msg);
				}
				$('.loading').hide();
			}
    	});
    });
});
//获取数据
function update(id) {
	$('.modal-title').text('编辑字典');
    $.ajax({
    	type: 'PUT',
    	url: '/dic/read/detail',
    	data: JSON.stringify({id: id}),
		dataType: 'json',
	    contentType:'application/json;charset=UTF-8',
		success : function(result) {
			if (result.code == 200) {
				$('#dicEdit').autofill(result.data);
			} else {
				error(result.msg);
			}
			$('.loading').hide();
		}
    });
}
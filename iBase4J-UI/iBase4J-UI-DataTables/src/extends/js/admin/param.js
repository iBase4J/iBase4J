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
    	target: '.param-list',
    	url: '/param/read/list',
    	params: {keyword: ''},
    	searchable : true,
    	addButton: '<button class="btn bg-teal add-btn" data-toggle="modal" data-target=".paramEdit"> <i class="icon-plus2"></i>新增 </button>'
    		+' <button class="btn bg-danger-400 add-btn reflush"><i class="icon-spinner4"></i>同步缓存</button>',
    	filterHolder: '参数键',
    	columns: [{ "data": "paramKey" },
               { "data": "paramValue" },
               { "data": "remark" },
               { "data": "updateTime" },
               { 'data' : null }
           ],
           columnDefs: [{
          	render: function(v, type, row) {
          		return '<ul class="icons-list">'
          		+ '<li class="text-primary-600"><a href="javascript:void(0)" onclick="update(\''+row.id+'\')" data-toggle="modal" data-target=".paramEdit" title="编辑"><i '
               	+ 'class="icon-pencil7"></i></a></li>'
        		+ '<li class="text-danger-600"><a href="javascript:void(0)" onclick="deleteRecord(\'/param\', \''+row.id+'\')" data-popup="tooltip" title="删除"><i '
                + 'class="icon-trash"></i></a></li>li>'
        		+ '</ul>';
           	},
            orderable: false,
               targets: 4
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
    	$('.modal-title').text('新增参数');
    	$('#paramEdit')[0].reset();
    });
    
    var validator = $('#paramEdit').validate({
    	messages : {
    		paramKey : {
    			required: '请输入参数键'
    		}, paramValue : {
    			required: '请输入参数值'
    		}
    	}
    });
    $('#reset').on('click', function() {
    	validator && validator.resetForm();
    });
    // 保存信息
    $('#paramEdit .submit').on('click', function() {
    	$(document).trigger('submit', {form: $('#paramEdit'), url: '/param', callback: function() {
    		$(document).trigger('refreshData');
    		$('[data-dismiss="modal"]').click();
    	}});
    });

    $(document).on('click', 'button.reflush', function() {
    	$.ajax({
    		type: 'POST',
    		url: '/cache/update',
    		data: {key: 'sysParam'},
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
	$('.modal-title').text('编辑参数');
    $.ajax({
    	type: 'GET',
    	url: '/param/read/detail',
    	data: {id: id},
		success : function(result) {
			if (result.code == 200) {
				$('#paramEdit').autofill(result.data);
			} else {
				error(result.msg);
			}
			$('.loading').hide();
		}
    });
}
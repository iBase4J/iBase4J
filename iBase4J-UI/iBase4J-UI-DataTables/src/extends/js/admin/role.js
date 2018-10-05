$(function () {
	localStorage.clear();

    // Setting datatable defaults
    $.extend($.fn.dataTable.defaults, {
        autoWidth: true,
        columnDefs: [{
            orderable: false,
            targets: [4]
        }],
        dom: '<"datatable-header"fBl><"datatable-scroll-wrap"t><"datatable-footer"ip>',
        language: {
            search: '<span>搜索：</span> _INPUT_',
            lengthMenu: '<span>显示：</span> _MENU_',
            paginate: {'first': 'First', 'last': 'Last', 'next': '&rarr;', 'previous': '&larr;'}
        }
    });

    // Basic initialization
    var option = {
    	target: '.role-list',
    	url: '/role/read/page',
    	params: {keyword: ''},
    	searchable : true,
    	addButton: '<a href="roleEdit.html" class="btn bg-teal add-btn"> <i class="icon-plus2"></i>新增 </a>',
    	filterHolder: '角色名称',
    	columns: [
               { "data": "roleName" },
               { "data": "deptName" },
               { "data": "remark" },
               { 'data' : null }
       ],
       columnDefs: [{
	       	render: function(data, type, row) {
	       		var r = '<ul class="icons-list">';
	       		if(row.permission) {
	       			r += '<li class="text-success-600"><a href="javascript:void(0)" onclick="showPermission(\''+row.permission+'\')" data-popup="tooltip" title="查看权限"><i '
		               + 'class="icon-eye"></i></a></li>';
	       		}
	       		r +='<li class="text-primary-600"><a href="javascript:void(0)" onclick="updateRecord(\'roleEdit.html\', \''+row.id+'\')" data-popup="tooltip" title="编辑"><i '
		            + 'class="icon-pencil7"></i></a></li>'
		       		+ '<li class="text-danger-600"><a href="javascript:void(0)" onclick="deleteRecord(\'/role\', \''+row.id+'\')" data-popup="tooltip" title="删除"><i '
		            + 'class="icon-trash"></i></a></li>li>'
		       		+ '</ul>';
	       		return r;
	       		},
	       		targets: 3
           }
       ],
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
    
    /*$('.dataTables_filter').append( '<div class="status">'
        +'      <label class="status-text">状态：</label>'
        +'       <select class="selectbox">'
        +'           <option value="0">全部</option>'
        +'           <option value="1">启用</option>'
        +'           <option value="2">禁用</option>'
        +'       </select>'
        +'  </div>');

    $(".selectbox").selectBoxIt({
        autoWidth: false
    });*/

});
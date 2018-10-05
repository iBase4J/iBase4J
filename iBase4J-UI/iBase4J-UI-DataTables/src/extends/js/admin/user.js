$(function () {
	localStorage.clear();
	
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
    var option = {
    	target: '.user-list',
    	url: '/user/read/list',
    	params: {keyword: ''},
    	searchable : true,
    	addButton: '<a href="userEdit.html" class="btn bg-teal add-btn"> <i class="icon-plus2"></i>新增 </a>',
    	filterHolder: '帐号/手机号/部门',
    	columns: [
               { "data": "account" },
               { "data": "phone" },
               { "data": "email" },
               { "data": "deptName" },
               { "data": "enable" },
               { 'data' : null }
           ],
           columnDefs: [{
               render: function(data, type, row) {
	               	if(data === '1') {
	               		return '<span class="label label-info">启用</span>';
	               	} else  {
	               		return '<span class="label label-default">禁用</span>';
	               	}
               },
               orderable: false,
               targets: 4
           }, {
           	render: function(data, type, row) {
           		var r = '<ul class="icons-list">';
           		if(row.permission) {
           			r += '<li class="text-success-600"><a href="javascript:void(0)" onclick="showPermission(\''+row.permission+'\')" data-popup="tooltip" title="查看权限"><i '
           			  + 'class="icon-eye"></i></a></li>';
           		}
           		r += '<li class="text-primary-600"><a href="javascript:void(0)" onclick="updateRecord(\'userEdit.html\', \''+row.id+'\')" data-popup="tooltip" title="编辑"><i '
                   	+ 'class="icon-pencil7"></i></a></li>'
           			+ '<li class="text-danger-600"><a href="javascript:void(0)" onclick="deleteRecord(\'/user\', \''+row.id+'\')" data-popup="tooltip" title="删除"><i '
           			+ 'class="icon-trash"></i></a></li>li>'
           			+ '</ul>';
           		return r;
           	},
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
               ]},
            callback: function() {
            	/*$('.dataTables_filter').append( '<div class="status">'
            		    +'      <label class="status-text">状态：</label>'
            		    +'       <select class="selectbox userState">'
            		    +'           <option value="0">全部</option>'
            		    +'           <option value="1">启用</option>'
            		    +'           <option value="2">禁用</option>'
            		    +'       </select>'
            		    +'  </div>');
            	$(".selectbox").selectBoxIt({
                    autoWidth: false
                });*/
            }
    };
    $(document).trigger('searchDatatables', option);
    // 重新加载
    $(document).on('refreshData', function() {
    	option['refresh'] = true;
    	$(document).trigger('searchDatatables', option);
    });
});

$(function () {
	localStorage.clear();
    // Setting datatable defaults
    $.extend($.fn.dataTable.defaults, {
        autoWidth: false,
        columnDefs: [{
            orderable: false,
            targets: [5]
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
    	target: '.unit-list',
    	url: '/unit/read/page',
    	params: {keyword: ''},
    	searchable : true,
    	addButton: '<a href="unitEdit.html" class="btn bg-teal add-btn"> <i class="icon-plus2"></i>新增 </a>',
    	filterHolder: '单位名称/负责人',
    	columns: [
               { "data": "unitName" },
               { "data": "principal" },
               { "data": "phone" },
               { "data": "address" },
               { 'data' : null }
           ],
           columnDefs: [{
           	render: function(data, type, row) {
           		return '<ul class="icons-list">'
                   + '<li class="text-primary-600"><a href="javascript:void(0)" onclick="updateRecord(\'unitEdit.html\', \''+row.id+'\')" data-popup="tooltip" title="编辑"><i '
                   + 'class="icon-pencil7"></i></a></li>'
           		+ '<li class="text-danger-600"><a href="javascript:void(0)" onclick="deleteRecord(\'/unit\', \''+row.id+'\')" data-popup="tooltip" title="删除"><i '
                   + 'class="icon-trash"></i></a></li>li>'
           		+ '</ul>';
           	},
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

})
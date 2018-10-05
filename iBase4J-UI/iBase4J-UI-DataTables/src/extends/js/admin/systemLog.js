$(function () {
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

    // 操作日志初始化
    var option = {
    	target: '.operation-log',
    	url: '/event/read/list',
    	params: {keyword: ''},
    	searchable : true,
    	filterHolder: '操作人',
    	columns: [{ "data": "title" },
               { "data": "userName" },
               { "data": "createTime" },
               { "data": "clientHost" },
               { "data": "userAgent" },
               { "data": "remark" }
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
});
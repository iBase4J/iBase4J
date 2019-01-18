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
    
    // 表格初始化
    var option = {
    	target: '.msg-list',
    	url: '/msg/read/list',
    	params: {keyword: ''},
    	searchable : true,
    	filterHolder: '手机号/短信内容',
    	columns: [
    	   { "data": "phone" },
    	   { "data": "type" },
           { "data": "content" },
           { 'data' : 'sendState' },
           { "data": "createTime" }
       ],
       columnDefs: [{
           render: function(v, type, row) {
              	if(v === '1') {
               		return '<span class="label label-info">已发送</span>';
               	} else if(v === '0') {
               		return '<span class="label label-default">未发送</span>';
               	} else {
               		return '<span class="label label-danger">发送失败</span>';
               	}
           },
           targets: 3
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
});
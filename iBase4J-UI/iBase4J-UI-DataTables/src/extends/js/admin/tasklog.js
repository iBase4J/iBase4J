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

    // Basic initialization
    var option = {
    	target: '.email-list',
    	url: '/scheduled/read/log',
    	params: {keyword: ''},
    	searchable : true,
    	filterHolder: '任务组/任务名称',
    	columns: [{ "data": "groupName" },
               { "data": "taskName" },
               { "data": "startTime" },
               { "data": "endTime" },
               { "data": "serverHost" },
               { 'data' : 'status' },
               { 'data' : 'fireInfo' }
       ], columnDefs: [{
    	   targets: 5,
    	   render: function(data, type, row) {
    		   return data == 'S' ? '<span class="label label-info">成功</span>' : '<span class="label label-warning">失败</span>';
          }
       }], buttons: {
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
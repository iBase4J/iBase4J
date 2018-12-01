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
    	url: '/email/read/page',
    	params: {keyword: ''},
    	searchable : true,
    	filterHolder: '邮件名称/使用发送',
    	columns: [{ "data": "id" },
               { "data": "emailName" },
               { "data": "sender" },
               { "data": "emailTitle" },
               { "data": "emailContent" },
               { 'data' : 'createTime' }
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
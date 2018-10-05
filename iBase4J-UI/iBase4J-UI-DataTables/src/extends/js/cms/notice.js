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

    var noticeTypes;
    $.ajax({
    	type: 'PUT',
        dataType: 'json',
		contentType:'application/json;charset=UTF-8',
 		url : '/dic/read/list',
 		data: JSON.stringify({type: 'NOTICETYPE'}),
 		success : function(result) {
 			if (result.code == 200) {
 				noticeTypes = result.data;
 			    $(document).trigger('searchDatatables', option);
 			} else {
				error(result.msg);
			}
 		}
    });
    
    // 表格初始化
    var option = {
    	target: '.notice-list',
    	url: '/notice/read/list',
    	params: {keyword: ''},
    	searchable : true,
    	addButton: '<a href="noticeEdit.html" class="btn bg-teal add-btn"> <i class="icon-plus2"></i>新增 </a>',
    	filterHolder: '公告标题/公告类别',
    	columns: [
    	   { "data": "id" },
    	   { "data": "noticeTitle" },
           { "data": "noticeType" },
           { "data": "sendTime" },
           { "data": "infoSources" },
           { "data": "readerTimes" },
           { 'data' : 'status' },
           { 'data' : null }
       ],
       columnDefs: [{
           render: function(v, type, row) {
        	   for(var i=0;i<noticeTypes.length;i++) {
        		   var t = noticeTypes[i];
        		   if(t.code==v) {
        			   return t.codeText;
        		   }
        	   }
        	   return '';
           },
           targets: 2
       }, {
       	render: function(v, type, row) {
       		return v=='1' ? '<span class="label label-info">已发布</span>' : '<span class="label label-default">未发布</span>';
       	},
           targets: 6
       }, {
      	render: function(v, type, row) {
      		return '<ul class="icons-list">'
      		+ '<li class="text-primary-600"><a href="javascript:void(0)" onclick="updateRecord(\'noticeEdit.html\', \''+row.id+'\')" data-toggle="modal" data-target=".noticeEdit" title="编辑"><i '
           	+ 'class="icon-pencil7"></i></a></li>'
    		+ '<li class="text-danger-600"><a href="javascript:void(0)" onclick="deleteRecord(\'/notice\', \''+row.id+'\')" data-popup="tooltip" title="删除"><i '
            + 'class="icon-trash"></i></a></li>li>'
    		+ '</ul>';
       	},
        orderable: false,
           targets: 7
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
    
    // 重新加载
    $(document).on('refreshData', function() {
    	option['refresh'] = true;
    	$(document).trigger('searchDatatables', option);
    });
});
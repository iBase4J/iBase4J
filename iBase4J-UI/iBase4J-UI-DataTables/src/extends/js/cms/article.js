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

    var articleTypes;
    $.ajax({
    	type: 'PUT',
        dataType: 'json',
		contentType:'application/json;charset=UTF-8',
 		url : '/dic/read/list',
 		data: JSON.stringify({type: 'ARTICLETYPE'}),
 		success : function(result) {
 			if (result.code == 200) {
 				articleTypes = result.data;
 			    $(document).trigger('searchDatatables', option);
 			} else {
				error(result.msg);
			}
 		}
    });
    
    // 表格初始化
    var option = {
    	target: '.article-list',
    	url: '/article/read/page',
    	params: {keyword: ''},
    	searchable : true,
    	addButton: '<a href="articleEdit.html" class="btn bg-teal add-btn"> <i class="icon-plus2"></i>新增 </a>',
    	filterHolder: '文章标题/文章内容',
    	columns: [
    	   { "data": "id" },
    	   { "data": "title" },
           { "data": "type" },
           { "data": "createTime" },
           { 'data' : 'enable' },
           { 'data' : null }
       ],
       columnDefs: [{
           render: function(v, type, row) {
        	   for(var i=0;i<articleTypes.length;i++) {
        		   var t = articleTypes[i];
        		   if(t.code==v) {
        			   return t.codeText;
        		   }
        	   }
           },
           targets: 2
       }, {
       	render: function(v, type, row) {
       		return v=='1' ? '<span class="label label-info">显示</span>' : '<span class="label label-default">隐藏</span>';
       	},
           targets: 4
       }, {
      	render: function(v, type, row) {
      		return '<ul class="icons-list">'
      		+ '<li class="text-primary-600"><a href="javascript:void(0)" onclick="updateRecord(\'articleEdit.html\', \''+row.id+'\')" data-toggle="modal" data-target=".editModal" title="编辑"><i '
           	+ 'class="icon-pencil7"></i></a></li>'
    		+ '<li class="text-danger-600"><a href="javascript:void(0)" onclick="deleteRecord(\'/article\', \''+row.id+'\')" data-popup="tooltip" title="删除"><i '
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
    
    // 重新加载
    $(document).on('refreshData', function() {
    	option['refresh'] = true;
    	$(document).trigger('searchDatatables', option);
    });
});
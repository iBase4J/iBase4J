$(function(){
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

    var newsTypes;
    var vm = new Vue({
    	el: '.page-container',
    	data: { newsTypes: [] },
    	methods:{
            getData: function(){
        		var that = this;
        	    $.ajax({
        	    	type: 'GET',
        	 		url : '/dic/read/list',
        	 		data: {type: 'NEWSTYPE'},
        	 		success : function(result) {
        	 			if (result.code == 200) {
        	 				vm.newsTypes = newsTypes = result.rows;
        	 			    $(document).trigger('searchDatatables', option);
        	 			} else {
        					error(result.msg);
        				}
        	 		}
        	    });
            }
    	}
    });
    vm.getData();
    
    // 表格初始化
    var option = {
    	target: '.news-list',
    	url: '/news/read/list',
    	params: {keyword: ''},
    	searchable : true,
    	addButton: '<a href="newsEdit.html" class="btn bg-teal add-btn"> <i class="icon-plus2"></i>新增 </a>',
    	filterHolder: '标题/作者/内容',
    	columns: [
       	       { "data": "id" },
    	       { "data": "newsTitle" },
               { "data": "author" },
               { "data": "editor" },
               { "data": "newsType" },
               { "data": "sendTime" },
               { "data": "readerTimes" },
               { 'data' : 'status' },
               { 'data' : null }
           ],
           columnDefs: [{
               render: function(v, type, row) {
            	   for(var i=0;i<newsTypes.length;i++) {
            		   var t = newsTypes[i];
            		   if(t.code==v) {
            			   return t.codeText;
            		   }
            	   }
            	   return '';
               },
               targets: 4
           }, {
           	render: function(v, type, row) {
           		return v=='1' ? '<span class="label label-info">已发布</span>' : '<span class="label label-default">未发布</span>';
           	},
               targets: 7
           }, {
          	render: function(v, type, row) {
          		return '<ul class="icons-list">'
          		+ '<li class="text-primary-600"><a href="javascript:void(0)" onclick="updateRecord(\'newsEdit.html\',\''+row.id+'\')" data-toggle="modal" data-target=".newsEdit" title="编辑"><i '
               	+ 'class="icon-pencil7"></i></a></li>'
        		+ '<li class="text-danger-600"><a href="javascript:void(0)" onclick="deleteRecord(\'/news\', \''+row.id+'\')" data-popup="tooltip" title="删除"><i '
                + 'class="icon-trash"></i></a></li>li>'
        		+ '</ul>';
           	},
            orderable: false,
               targets: 8
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
    
    $(document).on('click', '.newsType', function() {
    	$('.newsType').removeClass('active');
    	$(this).addClass('active');
    	option['params'] = { newsType: $(this).attr('data-id') };
    	option['refresh'] = true;
    	$(document).trigger('searchDatatables', option);
    });
});
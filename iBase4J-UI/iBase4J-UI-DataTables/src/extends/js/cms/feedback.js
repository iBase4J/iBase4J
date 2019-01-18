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

    var feedbackTypes;
    $.ajax({
    	type: 'GET',
 		url : '/dic/read/list',
 		data: {type: 'FEEDBACKTYPE'},
 		success : function(result) {
 			if (result.code == 200) {
 				feedbackTypes = result.rows;
 				//将获取的数据添加到select选项中
 				$.each(result.rows, function(n, value) {
                    $(".selectbox").data("selectBox-selectBoxIt").add({ value: value.code, text: value.codeText });
               });
 			    $(document).trigger('searchDatatables', option);
 			} else {
				error(result.msg);
			}
 		}
    });
    
    // 表格初始化
    var option = {
    	target: '.feedback-list',
    	url: '/feedback/read/list',
    	params: {keyword: ''},
    	searchable : true,
    	filterHolder: '留言',
    	columns: [{ "data": "userName" },
           { "data": "content" },
           { 'data' : 'type' },
           { "data": "createTime" }
       ],
       columnDefs: [{
           render: function(v, type, row) {
        	   for(var i=0;i<feedbackTypes.length;i++) {
        		   var t = feedbackTypes[i];
        		   if(t.code==v) {
        			   return t.codeText;
        		   }
        	   }
           },
           targets: 2
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
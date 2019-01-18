$(function () {
	// 
    $(".selectbox").selectBoxIt({
        autoWidth: false
    });
    // 通知人员
    $.ajax({
 	   type: 'GET',
 		url: '/user/read/list',
 	   data: {keyword:''},
 	   success: function(result) {
 		   if(result.code == 200) {
 			   var data = result.rows;
 			   for(var i=0;i<rows.length;i++) {
 				   data[i]['id'] = data[i].email;
 				   data[i]['name'] = data[i].userName;
 			   }
 			    $(document).trigger('initTree', {
 			    	parentEle: 'personnelAdd',
 			    	data: data
 			    });
 			    $(document).trigger('initTree', {
 			    	parentEle: 'personnelEdit',
 			    	data: data
 			    });
 		   } else {
 			   error(result.msg);
 		   }
 	   }
    });
    
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
    	target: '.timedTask-list',
    	url: '/scheduled/read/tasks',
    	params: {keyword: ''},
    	select: {
            style: 'os'
        },
    	columns: [{ "data": "taskGroup" },
               { "data": "taskName" },
               { "data": null },
               { "data": "taskCron" },
               { "data": "nextFireTime" },
               { 'data' : 'previousFireTime' },
               { "data": "status" },
               { "data": "contactName" }
       ],
       autoWidth: false,
       bLengthChange: false,
       bFilter: false,
       columnDefs: [{
           orderable: false,
           targets: [2,3,7]
       	 },{
           render: function(data, type, row) {
        	   if(row.targetObject && row.targetMethod)
        		   return  (row.taskType == 'LOCAL' ? '本地' : '远程') + row.targetObject + '.' + row.targetMethod;
        	   return '';
          },
          targets: 2
       },{
           render: function(data, type, row) {
              	switch(data) {
              	case 'NORMAL':
              		return '<span class="label label-info">正常</span>';
              	case 'PAUSED':
              		return '<span class="label label-default">暂停</span>';
              	case 'COMPLETE':
              		return '<span class="label label-success">完成</span>';
              	case 'ERROR':
              		return '<span class="label label-warning">异常</span>';
              	case 'BLOCKED':
              		return '<span class="label label-danger">堵塞</span>';
              	}
              	return '';
         },
         targets: 6
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
        createdRow: function(row, data, index) {
    		 $(row).attr('data-task', JSON.stringify(data));
        }
    };
    $(document).trigger('searchDatatables', option);
    // 重新加载
    $(document).on('refreshData', function() {
    	option['refresh'] = true;
    	$(document).trigger('searchDatatables', option);
    });

    // 表单校验
    var validator = $('#timedTaskAdd,#timedTaskEdit').validate({
    	rules : {
    		taskGroup : {
    			required: true
    		}, taskName : {
    			required: true
    		}, targetObject: {
    			required: true
    		}, targetMethod : {
    			required: true
    		}, jobType: {
    			required: true
    		}, taskType: {
    			required: true
    		}, taskCron : {
    			required: true
    		}
    	}, messages : {
    		taskGroup : {
    			required: '请输入分类'
    		}, taskName : {
    			required: '请输入名称'
    		}, targetObject: {
    			required: '请输入运行对象'
    		}, targetMethod : {
    			required: '请输入运行方法'
    		}, jobType: {
    			required: '请选择执行类型'
    		}, taskType: {
    			required: '请选择执行器类型'
    		}, taskCron : {
    			required: '请输入频率'
    		}
    	}
    });
    $('#reset').on('click', function() {
    	validator && validator.resetForm();
    });
    // 保存
    $('#timedTaskAdd .submit,#timedTaskEdit .submit').on('click', function() {
    	$(document).trigger('submit', {form: $(this).parents('form'), url: '/scheduled', callback: function() {
    		$(document).trigger('refreshData');
    		$('[data-dismiss="modal"]').click();
    	}});
    	return false;
    });
    
    $('.edit-right button').on('click', function(e) {
    	if($('tr.selected').length == 0) {
    		error('请选择一条记录.');
    		e.stopPropagation();
    	} else {
    		switch($(this).attr('data-operate')) {
    		case 'delete':
    			operate('确定删除这条记录?', '/scheduled', 'DELETE', '删除成功.');
    			return;
    		case 'edit':
    	    	var data = JSON.parse($('tr.selected').attr('data-task'));
    	    	$('#timedTaskEdit').autofill(data);
    			return;
    		case 'stop':
    			operate('确定暂停?', '/scheduled/stop', 'POST', '删除成功.');
    			return;
    		case 'start':
    			operate('确定启动?', '/scheduled/start', 'POST', '删除成功.');
    			return;
    		case 'run':
    			operate('确定立即执行?', '/scheduled/run', 'POST', '删除成功.');
    			return;
    		}
    	}
    });
    
    function operate(confirmMsg, url, type, noticeMsg) {
    	var data = JSON.parse($('tr.selected').attr('data-task'));
		var params = {taskGroup : data.taskGroup, taskName: data.taskName };
    	bootbox.confirm(confirmMsg, function(result) {
	        if (result) {
	        	$.ajax({
	    			type : type,
	    			url : url,
	    			data : params,
	    			success : function(result) {
	    				if (result.code == 200) {
	    					notice(noticeMsg);
	    				} else {
	    					error(result.msg);
	    				}
	    				$('.loading').hide();
	    			}
	    	   });
	        }
		});
    }
});
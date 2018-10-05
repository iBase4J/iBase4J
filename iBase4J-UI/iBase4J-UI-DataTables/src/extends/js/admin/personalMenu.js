$(function () {
    //人员列表
	var option = {
    	target: '.personnal-list',
    	url: '/user/read/list',
    	params: {keyword: ''},
    	searchable : true,
    	filterHolder: '帐号/姓名/部门',
    	columns: [
           { "data": "account" },
           { "data": "userName" },
           { "data": "deptName" }
       ],
       autoWidth: false,
       dom: '<"datatable-header"fl><"datatable-scroll-wrap"t><"datatable-footer"ip>',
       language: {
           search: '<span>搜索：</span> _INPUT_',
           lengthMenu: '<span>显示：</span> _MENU_',
           paginate: {'first': 'First', 'last': 'Last', 'next': '&rarr;', 'previous': '&larr;'},
    	   sInfo: "显示第 _START_ - _END_ 条记录 / 共 _TOTAL_ 条记录",
           sInfoEmpty: "没有符合条件的数据."
       },
       select: {
           style: 'os'
       },
       createdRow: function(row, data, index) {
    	   $(row).attr('data-tt-id', data.id).attr('data-tt-name', data.userName);
       }
    };
    $(document).trigger('searchDatatables', option);
    // 重新加载
    $(document).on('refreshData', function() {
    	option['refresh'] = true;
    	$(document).trigger('searchDatatables', option);
    });

    //菜单权限
    var menuOption = {
    	target: '.menu-permission',
    	url: '/menu/read/list',
    	params: {keyword: ''},
        bPaginate: false, //翻页功能
        bLengthChange: false, //改变每页显示数据数量
        bFilter: false, //过滤功能
        bSort: false, //排序功能
        select: {
            style: 'multi'
        },
    	columns: [
                { 'data' : null },
    	       { "data": "menuName" }
       ],
       dom: '<"datatable-header"fBl><"datatable-scroll-wrap"t><"datatable-footer"ip>',
       columnDefs: [{
	    	   orderable: false,
	           className: 'select-checkbox',
	           targets: 0,
	           render: function() {
	        	   return '';
	           }
       }, {
           targets: 1,
        	render: function(data, type, row) {
           		return row.leaf == 0 ? '<span class="folder">'+data+'</span>' : '<span class="file">'+data+'</span>';
           	}
   		}],
       createdRow: function(row, data, index) {
    	   if(data.parentId == 0) {
    		   $(row).attr('data-tt-id', data.id);
    	   } else {
    		   $(row).attr('data-tt-id', data.id).attr('data-tt-parent-id', data.parentId);
    	   }
    	   if(index == 0) {
    		   setTimeout(function() {
    			    //表格树
    			    $(".menu-permission").treetable({
    			        expandable: false,
    			        column: 1
    			    });
    		   }, 200);
    	   }
       }
    };
    $(document).trigger('searchDatatables', menuOption);
    // 重新加载
    $(document).on('refreshMenuData', function() {
    	menuOption['refresh'] = true;
    	$(document).trigger('searchDatatables', menuOption);
    });

    //复选框
    $(".styled, .multiselect-container input").uniform({
        radioClass: 'choice'
    });
    
    // 查询权限
    $('.personnal-list').on('click', 'tbody tr', function() {
    	$('#personalMenu').text('菜单列表');
		$('.menu-permission tr').removeClass('selected');
    	if($(this).hasClass('selected')) {
        	var userId = $(this).attr('data-tt-id');
        	$('#personalMenu').text('人员[' + $(this).attr('data-tt-name') + ']的菜单权限');
        	$.ajax({
        		type: 'PUT',
        		url: '/user/read/menu',
    			data : JSON.stringify({userId : userId}),
    			dataType: 'json',
    		    contentType:'application/json;charset=UTF-8',
    			success : function(result) {
    				if (result.code == 200) {
    					for(var i=0; i<result.rows.length;i++) {
    						$('.menu-permission tr[data-tt-id="' + result.rows[i] + '"]').addClass('selected');
    					}
    				} else {
    					error(result.msg);
    				}
    				$('.loading').hide();
    			}
        	});
    	}
    });
    // saveSet
    $('button.saveSet').on('click', function() {
    	if($('.personnal-list tr.selected').length == 1) {
        	var userId = $('.personnal-list tr.selected').attr('data-tt-id');
    		var data = [ {userId: userId, permission: 'read'} ];
    		$('.menu-permission tr.selected').each(function() {
    			data.push({userId: userId, menuId: $(this).attr('data-tt-id'), permission: 'read'});
    		});
        	$.ajax({
        		type: 'POST',
        		url: '/user/update/menu',
    			data : JSON.stringify(data),
    			dataType: 'json',
    		    contentType:'application/json;charset=UTF-8',
    			success : function(result) {
    				if (result.code == 200) {
    					notice('保存成功.');
    				} else {
    					error(result.msg);
    				}
    				$('.loading').hide();
    			}
        	});
    	} else {
    		error('请选择用户');
    	}
    });

    $(document).on('click', 'button.reflush', function() {
    	$.ajax({
    		type: 'POST',
    		url: '/cache/update',
    		data: JSON.stringify({key: 'Permission'}),
			success : function(result) {
				if (result.code == 200) {
					notice('同步成功.');
					setTimeout(function(){
						location.reload();
					}, 500);
				} else {
					error(result.msg);
				}
			}
    	});
    });
})
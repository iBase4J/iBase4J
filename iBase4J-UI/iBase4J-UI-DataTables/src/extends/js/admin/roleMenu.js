$(function(){
    //角色列表
	var option = {
    	target: '.role-list',
    	url: '/role/read/page',
    	filterHolder: '角色名/部门',
    	params: {keyword: '', permission:1},
    	searchable : true,
    	columns: [
           { "data": "roleName" },
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
    	   $(row).attr('data-tt-id', data.id).attr('data-tt-name', data.roleName);
       }
    };
    $(document).trigger('searchDatatables', option);
    
    // 重新加载
    $(document).on('refreshData', function() {
    	option['refresh'] = true;
    	$(document).trigger('searchDatatables', option);
    });

    //复选框
    $(".styled, .multiselect-container input").uniform({
        radioClass: 'choice'
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
    
    // 查询权限
    $('.role-list').on('click', 'tbody tr', function() {
    	$(this).siblings().removeClass('selected');
    	$(this).toggleClass('selected');
    	$('#roleMenu').text('菜单列表');
		$('.menu-permission tr').removeClass('selected');
    	if($(this).hasClass('selected')) {
        	var roleId = $(this).attr('data-tt-id');
        	$('#roleMenu').text('角色[' + $(this).attr('data-tt-name') + ']的菜单权限');
        	$.ajax({
        		type: 'PUT',
        		url: '/role/read/menu',
    			data : JSON.stringify({roleId : roleId}),
    			dataType: 'json',
    		    contentType:'application/json;charset=UTF-8',
    			success : function(result) {
    				if (result.httpCode == 200) {
    					for(var i=0; i<result.data.length;i++) {
    						$('.menu-permission tr[data-tt-id="' + result.data[i] + '"]').addClass('selected');
    					}
    				} else {
    					error(result.msg);
    				}
    				$('.loading').hide();
    			}
        	});
    	}
    });
    $('.menu-permission').on('click', 'tbody tr', function(e) {
    	$(this).toggleClass('selected');
    	if($(this).hasClass('selected')) {
    		selectedParent($(this));
    		//p.trigger('click');
    	}
    });
    function selectedParent(tr) {
    	var p = $('.menu-permission [data-tt-id='+tr.attr('data-tt-parent-id')+']');
    	if(p.length && !p.hasClass('selected')) {
    		p.addClass('selected');
    		selectedParent(p);
    	}
    }
    // saveSet
    $('button.saveSet').on('click', function() {
    	if($('.role-list tr.selected').length == 1) {
        	var roleId = $('.role-list tr.selected').attr('data-tt-id');
    		var data = [ {roleId: roleId, permission: 'read'} ];
    		$('.menu-permission tr.selected').each(function() {
    			data.push({roleId: roleId, menuId: $(this).attr('data-tt-id'), permission: 'read'});
    		});
        	$.ajax({
        		type: 'POST',
        		url: '/role/update/menu',
    			data : JSON.stringify(data),
    			dataType: 'json',
    		    contentType:'application/json;charset=UTF-8',
    			success : function(result) {
    				if (result.httpCode == 200) {
    					notice('保存成功.');
    				} else {
    					error(result.msg);
    				}
    				$('.loading').hide();
    			}
        	});
    	} else {
    		error('请选择角色');
    	}
    });
    
    $('button.reflush').on('click', function() {
    	$.ajax({
    		type: 'POST',
    		url: '/cache/update',
    		data: JSON.stringify({key: 'Permission'}),
			success : function(result) {
				if (result.httpCode == 200) {
					notice('同步成功.');
					setTimeout(function(){
						location.reload();
					}, 500);
				} else {
					error(result.msg);
    				$('.loading').hide();
				}
			}
    	});
    });
})

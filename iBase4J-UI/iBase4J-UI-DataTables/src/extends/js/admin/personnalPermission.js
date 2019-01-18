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
       bAutoWidth: false,
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
    	   $(row).attr('data-tt-id', data.id).attr('data-tt-name', data.userName).attr('data-tt-permission', data.permission);
       }
    };
    $(document).trigger('searchDatatables', option);
    // 重新加载
    $(document).on('refreshData', function() {
    	option['refresh'] = true;
    	$(document).trigger('searchDatatables', option);
    });
    
    // 
    $('.personnal-list').on('click', 'tbody tr', function() {
    	if($(this).hasClass('selected')) {
        	var userId = $(this).attr('data-tt-id');
        	var hasPermission = $(this).attr('data-tt-permission');
        	var icon = hasPermission ? 'icon-eye' : 'icon-eye-blocked';
        	$('#permissionEdit [name="userId"]').val(userId);
        	$('#personnalPermission').html('用户[' + $(this).attr('data-tt-name')
        			+ ']操作权限 <a href="javascript:void(0)" class="text-success-600" onclick="showPermission(\''
        			+ hasPermission +'\')" title="查看权限"><i class="' + icon + '"></i></a>');
        	getPermissionMenu(userId, '');
    	} else {
        	$('#personnalPermission').text('操作权限');
        	$('#permissionEdit [name="userId"]').val('');
        	$(".permissionValue").val('');
            $(".permissionName").val('');
        	menuTree.checkAllNodes(false);
    	}
    });
    
    // 获取权限
    function getPermissionMenu(userId, permission) {
    	menuTree.checkAllNodes(false);
    	$.ajax({
	    	type: 'GET',
			url: '/user/read/permission',
			data : {userId : userId},
			success : function(result) {
				if (result.code == 200) {
					 var nodes = menuTree.getCheckedNodes(false);
					 for(var i=0; i< result.rows.length;i++) {
						var p = result.rows[i];
						$.each(nodes, function(index, treeNode) {
							if(treeNode.parentId == p.menuId && treeNode.code == p.permission) {
								menuTree.checkNode(treeNode, true, true);
							}
						});
					}
				} else {
					error(result.msg);
				}
				$('.loading').hide();
			}
	    });
    }
    
    var setting = {
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        check: {
        	enable: true,
        	chkboxType : {
        		"Y" : "ps",
        		"N" : "ps"
        	}
        }
    };
    
    var menuTree;
    $.ajax({
	   type: 'GET',
	   url: '/menu/read/tree',
	   data: {keyword: ''},
	   success: function(result) {
		   if(result.code == 200) {
			   var data = result.rows;
			   for(var i=0;i<data.length;i++) {
				   data[i]['pId'] = data[i].parentId;
				   data[i]['name'] = data[i].menuName;
			   }
			   menuTree = $.fn.zTree.init($('#menuTree'), setting, data);
		   } else {
			   error(result.msg);
		   }
	   }
   });
        
    // saveSet
    $('button.saveSet').on('click', function() {
    	if($('.personnal-list tr.selected').length == 1) {
    		var userId = $('#permissionEdit [name="userId"]').val();
    		var data = [ ];
    		var nodes = menuTree.getCheckedNodes(true);
			if(nodes.length > 0) {
	    		$.each(nodes, function(index, treeNode) {
	    			if(treeNode.code) {
		    			data.push({ userId: userId, menuId: treeNode.parentId, permission: treeNode.code });
	    			}
	    		});
			} else {
				data.push({ userId: userId });
			}
        	$.ajax({
        		type: 'POST',
        		url: '/user/update/permission',
    			data : data,
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
    		data: {key: 'Permission'},
			success : function(result) {
				if (result.code == 200) {
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

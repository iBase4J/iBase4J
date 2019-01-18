$(function () {
    //部门树高度
    $('.department').height('450px');
    $('.departmentInfo .panel-body,.departmentAdd .panel-body,.departmentEdit .panel-body').height('450px');

    /**
     * 部门树结构
     */
    var setting = {
        view: {
            addHoverDom: addHoverDom, //当鼠标移动到节点上时，显示用户自定义控件
            removeHoverDom: removeHoverDom//离开节点时的操作
        },
        check: {
            enable: false,
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        edit: {
            enable: true
        },
        treeNode: {
            checked: false
        },

        callback: {
            onClick: zTreeOnClick, //单击事件
            beforeEditName: editDom, //编辑
            beforeRemove: deleteDom
        }
    };

    //新增职位
    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span"); //获取节点信息
        if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;

        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' data-id='"
        	+ treeNode.id + "' title='新增' onfocus='this.blur();'></span>"; //定义添加按钮
        sObj.after(addStr); //加载添加按钮
        var btn = $("#addBtn_" + treeNode.tId);

        //添加部门
        if (btn) btn.bind("click", function (event) {
            var zTree = $.fn.zTree.getZTreeObj("tree");
            $('.departmentAdd form input,select,textarea').removeAttr('disabled').val('');
            $('.departmentAdd form input[name="parentId"]').val($(this).attr('data-id'));
            $('.departmentAdd form input[name="parentName"]').val(treeNode.deptName);
            $('.departmentAdd').show();
            $('.departmentInfo,.departmentEdit').hide();
            event.stopPropagation();
        });
    };

    function removeHoverDom(treeId, treeNode) {
    	$("#addBtn_" + treeNode.tId).remove();
    };

    //点击部门显示对应信息
    function zTreeOnClick(event, treeId, treeNode) {
        $('#departmentInfo [name="unitId"]').val(treeNode.unitId);
        $('#departmentInfo .unitName').val(treeNode.unitName);
        $('#departmentInfo').autofill(treeNode);
        $('.departmentInfo').show();
        $('.departmentAdd,.departmentEdit').hide();
        $('.departmentInfo form input,select').attr("disabled", 'disabled');
    }

    //编辑部门信息
    function editDom(treeId, treeNode) {
        $('#departmentEdit [name="unitId"]').val(treeNode.unitId);
        $('#departmentEdit .unitEditName').val(treeNode.unitName);
        $('.departmentInfo form input,select,textarea').removeAttr('disabled');
        $('#departmentEdit').autofill(treeNode);
        $('.departmentEdit').show();
        $('.departmentAdd,.departmentInfo').hide();
        return false;
    }
    function deleteDom(treeId, treeNode) {
    	deleteRecord('/dept', treeNode.id);
    	return false;
    }
    var units = {};
    function initDeptTree() {
        $.ajax({
    	   type: 'GET',
    	   data: {keyword:''},
    	   url: '/dept/read/list',
    	   success: function(result) {
    		   if(result.code == 200) {
    			   var data = result.rows;
    			   for(var i=0;i<data.length;i++) {
    				   data[i]['open'] = true;
    				   data[i]['pId'] = data[i].parentId;
    				   data[i]['name'] = data[i].deptName;
    				   data[i]['unitName'] = units[data[i].unitId];
    			   }
    		       $.fn.zTree.init($("#departmentTree"), setting, data);
				    $(document).trigger('initTree', {
				    	parentEle: 'deptAdd',
				    	data: data
				    });
				    $(document).trigger('initTree', {
				    	parentEle: 'deptEdit',
				    	data: data
				    });
    		   }
    		   $('.loading').hide();
    	   }
     	});
    }
    // 重新加载
    $(document).on('refreshData', function() {
        initDeptTree();
    });
    
    //隶属单位
    $.ajax({
	   type: 'GET',
		url: '/unit/read/list',
	   data: {keyword:''},
	   success: function(result) {
		   if(result.code == 200) {
			   var data = result.rows;
			   for(var i=0;i<data.length;i++) {
				   data[i]['open'] = true;
				   data[i]['name'] = data[i].unitName;
				   units[data[i]['id']] = data[i]['name'];
			   }
			    initDeptTree();
			    $(document).trigger('initTree', {
			    	parentEle: 'unitAdd',
			    	checkParent: true,
			    	data: data
			    });
			    $(document).trigger('initTree', {
			    	parentEle: 'unitEdit',
			    	checkParent: true,
			    	data: data
			    });
		   } else {
			   error(result.msg);
		   }
	   }
   });
    var validator = $('#departmentAdd,#departmentEdit').validate({
    	rules : {
    		deptName : {
    			required: true
    		}, unitName : {
    			required: true
    		}
    	}, messages : {
    		deptName : {
    			required: '请输入部门名称'
    		}, unitName : {
    			required: '请选择隶属单位'
    		}
    	}
    });
    $('#reset').on('click', function() {
    	validator && validator.resetForm();
    });
    $('#departmentAdd .submit,#departmentEdit .submit').on('click', function() {
    	$(document).trigger('submit', {form: $(this).parents('form'), url: '/dept', callback: function() {
    		initDeptTree();
    	}});
    });
})

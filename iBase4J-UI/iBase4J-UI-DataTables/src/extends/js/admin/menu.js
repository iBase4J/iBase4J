$(function () {
    //菜单列表
    var option = {
    	target: '.menuManage',
    	url: '/menu/read/list',
    	params: {keyword: ''},
        bPaginate: false, //翻页功能
        bLengthChange: false, //改变每页显示数据数量
        bFilter: false, //过滤功能
        bSort: false, //排序功能
        select: {
            style: 'os'
        },
    	columns: [
    	       { "data": "menuName" },
               { "data": "request" },
               { "data": "iconcls" },
               { "data": "isShow" },
               { "data": "sortNo" },
               { 'data' : null }
       ],
       dom: '<"datatable-header"fBl><"datatable-scroll-wrap"t><"datatable-footer"ip>',
       columnDefs: [{
	         	render: function(data, type, row) {
	           		return row.leaf == 0 ? '<span class="folder">'+data+'</span>' : '<span class="file">'+data+'</span>';
	           	},
	           targets: 0
       		}, {
	          	render: function(data, type, row) {
	           		return data=='1'?'<span class="label label-info">启用</span>' : '<span class="label label-default">禁用</span>';
	           	},
               targets: 3
           }, {
        	   orderable: false,
        	   render: function(data, type, row) {
		       		return '<ul class="icons-list">'
		               + '<li class="text-primary-600"><a href="javascript:void(0)" data-toggle="modal" data-target="#menuEdit" onclick="update(\''+row.id+'\')" data-popup="tooltip" title="编辑"><i '
		               + 'class="icon-pencil7"></i></a></li>'
		       		+ '<li class="text-danger-600"><a href="javascript:void(0)" onclick="deleteRecord(\'/menu\', \''+row.id+'\')" data-popup="tooltip" title="删除"><i '
		               + 'class="icon-trash"></i></a></li>li>'
		       		+ '</ul>';
	       		},
	       		targets: 5
           }
       ], createdRow: function(row, data, index) {
    	   $(row).attr('data-tt-id', data.id).attr('data-tt-name', data.menuName);
    	   $(row).attr('data-tt-parent-id', data.parentId).attr('data-tt-parent-name', data.parentName);
    	   if(index == 0) {
    		   setTimeout(function() {
	   	    	    // 表格树
	   	    		$(".menuManage").treetable({
	   	    	        expandable: false
	   	    	    });
    		   }, 200);
    	   }
       }
    };
    $(document).trigger('searchDatatables', option);

    //复选框
    $(".styled, .multiselect-container input").uniform({
        radioClass: 'choice'
    });
    $(document).on('refreshData', function() {
    	location.reload();
    });
    //
    $('.menu-add button:eq(0)').on('click', function() {
    	$('#menuEdit form')[0].reset();
    	$('#menuEdit .modal-title').text('新增菜单');
    	$('#editMenu [name="parentId"]').val(0);
    	$('#editMenu [name="parentName"]').val('顶级菜单');
    });
    $('.menu-add button:eq(1)').on('click', function(e) {
    	if($('tr.selected').length == 0) {
    		error('请选择上级菜单');
    		e.stopPropagation();
    	}
    	$('#menuEdit form')[0].reset();
    	$('#menuEdit .modal-title').text('新增菜单');
    	$('#editMenu [name="parentId"]').val($('tr.selected').attr('data-tt-id'));
    	$('#editMenu [name="parentName"]').val($('tr.selected').attr('data-tt-name'));
    });
    // 表单校验
    var validator = $('#editMenu').validate({
    	rules : {
    		menuName : {
    			required: true
    		}, request : {
    			required: true
    		}, parentId: {
    			required: true
    		}
    	}, messages : {
    		menuName : {
    			required: '请输入菜单名称'
    		}, request : {
    			required: '请输入URL地址'
    		}, parentId: {
    			required: '请选择上级菜单'
    		}
    	}
    });
    $('#reset').on('click', function() {
    	validator && validator.resetForm();
    });
    // 保存
    $('#editMenu .submit').on('click', function() {
    	$(document).trigger('submit', {form: $('#editMenu'), url: '/menu', href: 'menu.html'});
    });
});

function update(id) {
	$('#menuEdit .modal-title').text('修改菜单');
	loadData('/menu/read/detail', {id : id}, function(data) {
		$('#editMenu').autofill(data);
		if(data.isShow == 'true') {
			$('#editMenu [name="isShow"]').get(0).checked = true;
			$('#editMenu [name="isShow"]').parents('span').addClass('checked');
		} else {
			$('#editMenu [name="isShow"]').get(0).checked = false;
			$('#editMenu [name="isShow"]').parents('span').removeClass('checked');
		}
	 });
}

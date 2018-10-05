$(function () {
    $.ajax({
    	type: 'PUT',
        dataType: 'json',
		contentType:'application/json;charset=UTF-8',
 		url : '/dic/read/list',
 		data: JSON.stringify({type: 'NOTICETYPE'}),
 		success : function(result) {
 			if (result.code == 200) {
 				//将获取的数据添加到select选项中
 				$.each(result.rows, function(n, value) {
                    $(".selectbox").data("selectBox-selectBoxIt").add({ value: value.code, text: value.codeText });
               });
 			    $(document).trigger('refreshData');
 			} else {
				error(result.msg);
			}
 		}
    });
    function init() {
        var id = localStorage.getItem('id');
        if(id) {
	        $.ajax({
	        	type: 'PUT',
	        	url: '/notice/read/detail',
	        	data: JSON.stringify({id: id}),
	    		dataType: 'json',
	    	    contentType:'application/json;charset=UTF-8',
	    		success : function(result) {
	    			if (result.code == 200) {
	    				$('#noticeEdit').autofill(result.data);
	    				$(".selectbox").data("selectBox-selectBoxIt").setOption(result.data.noticeType);
	    				$('.summernote').summernote('code', result.data.content);
	    				if(result.data.status == '1') {
	    					$('#noticeEdit [name="status"]').get(0).checked = true;
	    					$('#noticeEdit [name="status"]').parents('span').addClass('checked');
	    				} else {
	    					$('#noticeEdit [name="status"]').get(0).checked = false;
	    					$('#noticeEdit [name="status"]').parents('span').removeClass('checked');
	    				}
	    			} else {
	    				error(result.msg);
	    			}
	    			$('.loading').hide();
	    		}
	        });
        } else {
        	$('#reset').click();
    		$('.loading').hide();
        }
    }
    
    // 重新加载
    $(document).on('refreshData', function() {
    	 init()
    });
    //富文本编辑器
    $('.summernote').summernote({
        height: 300,
        lang: 'zh-CN',
        callbacks: {  
            onImageUpload: function(files) { //the onImageUpload API  
            	data = new FormData();  
                data.append("file", files[0]);  
                $.ajax({  
                    data: data,
                    type: "POST",
                    url: "/upload/image",
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function(result) {
            			if (result.code === 200) {
                            $(".summernote").summernote('insertImage', result.fileNames[0], 'image'); // the insertImage API  
            			} else {
            				error(result.msg);
            			}
            			$('.loading').hide();
                    }
                });
            }
        }
    });

    //下拉框插件
    $(".selectbox").selectBoxIt({
        autoWidth: false
    });
    //复选框
    $(".styled, .multiselect-container input").uniform({
        radioClass: 'choice'
    });

    //时间插件
    $('.date-picker').datepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
    }).on('changeDate', function () {
        $(this).datepicker('hide');
    });
    
    var validator = $('#noticeEdit').validate({
    	rules : {
    		noticeTitle : {
    			required: true
    		}, noticeType : {
    			required: true
    		}, sendTime : {
    			required: true
    		}
    	}, messages : {
    		noticeTitle : {
    			required: '请输入公告标题'
    		}, noticeType : {
    			required: '请选择公告类别'
    		}, sendTime : {
    			required: '请输入发布时间'
    		}
    	}
    });
    $('#reset').on('click', function() {
    	validator && validator.resetForm();
    });
    // 保存用户信息
    $('#noticeEdit .submit').on('click', function() {
    	$('[name="content"]').val($('.summernote').summernote('code'));
    	$(document).trigger('submit', {form: $('#noticeEdit'), url: '/notice', href: 'notice.html'});
    });
});
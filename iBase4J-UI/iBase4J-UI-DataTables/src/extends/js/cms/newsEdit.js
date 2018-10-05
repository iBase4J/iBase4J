$(function(){
	$.ajax({
    	type: 'PUT',
        dataType: 'json',
		contentType:'application/json;charset=UTF-8',
 		url : '/dic/read/list',
 		data: JSON.stringify({type: 'NEWSTYPE'}),
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
    // 重新加载
    $(document).on('refreshData', function() {
    	init();
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
    function init() {
        var id = localStorage.getItem('id');
        if(id) {
        	$('.OPT').text('编辑');
	        $.ajax({
	        	type: 'PUT',
	        	url: '/news/read/detail',
	        	data: JSON.stringify({id: id}),
	    		dataType: 'json',
	    	    contentType:'application/json;charset=UTF-8',
	    		success : function(result) {
	    			if (result.code == 200) {
	    				$('#newsEdit').autofill(result.data);
	    				$(".selectbox").data("selectBox-selectBoxIt").setOption(result.data.newsType);
	    				$('.summernote').summernote('code', result.data.content);
	    				if(result.data.status == '1') {
	    					$('#newsEdit [name="status"]').get(0).checked = true;
	    					$('#newsEdit [name="status"]').parents('span').addClass('checked');
	    				} else {
	    					$('#newsEdit [name="status"]').get(0).checked = false;
	    					$('#newsEdit [name="status"]').parents('span').removeClass('checked');
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

    var validator = $('#newsEdit').validate({
    	rules : {
    		newsTitle : {
    			required: true
    		}, newsType : {
    			required: true
    		}, sendTime : {
    			required: true
    		}, author : {
    			required: true
    		}, editor : {
    			required: true
    		}
    	}, messages : {
    		newsTitle : {
    			required: '请输入标题'
    		}, newsType : {
    			required: '请选择栏目'
    		}, sendTime : {
    			required: '请输入发布时间'
    		}, author : {
    			required: '请输入作者'
    		}, editor : {
    			required: '请输入编辑'
    		}
    	}
    });
    $('#reset').on('click', function() {
    	validator && validator.resetForm();
    });
    // 保存用户信息
    $('#newsEdit .submit').on('click', function() {
    	$('[name="content"]').val($('.summernote').summernote('code'));
    	$(document).trigger('submit', {form: $('#newsEdit'), url: '/news', href: 'news.html'});
    	return false;
    });
});
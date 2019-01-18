$(function () {
    $.ajax({
    	type: 'GET',
 		url : '/dic/read/list',
 		data: {type: 'ARTICLETYPE'},
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
            	type: 'GET',
            	url: '/article/read/detail',
            	data: {id: id},
        		success : function(result) {
        			if (result.code == 200) {
        				$('#editModal').autofill(result.data);
        				$(".selectbox").data("selectBox-selectBoxIt").setOption(result.data.type);
        				$('.summernote').summernote('code', result.data.content);
        				if(result.data.enable == '1') {
        					$('#editModal [name="enable"]').get(0).checked = true;
        					$('#editModal [name="enable"]').parents('span').addClass('checked');
        				} else {
        					$('#editModal [name="enable"]').get(0).checked = false;
        					$('#editModal [name="enable"]').parents('span').removeClass('checked');
        				}
        				if(result.data.isTop == '1') {
        					$('#editModal [name="isTop"]').get(0).checked = true;
        					$('#editModal [name="isTop"]').parents('span').addClass('checked');
        				} else {
        					$('#editModal [name="isTop"]').get(0).checked = false;
        					$('#editModal [name="isTop"]').parents('span').removeClass('checked');
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
    
    var validator = $('#editModal').validate({
    	rules : {
    		title : {
    			required: true
    		}, type : {
    			required: true
    		}
    	}, messages : {
    		title : {
    			required: '请输入文章标题'
    		}, type : {
    			required: '请选择文章类别'
    		}
    	}
    });
    $('#reset').on('click', function() {
    	validator && validator.resetForm();
    });
    // 保存用户信息
    $('#editModal .submit').on('click', function() {
    	$('[name="content"]').val($('.summernote').summernote('code'));
    	$(document).trigger('submit', {form: $('#editModal'), url: '/article', href: 'article.html'});
    });
});
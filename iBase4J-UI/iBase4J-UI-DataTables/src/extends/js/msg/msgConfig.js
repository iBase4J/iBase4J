$(function () {
  //获取数据
  function init() {
      $.ajax({
      	type: 'GET',
      	url: '/msgConfig/read/list',
      	data: {pageSize: 1},
  		success : function(result) {
  			if (result.code == 200) {
  				if(result.rows.length > 0) {
  	  				var data = result.rows[0];
  	  				$('#msgConfigEdit').autofill(data);
  	  				var k = ['orderIsSend', 'payIsSend', 'sendGoodsIsSend', 'registIsSend', 'adviceGoodsIsSend'];
  	  				for(var i=0; i<k.length;i++) {
  		  				if($('[name="' + k[i] + '"][value="' + data[k[i]] + '"]').length > 0) {
  		        			$('[name="' + k[i] + '"][value="' + data[k[i]] + '"]').get(0).checked = true;
  		        			$('[name="' + k[i] + '"][value="' + data[k[i]] + '"]').parents('span').addClass('checked');
  		  				}
  	  				}
  				}
  			} else {
  				error(result.msg);
  			}
    		$('.loading').hide();
  		}
      });
  }
  
    // 重新加载
    $(document).on('refreshData', function() {
    	init();
    });
	$(document).trigger('refreshData');

    //复选框
    $(".styled, .multiselect-container input").uniform({
        radioClass: 'choice'
    });

    var validator = $('#msgConfigEdit').validate({
    	rules : {
    		smsPlatUrl : {
    			required: true
    		}, smsPlatAccount : {
    			required: true
    		}, smsPlatPassword : {
    			required: true
    		}, senderName : {
    			required: true
    		}
    	}, messages : {
    		smsPlatUrl : {
    			required: '请输入短信平台地址'
    		}, smsPlatAccount : {
    			required: '请选择短信平台帐号'
    		}, smsPlatPassword : {
    			required: '请输入短信平台密码'
    		}, senderName : {
    			required: '短信签名'
    		}
    	}
    });
    $('#reset').on('click', function() {
    	validator && validator.resetForm();
    });
    // 保存用户信息
    $('#msgConfigEdit .submit').on('click', function() {
    	$('[name="content"]').val($('.summernote').summernote('code'));
    	$(document).trigger('submit', {form: $('#msgConfigEdit'), url: '/msgConfig', callback: function() {
    		$(document).trigger('refreshData');
    	}});
    });
});
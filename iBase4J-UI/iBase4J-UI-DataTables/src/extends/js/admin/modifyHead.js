$(function() {
	function init() {
		$.ajax({
			url : '/user/read/current',
			success : function(result) {
				if (result.code == 200) {
					if (result.data.avatar) {
						$('img.avatar').attr('src', result.data.avatar);
					}
				} else {
					error(result.msg);
				}
				$('.loading').hide();
			}
		});
	}

	init();

	$(document).on('refreshData', function() {
		init();
	});
	
	$('.avatar-form').on('submit', function() {
		window.submitDone = function(result) {
			if (result.data.avatar) {
				$('img.avatar').attr('src', result.data.avatar);
			}
			notice('修改头像成功.');
			if(window.parent) {
				window.parent.document.location.reload();
			} else {
				location.href = '/main/welcome.html';
			}
    		$('.loading').hide();
		}
		return false;
	});
});
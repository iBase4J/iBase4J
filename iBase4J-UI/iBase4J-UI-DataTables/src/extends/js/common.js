$(function () {
    //当前系统时间
    (function(){
		setInterval(show_cur_times, 100);

        function show_cur_times() {
        	if(document.getElementById("showtimes")) {
	            //获取当前日期
	            var date_time = new Date();
	            //定义星期
	            var week;
	            //switch判断
	            switch (date_time.getDay()) {
	                case 1:
	                    week = "星期一";
	                    break;
	                case 2:
	                    week = "星期二";
	                    break;
	                case 3:
	                    week = "星期三";
	                    break;
	                case 4:
	                    week = "星期四";
	                    break;
	                case 5:
	                    week = "星期五";
	                    break;
	                case 6:
	                    week = "星期六";
	                    break;
	                default:
	                    week = "星期天";
	                    break;
	            }
	
	            //年
	            var year = date_time.getFullYear();
	            //判断小于10，前面补0
	            if (year < 10) {
	                year = "0" + year;
	            }
	
	            //月
	            var month = date_time.getMonth() + 1;
	            //判断小于10，前面补0
	            if (month < 10) {
	                month = "0" + month;
	            }
	
	            //日
	            var day = date_time.getDate();
	            //判断小于10，前面补0
	            if (day < 10) {
	                day = "0" + day;
	            }
	
	            //时
	            var hours = date_time.getHours();
	            //判断小于10，前面补0
	            if (hours < 10) {
	                hours = "0" + hours;
	            }
	
	            //分
	            var minutes = date_time.getMinutes();
	            //判断小于10，前面补0
	            if (minutes < 10) {
	                minutes = "0" + minutes;
	            }
	
	            //秒
	            var seconds = date_time.getSeconds();
	            //判断小于10，前面补0
	            if (seconds < 10) {
	                seconds = "0" + seconds;
	            }
	
	            //拼接年月日时分秒
	            var date_str = year + "年" + month + "月" + day + "日 " + hours + ":" + minutes + ":" + seconds;
	
	            //显示在id为showtimes的容器里
	            document.getElementById("showtimes").innerHTML = date_str;
        	}
    	}
    })();

    // loading
    $('.page-container').append('<div class="loading"><div class="loading_bg"></div>'
    		+'<div class="loading_mean"><img src="../../extends/img/loading.gif" width="25"></div>'
    		+'</div>');
    
    // 全文搜索
	$(document).on('keyup', '#allSearch', function(e) {
		var k = $(this).val();
    	if(e.keyCode == 13 && k.length > 0) {
    		localStorage.setItem('key', k);
    	}
    });
   
   // 查询数据
   $(document).on('searchDatatables',function(e, opt) {
	   var option = {
		   bAutoWidth: false,
		   bDestroy: true,
		   bSort: false,
           bStateSave : true, 
           sAjaxSource : opt.url,
           bServerSide : true,
           searching: false,
           fnServerParams : function(aoData) {
        	   var sEcho,start,pageSize;
        	   for(var i=0;i<aoData.length;i++) {
        		   switch(aoData[i].name) {
        		   case 'sEcho':
        			   sEcho = aoData[i].value;
        			   break;
        		   case 'iDisplayStart':
        			   start = aoData[i].value;
        			   break;
        		   case 'iDisplayLength':
        			   pageSize = aoData[i].value;
        			   break;
        		   }
        	   }
        	   var params = $.extend(opt.params, {
                	sEcho: sEcho,
                	pageSize: pageSize,
                	pageIndex: (start / pageSize) + 1,
                	keyword: $('.dataTables_filter input[type="search"]').val()
                });
	            aoData.push({  
	                name : "params",
	                value : params
	            });
           },
           fnServerData : function(sSource, aoData, fnCallback, oSettings ) {
               $.ajax({
                   type : 'GET',
                   url : sSource,
                   data : aoData[aoData.length-1].value,
                   success : function(resp) {
                	   if(resp.code == 200) {
                		   resp.data || (resp.data = resp.rows);
                		   resp.recordsTotal || (resp.recordsTotal = resp.total);
                		   resp.recordsFiltered || (resp.recordsFiltered = resp.total);
                           fnCallback(resp);
                	   } else {
                		   error(resp.msg);
                	   }
               		   $('.loading').hide();
                   }
               });
           }, language: {
        	   sInfo: "显示第 _START_ - _END_ 条记录 / 共 _TOTAL_ 条记录",
               sInfoEmpty: "没有符合条件的数据.",
               sEmptyTable: '没有可显示的数据.',
               sZeroRecords: '没有可显示的数据.',
               sInfoFiltered: ''
           }
       };
	   option = $.extend(option, opt);
	   var table = $(opt.target).DataTable(option);
	   if(opt.searchable) {
		   // 搜索框
		   $('.datatable-header').each(function() {
			   if($(this).parent().find(opt.target).length > 0) {
				   $(this).prepend('<div class="dataTables_filter"><label><span>搜索：</span> <input type="search" class=""></label></div>');
				   // 搜索事件
				   $(this).find('.dataTables_filter input[type="search"]').keyup(function(e) {
			 		   if(e.keyCode == 13) {
			 			  table.search($(this).val()).draw();
			 		   }
			 	   });
			   }
		   });
	   }
   		// Enable Select2 select for the length option
	    $('.dataTables_length select').select2({
	        minimumResultsForSearch: Infinity,
	        width: 'auto'
	    });
	    // Add placeholder to the datatable filter option
	    $('.dataTables_filter input[type=search]').attr('placeholder', opt.filterHolder);

	    if(opt.addButton) { //添加新增按钮
	    	$('.dataTables_filter').prepend(opt.addButton);
	    }
	    if(opt.callback) {
	    	opt.callback();
	    }
   });

   // 
    window.showPermission = function(data) {
		var d = data.split(';');
		var p = '<div class="panel panel-white">'
			+ '<div class="panel-heading"><h5 class="panel-title text-bold">拥有权限</h5></div>'
			+ '<div class="panel-body">';
		for(var i=0;i<d.length;i++) {
			p += '<span class=" label label-info list-group-item-text">' + d[i] + '</span> ';
		}
		p += '</div></div>';
		bootbox.alert({
			message: p,
		    size: 'large'
		});
   }
    
   // 加载数据
   	window.loadData = function(url, params, callback) {
	   $.ajax({
			url: url,
			type : "GET",
			data : params ? params : '',
			success : function(result) {
				if (result.code == 200) {
					callback && callback(result.rows || result.data);
				} else {
					error(result.msg);
				}
	    		$('.loading').hide();
			}
	   })
   }
   
   // 修改数据
   window.updateRecord = function(href, id) {
	   localStorage.setItem('id', id);
	   location.href = href;
   };
   
   // 删除数据
   window.deleteRecord = function(url, id) {
	   bootbox.confirm("确定删除这条记录?", function(result) {
	        if (result) {
	        	$.ajax({
	    			type : "DELETE",
	    			url : url,
	    			data : {id : id},
	    			success : function(result) {
	    				if (result.code == 200) {
	    					notice('删除成功.');
	    					$(document).trigger('refreshData');
	    				} else {
	    					error(result.msg);
	    				}
	            		$('.loading').hide();
	    			}
	    	   });
	        }
	  });
   };

   // 图片预览
   $(document).on('click', 'img.view', function() {
	   var t = $(this).attr('title');
	   bootbox.dialog({ title: t ? t : '', message: '<div class="text-center"><img src="' + $(this).attr('src') + '" style="max-width:97%"></div>'});
   });
   
   // 保存信息
   $(document).on('submit', function(e, opt) {
	   if($(opt.form).valid()) {
		   if(opt.pre) {
			   opt.pre();
		   }
		   var params = $(opt.form).serializeObject();
           params.password && (params.password = hex_md5(params.password));
		   $.ajax({
	   			url : opt.url,
	   			type: 'POST',
	   			data: params
	   		}).then(function(result) {
	 	   		if (result.code == 200) {
					notice('保存成功.');
					if(opt.callback) {
						opt.callback();
					}
					if(opt.href) {
						location.href = opt.href;
					}
				} else {
					error(result.msg);
				}
	    		$('.loading').hide();
	   		});
	   }
   });
   
   // 初始化树形
   $(document).on('initTree', function(e, option){
       var setting = {
           view: {
               dblClickExpand: false
           },
           data: {
               simpleData: {
                   enable: true
               }
           },
           callback: {
               beforeClick: beforeClick,
               onClick: onClick
           }
       };

       function beforeClick(treeId, treeNode) {
    	   if(option.checkParent) {
    		   return true;
    	   }
           var check = (treeNode && !treeNode.isParent);
           return check;
       }

       function onClick(e, treeId, treeNode) {
           var zTree = $.fn.zTree.getZTreeObj(option.parentEle + 'Tree'),
               nodes = zTree.getSelectedNodes(),
               v = "", n="";
           nodes.sort(function compare(a, b) {
               return a.id - b.id;
           });
           for (var i = 0, l = nodes.length; i < l; i++) {
               v += nodes[i].id + ",";
               n += nodes[i].name + ",";
           }
           if (v.length > 0) v = v.substring(0, v.length - 1);
           if (n.length > 0) n = n.substring(0, n.length - 1);
           $("." + option.parentEle + "Value").val(v);
           $("." + option.parentEle + "Name").val(n);
           if(option.callback) {
        	   option.callback(v);
           }
       }

       $('.' + option.parentEle + 'Btn').click(function () {
           $("." + option.parentEle + "Content").show();
           $("body").bind("mousedown", onBodyDown);
       })

       function hideMenu() {
           $("." + option.parentEle + "Content").fadeOut("fast");
           $("body").unbind("mousedown", onBodyDown);
       }

       function onBodyDown(event) {
           if (!(event.target.class == option.parentEle + "Btn"
        		   || event.target.class == option.parentEle + "Content"
        		   || $(event.target).parents("." + option.parentEle + "Content").length > 0)) {
               hideMenu();
           }
       }
       
       if(option.url) {
           $.ajax({
     		   type: 'GET',
     		   url: option.url,
     		   data: option.data,
     		   success: function(result) {
     			   if(result.code == 200) {
     				   var data = option.resetData && option.resetData(result.rows) || result.rows;
     				   var tree = $.fn.zTree.init($('#' + option.parentEle + 'Tree'), setting, data);
     		    	   $(document).on('click', '[data-dismiss="modal"]', function() {
     		    		   tree.refresh();
     		    	   });
     			   } else {
    				   error(result.msg);
    			   }
     		   }
     	   });
       } else if(option.data) {
    	   var tree = $.fn.zTree.init($('#' + option.parentEle + 'Tree'), setting, option.data);
    	   $(document).on('click', '[data-dismiss="modal"]', function() {
    		   tree.refresh();
    	   });
       } else {
		   error('配置错误');
	   }
   });

   $(document).on('initUploader', function(e, opt){
		//实例化一个plupload上传对象
	    var uploader = new plupload.Uploader({
			container : opt.button + 'Container',//文件上传容器
	        browse_button : opt.button, //触发文件选择对话框的按钮，为那个元素id
	        url : '/upload/temp/image', //服务器端的上传页面地址
	        flash_swf_url : 'files/Moxie.swf', //swf文件，当需要使用swf方式进行上传时需要配置该参数
	        silverlight_xap_url : 'files/Moxie.xap', //silverlight文件，当需要使用silverlight方式进行上传时需要配置该参数
	        multi_selection : false,//是否可以在文件浏览对话框中选择多个文件，true为可以，false为不可以。默认true，即可以选择多个文件。
	        max_file_size : '10mb',
	        filters : [{
				title : 'Image files',
				extensions : 'jpg,gif,png,jpeg,bmp'
			}]
	    });
	    //在实例对象上调用init()方法进行初始化
	    uploader.init();
	    uploader.bind('PostInit',function(uploader){
	    });
	    //绑定各种事件，并在事件监听函数中做你想做的事
	    uploader.bind('FilesAdded',function(uploader,files){
	        //每个事件监听函数都会传入一些很有用的参数，
	        //我们可以利用这些参数提供的信息来做比如更新UI，提示上传进度等操作
	    	uploader.refresh();
			$('.loading').show();
			uploader.start(); //调用实例对象的start()方法开始上传文件，当然你也可以在其他地方调用该方法
	    });
	    uploader.bind('UploadProgress', function(up, file) {//上传进度
		});
	    uploader.bind('Error', function(up, err) {//出现错误
			Util.dialog.tips(err.message);
			up.refresh();
			$('.loading').hide();
		});
	    uploader.bind('FileUploaded', function(up, file, info) {//上传完毕
			$('#' + opt.button + 'Container span').text(file.name);
	    	uploader.destroy();
	    	$(document).trigger('initUploader', opt);
			var response = JSON.parse(info.response);
			if (response.code === 200) {
				$('[name="' + opt.target + '"]').val(response.fileNames[0]);
			} else {
				error(response.msg);
			}
			$('.loading').hide();
		});
	});
   
   $(document).on('initSelectOption', function(e, opt) {
		$.ajax({
	    	type: 'GET',
	 		url : '/dic/read/list',
	 		data: opt.param,
	 		success : function(result) {
	 			if (result.code == 200) {
 					$.each(opt.target.split(','), function(n, v) {
		 				$(v).data("selectBox-selectBoxIt").remove();
	                    $(v).data("selectBox-selectBoxIt").add({ value: '', text: '-请选择-' });
		 				//将获取的数据添加到select选项中
		 				$.each(result.rows, function(n, value) {
	                        $(v).data("selectBox-selectBoxIt").add({ value: value.code, text: value.codeText });
	 					});
                   });
	 			} else {
					error(result.msg);
				}
	 			if(opt.next) {
	 				setTimeout(function() {
	 					$(document).trigger('initSelectOption', opt.next);
	 				}, 100);
	 			} else if(opt.init) {
	 				opt.init();
	 			}
	 		}
	    });
   });

   try{
	   //富文本编辑器
	   $('.summernote').summernote({
	       height: 300,
	       lang: 'zh-CN',
	       callbacks: {
	           onImageUpload: function(files, editor, $editable) {
	               sendFile(files, editor, $editable);
	           }
	       }
	   });
   }catch(e){}
   
   // 上传图片
   window.sendFile = function(files, editor, $editable) {
	   var data = new FormData();
       data.append("files", files[0]);
       $.ajax({
           data : data,
           type : "POST",
           url : "/upload/image", //图片上传出来的url，返回的是图片上传后的路径，http格式
           cache : false,
           contentType : false,
           processData : false,
           success: function(data) {//data是返回的hash,key之类的值，key是定义的文件名
               $('.summernote').summernote('insertImage', data.fileNames[0]);
               $('.loading').hide();
           },
           error:function(){
        	   error("上传失败");
           }
       });
   }
});

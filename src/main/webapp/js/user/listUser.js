layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage','laydate'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		laydate = layui.laydate,
		$ = layui.jquery;
	
		//分页参数设置 这些全局变量关系到分页的功能
	    var currPageNo = 1; //当前页数(后台返回)
	    var pageSize = 10; //每页多少条
	    var totalPages = 0; //总页数(后台返回)
	    //加载页面数据
	    getDatas();
	    var dataList = '';
	    function getDatas(){
	    	$.ajax({
	    		type:"GET",
	    		url:"/user/queryPage",
	    		dataType:"json",
	    		data:{pageNo:currPageNo, pageSize:pageSize,selectValue:$("#search_input").val()},
	    		success:function(data,status){
	    			dataList = data.page.list;
		        	//console.log(data.page)
		        	currPageNo = data.page.pageNum;//当前页数(后台返回)
			        totalPages = data.page.pages;//总页数(后台返回)
			        todoTable(dataList);
		        }
	    	})
	    }
	//查询
	$(".search_btn").click(function(){
		if($(".search_input").val() != ''){
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
            	$.ajax({
					url : "/user/queryPage.json",
					type : "get",
					dataType : "json",
					success : function(data){
						if(window.sessionStorage.getItem("addUser")){
							var addUser = window.sessionStorage.getItem("addUser");
							dataList = JSON.parse(addUser).concat(data);
						}else{
							dataList = data;
						}
						for(var i=0;i<dataList.length;i++){
							var usersStr = dataList[i];
							var selectStr = $(".search_input").val();
		            		function changeStr(data){
		            			var dataStr = '';
		            			var showNum = data.split(eval("/"+selectStr+"/ig")).length - 1;  //全文查找、忽略大小写
		            			if(showNum > 1){
									for (var j=0;j<showNum;j++) {
		            					dataStr += data.split(eval("/"+selectStr+"/ig"))[j] + "<i style='color:#03c339;font-weight:bold;'>" + selectStr + "</i>";
		            				}
		            				dataStr += data.split(eval("/"+selectStr+"/ig"))[showNum];
		            				return dataStr;
		            			}else{
		            				dataStr = data.split(eval("/"+selectStr+"/ig"))[0] + "<i style='color:#03c339;font-weight:bold;'>" + selectStr + "</i>" + data.split(eval("/"+selectStr+"/ig"))[1];
		            				return dataStr;
		            			}
		            		}
		            	}
		            	todoTable(dataList);
					}
				})
            	
                layer.close(index);
            },2000);
		}else{
			layer.msg("请输入需要查询的内容");
		}
	})

	//添加会员
	$(".dataAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加会员",
			type : 2,
			content : "/user/toAdd",
			success : function(layero, index){
				setTimeout(function(){
					layui.layer.tips('点击此处返回会员列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				},500)
			}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	})

	//批量删除
	$(".batchDel").click(function(){
		var $checkbox = $('.users_list tbody input[type="checkbox"][name="checked"]');
		var $checked = $('.users_list tbody input[type="checkbox"][name="checked"]:checked');
		if($checkbox.is(":checked")){
			layer.confirm('确定删除选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
	            setTimeout(function(){
	            	//删除数据
	            	for(var j=0;j<$checked.length;j++){
	            		for(var i=0;i<dataList.length;i++){
							if(dataList[i].usersId == $checked.eq(j).parents("tr").find(".data_del").attr("data-id")){
								dataList.splice(i,1);
								todoTable(dataList);
							}
						}
	            	}
	            	$('.users_list thead input[type="checkbox"]').prop("checked",false);
	            	form.render();
	                layer.close(index);
					layer.msg("删除成功");
	            },2000);
	        })
		}else{
			layer.msg("请选择需要删除的文章");
		}
	})

    //全选
	form.on('checkbox(allChoose)', function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		child.each(function(index, item){
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});

	//通过判断文章是否全部选中来确定全选按钮是否选中
	form.on("checkbox(choose)",function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})

	//操作
	$("body").on("click",".data_edit",function(){  //编辑
		var _this = $(this);
		console.log(_this.attr("data-id"));
		window.sessionStorage.setItem("ui",JSON.stringify(_this.attr("data-id")));
		var index = layui.layer.open({
			title : "编辑会员",
			type : 2,
			content : "/user/toEdit?userId="+_this.attr("data-id"),
			success : function(layero, index){
				setTimeout(function(){
					layui.layer.tips('点击此处返回会员列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				},500)
			}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	})
	
	//删除
	$("body").on("click",".data_del",function(){  
		var _this = $(this);
		layer.confirm('确定删除此用户?',{icon:3, title:'提示信息'},function(index){
			//_this.parents("tr").remove();
			for(var i=0;i<dataList.length;i++){
				if(dataList[i].usersId == _this.attr("data-id")){
					dataList.splice(i,1);
					todoTable(dataList);
				}
			}
			layer.close(index);
		});
	})

	function todoTable(){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			if(dataList.length != 0){
				for(var i=0;i<dataList.length;i++){
					dataHtml += '<tr>'
			    	+  '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			    	+  '<td>'+dataList[i].nickname+'</td>'
			    	+  '<td>'+dataList[i].email+'</td>'
			    	+  '<td>'+dataList[i].createTime+'</td>'
			    	dataHtml +=   '<td>'+dataList[i].lastLoginTime+'</td>'
			    	+  '<td>'
					+    '<a class="layui-btn layui-btn-mini data_edit" data-id="'+dataList[i].id+'"><i class="iconfont icon-edit" ></i> 编辑</a>'
					+    '<a class="layui-btn layui-btn-danger layui-btn-mini data_del" data-id="'+dataList[i].id+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			        +  '</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
			}
		    return dataHtml;
		}

		//分页
		laypage({
			cont: 'page',
			pages: totalPages ,//得到总页数
            curr: currPageNo,//当前页
			jump: function(obj, first) {
				//得到了当前页，用于向服务端请求对应数据
				currPageNo = obj.curr;
				//pageFisrt = (obj.curr-1);
                
                $(".data_content").html(renderDate(dataList,obj.curr));
				$('.data_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
		    	
				if(!first) {//一定要加此判断，否则初始时会无限刷新
					getDatas();//一定要把翻页的ajax请求放到这里，不然会请求两次。
					layer.msg('第 '+ obj.curr +' 页');
				}
			}
		})
	}
        
})
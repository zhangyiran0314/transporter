layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage','laydate'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		laydate = layui.laydate,
		$ = layui.jquery;

	//加载页面数据
	var departsData = '';
	$.get("../../json/departsList.json", function(data){
		departsData = data;
		if(window.sessionStorage.getItem("addDepart")){
			var addDepart = window.sessionStorage.getItem("addDepart");
			departsData = JSON.parse(addDepart).concat(departsData);
		}
		//执行加载数据的方法
		departsList();
	})

	//查询
	$(".search_btn").click(function(){
		var departArray = [];
		if($(".search_input").val() != ''){
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
            	$.ajax({
					url : "../../json/departsList.json",
					type : "get",
					dataType : "json",
					success : function(data){
						if(window.sessionStorage.getItem("addDepart")){
							var addDepart = window.sessionStorage.getItem("addDepart");
							departsData = JSON.parse(addDepart).concat(data);
						}else{
							departsData = data;
						}
						for(var i=0;i<departsData.length;i++){
							var departsStr = departsData[i];
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
		            		//用户名
		            		if(departsStr.departName.indexOf(selectStr) > -1){
			            		departsStr["departName"] = changeStr(departsStr.departName);
		            		}
		            		
		            	}
		            	departsData = departArray;
		            	departsList(departsData);
					}
				})
            	
                layer.close(index);
            },2000);
		}else{
			layer.msg("请输入需要查询的内容");
		}
	})

	//添加部门 
	$(".departAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加部门 ",
			type : 2,
			content : "addDepart.html",
			success : function(layero, index){
				setTimeout(function(){
					layui.layer.tips('点击此处返回部门 列表', '.layui-layer-setwin .layui-layer-close', {
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
		var $checkbox = $('.departs_list tbody input[type="checkbox"][name="checked"]');
		var $checked = $('.departs_list tbody input[type="checkbox"][name="checked"]:checked');
		if($checkbox.is(":checked")){
			layer.confirm('确定删除选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
	            setTimeout(function(){
	            	//删除数据
	            	for(var j=0;j<$checked.length;j++){
	            		for(var i=0;i<departsData.length;i++){
							if(departsData[i].departsId == $checked.eq(j).parents("tr").find(".departs_del").attr("data-id")){
								departsData.splice(i,1);
								departsList(departsData);
							}
						}
	            	}
	            	$('.departs_list thead input[type="checkbox"]').prop("checked",false);
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
	$("body").on("click",".users_edit",function(){  //编辑
		var _this = $(this);
		console.log(_this.attr("data-id"));
		window.sessionStorage.setItem("ui",JSON.stringify(_this.attr("data-id")));
		var index = layui.layer.open({
			title : "编辑部门 ",
			type : 2,
			content : "editUser.html",
			success : function(layero, index){
				setTimeout(function(){
					layui.layer.tips('点击此处返回部门 列表', '.layui-layer-setwin .layui-layer-close', {
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
	$("body").on("click",".departs_del",function(){  
		var _this = $(this);
		layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
			//_this.parents("tr").remove();
			for(var i=0;i<departsData.length;i++){
				if(departsData[i].departsId == _this.attr("data-id")){
					departsData.splice(i,1);
					departsList(departsData);
				}
			}
			layer.close(index);
		});
	})

	function departsList(){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			currData = departsData.concat().splice(curr*nums-nums, nums);
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
					dataHtml += '<tr>'
			    	+  '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			    	+  '<td>'+currData[i].departName+'</td>'
			    	+  '<td>'+currData[i].departCreatTime+'</td>'
			    	if(currData[i].departstatus == "可用"){
			    		dataHtml += '<td style="color:#f00">'+currData[i].departstatus+'</td>';
			    	}else{
			    		dataHtml += '<td>'+currData[i].departstatus+'</td>';
			    	}
			    	dataHtml +=     '<td>'+currData[i].departRole+'</td>'
			    	+ '<td>'+currData[i].departInfo+'</td>'
			    	+  '<td>'
					+    '<a class="layui-btn layui-btn-mini users_edit" data-id="'+data[i].departsId+'"><i class="iconfont icon-edit" ></i> 编辑</a>'
					+    '<a class="layui-btn layui-btn-danger layui-btn-mini departs_del" data-id="'+data[i].departsId+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			        +  '</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
			}
		    return dataHtml;
		}


		//分页
		var nums = 10; //每页出现的数据量
		laypage({
			cont : "page",
			pages : Math.ceil(departsData.length/nums),
			jump : function(obj){
				$(".departs_content").html(renderDate(departsData,obj.curr));
				$('.departs_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}
        
})
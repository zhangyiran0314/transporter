var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;

 	var addUserArray = [],addUser;
 	form.on("submit(addUser)",function(data){
 		//是否添加过信息
	 	if(window.sessionStorage.getItem("addUser")){
	 		addUserArray = JSON.parse(window.sessionStorage.getItem("addUser"));
	 	}


 		for(key in data.field){
            obj = document.getElementsByName("role");
            check_val = [];
		    for(k in obj){
		        if(obj[k].checked)
		            check_val.push(obj[k].value);
		    }
        }

 		addUser = '{"usersId":"'+ new Date().getTime() +'",';//id
 		addUser += '"nickname":"'+ $(".userName").val() +'",';  //登录名
 		addUser += '"email":"'+ $(".userEmail").val() +'",';	 //邮箱
 	/*	addUser += '"userRole":"'+ check_val +'",'; //角色
 		addUser += '"userStatus":"'+ userStatus +'",'; //会员等级
 		addUser += '"userDepart":"'+ userDepart +'",'; //部门
*/ 		addUser += '"userEndTime":"'+ formatTime(new Date()) +'"}';  //创建时间
 		
 		console.log(addUser);
 		addUserArray.unshift(JSON.parse(addUser));
 		window.sessionStorage.setItem("addUser",JSON.stringify(addUserArray));
 		$.ajax({
    		type:"POST",
    		url:"/user/editUser",
    		dataType:"json",
    		data:{nickname:$(".userName").val(),email:$(".userEmail").val()},
    		//data:JSON.stringify(addUserArray),
    		success:function(data,status){
	        	console.log(data.page)
	        }
 		});
 		//弹出loading
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            top.layer.close(index);
			top.layer.msg("用户添加成功！");
 			layer.closeAll("iframe");
	 		//刷新父页面
	 		parent.location.reload();
        },2000);
 		return false;
 	})
 	
 	/*//加载默认数据
 	if(window.sessionStorage.getItem("addUser")){
 		var data = JSON.parse(window.sessionStorage.getItem("addUser"));
 		fillData(data);
 	}else{*/
 		//alert(window.sessionStorage.getItem("ui"));
 		var userId = $("#userId").val();
 		$.ajax({
			url : "/user/info.json",
			type : "get",
			data:{userId:userId},
			dataType : "json",
			success : function(data){
				fillData(data.result);
			}
		})

 	//填充数据方法
 	function fillData(data){
 		$(".userName").val(data.nickname);      //当前版本
 		$(".userEmail").val(data.email);      //当前版本
 	}
 	


	
})

//格式化时间
function formatTime(_time){
    var year = _time.getFullYear();
    var month = _time.getMonth()+1<10 ? "0"+(_time.getMonth()+1) : _time.getMonth()+1;
    var day = _time.getDate()<10 ? "0"+_time.getDate() : _time.getDate();
    var hour = _time.getHours()<10 ? "0"+_time.getHours() : _time.getHours();
    var minute = _time.getMinutes()<10 ? "0"+_time.getMinutes() : _time.getMinutes();
    return year+"-"+month+"-"+day+" "+hour+":"+minute;
}

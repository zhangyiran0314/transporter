var $$;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$$ = layui.jquery;

 	var addRoleArray = [],addRole;
 	form.on("submit(addRole)",function(data){
 		//是否添加过信息
	 	if(window.sessionStorage.getItem("addRole")){
	 		addRoleArray = JSON.parse(window.sessionStorage.getItem("addRole"));
	 	}

	 	var rolestatus,roleCreatTime;
	 	
 		//状态
 		if(data.field.rolestatus == '0'){
 			rolestatus = "可用";
 		}else if(data.field.rolestatus == '1'){
 			rolestatus = "不可用";
 		}

 		addRole = '{"rolesId":"'+ new Date().getTime() +'",';//id
 		addRole += '"roleName":"'+ $$(".roleName").val() +'",';  //登录名
 		addRole += '"rolestatus":"'+ rolestatus +'",'; //状态
 		addRole += '"roleInfo":"'+ $$("#navs").val() +'",'; //角色权限
 		addRole += '"roleCreatTime":"'+ formatTime(new Date()) +'"}';  //登录时间
 		console.log(addRole);
 		addRoleArray.unshift(JSON.parse(addRole));
 		window.sessionStorage.setItem("addRole",JSON.stringify(addRoleArray));
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

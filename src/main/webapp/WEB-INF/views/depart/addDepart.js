var $$;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$$ = layui.jquery;

 	var addDepartArray = [],addDepart;
 	form.on("submit(addDepart)",function(data){
 		//是否添加过信息
	 	if(window.sessionStorage.getItem("addDepart")){
	 		addDepartArray = JSON.parse(window.sessionStorage.getItem("addDepart"));
	 	}

	 	var departstatus,departCreatTime;
	 	
 		//状态
 		if(data.field.departstatus == '0'){
 			departstatus = "可用";
 		}else if(data.field.departstatus == '1'){
 			departstatus = "不可用";
 		}
 		
 		//角色
 		if(data.field.departRole == '0'){
 			departRole = "角色1";
 		}else if(data.field.departRole == '1'){
 			departRole = "角色2";
 		}
 		

 		addDepart = '{"departsId":"'+ new Date().getTime() +'",';//id
 		addDepart += '"departName":"'+ $$(".departName").val() +'",';  //登录名
 		addDepart += '"departstatus":"'+ departstatus +'",'; //状态
 		addDepart += '"departRole":"'+ departRole +'",'; 
 		addDepart += '"departInfo":"'+ $$(".departInfo").val() +'",'; //描述
 		addDepart += '"departCreatTime":"'+ formatTime(new Date()) +'"}';  //登录时间
 		console.log(addDepart);
 		addDepartArray.unshift(JSON.parse(addDepart));
 		window.sessionStorage.setItem("addDepart",JSON.stringify(addDepartArray));
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

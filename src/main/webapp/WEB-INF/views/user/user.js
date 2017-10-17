 
var $form;
var form;
var $;
layui.config({
	base : "../../js/"
}).use(['form','layer','upload','laydate'],function(){
	form = layui.form();
	var layer = parent.layer === undefined ? layui.layer : parent.layer;
		$ = layui.jquery;
		$form = $('form');
		laydate = layui.laydate;

    layui.upload({
    	url : "../../json/userface.json",
    	success: function(res){
    		var num = parseInt(4*Math.random());  //生成0-4的随机数
    		//随机显示一个头像信息
	    	userFace.src = res.data[num].src;
	    	window.sessionStorage.setItem('userFace',res.data[num].src);
	    }
    });

    //添加验证规则
    form.verify({
        oldPwd : function(value, item){
            if(value != "123456"){
                return "密码错误，请重新输入！";
            }
        },
        newPwd : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
        confirmPwd : function(value, item){
            if(!new RegExp($("#oldPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    })

    //判断是否修改过用户信息，如果修改过则填充修改后的信息
    if(window.sessionStorage.getItem('userInfo')){
        var userInfo = JSON.parse(window.sessionStorage.getItem('userInfo'));
        var citys;
        $(".realName").val(userInfo.realName); //用户名
        $(".userSex input[value="+userInfo.sex+"]").attr("checked","checked"); //性别
        $(".userPhone").val(userInfo.userPhone); //手机号
        $(".userBirthday").val(userInfo.userBirthday); //出生年月
       
        $(".userEmail").val(userInfo.userEmail); //用户邮箱
        $(".myself").val(userInfo.myself); //自我评价
        form.render();
    }

    //判断是否修改过头像，如果修改过则显示修改后的头像，否则显示默认头像
    if(window.sessionStorage.getItem('userFace')){
    	$("#userFace").attr("src",window.sessionStorage.getItem('userFace'));
    }else{
    	$("#userFace").attr("src","../../images/face.jpg");
    }

    //提交个人资料
    form.on("submit(changeUser)",function(data){
    	var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        //将填写的用户信息存到session以便下次调取
        var key,userInfoHtml = '';
        userInfoHtml = {
            'realName' : $(".realName").val(),
            'sex' : data.field.sex,
            'userPhone' : $(".userPhone").val(),
            'userBirthday' : $(".userBirthday").val(),
            'province' : data.field.province,
            'city' : data.field.city,
            'area' : data.field.area,
            'userEmail' : $(".userEmail").val(),
            'myself' : $(".myself").val()
        };
        for(key in data.field){
            if(key.indexOf("like") != -1){
                userInfoHtml[key] = "on";
            }
        }
        window.sessionStorage.setItem("userInfo",JSON.stringify(userInfoHtml));
        setTimeout(function(){
            layer.close(index);
            layer.msg("提交成功！");
        },2000);
    	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

    //修改密码
    form.on("submit(changePwd)",function(data){
    	var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            layer.msg("密码修改成功！");
            $(".pwd").val('');
        },2000);
    	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

})

 
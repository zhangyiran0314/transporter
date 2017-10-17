<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="utf-8">
	<title>登录--layui后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css" media="all" />
</head>
<body>
	<div class="login">
	    <h1>layuiCMS-管理登录</h1>
	    <form class="layui-form">
	    	<div class="layui-form-item">
				<input class="layui-input" name="username" id="username" placeholder="用户名" lay-verify="required" type="text" autocomplete="off">
		    </div>
		    <div class="layui-form-item">
				<input class="layui-input" name="password" id="password" placeholder="密码" lay-verify="required" type="password" autocomplete="off">
		    </div>
		    <div class="layui-form-item form_code">
				<input class="layui-input" name="code" placeholder="验证码" lay-verify="required" type="text" autocomplete="off">
				<div class="code"><img src="<%=request.getContextPath()%>/images/code.jpg" width="116" height="36"></div>
		    </div>
			<button class="layui-btn login_btn" type="button">登录</button>
		</form>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/layui/layui.js"></script>
	<script type="text/javascript">
		layui.config({
			base : "js/"
		}).use(['form','layer','jquery'],function(){
			var form = layui.form();
				layer = parent.layer === undefined ? layui.layer : parent.layer;
				$ = layui.jquery;
			//登录按钮事件
			$(".login_btn").click(function(){
				var username = $('#username').val();
		        var password = $('#password').val();
		        $.ajax({
		        	url:"<%=request.getContextPath()%>/login",
		        	data:{pswd:password,email:username},
		        	type:"post",
		        	dataType:"json",
		        	beforeSend:function(){
		        		layer.msg('开始登录，请注意后台控制台。');
		        	},
		        	success:function(result){
			    		if(result && result.status != 200){
			    			layer.msg(result.message,function(){});
			    			$('.password').val('');
			    			return;
			    		}else{
			    			layer.msg('登录成功！');
			    			setTimeout(function(){
			    				//登录返回
				    			window.location.href= result.back_url || "<%=request.getContextPath()%>";
			    			},1000)
			    		}
		        	},
		        	error:function(e){
		        		console.log(e,e.message);
		        		layer.msg('请看后台Java控制台，是否报错，确定已经配置数据库和Redis',new Function());
		        	}
		        });
			})
		})
	</script>
</body>
</html>
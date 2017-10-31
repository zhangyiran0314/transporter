<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="utf-8">
	<title>人员添加--layui后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/layui/css/layui.css" media="all" />
	<style type="text/css">
		.layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
		@media(max-width:1240px){
			.layui-form-item .layui-inline{ width:100%; float:none; }
		}
	</style>
</head>
<body class="childrenBody">
	<form class="layui-form" style="width:80%;">
		<div class="layui-form-item">
			<label class="layui-form-label">登录名</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input userName" lay-verify="required" placeholder="请输入登录名">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">邮箱</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input userEmail"  placeholder="请输入邮箱">
			</div>
		</div>
		<!-- <div class="layui-form-item">
		    <label class="layui-form-label">角色</label>
		    <div class="layui-input-block userRole">
		    	<input type="checkbox" name="role" value="管理员" title="管理员">
			    <input type="checkbox" name="role" value="高级用户" title="高级用户">
		    </div>
		</div> -->
		
	   <!--  <div class="layui-form-item">
		    <label class="layui-form-label">所属部门</label>
			<div class="layui-input-block">
				<select name="userDepart" class="userDepart" lay-filter="userDepart">
					<option value="0">商务部</option>
					<option value="1">IT部</option>
			        <option value="2">总裁办</option>
			        <option value="3">硬件部</option>
			    </select>
			</div>
	    </div> -->
	   <!--  <div class="layui-form-item">
		    <label class="layui-form-label">人员状态</label>
			<div class="layui-input-block">
				<select name="userStatus" class="userStatus" lay-filter="userStatus">
					<option value="0">正常使用</option>
					<option value="1">限制用户</option>
			    </select>
			</div>
	    </div> -->
		<div class="layui-form-item">
			<label class="layui-form-label">人员描述</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入人员描述" class="layui-textarea linksDesc"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="addUser">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="<%=request.getContextPath()%>/layui/layui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/user/addUser.js"></script>
</body>
</html>
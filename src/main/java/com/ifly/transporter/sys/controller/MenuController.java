package com.ifly.transporter.sys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifly.transporter.common.controller.BaseController;
import com.ifly.transporter.shiro.TokenManager;
import com.ifly.transporter.sys.bean.UPermission;
import com.ifly.transporter.sys.service.PermissionService;

@Controller
@RequestMapping("menu")
public class MenuController extends BaseController{
	
	@Autowired
	PermissionService permissionService;
	
	//查询当前用户的所有权限
	@RequestMapping("findMenus")
	@ResponseBody
	public Map<String,Object> findMenus(HttpServletRequest request){
		Long userId = TokenManager.getUserId();
		
		List<UPermission> menus = permissionService.findMenusByUserId(userId);
		resultMap.put("status", 200);
		resultMap.put("list", menus);
		
		return resultMap;
	}
}

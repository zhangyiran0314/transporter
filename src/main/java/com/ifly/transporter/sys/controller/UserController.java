package com.ifly.transporter.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ifly.transporter.common.controller.BaseController;
import com.ifly.transporter.sys.bean.UUser;
import com.ifly.transporter.sys.service.UUserService;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UUserService  userService;
	
	@RequestMapping("/")
	public String user(){
		return "user/allUser";
	}
	
	@RequestMapping("allUser")
	public String userPage(){
		return "user/allUser";
	}
	@RequiresPermissions("/user/allUser")
	@RequestMapping("userList")
	@ResponseBody
	public Map<String,Object> userList(Integer pageNo,HttpServletRequest request){
		PageInfo<UUser> page = userService.queryPage(resultMap, pageNo, 10);
		resultMap.put("page", page.getList());
		return resultMap;
	}
}

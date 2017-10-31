package com.ifly.transporter.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ifly.transporter.sys.bean.UUser;
import com.ifly.transporter.sys.service.UUserService;
import com.ifly.transporter.utils.PasswordUtil;
import com.sun.jmx.snmp.Timestamp;


@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UUserService  userService;
	
	@RequestMapping("/")
	public String user(){
		return "user/listUser";
	}
	
	@RequestMapping("listUser")
	public String userPage(){
		return "user/listUser";
	}
	@RequestMapping("queryPage")
	@ResponseBody
	public Map<String,Object> userList(Integer pageNo,HttpServletRequest request){
		PageInfo<UUser> page = userService.queryPage( pageNo, 10);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("page", page);
		return result;
	}
	@RequestMapping("info")
	@ResponseBody
	public Map<String,Object> info(Long userId,HttpServletRequest request){
		UUser user = userService.selectByPrimaryKey(userId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", user);
		return result;
	}
	@RequestMapping("toAdd")
	public String toAdd(){
		return "user/addUser";
	}
	@RequestMapping("addUser")
	@ResponseBody
	public Map<String,Object> add(UUser user,HttpServletRequest request){
		user.setCreateTime(new Date());
		user.setPswd(PasswordUtil.generatePassword(user.getEmail(), PasswordUtil.DEFAULT_PASSWORD).getPassword());
		UUser record = userService.insert(user);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", record);
		return result;
	}
	@RequestMapping("toEdit")
	public String toEdit(Long userId,HttpServletRequest request){
		request.setAttribute("userId", userId);
		return "user/editUser";
	}
	@RequestMapping("editUser")
	@ResponseBody
	public  Map<String,Object> edit(UUser user,HttpServletRequest request){
		int r = userService.updateByPrimaryKeySelective(user);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", r);
		return result;
	}
}

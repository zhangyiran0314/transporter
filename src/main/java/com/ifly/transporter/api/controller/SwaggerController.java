package com.ifly.transporter.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ifly.transporter.sys.bean.UUser;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("swagger")
public class SwaggerController {
	
	@ApiOperation(value="获取用户列表", notes="获取所有用户列表")
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object>  getUser(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> page =new HashMap<String,Object>();
		page.put("total",200);
		page.put("pageNo", 1);
		
		response.addHeader("X-page", JSON.toJSONString(page));
		Map<String,Object> result =new HashMap<String,Object>();
		result.put("status",200);
		result.put("msg", "0");
		result.put("result", "getUser info");
		return result;
	}
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", paramType = "path",required = true, dataType = "Long")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Map<String,Object> getUser(@PathVariable Long id) {
		Map<String,Object> result =new HashMap<String,Object>();
		result.put("status",200);
		result.put("msg", "0");
		result.put("result", "getUser info");
		return result;
    }
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UUser")
    @RequestMapping(value="", method=RequestMethod.POST)
    public String postUser(@RequestBody UUser user) {
		Map<String,Object> result =new HashMap<String,Object>();
		result.put("status",200);
		result.put("msg", "0");
		result.put("result", "create user info");
		return JSON.toJSONString(result);
    }

}

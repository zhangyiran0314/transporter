package com.ifly.transporter.jwt;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifly.transporter.utils.JwtUtil;

@Controller
@RequestMapping("/api")
public class ApiController {
	
	@RequestMapping("getUser")
	@ResponseBody
	public Map<String,Object>  getUser(String username,String password,HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> result =new HashMap<String,Object>();
		result.put("status",200);
		result.put("msg", "0");
		result.put("result", "getUser info");
		return result;
	}
}

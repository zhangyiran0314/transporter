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
@RequestMapping("/jwt")
public class JwtController {
	
	@RequestMapping("login")
	@ResponseBody
	public Map<String,Object>  login(String username,String password,HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> result =new HashMap<String,Object>();
		if(!"admin".equals(username) || !"123456".equals(password)){
			result.put("status",403);
			result.put("msg", "账号或者密码错误");
			return result;
		}
		String token = JwtUtil.createJWT(username, JwtUtil.JWT_TTL);
		String refreshToken = JwtUtil.createJWT(username, JwtUtil.JWT_REFRESH_TTL);
		JSONObject jo = new JSONObject();
		jo.put("token", token);
		jo.put("refreshToken", refreshToken);
		result.put("status",200);
		result.put("msg", "0");
		result.put("result", jo);
		return result;
	}
}

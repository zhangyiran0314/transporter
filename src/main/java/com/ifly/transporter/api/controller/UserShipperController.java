package com.ifly.transporter.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifly.transporter.api.service.UserShipperService;

@Controller("api/user")
@RequestMapping("api/user")
public class UserShipperController {
	
	@Autowired
	private UserShipperService userShipperService;
	
    @RequestMapping(value="getUser", method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUser() {
		Map<String,Object> result =new HashMap<String,Object>();
		result.put("status",200);
		result.put("msg", "0");
		result.put("result", "getUser info");
		return result;
    }
}

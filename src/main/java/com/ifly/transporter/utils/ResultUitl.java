package com.ifly.transporter.utils;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ifly.transporter.common.exception.BuzExceptionEnums;
import com.ifly.transporter.common.exception.ServiceException;

public class ResultUitl {
	
	public static String successResult(Map<String,Object> data){
		JSONObject jsonResult = new JSONObject();
		jsonResult.put(Constanst.Code_Key,Constanst.Code_Success);
		jsonResult.put(Constanst.Msg_Key, Constanst.Msg_Success);
		jsonResult.put(Constanst.Data_Key, data);
		return jsonResult.toJSONString();
	}
	public static String failureResult(){
		JSONObject jsonResult = new JSONObject();
		jsonResult.put(Constanst.Code_Key,Constanst.Code_Failure);
		jsonResult.put(Constanst.Msg_Key, Constanst.Msg_Failure);
		jsonResult.put(Constanst.Data_Key, null);
		return jsonResult.toJSONString();
	}
	public static String failureResult(String msg){
		JSONObject jsonResult = new JSONObject();
		jsonResult.put(Constanst.Code_Key,Constanst.Code_Failure);
		jsonResult.put(Constanst.Msg_Key, msg);
		jsonResult.put(Constanst.Data_Key, null);
		return jsonResult.toJSONString();
	}
	public static String failureResult(BuzExceptionEnums e){
		JSONObject jsonResult = new JSONObject();
		jsonResult.put(Constanst.Code_Key,e.getCode());
		jsonResult.put(Constanst.Msg_Key, e.getMessage());
		jsonResult.put(Constanst.Data_Key, null);
		return jsonResult.toJSONString();
	}
	public static String exceptionResult(ServiceException e){
		JSONObject jsonResult = new JSONObject();
		jsonResult.put(Constanst.Code_Key,e.getCode());
		jsonResult.put(Constanst.Msg_Key, e.getMessage());
		return jsonResult.toJSONString();
	}
}

package com.ifly.transporter.sys.bean;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
public class UserRole  implements Serializable{
	private static final long serialVersionUID = 1L;
	 /**{@link User.id}*/
    private Long uid;
    /**{@link Role.id}*/
    private Long rid;

    public UserRole(Long uid,Long rid) {
    	this.uid = uid;
    	this.rid = rid;
	}
    public UserRole() {
    }
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
    public String toString(){
    	return JSONObject.toJSONString(this).toString();
    }
}
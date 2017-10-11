package com.ifly.transporter.sys.bean;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class RolePermission  implements Serializable{
	private static final long serialVersionUID = 1L;
	/**{@link Role.id}*/
    private Long rid;
    /**{@link Permission.id}*/
    private Long pid;

    public RolePermission() {
	}
    public RolePermission(Long rid,Long pid) {
    	this.rid = rid;
    	this.pid = pid;
    }
    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
    public String toString(){
    	return JSONObject.toJSONString(this).toString();
    }
}
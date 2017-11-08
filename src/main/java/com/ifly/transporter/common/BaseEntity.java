package com.ifly.transporter.common;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable{
	
	private static final long serialVersionUID = -2564699098799048004L;

	private String id;

	private Date createDate;

	private Date updateDate;

	private Short dataStatus;

	private String desp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Short getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Short dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}
	
	
}

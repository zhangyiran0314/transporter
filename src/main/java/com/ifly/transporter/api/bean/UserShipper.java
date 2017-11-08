package com.ifly.transporter.api.bean;

import java.util.Date;

import com.ifly.transporter.common.BaseEntity;

public class UserShipper extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	private String mobile;

    private String password;

    private String nickname;

    private String lastLoginIp;

    private Date lastLoginDate;

    private Short status;

    private String lastLoginDevice;

    private String companyId;

    private String parentId;

    private String attachmentId1;

    private String attachmentId2;

    private String attachmentId3;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getLastLoginDevice() {
        return lastLoginDevice;
    }

    public void setLastLoginDevice(String lastLoginDevice) {
        this.lastLoginDevice = lastLoginDevice == null ? null : lastLoginDevice.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getAttachmentId1() {
        return attachmentId1;
    }

    public void setAttachmentId1(String attachmentId1) {
        this.attachmentId1 = attachmentId1 == null ? null : attachmentId1.trim();
    }

    public String getAttachmentId2() {
        return attachmentId2;
    }

    public void setAttachmentId2(String attachmentId2) {
        this.attachmentId2 = attachmentId2 == null ? null : attachmentId2.trim();
    }

    public String getAttachmentId3() {
        return attachmentId3;
    }

    public void setAttachmentId3(String attachmentId3) {
        this.attachmentId3 = attachmentId3 == null ? null : attachmentId3.trim();
    }
}
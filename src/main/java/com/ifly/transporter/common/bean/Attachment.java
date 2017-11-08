package com.ifly.transporter.common.bean;

import com.ifly.transporter.common.BaseEntity;

public class Attachment extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String attachementLink;

    private String attachementName;

    private String attachementType;

    private String refId;


    public String getAttachementLink() {
        return attachementLink;
    }

    public void setAttachementLink(String attachementLink) {
        this.attachementLink = attachementLink == null ? null : attachementLink.trim();
    }

    public String getAttachementName() {
        return attachementName;
    }

    public void setAttachementName(String attachementName) {
        this.attachementName = attachementName == null ? null : attachementName.trim();
    }

    public String getAttachementType() {
        return attachementType;
    }

    public void setAttachementType(String attachementType) {
        this.attachementType = attachementType == null ? null : attachementType.trim();
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId == null ? null : refId.trim();
    }
}
package com.ifly.transporter.common.service;

import com.ifly.transporter.common.bean.Attachment;

public interface AttachmentService {
	
	public int saveAttachment(Attachment record);
	
	public Attachment queryAttachment(String id);
}

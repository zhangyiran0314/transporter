package com.ifly.transporter.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifly.transporter.common.bean.Attachment;
import com.ifly.transporter.common.mapper.AttachmentMapper;
import com.ifly.transporter.common.service.AttachmentService;

@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService{

	@Autowired
	private AttachmentMapper attachmentMapper;
	@Override
	public int saveAttachment(Attachment record) {
		return attachmentMapper.insert(record);
	}

	@Override
	public Attachment queryAttachment(String id) {
		return attachmentMapper.selectByPrimaryKey(id);
	}

}

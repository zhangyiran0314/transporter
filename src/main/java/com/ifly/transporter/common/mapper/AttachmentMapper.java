package com.ifly.transporter.common.mapper;

import com.ifly.transporter.common.bean.Attachment;

public interface AttachmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Attachment record);

    int insertSelective(Attachment record);

    Attachment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKey(Attachment record);
}
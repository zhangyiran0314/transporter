package com.ifly.transporter.common.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
	protected final static Logger logger = LoggerFactory.getLogger(BaseController.class);
	protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
}

package com.ifly.transporter.utils;

import org.springframework.web.servlet.ModelAndView;

public class ViewUtil {
	public static ModelAndView forward(String name) {
		return new ModelAndView("/modules/" + name);
	}

	public static ModelAndView redirect(String name) {
		return new ModelAndView("redirect:" + name + ".htm");
	}
}

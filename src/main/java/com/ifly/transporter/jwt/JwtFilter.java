package com.ifly.transporter.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.ifly.transporter.utils.JwtUtil;

public class JwtFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("JwtFilter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String auth = httpRequest.getHeader("Authorization");
		if ((auth != null) && (auth.length() > 7)) {
			String HeadStr = auth.substring(0, 6).toLowerCase();
			if (HeadStr.compareTo("bearer") == 0) {

				auth = auth.substring(7, auth.length());
				if (JwtUtil.parseJWT(auth)!=null) {
					{
						chain.doFilter(request, response);
						return;
					}
				}
			}

			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setCharacterEncoding("UTF-8");
			httpResponse.setContentType("application/json; charset=utf-8");
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("status", 403);
			result.put("msg", "token失效");
			httpResponse.getWriter().write(JSON.toJSONString(result));
			return;
		}
	}

	@Override
	public void destroy() {
		
	}
	
}

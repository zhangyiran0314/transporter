package com.ifly.transporter.sys.controller;




import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifly.transporter.shiro.TokenManager;
import com.ifly.transporter.sys.bean.UUser;
import com.ifly.transporter.utils.LoggerUtils;
import com.ifly.transporter.utils.VerifyCodeUtils;

@Controller
public class LoginController{
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String loginPage(Model model){
        return "login";
    }
	/**
	 * 登陆页面
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> login(UUser user,Boolean rememberMe,HttpServletRequest request){
		 Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			/* String username = user.getEmail();
	        ShiroToken token = new ShiroToken(user.getEmail(), user.getPswd());
	        //获取当前的Subject  
	        Subject currentUser = SecurityUtils.getSubject();  
	        currentUser.login(token); */ 
		    user = TokenManager.login(user,rememberMe);
			resultMap.put("status", 200);
			resultMap.put("message", "登录成功");
			
			SavedRequest savedRequest = WebUtils.getSavedRequest(request);
			String url = null ;
			if(null != savedRequest){
				url = savedRequest.getRequestUrl();
			}
			/**
			 * 我们平常用的获取上一个请求的方式，在Session不一致的情况下是获取不到的
			 * String url = (String) request.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
			 */
			logger.info( "获取登录之前的URL:{}",url);
			//如果登录之前没有地址，那么就跳转到首页。
			if(StringUtils.isBlank(url)){
				url = request.getContextPath() + "/index";
			}
			//跳转地址
			resultMap.put("back_url", url);
		/**
		 * 这里其实可以直接catch Exception，然后抛出 message即可，但是最好还是各种明细catch 好点。。
		 */
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", 500);
			resultMap.put("message", "帐号或密码错误");
		}
			
		return resultMap;
	}

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "validateCode",method = RequestMethod.GET)
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg");  
          
        //生成随机字串  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
        //存入会话session  
        HttpSession session = request.getSession(true);  
        session.setAttribute("randCode", verifyCode.toLowerCase());  
        //生成图片  
        int w = 120, h = 40;  
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);  
    }
    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value="/index",method=RequestMethod.GET)
    public String index(){
        return "index";
    }
    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value="/",method=RequestMethod.GET)
    public String loginPage(){
        return "login";
    }
    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value="/main",method=RequestMethod.GET)
    public String mainPage(){
        return "main";
    }
    /**
	 * 退出
	 * @return
	 */
	@RequestMapping(value="/logout",method =RequestMethod.GET)
	public String logout(){
		try {
			TokenManager.logout();
		} catch (Exception e) {
			logger.error("errorMessage:" + e.getMessage());
			LoggerUtils.fmtError(getClass(), e, "退出出现错误，%s。", e.getMessage());
		}
		return "login";
	}
}

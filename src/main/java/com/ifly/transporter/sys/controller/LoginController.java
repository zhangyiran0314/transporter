package com.ifly.transporter.sys.controller;




import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ifly.transporter.common.controller.BaseController;
import com.ifly.transporter.shiro.ShiroRealm;
import com.ifly.transporter.shiro.ShiroToken;
import com.ifly.transporter.shiro.TokenManager;
import com.ifly.transporter.sys.bean.UUser;
import com.ifly.transporter.utils.UserUtil;
import com.ifly.transporter.utils.VerifyCodeUtils;
import com.ifly.transporter.utils.ViewUtil;

@Controller
@RequestMapping("/sys")
public class LoginController extends BaseController{

	/**
	 * 到登陆页面
	 * @return
	 */
	@RequestMapping(value="submitLogin",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> submitLogin(UUser user,Boolean rememberMe,HttpServletRequest request){
		
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
				url = request.getContextPath() + "/user/index.shtml";
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
	 * 登陆失败
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value="login",method = RequestMethod.POST)
	public ModelAndView fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		if(UserUtil.getUser()!=null){
			return ViewUtil.redirect("/sys/index");
		}
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		 return ViewUtil.forward("/sys/login");
	}

	/**
	 * index 页面(这里的权限是必须的，否则会导致权限认证的不会激发)
	 * @return
	 */
	@RequestMapping(value="index",method = RequestMethod.GET)
	@RequiresPermissions("sys:index")
	public ModelAndView index(HttpServletRequest request) {
		//String redirectUrl=null;
		request.getSession(false).setMaxInactiveInterval(3600*8);//Session超时时间设置为8小时
		//if((redirectUrl=(String)request.getSession().getAttribute("redirectUrl"))!=null){
		//	request.getSession().removeAttribute("redirectUrl");
			
			//return new ModelAndView("redirect:"+redirectUrl);
		//}
		//return ViewUtil.forward("/sys/index");

//		Subject subject = SecurityUtils.getSubject();  
//		Object obj = subject.getPrincipal();
//		
//        HttpSession session = request.getSession(true);  
//        String randCode =  (String) session.getAttribute("randCode");  
//        String userName =  request.getParameter("username");
		return ViewUtil.forward("/sys/agent/agentIndex");
	}
	
	/**
	 * index 页面
	 * @return
	 */
	@RequestMapping(value="top",method = RequestMethod.GET)
	@RequiresPermissions("sys:index")
	public ModelAndView top() {
		return ViewUtil.forward("/sys/top");
	}
	
	/**
	 * 坐席index 页面
	 * @return
	 */
	@RequestMapping(value="agent/agentTop",method = RequestMethod.GET)
	@RequiresPermissions("sys:index")
	public ModelAndView agentTop() {
		return ViewUtil.forward("/sys/agent/agentTop");
	}
	
	/**
	 * 坐席index 页面
	 * @return
	 */
	@RequestMapping(value="agent/agentLeft",method = RequestMethod.GET)
	@RequiresPermissions("sys:index")
	public ModelAndView agentLeft() {
		return ViewUtil.forward("/sys/agent/leftframe");
	}
	
	/**
	 * 坐席index 页面
	 * @return
	 */
	@RequestMapping(value="agent/mainframe",method = RequestMethod.GET)
	@RequiresPermissions("sys:index")
	public ModelAndView mainframe() {
		return ViewUtil.forward("/sys/agent/mainframe");
	}
	
	/**
	 * 坐席index 页面
	 * @return
	 */
	@RequestMapping(value="agent/switchTopBottom",method = RequestMethod.GET)
	@RequiresPermissions("sys:index")
	public ModelAndView switchTopBottom() {
		return ViewUtil.forward("/customer/customerCompanyInfo/switchTopBottom");
	}
	
	/**
	 * 坐席index 页面
	 * @return
	 */
	@RequestMapping(value="agent/bottom",method = RequestMethod.GET)
	@RequiresPermissions("sys:index")
	public ModelAndView bottom() {
		return ViewUtil.forward("/sys/agent/bottom");
	}
	/**
	 * 坐席index 页面
	 * @return
	 */
	@RequestMapping(value="agent/switchframe",method = RequestMethod.GET)
	@RequiresPermissions("sys:index")
	public ModelAndView switchframe() {
		return ViewUtil.forward("/sys/agent/switchframe");
	}
	/**
	 * index 页面
	 * @return
	 */
	@RequestMapping(value="left",method = RequestMethod.GET)
	@RequiresPermissions("sys:index")
	public ModelAndView left() {
		return ViewUtil.forward("/sys/left");
	}
	
	/**
	 * index 页面
	 * @return
	 */
	@RequestMapping(value="right",method = RequestMethod.GET)
	@RequiresPermissions("sys:index")
	public ModelAndView right() {
		return ViewUtil.forward("/sys/right");
	}
	
	/**
	 * index 页面
	 * @return
	 */
	@RequestMapping(value="mid",method = RequestMethod.GET)
	@RequiresPermissions("sys:index")
	public ModelAndView mid() {
		return ViewUtil.forward("/sys/mid");
	}

	/**
	 * 退出系统
	 * @return
	 */
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public ModelAndView logout(){
		try
		{
//			shiroRealm.clearCachedAuthorizationInfo(UserUtil.getUser());
			Subject user = SecurityUtils.getSubject();  
			user.logout();
			//UserUtil.logout();
		}
		catch(Exception e)
		{
			return new ModelAndView("redirect:/");
		}

		return new ModelAndView("redirect:/");
	}
	
	@Autowired(required=false)
	private ShiroRealm shiroRealm;
	
	
}

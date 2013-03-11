package com.shangde.common.intercepter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.DESCoder;
import com.shangde.common.util.StringUtil;

/**
 * 前台用户拦截器
 * @author guoqiang.liu
 */
@SuppressWarnings("unchecked")
public class LimitIntercepterForWeb extends AbstractInterceptor {
	private static final long serialVersionUID = 5622350756953568982L;
	//用户唯一ukey
	public static final String COOKIE_REMEMBERME_KEY="sedu.cookie.ukey"; 
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		boolean flag = false;
		String curKey = null;
		ActionContext actionContext = actionInvocation.getInvocationContext();   
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		//通过uid得到当前请求用户的ukey(base64加密过)
		//String uid = request.getParameter("uid");
		String cookieKey = CookieHandler.getCookieValueByName(request,COOKIE_REMEMBERME_KEY);  
		String uid = "";
		String uKey = "";
		if(!StringUtil.isNullString(cookieKey)){
			String str[]  = cookieKey.split(",");
			uid = str[1];
		    uKey = str[2];
		    if(!StringUtil.isNullString(uid)){
				curKey = uid+DESCoder.md5(DESCoder.encrypt(DESCoder.SECONDKEY));
			}
			//比对当前和cookie内保存的ukey是否一致
			Cookie[] cookies = request.getCookies();  
			if (cookies!=null&&curKey!=null) {   
				if(curKey.equals(uKey)){
					flag = true;
				}
			}
		}
		if(!flag){
			//提示注册学员才具有此功能，并转到注册页面
			HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
			CookieHandler.createOrUpdateCookie(request, response, "backURL", request.getRequestURL().toString(), 1);
			return "operatorLimit";
		}else{
			return actionInvocation.invoke();
		}
	}
}

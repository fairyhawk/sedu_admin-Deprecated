package com.shangde.common.intercepter;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class NoRepeatInterceptor extends com.opensymphony.xwork2.interceptor.MethodFilterInterceptor {

	/**
	 * BY ZHANG JU QIANG
	 */
	private static final long serialVersionUID = 1L;

	public String doIntercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();		
		
		//只拦截以FORM形式POST方法发出的请求，链接形式的GET方法放行。
		if(request.getMethod().equalsIgnoreCase("GET")){
			return arg0.invoke();
		}
		//AJAX请求，放行。（只能用于判断是JQUERY的AJAX）
		String requestType=request.getHeader("X-Requested-With");
		if("XMLHttpRequest".equals(requestType)){
			return arg0.invoke();
			//out.println("要懂得劳逸结合哦，喝口茶歇一会儿吧～");return null;
		}
		String requestURL=request.getRequestURI();//请求URL
		
		//用户修改个人头像时拦截会有问题，此操作无危害，放行
//		if(requestURL.indexOf("Photo")!=-1){
//			return arg0.invoke();
//		}
		long currTime=System.currentTimeMillis();//系统当前时间
		HttpSession session=request.getSession();		
		
		//以请求URL做key值，保证每个（action+方法）的唯一性，及多次连续请求时确定是基于一个业务操作。
		Long validateToken=(Long)session.getAttribute(requestURL);
		//第一次访问。在会话里打个标记
		if(validateToken==null){
			session.setAttribute(requestURL, currTime);
		}
		//读取属性文件。取得提交的限制时间
		InputStream is=this.getClass().getResourceAsStream("/noRepeatTime.properties");
		Properties props=new Properties();
		props.load(is);
		long intervalTime=Long.valueOf(props.getProperty("intervalTime"));
		is.close();
		//如果：1、第一次提交；2、距离上次提交超过限制时间，正常提交
		if(validateToken==null||currTime-validateToken>intervalTime){
			if(validateToken!=null){
				//若第2种情况，提交后重新打标记
				session.setAttribute(requestURL, currTime);
			}
			return arg0.invoke();
		}
		
		//否则，不允许提交
		
		//有乱码情况，不同浏览器设置不同编码。
		String userAgent=request.getHeader("user-agent");
		//System.out.println(userAgent);
		if(userAgent.indexOf("MSIE")!=-1||userAgent.indexOf("Safari")!=-1){
			response.setCharacterEncoding("GB2312");
		}else{
			response.setCharacterEncoding("UTF-8");
		}		
		PrintWriter out=response.getWriter();
		out.println("<script>alert('抱歉，您操作有点频繁，15秒后再试试……');window.history.go(-1);</script>");
		return null;
	}

}

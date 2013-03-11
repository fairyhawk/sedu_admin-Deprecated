package com.shangde.common.util;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shangde.common.intercepter.LimitIntercepterForWeb;
import com.shangde.edu.sys.action.BackLoginAction;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.domain.VisitHistory;
import com.shangde.edu.sys.service.IVisitHistory;

import flex.messaging.util.URLEncoder;

public class GetPathFilter  implements  Filter {

	private ServletContext  servletContext;
	public void destroy() {
		
	}

	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse,
			FilterChain filterchain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletrequest;
		HttpServletResponse response = (HttpServletResponse) servletresponse;
		HttpSession session = request.getSession();
		Date date=new Date();
		//获取访问路径
//		String url = request.getRequestURI();
//		System.out.println("========================="+url);
//		System.out.println("========================="+request.getRequestURL());
//		String path = request.getContextPath();
//		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		StringBuffer sbUrlParms = request.getRequestURL();
		//访问类型
		String visitTypeUrl=sbUrlParms.toString();
		
		StringBuffer strPar=new StringBuffer();
		Enumeration parNames = request.getParameterNames();
		if(parNames.hasMoreElements()==true){
			sbUrlParms.append("?");
		}
		while (parNames.hasMoreElements()) {
			String parName = parNames.nextElement().toString();
			    if(request.getParameter(parName).indexOf("@")!=-1){
			    	sbUrlParms.append(parName).append("=").append(request.getParameter(parName)).append("&");
			    	//参数
					strPar.append(parName).append("=").append(request.getParameter(parName)).append(",");
			    }else{
				sbUrlParms.append(parName).append("=").append(URLEncoder.encode(request.getParameter(parName),"UTF-8")).append("&");
				//参数
				strPar.append(parName).append("=").append(URLEncoder.encode(request.getParameter(parName),"UTF-8")).append(",");
			    }
				
		}
		//参数
		String strParameter=strPar.toString();
		if(strParameter.endsWith(",")){
			strParameter=strParameter.substring(0,strParameter.length()-1);
		}
		//路径
		String url=sbUrlParms.toString();
		if(url.endsWith("&")){
			url=url.substring(0,url.length()-1);
		}
		//访问类型
		String[] type=null;
		String visitType;
		String namespace="";
		String actionname="";
		if(visitTypeUrl.indexOf(".a")!=-1){
			type=visitTypeUrl.split("\\.a");
			visitType=".a"+type[1];
		    actionname=visitTypeUrl.substring(visitTypeUrl.lastIndexOf("/"));
		    actionname=actionname.substring(1,actionname.length());
		    namespace=visitTypeUrl.substring(0,visitTypeUrl.lastIndexOf("/"));
		    namespace=namespace.substring(namespace.lastIndexOf("/"));
		}else{
			visitType=".jsp";
		}
		//nameSpace,actionName
		//获取访问ip地址
		String ip=request.getRemoteAddr();
		//从cooki中得到前台的用户id
		String userIdStr = CookieHandler.getCookieValueByName(request, LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY);
		int cusId = 0;
		if(userIdStr != null&&!"".equals(userIdStr)){
			cusId = Integer.parseInt(userIdStr.split(",")[0]);
		}
		
		//取得后台用户的id
	//	User user=(User)ActionContext.getContext().getSession().get(BackLoginAction.SYS_USER_SESSION_NAME);
	//	User user = this.getSession(BackLoginAction.SYS_USER_SESSION_NAME);
		User user=(User)session.getValue(BackLoginAction.SYS_USER_SESSION_NAME);
		int userId=0;
		if(user!=null){
			userId=user.getUserId();
			}		
		//判断是哪种用户
		VisitHistory visitHistory =new VisitHistory();
		if(cusId!=0){
			visitHistory.setUserId(cusId);
			visitHistory.setUserType(VisitHistory.BEFORE_USER);
		}else if(userId!=0){
			visitHistory.setUserId(userId);
			visitHistory.setUserType(VisitHistory.ADMIN_USER);
		}else{
			visitHistory.setUserId(0);
			visitHistory.setUserType(VisitHistory.VISITOR);
		}
		visitHistory.setActionName(actionname);
		visitHistory.setNameSpace(namespace);
		visitHistory.setVisitInfo("访问描述");
		visitHistory.setVisitIp(ip);
		visitHistory.setVisitName("访问的功能名称");
		visitHistory.setVisitParms(strParameter);
		visitHistory.setVisitTime(date);
		visitHistory.setVisitType(visitType);
		visitHistory.setVisitUrl(url);
		
		WebApplicationContext wac =WebApplicationContextUtils.getWebApplicationContext(this.servletContext);
		IVisitHistory visitService  = (IVisitHistory)wac.getBean("visitHistoryService");
		visitService.addVisitHistory(visitHistory);
		
		try {
			filterchain.doFilter(servletrequest, servletresponse);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(FilterConfig config) throws ServletException {
		servletContext = config.getServletContext();
	}

}

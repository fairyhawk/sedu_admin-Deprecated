package com.shangde.common.intercepter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class KillSqlIntercepter extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation arg0) throws Exception {
		try {
			// String actionName = arg0.getInvocationContext().getName();
			// 获取action后附带参数
			ActionContext ctx = ActionContext.getContext();        
			HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);  
			String name=request.getMethod();
			if(name.equalsIgnoreCase("get")){
			Map<String, Object> map = arg0.getInvocationContext()
					.getParameters();
			Iterator<String> it = map.keySet().iterator();
			List<String> list = new ArrayList<String>();
			while (it.hasNext()) {
				Object key = it.next();
				Object mapValue=map.get(key);
				list.add(((String [])mapValue)[0]);
			}
			if (list.size() != 0) {
				boolean bool = KillSqlIntercepter.sqlFilter(list);
				if (!bool) {
					return "sqlerror";
				}
			}
			}
			return arg0.invoke();
		} catch (Exception ex) {
			System.out.println(ex);
			return "sqlerror";

		}
	}

	// 关键词数组
	private static String[] sqlfilter = {"select", "update", "delete",
			"insert", "count", "'", "|", ">", "<", "%", "(", ")", ";" };

	// 判断
	public static boolean sqlFilter(List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < sqlfilter.length; j++) {
				String te=String.valueOf(list.get(i));
				if (te.toLowerCase().contains(sqlfilter[j])) {
					return false;
				}
			}
		}
		return true;
	}

}

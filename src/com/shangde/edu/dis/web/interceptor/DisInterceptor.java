package com.shangde.edu.dis.web.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.shangde.edu.dis.domain.CusUserDis;
import com.shangde.edu.dis.domain.Discussion;

/**
 * 用于拦截小组用户的访问权限
 * 
 * @author Basil
 * 
 */
public class DisInterceptor extends MethodFilterInterceptor {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
		Map session = ActionContext.getContext().getSession();

		List<CusUserDis> cusUserDisList = null;
		cusUserDisList = (List<CusUserDis>) session.get("cus_user_dis_list");
		Discussion dis = (Discussion) session.get("dis_info");

		int auth = -1;
		int iDisId = 0;

		String disId = "";
		disId = request.getParameter("disId");

		if (cusUserDisList != null && cusUserDisList.size() > 0) {
			if (disId != null) {
				iDisId = Integer.valueOf(disId);

			} else {
				if (dis != null) {
					iDisId = dis.getId();
				}
			}

			for (CusUserDis cus : cusUserDisList) {
				if (cus.getDisId().equals(iDisId)) {
					// 取到控制权限
					auth = cus.getAuth();
					session.put("session_auth", auth);
				}
			}
		}

		if (auth > -1) {
			return invocation.invoke();
		} else {
			/**
			 * 当以下条件成立，则说明存在session数据失效
			 */
			if (cusUserDisList == null || dis == null) {
				return "invalid";
			} else {
				response.getWriter().print(auth);
				return null;
			}
		}
	}
}

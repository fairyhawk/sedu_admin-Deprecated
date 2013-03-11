package com.shangde.common.intercepter;

import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.shangde.edu.sys.action.BackLoginAction;
import com.shangde.edu.sys.condition.QueryFunctionCondition;
import com.shangde.edu.sys.domain.Function;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.IFunction;
import com.shangde.edu.sys.service.IUser;

import java.util.Map;
import java.util.List;

/**
 * 权限拦截器，判断登录用户是否有权限访问当前功能
 *
 * @author guoqiang.liu
 */
@SuppressWarnings("unchecked")
public class LimitIntercepter extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5622350756953568982L;
	private IFunction functionService;
	private IUser userService;
	private User loginedUser;
	public User getLoginedUser() {
		return loginedUser;
	}

	public void setLoginedUser(User loginedUser) {
		this.loginedUser = loginedUser;
	}

	public IUser getUserService() {
		return userService;
	}

	public void setUserService(IUser userService) {
		this.userService = userService;
	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		boolean flag = false;
		Map session = actionInvocation.getInvocationContext().getSession();
		//判断是否有此功能
		ActionProxy sap = actionInvocation.getProxy();
		String invokeUrl = new StringBuffer().append(sap.getNamespace()).append("/").append(sap.getActionName()).
		append("!").append(sap.getMethod()).append(".action").toString();
		List<Function> functionList = functionService.getFunctionList(new QueryFunctionCondition());
		boolean hasFunction = false;
		for (Function function : functionList) {
			if (function.getFunctionUrl() != null && function.getFunctionUrl().indexOf(invokeUrl) != -1){
				hasFunction = true;
			}
		}
		//session
		User user = (User) session.get(BackLoginAction.SYS_USER_SESSION_NAME);
		if(user==null){
			return "reLogin";
		}
		if (hasFunction) {
			List<String>  userfunctionList = (List<String>)session.get("userfunctionList");
			if(userfunctionList!=null){
				for (String function : userfunctionList) {
					if (function!= null && function.indexOf(invokeUrl) != -1){
						flag = true;
					}
				}
			}
		}
		setLoginedUser(user);
		if(!flag&&hasFunction){
			return BackLoginAction.ERROR_LIMIT_VERIFY;
		}else{
			return actionInvocation.invoke();
		}
	}

	public IFunction getFunctionService() {
		return functionService;
	}

	public void setFunctionService(IFunction functionService) {
		this.functionService = functionService;
	}
}

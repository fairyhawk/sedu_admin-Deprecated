package com.shangde.edu.sys.action;

import com.shangde.common.action.CommonAction;

/**
 * iframe跳转 action
 * @author guoqiang.liu
 * @version 1.0
 */
public class BackMainAction extends CommonAction {

	private static final long serialVersionUID = -3023237938807747649L;

	public String topframe(){
		return "topframe";
	}
	public String leftframe(){
		return "leftframe";
	}
	public String switchframe(){
		return "switchframe";
	}
	public String rightframe(){
		return "rightframe";
	}
}

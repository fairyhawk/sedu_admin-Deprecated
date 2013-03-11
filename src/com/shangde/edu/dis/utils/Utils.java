package com.shangde.edu.dis.utils;

import java.util.Map;

public class Utils {

	/**
	 * 系统参数配置
	 */
	private Map<String, String> sysParam;
	/**
	 * 默认显示回复记录数
	 */
	public static int REPLY_DIS_COUNT = 6;

	public Map<String, String> getSysParam() {
		return sysParam;
	}

	public void setSysParam(Map<String, String> sysParam) {
		this.sysParam = sysParam;
	}
}

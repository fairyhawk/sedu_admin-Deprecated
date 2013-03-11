package com.shangde.common.util;

import com.shangde.edu.sys.domain.DicCode;

public class DicCodeSingleton {
	private static DicCode dicCode=null;
	public static DicCode getInstance(){
		if(dicCode==null){
			synchronized (DicCode.class) {
				dicCode=new DicCode();
			}
		}
		return dicCode;
	}
}

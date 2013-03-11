package com.shangde.edu.sys.service;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QuerySignUpCondition;

public interface SignUpService {
	/**
	 * 查询最新注会报考
	 * @param querySignUpCondition
	 * @return
	 */
	public PageResult getSignUpInfoList(QuerySignUpCondition querySignUpCondition);
}

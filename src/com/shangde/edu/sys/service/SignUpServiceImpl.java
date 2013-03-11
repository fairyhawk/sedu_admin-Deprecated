package com.shangde.edu.sys.service;

import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QuerySignUpCondition;

public class SignUpServiceImpl extends BaseServiceManyDb implements SignUpService {

	/**
	 * 查询最新注会报考
	 * @param querySignUpCondition
	 * @return
	 */
	public PageResult getSignUpInfoList(
			QuerySignUpCondition querySignUpCondition) {
		return simpleDaoRead.getPageResult("SignUp_NS.getSignUpInfoList", "SignUp_NS.getSignUpInfoListCount", querySignUpCondition);
	}

}

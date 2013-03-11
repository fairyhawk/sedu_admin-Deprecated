package com.shangde.edu.sp.service;

import java.util.List;
import com.shangde.common.service.BaseService;
import com.shangde.edu.sp.condition.QuerySentPersonInfoCondition;
import com.shangde.edu.sp.domain.SentPerson;
import com.shangde.edu.sp.domain.SentPersonInfo;




public class SentPersonInfoImpl extends BaseService  implements ISentPersonInfo {

	public Integer addSentPersonInfo(SentPersonInfo sentPersonInfo) {
		return simpleDao.createEntity("SentPersonInfo_NS.createSentPersonInfo", sentPersonInfo);
	}

	public void delSentPersonInfoById(int sentPersonInfoId) {
		// TODO Auto-generated method stub
		
	}

	public SentPerson getSentPersonInfoById(int sentPersonInfoId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SentPersonInfo> getSentPersonInfoByList(
			QuerySentPersonInfoCondition querySentPersonInfoCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateSentPersonInfo(SentPersonInfo sentPersonInfo) {
		// TODO Auto-generated method stub
		
	}

	public List<SentPersonInfo> getSPInfoByspId(int spId) {
		
		return simpleDao.getForList("SentPersonInfo_NS.getSentPersonInfoListByspId", spId);
	}

	public void delSPInfoByspId(int spId) {
		simpleDao.delete("SentPersonInfo_NS.delSPInfoByspId", spId);
		
	}


	

    

 
}

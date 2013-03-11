package com.shangde.edu.sp.service;

import java.util.List;
import com.shangde.edu.sp.domain.SentPerson;
import com.shangde.edu.sp.domain.SentPersonInfo;
import com.shangde.edu.sp.condition.QuerySentPersonInfoCondition;


public interface ISentPersonInfo {
	  
    public Integer addSentPersonInfo(SentPersonInfo sentPersonInfo);
   
    public void delSentPersonInfoById(int sentPersonInfoId);
    
    public void delSPInfoByspId(int spId);

    public void updateSentPersonInfo(SentPersonInfo sentPersonInfo);

    public SentPerson getSentPersonInfoById(int sentPersonInfoId);

    public List<SentPersonInfo> getSentPersonInfoByList(QuerySentPersonInfoCondition querySentPersonInfoCondition);
    
    public List<SentPersonInfo> getSPInfoByspId(int spId);
    
    
}

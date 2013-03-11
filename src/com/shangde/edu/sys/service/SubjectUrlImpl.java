package com.shangde.edu.sys.service;

import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.domain.SubjectUrl;

public class SubjectUrlImpl extends BaseService implements ISubjectUrl {
	private static final long serialVersionUID = 1L;

	public SubjectUrl getSubjectListByUserId(int subId) {
		
		return simpleDao.getEntity("SubjectUrl_NS.getSubjectUrlById", subId);
	}

	public Integer addSubjectUrl(SubjectUrl su) {
		return simpleDao.createEntity("SubjectUrl_NS.insertSubject",su);
	}	
	 public Integer insertSubjectUrl_back(SubjectUrl su)
	 {
		return null; 
	 }
	
}

/**
 * 
 */
package com.shangde.edu.sys.webservice;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.domain.Subject;

/**
 * @author	caowei
 * @date	2011-8-9
 * @desc	
 */
public class WebServiceSubjectImpl extends BaseService implements WebServiceSubject {

	/* (non-Javadoc)
	 * @see com.shangde.edu.sys.webservice.WebServiceSubject#getAllSubjects()
	 */
	public List<Subject> getAllSubjects() {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Subject_NS.getAllSubjectForAgent", 1);
	}

}

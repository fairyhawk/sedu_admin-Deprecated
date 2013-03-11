/**
 * 
 */
package com.shangde.edu.sys.webservice;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.dto.SubjectAgentDTO;

/**
 * @author	caowei
 * @date	2011-8-8
 * @desc	
 */
public class SubjectWSImpl extends BaseService implements ISubjectWS {

	public List<SubjectAgentDTO> getAllSubjects() {
		
		List<SubjectAgentDTO> sList = simpleDao.getForList("Subject_NS.getAllSubjectForAgent", 1);

		return sList;
	}
	
	public String test(){
		return "ceshi.....";
	}

}

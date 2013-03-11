/**
 * 
 */
package com.shangde.edu.sys.webservice;

import java.util.List;

import com.shangde.edu.sys.dto.SubjectAgentDTO;

/**
 * @author caowei
 * @date 2011-8-8
 * @desc
 */
public interface ISubjectWS {

	/**
	 * 提供所有的专业
	 * 
	 * @return 专业对象的集合
	 */
	public List<SubjectAgentDTO> getAllSubjects();

	public String test();
	
}

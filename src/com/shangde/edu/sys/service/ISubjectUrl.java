package com.shangde.edu.sys.service;

import java.io.Serializable;
import java.util.*;
import com.shangde.edu.sys.domain.SubjectUrl;

/**
 * 项目url
 * @author wd
 */
public interface ISubjectUrl extends Serializable{

    /**
     * 根据id获取项目列表
     * @param subId 
     * @return SubjectUrl
     * 
     */
	
    public SubjectUrl getSubjectListByUserId(int subId);
    

    public Integer addSubjectUrl(SubjectUrl su);
    public Integer insertSubjectUrl_back(SubjectUrl su);
}
package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryClazzCondition;
import com.shangde.edu.cou.domain.Clazz;

public interface IClazz {
    public java.lang.Integer addClazz(Clazz clazz);
    public void delClazzById(int clazzId);
    public void updateClazz(Clazz clazz);
    public Clazz getClazzById(int clazzId);
    public List<Clazz> getClazzList(QueryClazzCondition queryClazzCondition);
    
    public PageResult getClazzsListByCondition(QueryClazzCondition queryClazzCondition);
    
    /**
	 * 获取班级for TAG
	 * @param queryClazzCondition
	 * @return
	 */
	public List getClazzListForTag(QueryClazzCondition queryClazzCondition);
}
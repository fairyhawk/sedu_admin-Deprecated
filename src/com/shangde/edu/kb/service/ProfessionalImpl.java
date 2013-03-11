package com.shangde.edu.kb.service;

import java.util.List;
import com.shangde.edu.kb.domain.Professional;
import com.shangde.edu.kb.condition.QueryProfessionalCondition;
import com.shangde.common.service.BaseService;

/**
 * Professional操作对象实现
 * User: guoqiang.liu
 * Date: 2010-12-27
 */
public class ProfessionalImpl extends BaseService implements IProfessional{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public java.lang.Integer addProfessional(Professional professional) {
    	return simpleDao.createEntity("Professional_NS.createProfessional",professional);
    }

    public void delProfessionalById(int pId){
        simpleDao.deleteEntity("Professional_NS.deleteProfessionalById",pId);
    }

    public void updateProfessional(Professional professional) {
        simpleDao.updateEntity("Professional_NS.updateProfessional",professional);
    }

    public Professional getProfessionalById(int pId) {
        return simpleDao.getEntity("Professional_NS.getProfessionalById",pId);
    }

    /**
     * @author 代长福
     * 考试后台用到
     */
    public List<Professional> getProfessionalList(QueryProfessionalCondition queryProfessionalCondition) {
        return simpleDao.getForList("Professional_NS.getProfessionalList",queryProfessionalCondition);
    }
    
    public List<Professional> getProfessionalByList(QueryProfessionalCondition queryProfessionalCondition){
    	
    	return simpleDao.getForList("Professional_NS.getProfessionalByList",queryProfessionalCondition);
    }
    public Professional getProfessionalLast(){
    	return simpleDao.getEntity("Professional_NS.getProfessionalLast", "");
    }
    
}

package com.shangde.edu.kno.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.exam.domain.Options;
import com.shangde.edu.kno.condition.QueryQstRelCondition;
import com.shangde.edu.kno.domain.QstRel;
import com.shangde.edu.kno.dto.QstRelDTO;


public class QstRelImpl extends BaseService implements IQstRel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QueryQstRelCondition queryQstRelCondition;

	public java.lang.Integer addQstRel(QstRel qstRel) {
		return simpleDao.createEntity("QstRel_NS.createQstRel",qstRel);
    }

    public void delQstRelById(int qrId){
        simpleDao.deleteEntity("QstRel_NS.deleteQstRelById",qrId);
    }

    public void updateQstRel(QstRel qstRel) {
        simpleDao.updateEntity("QstRel_NS.updateQstRel",qstRel);
    }

    public QstRel getQstRelById(int qrId) {
        return simpleDao.getEntity("QstRel_NS.getQstRelById",qrId);
    }

    public List<QstRel> getQstRelList(QueryQstRelCondition queryQstRelCondition) {
        return simpleDao.getForList("QstRel_NS.getQstRelList",queryQstRelCondition);
    }
    
    /**
     * 根据ksnId获取QstRel对象列表
     * @param ksnId
     * @return
     */
    public List<QstRel> getQstRelListByKsnId(int ksnId){
    	return simpleDao.getForList("QstRel_NS.getQstRelListByKsnId",ksnId);
    }
    
    public List<QstRelDTO> getQstListByKsnIdAndLimNum(int ksnId,int limitNumber){
    	this.getQueryQstRelCondition().setLimitNumber(limitNumber);
    	this.getQueryQstRelCondition().setKsnId(ksnId);
    	return simpleDao.getForList("QstRel_NS.getQstListByKsnId",this.getQueryQstRelCondition());
    }
    
    /**
     * 通过qstId获取试题选项
     * @param qstId
     * @return
     */
    public List<Options> getOptionsListByQstId(int qstId){
    	return simpleDao.getForList("Options_NS.getOptionsListByQstId", qstId);
    }

	public QueryQstRelCondition getQueryQstRelCondition() {
		if(queryQstRelCondition==null){
			queryQstRelCondition=new QueryQstRelCondition();
		}
		return queryQstRelCondition;
	}

	public void setQueryQstRelCondition(QueryQstRelCondition queryQstRelCondition) {
		this.queryQstRelCondition = queryQstRelCondition;
	}
	
    /**
     * 根据试题ID获取试题信息
     * @param qstId
     * @return
     */
    public QstRel getQstRelByQstId(int qstId){
    	return simpleDao.getEntity("QstRel_NS.getQstRelByQstId", qstId);
    }
    /**
     * 根据试题ID删除试题知识点关联信息
     * @author 代长福
     * @param qstId
     * @return
     */
    public int deleteRelByQstId(int qstId){
    	return simpleDao.delete("QstRel_NS.deleteRelByQstId", qstId);
    }
}

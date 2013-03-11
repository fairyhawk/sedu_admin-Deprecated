package com.shangde.edu.exam.service;

import java.util.List;

import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.exam.domain.Qst;
import com.shangde.edu.exam.dto.QstAddQueryDTO;
import com.shangde.edu.exam.condition.QueryQstCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.util.StringUtil;
import com.shangde.common.vo.PageResult;

/**
 * IQst接口的实现类
 * 试题操作类
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public class QstImpl extends BaseService implements IQst{
	/**
     * 添加试题
     * @param qst 试题
     * @return id
     */
    public java.lang.Integer addQst(Qst qst) {
    	return simpleDao.createEntity("Qst_NS.createQst",qst);
    }
    
    /**
     * 通过ID删除试题
     * @param qstId 试题Id
     */
    public void delQstById(int qstId){
        simpleDao.deleteEntity("Qst_NS.deleteQstById",qstId);
    }
    
    /**
     * 更新试题
     * @param qst 试题
     */
    public void updateQst(Qst qst) {
        simpleDao.updateEntity("Qst_NS.updateQst",qst);
    }
    
    /**
     * 通过ID获取试题
     * @param qstId 试题ID
     * @return 试题
     */
    public Qst getQstById(int qstId) {
        return simpleDao.getEntity("Qst_NS.getQstById",qstId);
    }
    
    /**
     * 查询试题信息跳转到添加子题
     * @param qstId
     * @return
     */
    public Qst getQstCaiLiaoToAdd(int qstId){
    	return simpleDao.getEntity("Qst_NS.getQstCaiLiaoToAdd", qstId);
    }
    
    /**
     * 查询试题信息跳转到试题更新页
     * @param qstId
     * @return
     */
    public Qst getQstToUpdateById(int qstId){
    	return simpleDao.getEntity("Qst_NS.getQstToUpdateById", qstId);
    }
    
    /**
     * 根据ID获取试题用过添加试题时 信息记录
     * @author daichangfu
     * @param qstId
     * @return
     */
    public QstAddQueryDTO getQstAddById(int qstId){
    	return simpleDao.getEntity("Qst_NS.getQstAddById",qstId);
    }
    
    /**
     * 通过试卷ID获取试题
     * @param epId 试题ID
     * @return 试题
     */
    public List<Qst> getQstByIdList(int epId) {
        return simpleDao.getForList("Qst_NS.getQstListByEpid",epId);
    }
    
    /**
     * 根据条件获取试题集合
     * @param queryQstCondition 查询条件
     * @return 试题集合
     */
    public List<Qst> getQstList(QueryQstCondition queryQstCondition) {
        return simpleDao.getForList("Qst_NS.getQstList",queryQstCondition);
    }
    
    
    /**
     * 分页查询弹框
     */
    public PageResult getQstListPageAll(QueryQstCondition queryQstCondition){
    	
    	return  simpleDao.getPageResult("Qst_NS.getQstListPage", "Qst_NS.getQstListPageCount", queryQstCondition);
    }
    
    /**
     * 分页查询
     */
    public PageResult getQstListPageAllqst(QueryQstCondition queryQstCondition){
    	/*PageResult pageResult = simpleDao.getPageResult("Qst_NS.getQstListPageqst", "Qst_NS.getQstListPageCountqst", queryQstCondition);
    	//过滤HTML标签  截取题干长度50
    	List<Qst> qstList = pageResult.getPageResult();
    	for (Qst qst : qstList) {
    		String qstContent = StringUtil.filterHTML(qst.getQstContent());
			qstContent = StringUtil.chop(qstContent, 50, "...");
			qst.setQstContent(qstContent);
		}*/
    	return simpleDao.getPageResult("Qst_NS.getQstListPageqst", "Qst_NS.getQstListPageCountqst", queryQstCondition);
    }
    /**
     * 查询所有的课程
     * @return  liming
     */
	public List<Coursesort> getCourseList() {
		return simpleDao.getForList("Qst_NS.getCourseList", null);
	}
	
	@Override
	public List<Qst> getAllQstByType(QueryQstCondition con) {
		return simpleDao.getForList("Qst_NS.getAllQstByType", con);
	}
}

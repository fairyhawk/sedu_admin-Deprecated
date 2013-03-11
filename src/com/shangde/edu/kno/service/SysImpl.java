package com.shangde.edu.kno.service;

import java.util.List;
import com.shangde.edu.kno.domain.Sys;
import com.shangde.edu.kno.dto.QstRelDTO;
import com.shangde.edu.kno.dto.SysDTO;
import com.shangde.edu.kno.condition.QuerySysCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


@SuppressWarnings("unchecked")
public class SysImpl extends BaseService implements ISys{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addSys(Sys sys) {
return simpleDao.createEntity("Sys_NS.createSys",sys);
    }

    public void delSysById(int ksId){
        simpleDao.deleteEntity("Sys_NS.deleteSysById",ksId);
    }

    public void updateSys(Sys sys) {
        simpleDao.updateEntity("Sys_NS.updateSys",sys);
    }

    public Sys getSysById(int ksId) {
        return simpleDao.getEntity("Sys_NS.getSysById",ksId);
    }

    public List<Sys> getSysList(QuerySysCondition querySysCondition) {
        return simpleDao.getForList("Sys_NS.getSysList",querySysCondition);
    }
    /**
     * 获取知识树page对象
     */
    public PageResult listSysByCondition(QuerySysCondition querySysCondition){
    	return simpleDao.getPageResult("Sys_NS.getSysList", "Sys_NS.getSysListCount", querySysCondition);
    }
    
    /**
     * 获取消息列表，只获取审核中（2），发布（3），返工的状态的数据（5）
     * @author 何海强
     */
    
    
    public List<Sys> getSysInFoList(){
    	
    	return simpleDao.getForList("Sys_NS.getSysInFoList", null);
    }
    
    /**
     * 获取审核树里面的知识树信息
     * @return
     */
    public Sys getSysInFoByKsId(int ksId){
    	return simpleDao.getEntity("Sys_NS.getSysInFoByKsId", ksId);
    }
    
    /**
     * 获取发布知识树列表
     * @return
     */
    public List<Sys> getFaBuSysList(){
    	return simpleDao.getForList("Sys_NS.getFaBuSysList", null);
    }
    
    /**
     * 获取知识点对应的试题列表
     * @author 王超
     * @param ksnId
     * @return
     */
    public List<QstRelDTO> getQstListByKsnId(int ksnId){
    	return simpleDao.getForList("Sys_NS.getQstListByKsnId", ksnId);
    }
    
    /**
     * 根据专业ID查询已发布知识树
     * @author 代长福
     * @param subjectId
     * @return
     */
    public List<Sys> getSysBySubjectId(int subjectId){
    	return simpleDao.getForList("Sys_NS.getSysBySubjectId", subjectId);
    }
    
    /**
     * 根据知识点ID查询知识树 
     * @author 代长福
     * @param ksnId
     * @return
     */
    public SysDTO getSysByKsnId(int ksnId){
       	return simpleDao.getEntity("Sys_NS.getSysByKsnId", ksnId);
    }
}

package com.shangde.edu.kno.service;

import java.util.List;

import com.shangde.edu.kno.domain.Sys;
import com.shangde.edu.kno.domain.SysNode;
import com.shangde.edu.kno.dto.SysNodeDTO;
import com.shangde.edu.kno.condition.QuerySysNodeCondition;
import com.shangde.common.service.BaseService;


@SuppressWarnings("unchecked")
public class SysNodeImpl extends BaseService implements ISysNode{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SysNode sysNode;


	public SysNode getSysNode() {
		return sysNode;
	}

	public void setSysNode(SysNode sysNode) {
		this.sysNode = sysNode;
	}

	public java.lang.Integer addSysNode(SysNode sysNode) {
return simpleDao.createEntity("SysNode_NS.createSysNode",sysNode);
    }

    public void delSysNodeById(int ksnId){
        simpleDao.deleteEntity("SysNode_NS.deleteSysNodeById",ksnId);
    }

    public void updateSysNode(SysNode sysNode) {
        simpleDao.updateEntity("SysNode_NS.updateSysNode",sysNode);
    }

    public SysNode getSysNodeById(int ksnId) {
        return simpleDao.getEntity("SysNode_NS.getSysNodeById",ksnId);
    }

    public List<SysNode> getSysNodeList(QuerySysNodeCondition querySysNodeCondition) {
        return simpleDao.getForList("SysNode_NS.getSysNodeList",querySysNodeCondition);
    }
    
    /**
     * 获取子节点数量
     * @param parentId
     * @return
     */
    public int getCountByParentId(int parentId){
    	return simpleDao.getEntity("SysNode_NS.getCountByParentId", parentId);
    }
    
    /**
     * 获取子节点DTO对象列表
     * @param parentId
     * @return
     */
    public List<SysNodeDTO> getSysNodeDTOListByParentId(int parentId){
    	return simpleDao.getForList("SysNode_NS.getSysNodeDTOList", parentId);
    }
    
    /**
     * 获取根节点对象
     * @param parentId
     * @return
     */
    public SysNode getSysNodeBySysId(int ksId){
    	return simpleDao.getEntity("SysNode_NS.getSysNodeBySysId", ksId);
    }
    
    /**
     * 根据条件获取知识树信息
     * @param ksId
     * @return
     */
    public List<SysNode> getSysTreeById(int ksId){
    	return simpleDao.getForList("SysNode_NS.getSysTreeById", ksId);
    }

    /**
     * 获取知识树子节点数量
     * @param ksId
     * @return
     */
    public int getSysNodeCount(int ksId){
    	return simpleDao.getEntity("SysNode_NS.getSysNodeCount", ksId);
    }
    
    /**
     * 查询知识树下某节点子节点排序超过x的节点对象
     * @param querySysNodeCondition
     * @return
     */
    public List<SysNode> getSysNodeListByCondition(QuerySysNodeCondition querySysNodeCondition){
    	return simpleDao.getForList("SysNode_NS.getSysNodeListByCondition", querySysNodeCondition);
    }
    
	public String getSysAuthorByKsId(int ksId) {		
		return simpleDao.getEntity("SysNode_NS.getSysAuthorByKsId", ksId);
	}

	public String getRelateKInfo(int ksnId,StringBuffer relateKInfo) {
		sysNode=simpleDao.getEntity("SysNode_NS.getSysNodeById",ksnId);
		if(ksnId!=-1){
			relateKInfo.append(sysNode.getNodeName()+",");
		}else{
			relateKInfo.append(sysNode.getNodeName());
		}
		while(sysNode.getParentId()!=-1){
			getRelateKInfo(sysNode.getParentId(),relateKInfo);
		}
		return relateKInfo.toString();
	}
	
	 /**王超 开始 */
    
    
    /**
     * @author 王超
     * 通过科目(知识节点)id获取(子节点)知识树
     * @param querySysNodeCondition
     * @return
     */
    public List<SysNode> getSysTreeByKsnId(QuerySysNodeCondition querySysNodeCondition){
    	return simpleDao.getForList("SysNode_NS.getSysTreeByKsnId",querySysNodeCondition);
    }
    
    /**
     * @author 王超
     * 通过专业id获取知识库
     * @param subjectId
     * @return
     */
    public Sys getSysBySubjectId(int subjectId){
    	return simpleDao.getEntity("Sys_NS.getFabuSysBySubjectId", subjectId);
    }
    
    
    /**王超 结束 */
}

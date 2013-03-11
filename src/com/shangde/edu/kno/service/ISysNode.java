package com.shangde.edu.kno.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.edu.kno.condition.QuerySysNodeCondition;
import com.shangde.edu.kno.domain.Sys;
import com.shangde.edu.kno.domain.SysNode;
import com.shangde.edu.kno.dto.SysNodeDTO;

/**
 * SysNode管理接口
 * User: guoqiang.liu
 * Date: 2011-08-31
 */
public interface ISysNode extends Serializable{
    /**
     * 添加SysNode
     * @param sysNode 要添加的SysNode
     * @return id
     */
    public java.lang.Integer addSysNode(SysNode sysNode);

    /**
     * 根据id删除一个SysNode
     * @param ksnId 要删除的id
     */
    public void delSysNodeById(int ksnId);
    
    /**
     * 获取子节点数量
     * @param parentId
     * @return
     */
    public int getCountByParentId(int parentId);
    
    
    /**
     * 获取子节点DTO对象列表
     * @param parentId
     * @return
     */
    public List<SysNodeDTO> getSysNodeDTOListByParentId(int parentId);
    /**
     * 获取根节点对象
     * @param parentId
     * @return
     */
    public SysNode getSysNodeBySysId(int ksId);

    /**
     * 修改SysNode
     * @param sysNode 要修改的SysNode
     */
    public void updateSysNode(SysNode sysNode);

    /**
     * 根据id获取单个SysNode对象
     * @param ksnId 要查询的id
     * @return 年级
     */
    public SysNode getSysNodeById(int ksnId);

    /**
     * 根据条件获取SysNode列表
     * @param querySysNodeCondition 查询条件
     * @return 查询结果
     */
    public List<SysNode> getSysNodeList(QuerySysNodeCondition querySysNodeCondition);
    
    /**
     * 根据条件获取知识树信息
     * @param ksId
     * @return
     */
    public List<SysNode> getSysTreeById(int ksId);
    /**
     * 获取知识树子节点数量
     * @param ksId
     * @return
     */
    public int getSysNodeCount(int ksId);
    
    /**
     * 查询知识树下某节点子节点排序超过x的节点对象
     * @param querySysNodeCondition
     * @return
     */
    public List<SysNode> getSysNodeListByCondition(QuerySysNodeCondition querySysNodeCondition);
    
    /**
     * 根据KSID查询作者，返工时用
     */
    public String getSysAuthorByKsId(int ksId);
    public String getRelateKInfo(int ksnId,StringBuffer strBuffer);
    
    /**
     * @author 王超
     * 通过科目(知识节点)id获取(子节点)知识树
     * @param querySysNodeCondition
     * @return
     */
    public List<SysNode> getSysTreeByKsnId(QuerySysNodeCondition querySysNodeCondition);
    
    /**
     * @author 王超
     * 通过专业id获取知识库
     * @param subjectId
     * @return
     */
    public Sys getSysBySubjectId(int subjectId);
    
    
    /** 王超开始 */
}
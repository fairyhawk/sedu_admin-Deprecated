package com.shangde.edu.kno.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.kno.condition.QuerySysCondition;
import com.shangde.edu.kno.domain.Sys;
import com.shangde.edu.kno.dto.QstRelDTO;
import com.shangde.edu.kno.dto.SysDTO;

/**
 * Sys管理接口
 * User: guoqiang.liu
 * Date: 2011-08-30
 */
public interface ISys extends Serializable{
    /**
     * 添加Sys
     * @param sys 要添加的Sys
     * @return id
     */
    public java.lang.Integer addSys(Sys sys);

    /**
     * 根据id删除一个Sys
     * @param ksId 要删除的id
     */
    public void delSysById(int ksId);

    /**
     * 修改Sys
     * @author 王超
     * @param sys 要修改的Sys
     */
    public void updateSys(Sys sys);

    /**
     * 根据id获取单个Sys对象
     * @param ksId 要查询的id
     * @return 年级
     */
    public Sys getSysById(int ksId);
    
    /**
     * 获取知识树page对象
     * @author 王超
     */
    public PageResult listSysByCondition(QuerySysCondition querySysCondition);

    /**
     * 根据条件获取Sys列表
     * @author 王超
     * @param querySysCondition 查询条件
     * @return 查询结果
     */
    public List<Sys> getSysList(QuerySysCondition querySysCondition);
    
    /**
     * 获取消息列表，只获取审核中（2），发布（3），返工的状态的数据（5）
     * @author 何海强
     */
    
    public List<Sys> getSysInFoList();
    
    /**
     * 获取审核树里面的知识树信息
     * @return
     */
    public Sys getSysInFoByKsId(int ksId);
    
    /**
     * 获取发布知识树列表
     * @author 王超
     * @return
     */
    public List<Sys> getFaBuSysList();
    
    /**
     * 获取知识点对应的试题列表
     * @author 王超
     * @param ksnId
     * @return
     */
    public List<QstRelDTO> getQstListByKsnId(int ksnId);
    
    /**
     * 根据专业ID查询已发布知识树
     * @author 代长福
     * @param subjectId
     * @return
     */
    public List<Sys> getSysBySubjectId(int subjectId);
    
    /**
     * 根据知识点ID查询知识树 
     * @author 代长福
     * @param ksnId
     * @return
     */
    public SysDTO getSysByKsnId(int ksnId);
    
}
package com.shangde.edu.kno.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.edu.exam.domain.Options;
import com.shangde.edu.kno.domain.QstRel;
import com.shangde.edu.kno.dto.QstRelDTO;
import com.shangde.edu.kno.condition.QueryQstRelCondition;

/**
 * QstRel管理接口
 * User: guoqiang.liu
 * Date: 2011-09-14
 */
public interface IQstRel extends Serializable {
    /**
     * 添加QstRel
     * @param qstRel 要添加的QstRel
     * @return id
     */
    public java.lang.Integer addQstRel(QstRel qstRel);

    /**
     * 根据id删除一个QstRel
     * @param qrId 要删除的id
     */
    public void delQstRelById(int qrId);

    /**
     * 修改QstRel
     * @param qstRel 要修改的QstRel
     */
    public void updateQstRel(QstRel qstRel);

    /**
     * 根据id获取单个QstRel对象
     * @param qrId 要查询的id
     * @return 年级
     */
    public QstRel getQstRelById(int qrId);
    
    /**
     * 根据ksnId获取QstRel对象列表
     * @param ksnId
     * @return
     */
    public List<QstRel> getQstRelListByKsnId(int ksnId);
    
    /**
     * 通过qstId获取试题选项
     * @param qstId
     * @return
     */
    public List<Options> getOptionsListByQstId(int qstId);
    
    /**
     * 通过ksnId 和limitNumber查询对应试题列表
     * @param ksnId 知识点id
     * @param limitNumber 限制题数
     * @return
     */
    public List<QstRelDTO> getQstListByKsnIdAndLimNum(int ksnId,int limitNumber);

    /**
     * 根据条件获取QstRel列表
     * @param queryQstRelCondition 查询条件
     * @return 查询结果
     */
    public List<QstRel> getQstRelList(QueryQstRelCondition queryQstRelCondition);
    
    /**
     * 根据试题ID获取试题信息
     * @author 代长福
     * @param qstId
     * @return
     */
    public QstRel getQstRelByQstId(int qstId);
    
    /**
     * 根据试题ID删除试题知识点关联信息
     * @author 代长福
     * @param qstId
     * @return
     */
    public int deleteRelByQstId(int qstId);
}
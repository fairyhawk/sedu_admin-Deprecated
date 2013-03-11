package com.shangde.edu.kb.service;

import java.util.List;
import com.shangde.edu.kb.domain.Section;
import com.shangde.edu.kb.condition.QuerySectionCondition;

/**
 * Section管理接口
 * User: guoqiang.liu
 * Date: 2010-12-27
 */
public interface ISection {
    /**
     * 添加Section
     * @param section 要添加的Section
     * @return id
     */
    public java.lang.Integer addSection(Section section);

    /**
     * 删除Section
     * @param sId 要删除的id
     */
    public void delSectionById(int sId);

    /**
     * 修改Section޸
     * @param section 要修改的Section
     */
    public void updateSection(Section section);

    /**
     * 根据id获取单个Section对象
     * @param sId 查询id
     * @return Section
     */
    public Section getSectionById(int sId);

    /**
     * 分页查询
     * @param querySectionCondition 查询条件
     * @return 分页查询结果
     */
    public List<Section> getSectionList(QuerySectionCondition querySectionCondition);
    public List<Section> getSectionByList(QuerySectionCondition querySectionCondition);
}
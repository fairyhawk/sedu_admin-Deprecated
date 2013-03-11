package com.shangde.edu.kb.service;

import java.util.List;
import com.shangde.edu.kb.domain.Chapter;
import com.shangde.edu.kb.condition.QueryChapterCondition;


public interface IChapter {
    /**
     * 添加chapter
     * @param chapter 要添加的chapter
     * @return id
     */
    public java.lang.Integer addChapter(Chapter chapter);

    /**
     * 根据chId删除一个chapter
     * @param chId 要删除的id
     */
    public void delChapterById(int chId);

    /**
     * 修改chapter޸
     * @param chapter 要修改的chapter
     */
    public void updateChapter(Chapter chapter);

    /**
     * 根据chId获取单个chapter
     * @param chId 要查询的chId
     * @return Chapter章信息
     */
    public Chapter getChapterById(int chId);

    /**
     * @param queryChapterCondition 查询条件
     * @return 分页结果集合
     */
    public List<Chapter> getChapterList(QueryChapterCondition queryChapterCondition);
    
    public List<Chapter> getChapterByList(QueryChapterCondition queryChapterCondition);
    
    
}
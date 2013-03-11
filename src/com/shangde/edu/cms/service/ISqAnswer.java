package com.shangde.edu.cms.service;

import java.util.List;
import com.shangde.edu.cms.domain.SqAnswer;
import com.shangde.edu.cms.condition.QuerySqAnswerCondition;

/**
 * SqAnswer管理接口
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
public interface ISqAnswer {
   
	 /**
     * 添加addSqAnswer
     * @param sqAnswer 要添加的SqAnswer
     * @return id
     */
    public java.lang.Integer addSqAnswer(SqAnswer sqAnswer);

    /**
     * 根据id删除一个SqAnswer
     * @param asId 要删除的id
     */
    public void delSqAnswerById(int asId);

    /**
     * 修改SqAnswer
     * @param sqAnswer 要修改的SqAnswer
     */
    public void updateSqAnswer(SqAnswer sqAnswer);


    /**
     * 根据id获取单个SqAnswer对象
     * @param asId 要查询的id
     * @return SqAnswer
     */
    public SqAnswer getSqAnswerById(int asId);

    /**
     * 根据条件获取SqAnswer列表
     * @param QuerySqAnswerCondition 查询条件
     * @return 查询结果
     */
    public List<SqAnswer> getSqAnswerList(QuerySqAnswerCondition querySqAnswerCondition);
}
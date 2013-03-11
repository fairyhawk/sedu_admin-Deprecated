package com.shangde.edu.freshnews.service;

import java.util.List;

import com.shangde.edu.cus.domain.ReProblem;
import com.shangde.edu.freshnews.domain.ActionReply;
import com.shangde.edu.freshnews.condition.QueryActionReplyCondition;

/**
 * ActionReply����ӿ�
 * User: guoqiang.liu
 * Date: 2012-06-14
 */
public interface IActionReply {
    /**
     * ���ActionReply
     * @param actionReply 
     * @return 
     * @return id
     */
    public  void addActionReply(ActionReply actionReply);
    /**
     * 通过highso问答的回复来添加新鲜事回复
     */
    void addActionReplyByReProblem(ReProblem reProblem);
}
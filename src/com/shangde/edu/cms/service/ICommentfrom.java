package com.shangde.edu.cms.service;

import java.util.List;
import com.shangde.edu.cms.domain.Commentfrom;
import com.shangde.edu.cms.condition.QueryCommentfromCondition;

/**
 * Commentfrom管理接口
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
public interface ICommentfrom {
    /**
     * 添加Commentfrom
     * @param commentfrom 要添加的Commentfrom
     * @return id
     */
    public java.lang.Integer addCommentfrom(Commentfrom commentfrom);

    /**
     * 根据id删除一个Commentfrom
     * @param cfId 要删除的id
     */
    public void delCommentfromById(int cfId);

    /**
     * 修改Commentfrom
     * @param commentfrom 要修改的Commentfrom
     */
    public void updateCommentfrom(Commentfrom commentfrom);

    /**
     * 根据id获取单个Commentfrom对象
     * @param cfId 要查询的id
     * @return 年级
     */
    public Commentfrom getCommentfromById(int cfId);

    /**
     * 根据条件获取Commentfrom列表
     * @param queryCommentfromCondition 查询条件
     * @return 查询结果
     */
    public List<Commentfrom> getCommentfromList(QueryCommentfromCondition queryCommentfromCondition);
}
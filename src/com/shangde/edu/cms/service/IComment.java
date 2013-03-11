package com.shangde.edu.cms.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryCommentCondition;
import com.shangde.edu.cms.domain.Comment;
import com.shangde.edu.cms.domain.Commentfrom;
import com.shangde.edu.cms.dto.ExportCommentDTO;

/**
 * Comment管理接口
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
public interface IComment {
    /**
     * 添加Comment
     * @param comment 要添加的Comment
     * @return id
     */
    public java.lang.Integer addComment(Comment comment);

    /**
     * 根据id删除一个Comment
     * @param cmtId 要删除的id
     */
    public void delCommentById(int cmtId);

    /**
     * 修改Comment
     * @param comment 要修改的Comment
     */
    public void updateComment(Comment comment);

    /**
     * 根据id获取单个Comment对象
     * @param cmtId 要查询的id
     * @return 年级
     */
    public Comment getCommentById(int cmtId);

    /**
     * 根据条件获取Comment列表
     * @param queryCommentCondition 查询条件
     * @return 查询结果
     */
    public List<Comment> getCommentList(QueryCommentCondition queryCommentCondition);
    
    /**
     * 根据条件查询comment列表，并分页
     * @param queryCommentCondition
     * @return 查询结果
     */
	public PageResult getCommentListByCondition(PageQuery queryCommentCondition);

	/**
	 * 获取所有评论来源
	 * @return
	 */
	public List<Commentfrom> getCommentFromList();


    /**
     * 根据来源和资源查询，并分页
     * @param queryCommentCondition 查询条件，来源id和资源id
     * @return 分页查询结果
     */
	public PageResult getCommentListBySource(
			PageQuery queryCommentCondition);

	/**
	 * 根据来源和资源，查询好评、中评以及差评的数量
	 * @param queryCommentCondition 查询条件
	 * @return 查询结果，数量的List
	 */
	public List<Integer> getGradeCountBySource(
			QueryCommentCondition queryCommentCondition);

	/**
	 * 根据来源和资源，查询最近评论的内容
	 * @param queryCommentCondition 查询条件，来源id和资源id
	 * @return 查询结果，最新评论内容
	 */
	public String getNewCommentBySource(
			QueryCommentCondition queryCommentCondition);

	/**
	 * 根据资源id及来源id获取评论数
	 * @param queryCommentCondition 资源id及来源id
	 * @return
	 */
	public Integer getCmtCount(QueryCommentCondition queryCommentCondition);

	/**
	 * 获取意见建议列表
	 * @return
	 */
	public PageResult getAdviceList(PageQuery queryCommentCondition);

	/**
	 * 查询意见建议
	 * @param queryCommentCondition
	 * @return
	 */
	public PageResult getAdviceListByCondition(
			PageQuery queryCommentCondition);

	/**
	 * 根据id获取回复
	 * @param cmtId
	 * @return
	 */
	public List<Comment> getReplyById(Map<String, Integer> param);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Comment getReplyById(int id);

	/**
	 * 根据用户id获取上次意见
	 * @param loginUserId
	 * @return
	 */
	public Comment getLastAdviceByCus(int userId);

	/**
	 * 为标签处理准备方法(静态化)
	 * @param queryCommentCondition
	 * @return
	 */
	public List<Comment> getCommentListByClazzIdForTag(
			QueryCommentCondition queryCommentCondition);

	/**
	 * 删除意见回复
	 * @param cmtId
	 */
	public void delAdviceReply(Integer cmtId);
	
	public Integer getCmtCountBySource(QueryCommentCondition condition);
	
	

    /**                留言开始  王超                */
    /**
     * 根据条件获取留言列表
	 * @author 王超
     * @param queryCommentCondition 查询条件
     * @return 查询结果
     */
    public PageResult getAdviceMsgList(QueryCommentCondition queryCommentCondition);
    
    /**
	 * 通过sourceId获取回复列表
	 * @author 王超
	 * @param queryCommentCondition
	 * @return
	 */
	public List<Comment> getCommentMsgListBySource(QueryCommentCondition queryCommentCondition);

	/**
	 * 通过sourceId获取系统用户回复信息
	 * @param id
	 * @return
	 */
	public List<Comment> getReplyBySourceId(int id);
	
	/**
	 * 根据id获取留言信息及管理员回复 
	 * @author 王超
	 */
	public Comment getCommentMsgById(int cmtId) ;
	/**
	 * 导出留言信息及管理员回复 
	 * @param queryCommentCondition
	 * @return
	 */
	public List<ExportCommentDTO> exportComment(QueryCommentCondition queryCommentCondition);
	/**                留言结束  王超                */
}
package com.shangde.edu.cms.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryCommentCondition;
import com.shangde.edu.cms.domain.Comment;
import com.shangde.edu.cms.domain.Commentfrom;
import com.shangde.edu.cms.dto.ExportCommentDTO;

/**
 * Comment对象操作实现类 User: guoqiang.liu Date: 2010-07-14
 */
@SuppressWarnings("unchecked")
public class CommentImpl extends BaseService implements IComment {
	public java.lang.Integer addComment(Comment comment) {
		return simpleDao.createEntity("Comment_NS.createComment", comment);
	}

	public void delCommentById(int cmtId) {
		simpleDao.deleteEntity("Comment_NS.deleteCommentById", cmtId);
	}

	public void updateComment(Comment comment) {
		simpleDao.updateEntity("Comment_NS.updateComment", comment);
	}

	public Comment getCommentById(int cmtId) {
		return simpleDao.getEntity("Comment_NS.getCommentById", cmtId);
	}
	
	public List<Comment> getCommentList(
			QueryCommentCondition queryCommentCondition) {
		return simpleDao.getForList("Comment_NS.getCommentList",
				queryCommentCondition);
	}

	public PageResult getCommentListByCondition(
			PageQuery queryCommentCondition) {
		return simpleDao.getPageResult("Comment_NS.getCommentListByCondition", "Comment_NS.getCommentCountByCondition", queryCommentCondition);
	}

	public List<Commentfrom> getCommentFromList() {
		return simpleDao.getForList("Commentfrom_NS.getCommentfromList", null);
	}

	public PageResult getCommentListBySource(PageQuery queryCommentCondition) {
		return simpleDao.getPageResult("Comment_NS.getCommentListBySource", "Comment_NS.getCommentCountBySource", queryCommentCondition);
	}
	
	public List<Integer> getGradeCountBySource(
			QueryCommentCondition queryCommentCondition) {
		return simpleDao.getForList("Comment_NS.getGradeCountBySource",
				queryCommentCondition);
	}

	public String getNewCommentBySource(
			QueryCommentCondition queryCommentCondition) {
		return simpleDao.getEntity("Comment_NS.getNewCommentBySource", queryCommentCondition);
	}

	public Integer getCmtCount(QueryCommentCondition queryCommentCondition) {
		return simpleDao.getEntity("Comment_NS.getCmtCount", queryCommentCondition);
	}

	public PageResult getAdviceList(PageQuery queryCommentCondition) {
		return simpleDao.getPageResult("Comment_NS.getAdviceList", "Comment_NS.getAdviceCount", queryCommentCondition);
	}

	public PageResult getAdviceListByCondition(
			PageQuery queryCommentCondition) {
		return simpleDao.getPageResult("Comment_NS.getAdviceListByCondition", "Comment_NS.getAdviceCountByCondition", queryCommentCondition);
	}

	public List<Comment> getReplyById(Map<String, Integer> param) {
		return simpleDao.getForList("Comment_NS.getReplyById", param);
	}
	

	public Comment getReplyById(int id) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Comment_NS.getReplyById", id);
	}

	public Comment getLastAdviceByCus(int userId) {
		return simpleDao.getEntity("Comment_NS.getLastAdviceByCus", userId);
	}

	public List<Comment> getCommentListByClazzIdForTag(
			QueryCommentCondition queryCommentCondition) {
		return simpleDao.getForList("Comment_NS.getCommentListByClazzIdForTag",
				queryCommentCondition);
	}

	public void delAdviceReply(Integer cmtId) {
		simpleDao.deleteEntity("Comment_NS.delAdviceReply", cmtId);
	}
	public Integer getCmtCountBySource(QueryCommentCondition condition){
		return simpleDao.getEntity("Comment_NS.getCommentCountBySource", condition);
	}
	
	/**                留言开始  王超                */

	/**
     * 根据条件获取留言列表
	 * @author 王超
     * @param queryCommentCondition 查询条件
     * @return 查询结果
     */
    public PageResult getAdviceMsgList(QueryCommentCondition queryCommentCondition){
    	return simpleDao.getPageResult("Comment_NS.getCommentMsgList","Comment_NS.getCommentMsgCount", queryCommentCondition);
    }
    
    /**
	 * 通过sourceId获取回复列表
	 * @author 王超
	 * @param queryCommentCondition
	 * @return
	 */
	public List<Comment> getCommentMsgListBySource(QueryCommentCondition queryCommentCondition){
		return simpleDao.getForList("Comment_NS.getCommentMsgListBySource", queryCommentCondition);
	}
	/**
	 * 通过sourceId获取系统用户回复信息
	 * @param id
	 * @return
	 */
	public List<Comment> getReplyBySourceId(int id) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Comment_NS.getReplyBySourceId", id);
	}
	/**
	 * 根据id获取该留言信息的管理员回复信息 
	 * @author 王超
	 */
	public Comment getCommentMsgById(int cmtId) {
		return simpleDao.getEntity("Comment_NS.getCommentMsgById", cmtId);
	}
	
	/**
	 * 导出留言信息及管理员回复 
	 * @param queryCommentCondition
	 * @return
	 */
	public List<ExportCommentDTO> exportComment(QueryCommentCondition queryCommentCondition){
		return simpleDao.getForList("Comment_NS.exportComment", queryCommentCondition);
	}
	
	
	/**                留言结束  王超                */
}

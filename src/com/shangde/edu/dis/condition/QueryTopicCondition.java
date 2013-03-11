package com.shangde.edu.dis.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

/**
 * 话题表查询类
 * 
 * @author Basil
 *
 */
public class QueryTopicCondition extends PageQuery implements java.io.Serializable{
	private int id;// 主键 id
	private String title;// 100 标题
	private String content;// 内容
	private int cusId;// 创建人
	private int disId;// 所属组
	private int disAreaId;// 所属区域
	private int isVote;// 是否投票
	private int voteId;// 投票ID
	private Date createTime;// 创建时间
	private Date replyTime;// 最后回复时间
	private int clickCounts;// 点击次数
	private int replyCounts;// 回复次数
	private int recNum;// 推荐积分
	private int recCount;// 默认值0 推荐次数
	private int canReply;// 默认值0 是否允许回复
	private int isTop;// 默认值0 是否置顶
	
	private String keyWorld;//搜索关键词
	private String searchCriteria;//检索条件
	private int subjectId = -1;//项目ID，科目id
	private int type = -1;//帖子类型
	private int status = -1;//帖子状态
	private String createTimeStart;//发布时间，起
	private String createTimeEnd;//发布时间，结
	private String modifiedStart;//修改时间，起
	private String modifiedEnd;//修改时间，结
	
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the cusId
	 */
	public int getCusId() {
		return cusId;
	}
	/**
	 * @param cusId the cusId to set
	 */
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	/**
	 * @return the disId
	 */
	public int getDisId() {
		return disId;
	}
	/**
	 * @param disId the disId to set
	 */
	public void setDisId(int disId) {
		this.disId = disId;
	}
	/**
	 * @return the disAreaId
	 */
	public int getDisAreaId() {
		return disAreaId;
	}
	/**
	 * @param disAreaId the disAreaId to set
	 */
	public void setDisAreaId(int disAreaId) {
		this.disAreaId = disAreaId;
	}
	/**
	 * @return the isVote
	 */
	public int getIsVote() {
		return isVote;
	}
	/**
	 * @param isVote the isVote to set
	 */
	public void setIsVote(int isVote) {
		this.isVote = isVote;
	}
	/**
	 * @return the voteId
	 */
	public int getVoteId() {
		return voteId;
	}
	/**
	 * @param voteId the voteId to set
	 */
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the replyTime
	 */
	public Date getReplyTime() {
		return replyTime;
	}
	/**
	 * @param replyTime the replyTime to set
	 */
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	/**
	 * @return the clickCounts
	 */
	public int getClickCounts() {
		return clickCounts;
	}
	/**
	 * @param clickCounts the clickCounts to set
	 */
	public void setClickCounts(int clickCounts) {
		this.clickCounts = clickCounts;
	}
	/**
	 * @return the replyCounts
	 */
	public int getReplyCounts() {
		return replyCounts;
	}
	/**
	 * @param replyCounts the replyCounts to set
	 */
	public void setReplyCounts(int replyCounts) {
		this.replyCounts = replyCounts;
	}
	/**
	 * @return the recNum
	 */
	public int getRecNum() {
		return recNum;
	}
	/**
	 * @param recNum the recNum to set
	 */
	public void setRecNum(int recNum) {
		this.recNum = recNum;
	}
	/**
	 * @return the recCount
	 */
	public int getRecCount() {
		return recCount;
	}
	/**
	 * @param recCount the recCount to set
	 */
	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}
	/**
	 * @return the canReply
	 */
	public int getCanReply() {
		return canReply;
	}
	/**
	 * @param canReply the canReply to set
	 */
	public void setCanReply(int canReply) {
		this.canReply = canReply;
	}
	/**
	 * @return the isTop
	 */
	public int getIsTop() {
		return isTop;
	}
	/**
	 * @param isTop the isTop to set
	 */
	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}
	public String getKeyWorld() {
		return keyWorld;
	}
	public void setKeyWorld(String keyWorld) {
		this.keyWorld = keyWorld;
	}
	public String getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public String getModifiedStart() {
		return modifiedStart;
	}
	public void setModifiedStart(String modifiedStart) {
		this.modifiedStart = modifiedStart;
	}
	public String getModifiedEnd() {
		return modifiedEnd;
	}
	public void setModifiedEnd(String modifiedEnd) {
		this.modifiedEnd = modifiedEnd;
	}
	

}
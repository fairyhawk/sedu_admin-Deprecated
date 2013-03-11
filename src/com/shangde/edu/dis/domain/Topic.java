package com.shangde.edu.dis.domain;

import java.io.Serializable;
import java.util.Date;

import com.shangde.edu.cus.domain.Customer;

/**
 * 
 * @author ...
 * @author Libg
 * 
 */
public class Topic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2780125588332824361L;

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
	private Date modified;//修改时间
	private int clickCounts;// 点击次数
	private int replyCounts;// 回复次数
	private int recNum;// 推荐积分
	private int recCount;// 默认值0 推荐次数
	private int canReply;// 默认值0 是否允许回复
	private int isTop;// 默认值0 是否置顶
	private Customer customer;
	private int type;
	private int status;
	private int voteCount;// 投票次数
	private String sysUserName;//管理员设置的名称
	private String tags;//标签项,格式：A,B,C 之间“,”逗号隔开
	private String picUrl;//图片地址，新版添加时可以附带图片
	
	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return title + "-isTop" + isTop + "-content>" + content;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getDisId() {
		return disId;
	}

	public void setDisId(int disId) {
		this.disId = disId;
	}

	public int getDisAreaId() {
		return disAreaId;
	}

	public void setDisAreaId(int disAreaId) {
		this.disAreaId = disAreaId;
	}

	public int getIsVote() {
		return isVote;
	}

	public void setIsVote(int isVote) {
		this.isVote = isVote;
	}

	public int getVoteId() {
		return voteId;
	}

	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public int getClickCounts() {
		return clickCounts;
	}

	public void setClickCounts(int clickCounts) {
		this.clickCounts = clickCounts;
	}

	public int getReplyCounts() {
		return replyCounts;
	}

	public void setReplyCounts(int replyCounts) {
		this.replyCounts = replyCounts;
	}

	public int getRecNum() {
		return recNum;
	}

	public void setRecNum(int recNum) {
		this.recNum = recNum;
	}

	public int getRecCount() {
		return recCount;
	}

	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}

	public int getCanReply() {
		return canReply;
	}

	public void setCanReply(int canReply) {
		this.canReply = canReply;
	}

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}

package com.shangde.edu.dis.web.action;

import java.util.HashMap;
import java.util.Map;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.dis.domain.VoteDetail;
import com.shangde.edu.dis.domain.VoteLog;
import com.shangde.edu.dis.service.ITopic;
import com.shangde.edu.dis.service.IVoteDetail;
import com.shangde.edu.dis.service.IVoteLog;

public class VoteWebAction extends CommonAction {

	
	/*****V1.0属性声明区域 开始*****/
	private IVoteDetail voteDetailService;
	private IVoteLog voteLogService;
	private ITopic topicService;

	private VoteDetail voteDetail;
	private Integer voteType;

	private Integer topicId;// 话题id
	private Integer voteId;// 投票id

	private Integer voteRadio;// 单选组件其中的一个值
	private String[] voteCheckbox;// 多选框组件中的所有所选值
	/*****V1.0属性声明区域 结束*****/
	
	
	
	/*****V1.1属性声明区域 开始*****/
	
	
	
	
	
	
	
	/*****V1.1属性声明区域 结束*****/
	
	
	
	
	
	/*****V1.1方法(method)声明区域 开始*****/
	
	
	
	
	
	
	
	/*****V1.1方法(method)声明区域 结束*****/
	
	

	
	/**
	 * 投票
	 * 
	 * @return
	 */
	public String vote() {

		Integer loginUserId = getLoginUserId();

		StringBuffer voteDetailIds = new StringBuffer();
		Map<String, Integer> map = new HashMap<String, Integer>();

		map.put("voteId", voteId);
		map.put("cusId", loginUserId);

		// 记录某用户投票信息
		VoteLog voteLog = new VoteLog();
		voteLog.setCusId(loginUserId);
		voteLog.setVoteId(voteId);

		// 记录投票信息
		if (voteType.intValue() == 0) {//单选投票
			voteLog.setVoteDetailId(String.valueOf(voteRadio));
			voteDetailService.updateVoteDetailCount(voteRadio);
		} else {//多选投票
			if (voteCheckbox != null) {
				/**
				 * 批量修改
				 */
				for (String s : voteCheckbox) {
					voteDetailIds.append(s).append(",");
					voteDetailService
							.updateVoteDetailCount(Integer.parseInt(s));
				}
				// 复选框时，记录所选id值
				voteLog.setVoteDetailId(voteDetailIds.substring(0,
						voteDetailIds.length() - 1).toString());
			}
		}

		/**
		 * 添加
		 */
		voteLogService.addVoteLog(voteLog);

		/**
		 * 记录投票次数++
		 */
		Map<String, Integer> voteMap = new HashMap<String, Integer>();
		voteMap.put("incremental", 1);
		voteMap.put("id", topicId);
		topicService.updateTopicVoteCount(voteMap);

		return "topic_info";
	}

	public Integer getVoteType() {
		return voteType;
	}

	public void setVoteType(Integer voteType) {
		this.voteType = voteType;
	}

	public VoteDetail getVoteDetail() {
		return voteDetail;
	}

	public void setVoteDetail(VoteDetail voteDetail) {
		this.voteDetail = voteDetail;
	}

	public Integer getVoteRadio() {
		return voteRadio;
	}

	public void setVoteRadio(Integer voteRadio) {
		this.voteRadio = voteRadio;
	}

	public String[] getVoteCheckbox() {
		return voteCheckbox;
	}

	public void setVoteCheckbox(String[] voteCheckbox) {
		this.voteCheckbox = voteCheckbox;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public IVoteDetail getVoteDetailService() {
		return voteDetailService;
	}

	public void setVoteDetailService(IVoteDetail voteDetailService) {
		this.voteDetailService = voteDetailService;
	}

	public IVoteLog getVoteLogService() {
		return voteLogService;
	}

	public void setVoteLogService(IVoteLog voteLogService) {
		this.voteLogService = voteLogService;
	}

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}

	public ITopic getTopicService() {
		return topicService;
	}

	public void setTopicService(ITopic topicService) {
		this.topicService = topicService;
	}

}

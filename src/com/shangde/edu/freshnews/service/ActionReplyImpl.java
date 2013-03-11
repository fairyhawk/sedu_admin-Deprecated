package com.shangde.edu.freshnews.service;


import com.shangde.edu.cus.domain.ReProblem;
import com.shangde.edu.freshnews.domain.ActionRecord;
import com.shangde.edu.freshnews.domain.ActionReply;
import com.shangde.common.service.BaseService;

public class ActionReplyImpl extends BaseService implements IActionReply{
	private IActionRecord actionRecordService;//新鲜事记录service
	public IActionRecord getActionRecordService() {
		return actionRecordService;
	}
	public void setActionRecordService(IActionRecord actionRecordService) {
		this.actionRecordService = actionRecordService;
	}
	public void addActionReply(ActionReply actionReply) {
		simpleDao.createEntity("ActionReply_NS.createActionReply", actionReply);
	}
	/**
     * 通过highso问答的回复来添加新鲜事回复
     */
	public void addActionReplyByReProblem(ReProblem reProblem) {
		ActionReply actionReply=new ActionReply();
		//填充ActionReply
		fillActionReply(reProblem,actionReply);
		if(actionReply.getActionId()!=null){
			addActionReply(actionReply);
		}
	}
	 /**
	  * 根据highso问答中回答的信息填充新鲜事回复中
	  * @param reProblem
	  * @return
	  */
	private  void fillActionReply(ReProblem reProblem,ActionReply actionReply){
		ActionRecord actionRecord=actionRecordService.getActionRecordByProblemId(reProblem.getPblId());
		if(actionRecord!=null){
			actionRecord.setIsAnswer(1);
			//将新鲜事记录的is_answer更新为1：已回答
			actionRecordService.updateActionRecord(actionRecord); 
			actionReply.setActionId(actionRecord.getId());
			actionReply.setContent(reProblem.getReInfo());
			actionReply.setCreateTime(reProblem.getReTime());
			actionReply.setOperateType(1);//表示highso问答
			actionReply.setOperateUserName("嗨学网回答");
			actionReply.setReplyFrom(1);
		}
	}
}

package com.shangde.edu.cus.service;

import java.util.List;
import com.shangde.edu.cus.domain.ReProblem;
import com.shangde.edu.cus.condition.QueryReProblemCondition;
import com.shangde.edu.freshnews.service.IActionReply;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


@SuppressWarnings("unchecked")
public class ReProblemImpl extends BaseService implements IReProblem{
	private IActionReply actionReplyService;
    public IActionReply getActionReplyService() {
		return actionReplyService;
	}

	public void setActionReplyService(IActionReply actionReplyService) {
		this.actionReplyService = actionReplyService;
	}

	public java.lang.Integer addReProblem(ReProblem reProblem) {
    	//根据问题回复的信息添加新鲜事回复信息
    	actionReplyService.addActionReplyByReProblem(reProblem);
    	return simpleDao.createEntity("ReProblem_NS.createReProblem",reProblem);
    }

    public void delReProblemById(int reId){
        simpleDao.deleteEntity("ReProblem_NS.deleteReProblemById",reId);
    }

    public void updateReProblem(ReProblem reProblem) {
        simpleDao.updateEntity("ReProblem_NS.updateReProblem",reProblem);
    }

    public ReProblem getReProblemById(int reId) {
        return simpleDao.getEntity("ReProblem_NS.getReProblemById",reId);
    }

    public List<ReProblem> getReProblemList(QueryReProblemCondition queryReProblemCondition) {
        return simpleDao.getForList("ReProblem_NS.getReProblemList",queryReProblemCondition);
    }

	public PageResult getPageReProblemList(QueryReProblemCondition queryReProblemCondition) {
		
		return simpleDao.getPageResult("ReProblem_NS.getReProblemByList", "ReProblem_NS.getReProblemCountByList", queryReProblemCondition);
	}
	
	/**
	 * @author cxs
	 * 功能：获取用户回复数量
	 * @param queryReProblemCondition
	 * @return
	 */
	public Integer getCountByCusId(QueryReProblemCondition queryReProblemCondition){
		return simpleDao.getEntity("ReProblem_NS.getCountByCusId", queryReProblemCondition);
	}

	public Integer delBathReProblemByCusIds(String ids) {
		return simpleDao.delete("ReProblem_NS.deleteReProblemByCusIds", ids);
	}
	
}

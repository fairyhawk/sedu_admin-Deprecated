package com.shangde.edu.sys.service;

import java.util.Date;
import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.domain.WebFromAgent;
import com.shangde.edu.sys.condition.QueryWebFromAgentCondition;

public class IWebFromAgentImpl extends BaseServiceManyDb implements IWebFromAgent {

	/**
	 * Author:yanbaixi
	 * 添加WebFromAgent
	 * @param webfromagent
	 * @return
	 */
	public Integer addWebFromAgent(WebFromAgent webfromagent) {
		webfromagent.setCreateTime(new Date());
		int i = simpleDao.createEntity("WebFromAgent_NS.createWebFromAgent",webfromagent);
		System.out.println("fgdfgdf");
		return i;
	}
	/**
	 * Author:yanbaixi
	 *  根据id删除WebFromAgent
	 */
	public void deleteWebFromAgentById(int id) {
		simpleDao.deleteEntity("WebFromAgent_NS.deleteWebFromById", id);
	}
	/**
	 * Author:yanbaixi
	 * 根据id查询单个webfromAgent信息
	 */
	public WebFromAgent getWebFromAgentById(int id){
		return simpleDao.getEntity("WebFromAgent_NS.getWebFromAgentById", id);
	}
	/**
	 * Author:yanbaixi
	 * 根据id修改webfromAgent信息
	 */
	public void updateWebFromAgentById(WebFromAgent webFromAgent){
		simpleDao.updateEntity("WebFromAgent_NS.updateWebFromAgentById", webFromAgent);
	}
	/**
	 * Author:yanbaixi
	 * 查询所有的webFromAgent信息
	 */
	public List<WebFromAgent> getAllWebAgentList(QueryWebFromAgentCondition queryWebFromAgentCondition){
		return simpleDao.getForList("WebFromAgent_NS.getAllWebFromAgentList", queryWebFromAgentCondition);
	}
	/**
	 * Author:yanbaixi
	 * 统计全部WebFromAgent信息
	 */
	public PageResult getCountWebFromAgentList(QueryWebFromAgentCondition queryWebFromAgentCondition){
		return simpleDao.getPageResult("WebFromAgent_NS.getAllWebFromAgentList", "WebFromAgent_NS.getAllWebFromAgentCount", queryWebFromAgentCondition);
	}
	/**
	 * Author:yanbaixi
	 * 添加webFromAgent时根据webFrom和webAgent查询判断
	 */
	public WebFromAgent getWebFromAgentBywebFromAndwebAgent(QueryWebFromAgentCondition queryWebFromAgentCondition){
		return simpleDao.getEntity("WebFromAgent_NS.getWebFromAgentByFromAndAgent", queryWebFromAgentCondition);
	}
	/**
	 * Author:yanbaixi
	 *  组合条件查询
	 */
	public PageResult getWebFromAgentCountByTiaoJian(QueryWebFromAgentCondition queryWebFromAgentCondition){
		return simpleDaoRead.getPageResult("WebFromAgent_NS.getWebFromAgentByTiaoJian","WebFromAgent_NS.getWebFromAgentCountByTiaoJian",queryWebFromAgentCondition);
	}
	/**
	 * Author:yanbaixi
	 * 修改webFromAgent时根据webFrom和webAgent查询判断
	 */
	public WebFromAgent getWebFromAgentBywebFromAndwebAgentNotid (QueryWebFromAgentCondition queryWebFromAgentCondition){
		return simpleDao.getEntity("WebFromAgent_NS.getWebFromAgentByFromAndAgentNotid", queryWebFromAgentCondition);
	}
	

}

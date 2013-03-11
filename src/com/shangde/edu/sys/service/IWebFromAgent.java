package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QueryWebFromAgentCondition;
import com.shangde.edu.sys.domain.WebFromAgent;

/**
 * 推广来源接口
 * @author Administrator
 *
 */
public interface IWebFromAgent {
	
	/**
	 * Author:yanbaixi
	 * 添加WebFromAgent
	 * @param webfromagent
	 * @return
	 */
	public java.lang.Integer addWebFromAgent(WebFromAgent webfromagent);
	 /**
	  * Author:yanbaixi
	  *  根据id删除WebFromAgent
	 */
	public void deleteWebFromAgentById(int id);
	/**
	 * Author:yanbaixi
	 * 根据id查询单个webfromAgent信息
	 */
	public WebFromAgent getWebFromAgentById(int id);
	/**
	 * Author:yanbaixi
	 * 根据id修改webfromAgent信息
	 */
	public void updateWebFromAgentById(WebFromAgent webFromAgent);
	/**
	 * Author:yanbaixi
	 * 查询所有的webFromAgent信息
	 */
	public List<WebFromAgent> getAllWebAgentList(QueryWebFromAgentCondition queryWebFromAgentCondition);
	/**
	 * Author:yanbaixi
	 * 统计全部WebFromAgent信息
	 */
	public PageResult getCountWebFromAgentList(QueryWebFromAgentCondition queryWebFromAgentCondition);
	/**
	 * Author:yanbaixi
	 * 添加webFromAgent时根据webFrom和webAgent查询判断
	 */
	public WebFromAgent getWebFromAgentBywebFromAndwebAgent(QueryWebFromAgentCondition queryWebFromAgentCondition);
	/**
	 * Author:yanbaixi
	 * 修改webFromAgent时根据webFrom和webAgent查询判断
	 */
	public WebFromAgent getWebFromAgentBywebFromAndwebAgentNotid (QueryWebFromAgentCondition queryWebFromAgentCondition);
	/**
	 * Author:yanbaixi
	 *  组合条件查询
	 */
	public PageResult getWebFromAgentCountByTiaoJian(QueryWebFromAgentCondition queryWebFromAgentCondition);
	
	
	
}

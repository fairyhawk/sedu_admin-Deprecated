/**
 * 
 */
package com.shangde.edu.cus.webservice;


/**
 * @author	caowei
 * @date	2011-8-9
 * @desc	
 */
public interface ICustomerWS {
	/**
	 * 系统自动生成方法
	 * @param agentId 分销商id
	 * @param agentName 分销商名称
	 * @param createSum 要生成的账号数量
	 * @param project 购买课程
	 * @param sellWay 购买方式
	 * @param discount 总额
	 * @param consumeamount 折扣价
	 * @param consumediscountamount 优惠价
	 * @return
	 */
	public String createCustomerByAgent(int agentId, String agentName,
			int createSum, String project, String sellWay,double discount,double consumeamount,double consumediscountamount);
	/**
	 * 自定义生成方法
	 * @param createInfo 要生成的账号信息
	 * @param project 购买课程
	 * @param sellWay 购买方式
	 * @param discount 总额
	 * @param consumeamount 折扣价
	 * @param consumediscountamount 优惠价
	 * @return
	 */
	public String createByCustom(String createInfo,String project,String sellWay,double discount,double consumeamount,double consumediscountamount);
	
}

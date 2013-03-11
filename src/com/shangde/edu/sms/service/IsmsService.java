package com.shangde.edu.sms.service;

import  com.shangde.edu.sms.webService.SmsServiceStub.SendExResp;

public interface IsmsService {

	/**
	 * 获取群发标识
	 * @return GUID格式的字符串
	 */
	public String getBatchSendID() throws java.lang.Exception;
	/**
	 * 得到指定业务的所有屏蔽词
	 * @param bizType 业务代码(必填)，由代理商提供
	 * @return 屏蔽词之间以英文;分隔
	 */
	public String GetBlackWords() throws java.lang.Exception;
	/**
	 * 提交一条短信
	 * @param msgContent 短信内容(必填)，最大长度由BizType决定,由代理商提供.如果是长短信,将按照原来短信最大长度减去6个字节(3个汉字)来计一条的费用,例:假设业务代码1的短信最大长度为70汉字,如果是长短信,则按一条67个汉字来计费,135个汉字将会计3条费用而不是2条
	 * @param destNumber 目标号码(必填)，多个号码间由半角分号隔开,最多1000个
	 * @param sendTime 计划发送时间，不填则立即发送,格式:yyyy-mm-dd hh:mi:ss
	 * @param subNumber 子号码，由用户任意扩展，自动追加到用户服务号码后,一般限制最大长度为20位(代理商分配给您的服务号码位数+本参数所填的号码位数)
	 * @param wapURL Wap Push的URL地址,如果为空,则当做普通短信发送,需业务代码支持
	 * @return
	 */
	public SendExResp  sendEx(String msgContent, String destNumber, String sendTime, String subNumber, String wapURL) throws java.lang.Exception;
	/**
	 * 查询余额
	 * @param userId 用户编号(必填)，由代理商提供
	 * @param password 密码(必填)，由代理商提供
	 * @param bizType 业务代码(必填)，由代理商提供
	 * @return 
	 */
	public String GetBalance() throws java.lang.Exception;
	/**
	 * 更改用户密码
	 * @param userId 用户名
	 * @param OldPassword 旧密码
	 * @param NewPassword 新密码
	 * @return 成功返回0
	 */
	public String SetPassword(String UserId, String OldPassword, String NewPassword) throws java.lang.Exception;
	
	/**
	 * 被动接收推送的上行sms
	 */
	public String getUpSms() throws java.lang.Exception;
	/**
	 * 按用户名获取状态报告
	 * @param userId 用户编号(必填)，由代理商提供
	 * @param password 密码(必填)，由代理商提供
	 * @return
	 */
	public String getSmsStateEx() throws java.lang.Exception;
	
}

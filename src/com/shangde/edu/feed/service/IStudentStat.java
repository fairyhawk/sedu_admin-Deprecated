package com.shangde.edu.feed.service;

import java.util.List;
import java.util.Map;



import com.shangde.edu.feed.condition.QueryEntranceCondition;
import com.shangde.edu.feed.condition.QueryUserStatLogCondition;
import com.shangde.edu.feed.condition.QueryUserUseCondition;
import com.shangde.edu.feed.domain.From;
import com.shangde.edu.feed.domain.StudentStat;
import com.shangde.edu.feed.domain.UserStatLog;
import com.shangde.edu.feed.dto.StudentStatDTO;

public interface IStudentStat {
	
	/**
	 * 微学习  插入
	 */
	public Integer addStudentStat(StudentStat studentStat);
	
	/**
	 *	微学习 页面流量（独立ip）
	 */
	public Integer getPageFlowCount(QueryEntranceCondition queryEntranceCondition);
	
	/**
	 *	 微学习 用户统计
	 */
	public  List<StudentStatDTO> getStudentUseCount(QueryUserUseCondition queryUserUseCondition);
	
	/**
	 * 微学习  注册统计
	 */
	public List<StudentStatDTO> queryStudentUseLogin(QueryUserUseCondition queryUserUseCondition);
	
	/**
	 * 微学习  登录统计
	 */
	public List<StudentStatDTO> queryStudentUseRegister(QueryUserUseCondition queryUserUseCondition);
	
	
	
	/**
	 * 	微学习 首次用户
	 */
	public List<StudentStatDTO>  getFirstUserCount(Map<String,Object> map);

	/**
	 * 	微学习 非重复用户
	 */
	public List<StudentStatDTO> getNonRepeatUserCount(Map<String,Object> map);
	
	/**
	 * 	微学习 重复用户
	 */
	public List<StudentStatDTO> getRepeatUserCount(Map<String,Object> map);
	
	/**
	 * 微学习 查询所有统计信息
	 */
	public List<StudentStat> queryStudentStatList(String sreachDate);
	
	/**
	 *  微学习 查询所有统计信息  搜索
	 */
	public List<StudentStat> searchStudentStat(String sreachDate);
	
	/**
	 * 带来订单数量
	 */
	public Integer queryOrderCount(Map<String, Object> map);
	
	/**
	 * 带来流水(带来收益)
	 */
	public String getBringWater(Map<String,Object> map);
	/**
	 * 根据来源条件进行查询用户统计的信息
	 */
	public List<StudentStat> searchStuStatByFromIds(Map<String,Object> map);
	
	/**
	 * 统计当天
	 * 查询有多少用户浏览
	 */
	public List<UserStatLog> queryUserStatLog(QueryUserStatLogCondition queryUserStatLogCondition);
}

package com.shangde.edu.res.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.res.domain.Vediogroup;
import com.shangde.edu.res.condition.QueryVediogroupCondition;

/**  

 * @author miaoshusen

 * @version 1.0

 */
public interface IVediogroup {
	/**
	 * 添加视频组的方法
	 */
	public java.lang.Integer addVediogroup(Vediogroup vediogroup);

	/**
	 * 根据视频组id来删除视频组的方法
	 */
	public void delVediogroupById(int vgId);

	/**
	 * 修改视频组的方法
	 */
	public void updateVediogroup(Vediogroup vediogroup);

	/**
	 * 根据视频组的id获得视频组对象的方法
	 */
	public Vediogroup getVediogroupById(int vgId);

	/**
	 * 分页查询返回PageResult对象
	 */
	public PageResult getVediogroupList(
			QueryVediogroupCondition queryVediogroupCondition);
	/**
	 * 获得视频组的List
	 */
	public List<Vediogroup> getVediogroupByList(QueryVediogroupCondition queryVediogroupCondition); 
}
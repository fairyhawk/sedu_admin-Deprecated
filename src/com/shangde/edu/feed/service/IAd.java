package com.shangde.edu.feed.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryAdCondition;
import com.shangde.edu.feed.domain.Ad;

/**
 * 微学习，ad广告接口
 * 
 * @author Lee
 * 
 */
public interface IAd {

	/**
	 * 添加
	 * 
	 * @param ad
	 * @return
	 */
	public int addAd(Ad ad);

	/**
	 * 修改
	 * 
	 * @param ad
	 * @return
	 */
	public int updateAd(Ad ad);

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public Ad getAdById(Integer id);

	/**
	 * 分页查询
	 * 
	 * @param queryAdCondition
	 * @return
	 */
	public PageResult getAdList(QueryAdCondition queryAdCondition);

	/**
	 * 查询所有广告数据
	 * 
	 * status=1正常/-1屏蔽
	 * 
	 * @return
	 */
	public List<Ad> getAdAllList(QueryAdCondition queryAdCondition);

}

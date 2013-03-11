package com.shangde.edu.vst.service;

import java.util.List;

import com.shangde.edu.vst.domain.SellWay;
import com.shangde.edu.vst.domain.VideoCusInfo;

public interface Ihdata {

	/**
	 * 获取包下所有数据
	 * @return
	 */
	public List<VideoCusInfo> getAllPackageCourseTop(int sellId);
	/**
	 * 根据商品ID查询数据
	 * @param sellId 商品ID
	 * @return
	 */
	public List<VideoCusInfo> getAllPackageCourseClick(int sellId);
	
	/**
	 * 获取所有的商品
	 * @return
	 */
	public List<SellWay> getAllSell();
	
}

package com.shangde.edu.vst.service;

import java.util.List;

import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.edu.vst.domain.SellWay;
import com.shangde.edu.vst.domain.VideoCusInfo;

public class HdataImpl extends BaseServiceManyDb implements Ihdata{

	@Override
	public List<VideoCusInfo> getAllPackageCourseTop(int sellId) {
		return this.simpleDaoHaData.getForList("HADATA_NS.getAllPackageCourseTop", sellId);
	}

	@Override
	public List<SellWay> getAllSell() {
		return this.simpleDaoHaData.getForList("HADATA_NS.getAllSell", null);
	}

	@Override
	public List<VideoCusInfo> getAllPackageCourseClick(int sellId) {
		return this.simpleDaoHaData.getForList("HADATA_NS.getAllPackageCourseCount", sellId);
	}
	
}

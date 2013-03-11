package com.shangde.edu.stu.service;

import com.shangde.common.service.BaseService;
import com.shangde.edu.stu.domain.VideoStatisticsDetail;

/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:
 *
 * @author		Yangning
 * @date		2012-2-3
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public class VideoStatisticsDetailImpl extends BaseService implements IVideoStatisticsDetail {

	public int addVideoStatis(VideoStatisticsDetail detail) {
		// TODO Auto-generated method stub
		return simpleDao.createEntity("VideoStatisticsDetail_NS.createVideoStatisticsDetail", detail);
		
	}
}

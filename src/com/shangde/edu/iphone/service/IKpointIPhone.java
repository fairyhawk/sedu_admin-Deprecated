package com.shangde.edu.iphone.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.iphone.condition.QueryKpointIPhoneCondition;
import com.shangde.edu.iphone.dto.IphoneModel;

public interface IKpointIPhone {
	
	/**
	 * 为张栋提供iphone端数据
	 * 获取当前课程  讲数（一共有多少节课）
	 */
	public Integer getKpointSumByCourseId(int courseId);
	
	/**
	 * 根据kpointID  得到当前节点对应的视频地址
	 * 为张栋提供数据 课程首播视频
	 * @return
	 */
	public String getKpointVoUrlById(int courseId);
	
	/**
	 * 为张栋提供数据
	 */
    public PageResult getIphoneKpointListByPid(QueryKpointIPhoneCondition queryKpointIPhoneCondition);
    
    /**
     * 通过PID获取子集合
     * @param queryKpointCondition
     * @return
     */
    public List<IphoneModel> getKpointListByPid(QueryKpointIPhoneCondition queryKpointIPhoneCondition);

}

package com.shangde.edu.feed.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryMicroVideoCondition;
import com.shangde.edu.feed.domain.MicroVideo;
import com.shangde.edu.feed.dto.MicroVideoDTO;
import com.shangde.edu.feed.dto.VideoDetailsDTO;

/**
 * 微学习-视频接口
 * 
 * @author chensong
 * 
 */
public interface IMicroVideo {
	/**
	 * 添加视频方法
	 * 
	 * @param microVideo
	 */
	public void addVideoAddress(MicroVideo microVideo);

	/**
	 * 修改视频方法
	 * 
	 * @param microVideo
	 */
	public void updateVideoAddress(MicroVideo microVideo);

	/**
	 * 查询所有视频并分页的方法
	 * 
	 * @param queryMicroVideoCondition
	 * @return
	 */
	public PageResult getAllVideoAddress(
			QueryMicroVideoCondition queryMicroVideoCondition);

	/**
	 * 根据视频id删除视频
	 * 
	 * @param id
	 */
	public void deleteVideoAddress(int id);

	/**
	 * 根据id查找单个视频
	 * 
	 * @param id
	 * @return
	 */
	public MicroVideo getVideoAddressById(int id);

	/**
	 * libaogang
	 * 
	 * @param queryMicroVideoCondition
	 * @return
	 */
	public PageResult getVideoAddressBySubjectIdBack(
			QueryMicroVideoCondition queryMicroVideoCondition);

	/**
	 * 根据专业查找视频并分页
	 * 
	 * @param queryMicroVideoCondition
	 * @return
	 */
	public PageResult getVideoAddressBySubjectId(
			QueryMicroVideoCondition queryMicroVideoCondition);

	/**
	 * 查询subjectId下的视频列表，并且与某个人绑定
	 * 
	 * @param map
	 *            Map key=email,subjectId
	 * @return
	 */
	public List<MicroVideoDTO> getVideoAddressBySubjectId(Map map);

	/**
	 * 根据课程名称查找课程
	 * 
	 * @param courseId
	 * @return
	 */
	public List<MicroVideo> getVideoByCourseId(int courseId);

	/**
	 * 视频统计查询
	 * 
	 * @param queryMicroVideoCondition
	 * @return
	 */
	public PageResult videoStatSearch(
			QueryMicroVideoCondition queryMicroVideoCondition);

	/**
	 * 查询导出的数据，与方法 videoStatSearch逻辑一直
	 * 
	 * @param queryMicroVideoCondition
	 * @return
	 */
	public List<MicroVideoDTO> videoStatSearchExport(
			QueryMicroVideoCondition queryMicroVideoCondition);

	/**
	 * 反查某视频观看人是谁
	 * 
	 * @param queryMicroVideoCondition
	 * @return
	 */
	public List<VideoDetailsDTO> videoReverseWatchSearch(
			QueryMicroVideoCondition queryMicroVideoCondition);

	/**
	 * 反查某视频观看完人是谁
	 * 
	 * @param queryMicroVideoCondition
	 * @return
	 */
	public List<VideoDetailsDTO> videoReverseWatchDoneSearch(
			QueryMicroVideoCondition queryMicroVideoCondition);

	/**
	 * 反查某视频，下载人是谁
	 * 
	 * @param queryMicroVideoCondition
	 * @return
	 */
	public List<VideoDetailsDTO> videoReverseDownSearch(
			QueryMicroVideoCondition queryMicroVideoCondition);

	/**
	 * 反查某视频，收藏人是谁
	 * 
	 * @param queryMicroVideoCondition
	 * @return
	 */
	public List<VideoDetailsDTO> videoReverseCollectSearch(
			QueryMicroVideoCondition queryMicroVideoCondition);

}

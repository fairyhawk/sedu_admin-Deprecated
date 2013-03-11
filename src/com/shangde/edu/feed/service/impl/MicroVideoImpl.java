package com.shangde.edu.feed.service.impl;

import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryMicroVideoCondition;
import com.shangde.edu.feed.domain.MicroVideo;
import com.shangde.edu.feed.dto.MicroVideoDTO;
import com.shangde.edu.feed.dto.VideoDetailsDTO;
import com.shangde.edu.feed.service.IMicroVideo;
import com.shangde.edu.feed.utils.Utils;

/**
 * 微学习-视频接口实现类
 * 
 * @author Libg
 * @author chensong
 * 
 */
public class MicroVideoImpl extends BaseService implements IMicroVideo {

	public void addVideoAddress(MicroVideo microVideo) {
		simpleDao.createEntity("Videoadd_NS.createVideoAddress", microVideo);
	}

	public void updateVideoAddress(MicroVideo microVideo) {
		simpleDao.update("Videoadd_NS.updateVideoAddress", microVideo);
	}

	public PageResult getAllVideoAddress(
			QueryMicroVideoCondition queryMicroVideoCondition) {

		return simpleDao.getPageResult("Videoadd_NS.selectAllVideoAddress",
				"Videoadd_NS.videoCount", queryMicroVideoCondition);
	}

	public void deleteVideoAddress(int id) {
		simpleDao.delete("Videoadd_NS.deleteVideoAddress", id);
	}

	public MicroVideo getVideoAddressById(int id) {
		MicroVideo microVideo = simpleDao.getEntity(
				"Videoadd_NS.selectVideoAddress", id);
		return microVideo;
	}

	public PageResult getVideoAddressBySubjectIdBack(
			QueryMicroVideoCondition queryMicroVideoCondition) {
		return simpleDao.getPageResult(
				"Videoadd_NS.selectVideoAddressBySubjectIdBack",
				"Videoadd_NS.videoCountBySubjectIdBack",
				queryMicroVideoCondition);
	}

	public PageResult getVideoAddressBySubjectId(
			QueryMicroVideoCondition queryMicroVideoCondition) {
		return simpleDao.getPageResult(
				"Videoadd_NS.selectVideoAddressBySubjectId",
				"Videoadd_NS.videoCountBySubjectId", queryMicroVideoCondition);
	}

	public List<MicroVideoDTO> getVideoAddressBySubjectId(Map map) {
		return simpleDao.getForList(
				"Videoadd_NS.selectVideoAddressBySubjectId", map);
	}

	public List<MicroVideo> getVideoByCourseId(int courseId) {
		return simpleDao.getForList("Videoadd_NS.getVideoListByCourseId",
				courseId);
	}

	/*
	 * PageResult(non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IMicroVideo#videoStatSearch(com.shangde.edu.feed.condition.QueryMicroVideoCondition)
	 */
	public PageResult videoStatSearch(
			QueryMicroVideoCondition queryMicroVideoCondition) {

		PageResult pr = null;
		List<MicroVideoDTO> mvList = null;
		try {
			pr = simpleDao.getPageResult("VideoStatModule_NS.videoStatSearch",
					"VideoStatModule_NS.videoStatCountSearch",
					queryMicroVideoCondition);
			mvList = pr.getPageResult();

			if (mvList != null) {
				for (MicroVideoDTO mv : mvList) {
					queryMicroVideoCondition.setId(mv.getId());
					mv.setClickUserNum((Integer) simpleDao.getEntity(
							"VideoStatModule_NS.videoWatchSearch",
							queryMicroVideoCondition));
					mv.setClickDoneUserNum((Integer) simpleDao.getEntity(
							"VideoStatModule_NS.videoWatchDoneSearch",
							queryMicroVideoCondition));
					mv.setDownNum((Integer) simpleDao.getEntity(
							"VideoStatModule_NS.videoDownSearch",
							queryMicroVideoCondition));
					if(mv.getTotalWatchTime() != null){
						mv.setTotalWatchTimeStr(Utils.formatMinutes(mv.getTotalWatchTime()));
					}
				}
			}
		} catch (Exception e) {
			logger.error("视频统计查询失败", e);
		}
		return pr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IMicroVideo#videoStatSearchExport(com.shangde.edu.feed.condition.QueryMicroVideoCondition)
	 */
	public List<MicroVideoDTO> videoStatSearchExport(
			QueryMicroVideoCondition queryMicroVideoCondition) {

		List<MicroVideoDTO> mvList = null;
		try {
			mvList = simpleDao.getForList(
					"VideoStatModule_NS.videoStatSearchExport",
					queryMicroVideoCondition);

			if (mvList != null) {
				for (MicroVideoDTO mv : mvList) {
					queryMicroVideoCondition.setId(mv.getId());
					mv.setClickUserNum((Integer) simpleDao.getEntity(
							"VideoStatModule_NS.videoWatchSearch",
							queryMicroVideoCondition));
					mv.setClickDoneUserNum((Integer) simpleDao.getEntity(
							"VideoStatModule_NS.videoWatchDoneSearch",
							queryMicroVideoCondition));
					mv.setDownNum((Integer) simpleDao.getEntity(
							"VideoStatModule_NS.videoDownSearch",
							queryMicroVideoCondition));
					if(mv.getTotalWatchTime() != null){
						mv.setTotalWatchTimeStr(Utils.formatMinutes(mv.getTotalWatchTime()));
					}
				}
			}
		} catch (Exception e) {
			logger.error("视频统计查询失败", e);
		}
		return mvList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IMicroVideo#videoReverseCollectSearch(com.shangde.edu.feed.condition.QueryMicroVideoCondition)
	 */
	public List<VideoDetailsDTO> videoReverseCollectSearch(
			QueryMicroVideoCondition queryMicroVideoCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList(
				"VideoStatModule_NS.videoReverseCollectSearch",
				queryMicroVideoCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IMicroVideo#videoReverseDownSearch(com.shangde.edu.feed.condition.QueryMicroVideoCondition)
	 */
	public List<VideoDetailsDTO> videoReverseDownSearch(
			QueryMicroVideoCondition queryMicroVideoCondition) {
		// TODO Auto-generated method stub

		return simpleDao.getForList(
				"VideoStatModule_NS.videoReverseDownSearch",
				queryMicroVideoCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IMicroVideo#videoReverseWatchDoneSearch(com.shangde.edu.feed.condition.QueryMicroVideoCondition)
	 */
	public List<VideoDetailsDTO> videoReverseWatchDoneSearch(
			QueryMicroVideoCondition queryMicroVideoCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList(
				"VideoStatModule_NS.videoReverseWatchDoneSearch",
				queryMicroVideoCondition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shangde.edu.feed.service.IMicroVideo#videoReverseWatchSearch(com.shangde.edu.feed.condition.QueryMicroVideoCondition)
	 */
	public List<VideoDetailsDTO> videoReverseWatchSearch(
			QueryMicroVideoCondition queryMicroVideoCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList(
				"VideoStatModule_NS.videoReverseWatchSearch",
				queryMicroVideoCondition);
	}

}

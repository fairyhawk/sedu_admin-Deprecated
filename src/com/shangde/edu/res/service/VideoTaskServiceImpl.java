package com.shangde.edu.res.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.res.condition.QueryVedioCondition;
import com.shangde.edu.res.domain.Vedio;

public class VideoTaskServiceImpl extends BaseService implements IVideoTaskService{
	
		@Override
		public int updatevideoPicByid(Vedio vedio) {
			// TODO Auto-generated method stub
			return simpleDao.update("VedioTask_NS.updateCCSmallUrlByCCId", vedio);
		}
	
		@Override
		public int updateCoursePicByid(Course course) {
			// TODO Auto-generated method stub
			return simpleDao.update("VedioTask_NS.updateCoursePicByid", course);
		}

		@Override
		public PageResult getNotPicListVedio(QueryVedioCondition condition) {
			// TODO Auto-generated method stub
			return simpleDao.getPageResult("VedioTask_NS.getVedioByPage4CCSmallPic","VedioTask_NS.getVedioByCount4CCSmallPic",condition);
		}

		@Override
		public List<Course> getNotPicListCourse() {
			// TODO Auto-generated method stub
			return simpleDao.getForList("VedioTask_NS.getCourseNotPicList",null);
		}

		@Override
		public int getNotPicListVedioCount() {
			return simpleDao.getEntity("VedioTask_NS.getVedioByCount4CCSmallPic", null);
		}

		@Override
		public String getCoursePicViaVedioPic(int courseId) {
			return simpleDao.getEntity("VedioTask_NS.getCourseFirstPicByCourseId", courseId);
		}
	
}

package com.shangde.edu.feed.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.feed.domain.CourseCategory;
import com.shangde.edu.feed.service.ICourseCategory;

public class CourseCategoryAction extends CommonAction {
	private static Logger logger = LoggerFactory
			.getLogger(CourseCategoryAction.class);
	/**
	 * 服务接口
	 */
	private ICourseCategory courseCategoryService;
	/**
	 * 集合对象_类别管理列表
	 * 
	 * @return
	 */
	private List<CourseCategory> courseCategorylist;

	/**
	 * 查询类别管理列表
	 * 
	 * @return
	 */
	public String categoryList() {
		System.out.println("进来这个方法了吗");
		courseCategorylist = courseCategoryService.queryList();
		System.out.println("他的集合值是" + courseCategorylist.size());
		return "queryList_ok";
	}

	public ICourseCategory getCourseCategoryService() {
		return courseCategoryService;
	}

	public void setCourseCategoryService(ICourseCategory courseCategoryService) {
		this.courseCategoryService = courseCategoryService;
	}

	public List<CourseCategory> getCourseCategorylist() {
		return courseCategorylist;
	}

	public void setCourseCategorylist(List<CourseCategory> courseCategorylist) {
		this.courseCategorylist = courseCategorylist;
	}


}

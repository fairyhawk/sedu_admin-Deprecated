package com.shangde.edu.feed.service.impl;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.feed.domain.CourseCategory;
import com.shangde.edu.feed.service.ICourseCategory;

public class CourseCategoryImpl extends BaseService implements ICourseCategory{

	public void addCategory(CourseCategory coursecategory) {
		// TODO Auto-generated method stub
		
	}

	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		
	}

	public int getById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<CourseCategory> queryList() {
		return simpleDao.getForList("Feed_CourseCategory_NS.getCourseCategoryList", null);
	}

	public CourseCategory updateCategory(CourseCategory coursecategory) {
		// TODO Auto-generated method stub
		return null;
	}


	




}

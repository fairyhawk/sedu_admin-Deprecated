package com.shangde.edu.feed.service;

import java.util.List;

import com.shangde.edu.feed.domain.CourseCategory;

public interface ICourseCategory {
		public void addCategory(CourseCategory coursecategory);
		
		public void deleteCategory(int id);
		
		public CourseCategory updateCategory(CourseCategory coursecategory);
		
		public List<CourseCategory> queryList();
		
		public int getById(int id);
}

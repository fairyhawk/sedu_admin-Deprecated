package com.shangde.edu.cou.web;

import java.util.ArrayList;
import java.util.List;

import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.dto.CourseSortListDTO;
import com.shangde.edu.cou.service.ICoursesort;



/**
 * 课程分类
 * @author chenshuai
 */
public class CourseSortWebAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 课程分类
	 */
	private Coursesort sort;
	
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService;
	
	/**
	 * 配置服务
	 */
	private ConfigService configurator;
	
	/**
	 * 课程分类集合
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();
	
	/**
	 * 课程分类查询条件
	 */
	private QueryCoursesortCondition queryCourseSortCondition;
	
	/**
	 * 分类ID
	 */
	private int sortId;
	
	/**
	 * 分类ID集合
	 */
	private String delsortIds;
	
	/**
	 * 课程分类集合
	 */
	private List<Coursesort> sortList = new ArrayList<Coursesort>();
	
	/**
	 * 查询条件
	 */
	private String searchKey;
	
	private int[] sortIds;
	
	private List<CourseSortListDTO> courseSortListDTOList;
	
	/**
	 * 获取所有课程分类及其课程
	 * 进入所有课程列表
	 * @return
	 */
	public String toListAllCoursesortListDTO(){
		try{
			courseSortListDTOList = coursesortService.getCourseSortListDTOList();
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		
		return "toListAllCoursesortListDTO";
	}
	
	public Coursesort getSort() {
		return sort;
	}

	public void setSort(Coursesort sort) {
		this.sort = sort;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}

	public List<Coursesort> getCourseSortList() {
		return courseSortList;
	}

	public void setCourseSortList(List<Coursesort> courseSortList) {
		this.courseSortList = courseSortList;
	}

	public QueryCoursesortCondition getQueryCourseSortCondition() {
		
		if(queryCourseSortCondition == null){
			queryCourseSortCondition = new QueryCoursesortCondition();
		}
		return queryCourseSortCondition;
	}

	public void setQueryCourseSortCondition(
			QueryCoursesortCondition queryCourseSortCondition) {
		this.queryCourseSortCondition = queryCourseSortCondition;
	}
	
	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	public List<Coursesort> getSortList() {
		return sortList;
	}

	public void setSortList(List<Coursesort> sortList) {
		this.sortList = sortList;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getDelsortIds() {
		return delsortIds;
	}

	public void setDelsortIds(String delsortIds) {
		this.delsortIds = delsortIds;
	}

	public int[] getSortIds() {
		return sortIds;
	}

	public void setSortIds(int[] sortIds) {
		this.sortIds = sortIds;
	}
	public List<CourseSortListDTO> getCourseSortListDTOList() {
		return courseSortListDTOList;
	}
	public void setCourseSortListDTOList(
			List<CourseSortListDTO> courseSortListDTOList) {
		this.courseSortListDTOList = courseSortListDTOList;
	}

}

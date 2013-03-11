package com.shangde.edu.cou.action;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.HTMLDecoder;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.dto.CourseSortListDTO;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;


/**
 * 
 *课程分类Action
 *@author chenshuai 
 */
public class CourseSortAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(CourseSortAction.class);
	
	/**
	 * 课程分类
	 */
	private Coursesort sort;
	
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService;
	
	/**
	 * 学科服务
	 */
	private ISubject subjectService;
	
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
	 * 专业id
	 */
	private int  subjectId;
	/**
	 *  分类ID集合
	 */
	
	private String delsortIds;
	

	/**
	 *  课程分类集合
	 */
	private List<Coursesort> sortList = new ArrayList<Coursesort>();
	
	/**
	 * 查询条件
	 */
	private String searchKey;
	
	private int[] sortIds;
	
	private List<CourseSortListDTO> courseSortListDTOList;
	
	private List<Subject> subjectList;//学科集合
	
	private List<File> sortPic;
	
	private List<String> sortPicFileName;
	
	/**
	 * 批量冻结课程分类
	 * @return
	 */
	public String freezeCoursesorts(){
		
		try{
			if(sortIds != null){
				Coursesort temp = null;
				for(int i = 0; i <sortIds.length ; i++){
					
					temp = coursesortService.getCoursesortById(sortIds[i]);
					temp.setStatus(Coursesort.COURSESORT_FREEZE_STATUS);
					coursesortService.updateCoursesort(temp);
				}
			}
		}catch(Exception ex){
			logger.error("CourseSortAction.freezeCoursesorts", ex);
			return ERROR;
		}
		return "freezeCoursesorts";
	}
	
	/**
	 *冻结课程分类
	 *@return String 
	 */
	public String freezeCoursesort() {
		try{
			sort = coursesortService.getCoursesortById(sort.getCoursesortId());
			if(sort.getStatus() == Coursesort.COURSESORT_DEFAULT_STATUS){
				sort.setStatus(Coursesort.COURSESORT_FREEZE_STATUS);
			}else{
				sort.setStatus(Coursesort.COURSESORT_DEFAULT_STATUS);
			}
			coursesortService.updateCoursesort(sort);
		}catch(Exception ex){
			logger.error("CourseSortAction.freezeCoursesort", ex);
			return ERROR;
		}
		
		return "listCoursesortsByKey";
	}
	
	
	/**
	 *添加预条件课程分类
	 *@return String 
	 */
	
	public String toAddCourseSort() {
		try{
			this.getQueryCourseSortCondition().setPId(-1);
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			courseSortList = coursesortService.getChildCoursesortList(queryCourseSortCondition);
		}catch(Exception ex){
			logger.error("CourseSortAction.toAddCourseSort", ex);
			return ERROR;
		}
		return "toAddCourseSort";
	}
	
	/**
	 * 选择课程分类
	 * @return String
	 */
	public String toSelectCourseSort() {
		try{
			this.getQueryCourseSortCondition().setPId(-1);
			courseSortList = coursesortService.getChildCoursesortList(queryCourseSortCondition);
		}catch(Exception ex){
			logger.error("CourseSortAction.toSelectCourseSort", ex);
			return ERROR;
		}
		return "toSelectCourseSort";
	}
	
	/**
	 * 获子课程分类
	 * @return String
	 */
	public String toSelectCourseSortPage() {
		try{
			this.getQueryCourseSortCondition().setPId(-1);
			courseSortList = coursesortService.getChildCoursesortList(queryCourseSortCondition);
		}catch(Exception ex){
			logger.error("CourseSortAction.toSelectCourseSortPage", ex);
			return ERROR;
		}
		return "toSelectCourseSortPage";
	}
	/**
	 *	添加课程分类 
	 * @return String
	 */
	public String addCourseSort() {
		try{
			int sortId = coursesortService.addCoursesort(sort);
			sort.setCoursesortId(sortId);

			String filename = null;
			String filetype = null;
			List<String> filenamelist = new ArrayList<String>();
			for(int i =0; i < sortPic.size(); i ++){
				
				filename = sdf.format(new Date());
				filetype = this.getFileType(sortPicFileName.get(i));
				filename = filename + "-" + sortId + this.getFileType(filetype);
				
				sort.setSortPicUrl(filename);
				filenamelist.add(filename);
			}
			
			this.upload(filenamelist, sortPic);
			
			coursesortService.updateCoursesort(sort);
		}catch(Exception ex){
			logger.error("CourseSortAction.addCourseSort", ex);
			return ERROR;
		}
		return "addCourseSort";
	}
	/**
	 * 删除课程分类
	 * @return String
	 */
	public String deleteCourseSort() {
		try{
			sort = coursesortService.getCoursesortById(sort.getCoursesortId());
			sort.setStatus(Coursesort.COURSESORT_DELETE_STATUS);
			coursesortService.updateCoursesort(sort);
		}catch(Exception ex){
			logger.error("CourseSortAction.deleteCourseSort", ex);
			return ERROR;
		}
		
		return "deleteCourseSort";
	}
	/**
	 * 预批量删除课程分类
	 * @return String
	 */
	public String toDeleteCourseSorts() {
		try{
			sortList = coursesortService.getCoursesortTreeList(this.getQueryCourseSortCondition());
		}catch(Exception ex){
			logger.error("CourseSortAction.toDeleteCourseSorts", ex);
			return ERROR;
		}
		return "toDeleteCourseSorts";
	}
	
	/**
	 * 批量删除课程分类
	 * @return String
	 */
	public String deleteCourseSorts() {
		try{
			coursesortService.deleteCourseSorts(delsortIds);
		}catch(Exception ex){
			logger.error("CourseSortAction.deleteCourseSorts", ex);
			return ERROR;
		}
		return "deleteCourseSorts";
	}
	
	/**
	 * 预更新课程分类
	 * @return
	 */
	public String toUpdateCoursesort(){
		try{
			if(searchKey != null){
				searchKey = URLEncoder.encode(searchKey,"UTF-8");
			}
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			courseSortList = coursesortService.getChildCoursesortList(this.getQueryCourseSortCondition());
			sort = coursesortService.getCoursesortById(sort.getCoursesortId());
			courseSortList.remove(sort);
		}catch(Exception ex){
			logger.error("CourseSortAction.toUpdateCoursesort", ex);
			return ERROR;
		}
		return "toUpdateCoursesort";
	}
	
	/**
	 * 更新课程分类
	 * @return
	 */
	public String updateCoursesort(){
		try{
			if(sort.getCoursesortId() != -1){
				File fileTemp = null;
				String filename = null;
				String filetype = null;
				
				if(sortPic == null || sortPic.size() == 0){
					Coursesort temp = coursesortService.getCoursesortById(sort.getCoursesortId());
					
					sort.setSortPicUrl(temp.getSortPicUrl());//保存原图片路径
				}else{
					if(sortPic != null){
						List<String> filenamelist = new ArrayList<String>();
						for(int i =0; i < sortPic.size(); i ++){
							
							fileTemp = sortPic.get(i);
							filename = sdf.format(new Date());
							filetype = this.getFileType(sortPicFileName.get(i));
							filename = filename + "-" + sortId + this.getFileType(filetype);
							
							sort.setSortPicUrl(filename);
							filenamelist.add(filename);
						}
						this.upload(filenamelist, sortPic);
					}
				}
				
				coursesortService.updateCoursesort(sort);
			}
		}catch(Exception ex){
			logger.error("CourseSortAction.updateCoursesort", ex);
			return ERROR;
		}
		return "updateCoursesort";
	}
	/**
	 * 通过条件查看课程分类
	 * @return
	 */
	public String listCoursesortsByKey()  {
		try{
			if(searchKey != null){
				if(searchKey.indexOf("&#") > -1){
					searchKey = HTMLDecoder.decode(searchKey);//将这类代码转换成中文
				}
				this.getQueryCourseSortCondition().setSearchKey(searchKey.trim());
			}
			
			setPage(this.getCoursesortService().getNomalCoursesortList(this.getQueryCourseSortCondition()));
			
			setPageUrlParms();
		}catch(Exception ex){
			logger.error("CourseSortAction.listCoursesortsByKey", ex);
			return ERROR;
		}
		return "listCoursesortsByKey";
	}
	/**
	 * 
	 *通过课程分类ID获取子课程分类（联动）
	 *@return
	 *@author chenshuai 
	 */
	public String getChildSortById(){
		try{
			this.getQueryCourseSortCondition().setPId(sortId);
			this.getQueryCourseSortCondition().setSubjectId(subjectId);
			List<Coursesort> childSortList = this.coursesortService.getChildCoursesortList(queryCourseSortCondition);
			
			List<KeyValueDTO> myList = new ArrayList<KeyValueDTO>();
			
			KeyValueDTO keyvalue = null;
			
			for(int i=0; i < childSortList.size(); i++) {
				keyvalue = new KeyValueDTO();
				Coursesort grouptemp = childSortList.get(i); 
				 
				keyvalue.setId(grouptemp.getCoursesortId());
				keyvalue.setName(grouptemp.getCoursesortName());
				myList.add(keyvalue);
			}
			this.setResult(new Result<List<KeyValueDTO>>(true,"",null,myList));
		}catch(Exception ex){
			logger.error("CourseSortAction.getChildSortById", ex);
			return ERROR;
		}
		return "getChildSortById";
	}
	/**
	 * 通过sortId查询subjectId
	 * @return
	 */
	public String getSortById(){
		try{
			if(sortId>0){
				
				this.setResult(new Result(true,"",null,this.getCoursesortService().getCoursesortById(sortId).getSubjectId()));
			
			}
			return "sortJson";
		}catch(Exception e){
			logger.error("CourseSortAction.getSortById", e);
			return ERROR;
		}
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
	public ISubject getSubjectService() {
		return subjectService;
	}
	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}
	public List<Subject> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	public List<File> getSortPic() {
		return sortPic;
	}
	public void setSortPic(List<File> sortPic) {
		this.sortPic = sortPic;
	}
	public List<String> getSortPicFileName() {
		return sortPicFileName;
	}
	public void setSortPicFileName(List<String> sortPicFileName) {
		this.sortPicFileName = sortPicFileName;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

}

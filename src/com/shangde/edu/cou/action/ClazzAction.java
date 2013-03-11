package com.shangde.edu.cou.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.cou.condition.QueryClazzCondition;
import com.shangde.edu.cou.condition.QueryClazzCouCondition;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Clazz;
import com.shangde.edu.cou.domain.ClazzCou;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.service.IClazz;
import com.shangde.edu.cou.service.IClazzCou;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.sys.domain.Grade;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.IGrade;
import com.shangde.edu.sys.service.ISubject;

/**  
 * 推荐课程Action
 * @author chenshuai
 * @version 1.0
 */
public class ClazzAction extends CommonAction{
	private static final Logger logger = Logger.getLogger(ClazzAction.class);
	/**
	 * 班级
	 */
	private Clazz clazz;
	
	/**
	 * 班级服务对象
	 */
	private IClazz clazzService;
	
	private IClazzCou clazzCouService;
	
	/**
	 * 查询班级条件
	 */
	private QueryClazzCondition queryClazzCondition;
	
	/**
	 * 按班级查询课程条件
	 */
	private QueryClazzCouCondition queryClazzCouCondition;
	
	/**
	 * 班级集合
	 */
	private List<Clazz> clazzList;
	
	/**
	 * 查询条件
	 */
	private String searchKey;
	
	private IGrade gradeService;//年级服务
	
	private ISubject subjectService;//学科服务
	
	private List<Grade> gradeList;//年级集合
	
	private List<Subject> subjectList;//学科集合
	
	private int[] clazzIds;
	
	
	/**
	 * 课程分类集合查询
	 */
	private List<Coursesort> courseSortList;
	
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService; 
	
	/**
	 * 班级课程
	 */
	private ClazzCou clazzCou;
	
	/**
	 * 班级首页图片
	 */
	private List<File> clazzIndexPic = new ArrayList<File>();
	
	/**
	 * 班级页图片
	 */
	private List<File> clazzPic = new ArrayList<File>();
	
	/**
	 * 班级首页图片
	 */
	private List<String> clazzIndexPicFileName = new ArrayList<String>();
	
	/**
	 * 班级页图片
	 */
	private List<String> clazzPicFileName = new ArrayList<String>();

	/**
	 * 预添加班级
	 * @return
	 */
	public String toAddClazz(){
		try{
			gradeList = gradeService.getGradeListByStatus(Grade.GRADE_DEFAULT_STATUS);
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
		}catch(Exception ex){
			logger.error("ClazzAction.toAddClazz", ex);
			return ERROR;
		}
		return "toAddClazz";
	}
	
	/**
	 * 添加班级
	 * @return
	 */
	public String addClazz(){
		try{
			int clazzId = clazzService.addClazz(clazz);
			clazz.setClazzId(clazzId);
			
			File fileTemp = null;
			String filename = null;
			String filetype = null;
			
			List<String> filenamelist = new ArrayList<String>();//添加首页班级图片
			for(int i =0; i < clazzIndexPic.size(); i ++){
				
				fileTemp = clazzIndexPic.get(i);
				filename = sdf.format(new Date());
				filetype = this.getFileType(clazzIndexPicFileName.get(i));
				filename = filename + "-" + clazzId + this.getFileType(filetype);
				
				clazz.setClazzIndexPic(filename);
				filenamelist.add(filename);
			}
			
			
			this.upload(filenamelist, clazzIndexPic);
			
			filenamelist.clear();//添加班级图片
			for(int i =0; i < clazzIndexPic.size(); i ++){
				
				fileTemp = clazzPic.get(i);
				filename = sdf.format(new Date());
				filetype = this.getFileType(clazzPicFileName.get(i));
				filename = filename + "-" + clazzId + this.getFileType(filetype);
				
				clazz.setClazzPic(filename);
				filenamelist.add(filename);
			}
			
			this.upload(filenamelist, clazzPic);
			
			clazzService.updateClazz(clazz);
			
		}catch(Exception ex){
			logger.error("ClazzAction.addClazz", ex);
			return ERROR;
		}
		return "addClazz";
	}
	
	protected void upload(List<String> nameList, List<File> fileList) throws Exception {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        for(int i=0; i<fileList.size(); i++) {
        fos = new FileOutputStream(ServletActionContext.getServletContext().getRealPath("/back/upload/clazz")+"/"+nameList.get(i));   
	        fis = new FileInputStream(fileList.get(i));   
	        byte[] b = new byte[1024];   
	        while((fis.read(b))!=-1){   
	            fos.write(b);   
	        }   
	        fos.close();   
	        fis.close();   
        }
    }
	
	/**
	 * 预更新班级
	 * @return
	 */
	public String toUpdateClazz(){
		try{
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			gradeList = gradeService.getGradeListByStatus(Grade.GRADE_DEFAULT_STATUS);
			if(clazz != null && clazz.getClazzId() != 0){
				clazz = clazzService.getClazzById(clazz.getClazzId());
			}else{
				return "listClazzs";
			}
		}catch(Exception ex){
			logger.error("ClazzAction.toUpdateClazz", ex);
			return ERROR;
		}
		return "toUpdateClazz";
	}
	
	/**
	 * 更新班级
	 * @return
	 */
	public String updateClazz(){
		try{
			if(clazz != null && clazz.getClazzId() != 0){
				Clazz clazzTemp = clazzService.getClazzById(clazz.getClazzId());
				
				Random r = new Random();
				int clazzId = clazz.getClazzId();
				
				File fileTemp = null;
				String filename = null;
				String filetype = null;
				
				List<String> filenamelist = new ArrayList<String>();//添加首页班级图片
				for(int i =0; i < clazzIndexPic.size(); i ++){
					
					fileTemp = clazzIndexPic.get(i);
					filename = sdf.format(new Date());
					filetype = this.getFileType(clazzIndexPicFileName.get(i));
					filename = filename + "-" + clazzId +"-" +  r.nextInt(100) + this.getFileType(filetype);
					
					clazz.setClazzIndexPic(filename);
					filenamelist.add(filename);
				}
				
				this.upload(filenamelist, clazzIndexPic);
			
				filenamelist.clear();//添加班级图片
				for(int i =0; i < clazzPic.size(); i ++){
					
					fileTemp = clazzPic.get(i);
					filename = sdf.format(new Date());
					filetype = this.getFileType(clazzPicFileName.get(i));
					filename = filename + "-" + clazzId + "-" +  r.nextInt(100) + this.getFileType(filetype);
					
					clazz.setClazzPic(filename);
					filenamelist.add(filename);
				}
				
				if(clazzIndexPic == null || clazzIndexPic.size() == 0){
					clazz.setClazzIndexPic(clazzTemp.getClazzIndexPic());
				}
				
				if(clazzPic == null || clazzPic.size() == 0){
					clazz.setClazzPic(clazzTemp.getClazzPic());
				}
				
				this.upload(filenamelist, clazzPic);
				
				clazzService.updateClazz(clazz);
			}else{
				return ERROR;
			}
		}catch(Exception ex){
			logger.error("ClazzAction.updateClazz", ex);
			return ERROR;
		}
		return "updateClazz";
	}
	
	/**
	 * 批量冻结课程
	 * @return
	 */
	public String freezeAllClazz(){
		try{
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			gradeList = gradeService.getGradeListByStatus(Grade.GRADE_DEFAULT_STATUS);
			
			if(clazzIds != null){
				Clazz temp = null;
				for(int i = 0; i <clazzIds.length ; i++){
					temp = clazzService.getClazzById(clazzIds[i]);
					temp.setStatus(Course.COURSE_FREEZE_STATUS);
					clazzService.updateClazz(temp);
				}
			}
			
			
		}catch(Exception ex){
			logger.error("ClazzAction.freezeAllClazz", ex);
			return ERROR;
		}
		
		return "toListClazzs";
	}
	/**
	 * 冻结班级
	 * @return
	 */
	public String freezeClazz(){
		try{
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			gradeList = gradeService.getGradeListByStatus(Grade.GRADE_DEFAULT_STATUS);
			clazz = this.clazzService.getClazzById(clazz.getClazzId());
			
			if (clazz.getStatus() == 0) {
				clazz.setStatus(1);
			} else {
				clazz.setStatus(0);
			}
			this.clazzService.updateClazz(clazz);
		}catch(Exception ex){
			logger.error("ClazzAction.freezeClazz", ex);
			return ERROR;
		}
		return "listClazzs";
	}
	
	/**
	 * 查询班级列表
	 * 可以按关键字和频道查询（subject）
	 * @return
	 */
	public String listClazzs(){
		try{
			if(searchKey != null){
				this.getQueryClazzCondition().setSearchKey(searchKey);
			}
			
			if(clazz != null && clazz.getSubjectId() != 0)
				this.getQueryClazzCondition().setSubjectId(clazz.getSubjectId());
			
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			gradeList = gradeService.getGradeListByStatus(Grade.GRADE_DEFAULT_STATUS);
			
			getQueryClazzCondition().setPageSize(8);
			setPage(clazzService.getClazzsListByCondition(this.getQueryClazzCondition()));
			this.getPage().setPageSize(8);
			setPageUrlParms();
		}catch(Exception ex){
			logger.error("ClazzAction.listClazzs", ex);
			return ERROR;
		}
		return "listClazzs";
	}
	
	/**
	 * 进入班级课程管理页面
	 * @return
	 */
	public String toClazzCourse(){
		try{
			clazz = clazzService.getClazzById(clazz.getClazzId());
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			
			this.getQueryClazzCouCondition().setClazzId(clazz.getClazzId());
			
			getQueryClazzCouCondition().setPageSize(8);
			setPage(clazzCouService.getCourseListByClazzId(queryClazzCouCondition));
			this.getPage().setPageSize(8);
			setPageUrlParms();
		}catch(Exception ex){
			logger.error("ClazzAction.toClazzCourse", ex);
			return ERROR;
		}
		return "toClazzCourse";
	}
	
	/**
	 * 为班级添加课程
	 * @return
	 */
	public String addClazzCou(){
		try{
			if(clazzCou != null && clazz != null){
				clazzCou.setClazzId(clazz.getClazzId());
				
				this.getQueryClazzCouCondition().setClazzId(clazzCou.getClazzId());
				this.getQueryClazzCouCondition().setCourseId(clazzCou.getCourseId());
				
				if(clazzCouService.isExistInClazz(getQueryClazzCouCondition()) == 0){//不存在的情况下添加
					clazzCouService.addClazzCou(clazzCou);
				}
			}
		}catch(Exception ex){
			logger.error("ClazzAction.addClazzCou", ex);
			return ERROR;
		}
		return "addClazzCou";
	}
	
	/**
	 * 从班级中删除课程
	 * @return
	 */
	public String deleteClazzCou(){
		try{
			if(clazzCou != null && clazz != null){
				clazzCou.setClazzId(clazz.getClazzId());
				
				this.getQueryClazzCouCondition().setClazzId(clazzCou.getClazzId());
				this.getQueryClazzCouCondition().setCourseId(clazzCou.getCourseId());
				
				clazzCouService.deleteCourseInClazz(queryClazzCouCondition);
			}
		}catch(Exception ex){
			logger.error("ClazzAction.deleteClazzCou", ex);
			return ERROR;
		}
		return "deleteClazzCou";
	}
	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public IClazz getClazzService() {
		return clazzService;
	}

	public void setClazzService(IClazz clazzService) {
		this.clazzService = clazzService;
	}

	public QueryClazzCondition getQueryClazzCondition() {
		if(this.queryClazzCondition == null){
			this.queryClazzCondition = new QueryClazzCondition();
		}
		return queryClazzCondition;
	}

	public void setQueryClazzCondition(QueryClazzCondition queryClazzCondition) {
		this.queryClazzCondition = queryClazzCondition;
	}

	public IGrade getGradeService() {
		return gradeService;
	}

	public void setGradeService(IGrade gradeService) {
		this.gradeService = gradeService;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<Clazz> getClazzList() {
		return clazzList;
	}

	public void setClazzList(List<Clazz> clazzList) {
		this.clazzList = clazzList;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int[] getClazzIds() {
		return clazzIds;
	}

	public void setClazzIds(int[] clazzIds) {
		this.clazzIds = clazzIds;
	}

	public List<Coursesort> getCourseSortList() {
		return courseSortList;
	}

	public void setCourseSortList(List<Coursesort> courseSortList) {
		this.courseSortList = courseSortList;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public ClazzCou getClazzCou() {
		return clazzCou;
	}

	public void setClazzCou(ClazzCou clazzCou) {
		this.clazzCou = clazzCou;
	}

	public IClazzCou getClazzCouService() {
		return clazzCouService;
	}

	public void setClazzCouService(IClazzCou clazzCouService) {
		this.clazzCouService = clazzCouService;
	}

	public QueryClazzCouCondition getQueryClazzCouCondition() {
		if(queryClazzCouCondition == null){
			queryClazzCouCondition = new QueryClazzCouCondition();
		}
		return queryClazzCouCondition;
	}

	public void setQueryClazzCouCondition(QueryClazzCouCondition queryClazzCouCondition) {
		this.queryClazzCouCondition = queryClazzCouCondition;
	}

	public List<File> getClazzIndexPic() {
		return clazzIndexPic;
	}

	public void setClazzIndexPic(List<File> clazzIndexPic) {
		this.clazzIndexPic = clazzIndexPic;
	}

	public List<File> getClazzPic() {
		return clazzPic;
	}

	public void setClazzPic(List<File> clazzPic) {
		this.clazzPic = clazzPic;
	}

	public List<String> getClazzIndexPicFileName() {
		return clazzIndexPicFileName;
	}

	public void setClazzIndexPicFileName(List<String> clazzIndexPicFileName) {
		this.clazzIndexPicFileName = clazzIndexPicFileName;
	}

	public List<String> getClazzPicFileName() {
		return clazzPicFileName;
	}

	public void setClazzPicFileName(List<String> clazzPicFileName) {
		this.clazzPicFileName = clazzPicFileName;
	}
}

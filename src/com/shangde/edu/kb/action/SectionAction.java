package com.shangde.edu.kb.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.kb.condition.QueryChapterCondition;
import com.shangde.edu.kb.condition.QuerySectionCondition;
import com.shangde.edu.kb.domain.Chapter;
import com.shangde.edu.kb.domain.Section;
import com.shangde.edu.kb.domain.StudyCourse;
import com.shangde.edu.kb.service.IChapter;
import com.shangde.edu.kb.service.ISection;
import com.shangde.edu.kb.service.IStudyCourse;
/**
 * 章管理action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class SectionAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(SectionAction.class);
	/**
	 * 查询条件
	 */
	private QuerySectionCondition querySectionCondition;
	/**
	 * 实体对象
	 */
	private StudyCourse studycourse;
	private Chapter chapter;
	private Section section;
	
	/**
	 * List
	 */
	private List<Section> sList=new ArrayList<Section>();
	/**
	 * 声名学科服务
	 */
	private IStudyCourse studyCourseService;
	/**
	 * 声名学科服务
	 */
	private IChapter chapterService;
	/**
	 * 声名节服务
	 */
	private ISection sectionService;
	/**
	 * 获得节列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getSectionList(){
		try{
			if(section.getChId()!=0){
				this.getQuerySectionCondition().setChId(section.getChId());
			}
			sList=this.sectionService.getSectionByList(this.getQuerySectionCondition());
		} catch (Exception e) {
			logger.error("SectionAction.getSectionList",e);
			return ERROR;
		}
		return "sectionList";
	}
	/**
	 * 打开节添加页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toSectionAdd(){
		try{
			if(chapter.getChId()!=0){
				chapter=this.chapterService.getChapterById(chapter.getChId());
			}
		} catch (Exception e) {
			logger.error("SectionAction.toSectionAdd",e);
			return ERROR;
		}
		return "sectionAdd";
	}
	/**
	 * 添加节
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String sectionAdd(){
		try{
			String number="";
			Date date=new Date();
			section.setSCreatetime(date);
			//处理索引编号，专业编号+下划线+学科编号+下划线+章编号+下划线+节编号＝节编号
			if(chapter.getChIndex()!=null&&!"".equals(chapter.getChIndex())){
				number=chapter.getChIndex()+"_"+section.getSIndex();
			}
			section.setSIndex(number);
			this.sectionService.addSection(section);
		} catch (Exception e) {
			logger.error("SectionAction.sectionAdd",e);
			return ERROR;
		}
		return "section_Add";
	}
	
	/**
	 * 删除节
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deleteSection(){
		try{
			if(section.getSId()!=0){
				this.sectionService.delSectionById(section.getSId());
			}
		} catch (Exception e) {
			logger.error("SectionAction.deleteSection",e);
			return ERROR;
		}
		return "section_Add";
	}
	/**
	 * 打开修改节的页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toEditSection(){
		try{
			String newIndex="";
			if(section.getSId()!=0){
				section=this.sectionService.getSectionById(section.getSId());
				newIndex=section.getSIndex();
				newIndex=newIndex.substring(newIndex.lastIndexOf("_")+1,newIndex.length());
				section.setSIndex(newIndex);
			}
			if(section.getChId()!=0){
				chapter=this.chapterService.getChapterById(section.getChId());
			}
		} catch (Exception e) {
			logger.error("SectionAction.toEditSection",e);
			return ERROR;
		}
		return "sectionEdit";
	}
	/**
	 * 节修改
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String sectionEdit(){
		try{
			String number="";
			Date date=new Date();
			section.setSCreatetime(date);
			//处理索引编号，专业编号+下划线+学科编号+下划线+章编号+下划线+节编号＝节编号
			if(chapter.getChIndex()!=null&&!"".equals(chapter.getChIndex())){
				number=chapter.getChIndex()+"_"+section.getSIndex();
			}
			section.setSIndex(number);
			this.sectionService.updateSection(section);
		} catch (Exception e) {
			logger.error("SectionAction.sectionEdit",e);
			return ERROR;
		}
		return "section_Add";
	}
	public StudyCourse getStudycourse() {
		return studycourse;
	}
	public void setStudycourse(StudyCourse studycourse) {
		this.studycourse = studycourse;
	}
	public IStudyCourse getStudyCourseService() {
		return studyCourseService;
	}
	public void setStudyCourseService(IStudyCourse studyCourseService) {
		this.studyCourseService = studyCourseService;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public IChapter getChapterService() {
		return chapterService;
	}
	public void setChapterService(IChapter chapterService) {
		this.chapterService = chapterService;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public ISection getSectionService() {
		return sectionService;
	}
	public void setSectionService(ISection sectionService) {
		this.sectionService = sectionService;
	}
	public QuerySectionCondition getQuerySectionCondition() {
		if(querySectionCondition==null){
			querySectionCondition=new QuerySectionCondition();
		}
		return querySectionCondition;
	}
	public void setQuerySectionCondition(QuerySectionCondition querySectionCondition) {
		this.querySectionCondition = querySectionCondition;
	}
	public List<Section> getSList() {
		return sList;
	}
	public void setSList(List<Section> list) {
		sList = list;
	}
}

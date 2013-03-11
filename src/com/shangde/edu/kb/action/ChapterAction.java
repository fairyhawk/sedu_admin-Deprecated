package com.shangde.edu.kb.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.kb.condition.QueryChapterCondition;
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
public class ChapterAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(ChapterAction.class);
	
	/**
	 * 查询条件
	 */
	private QueryChapterCondition queryChapterCondition;
	/**
	 * 实体对象
	 */
	private StudyCourse studycourse;
	private Chapter chapter;
	/**
	 * List
	 */
	private List<Chapter> chList=new ArrayList<Chapter>();
	/**
	 * 声名学科服务
	 */
	private IStudyCourse studyCourseService;
	/**
	 * 声名章服务
	 */
	private IChapter chapterService;
	
	/**
	 * 获得学科列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getChapterList(){
		try{
			if(chapter.getCId()!=0){
				this.getQueryChapterCondition().setCId(chapter.getCId());
			}
			chList=this.chapterService.getChapterByList(this.getQueryChapterCondition());
		} catch (Exception e) {
			logger.error("ChapterAction.getChapterList", e);
			return ERROR;
		}
		return "chapterList";
	}
	/**
	 * 打开章添加页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toChapterAdd(){
		try{
			if(studycourse.getCId()!=0){
				studycourse=this.studyCourseService.getStudyCourseById(studycourse.getCId());
			}
		} catch (Exception e) {
			logger.error("ChapterAction.toChapterAdd", e);
			return ERROR;
		}
		return "chapterAdd";
	}
	
	/**
	 * 添加章
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String chapterAdd(){
		try{
			String number="";
			Date date=new Date();
			chapter.setChCreatetime(date);
			//处理索引编号，专业编号+下划线+学科编号+下划线+章编号＝章编号
			if(studycourse.getCIndex()!=null&&!"".equals(studycourse.getCIndex())){
				number=studycourse.getCIndex()+"_"+chapter.getChIndex();
			}
			chapter.setChIndex(number);
			this.chapterService.addChapter(chapter);
		} catch (Exception e) {
			logger.error("ChapterAction.chapterAdd", e);
			return ERROR;
		}
		return "chapter_Add";
	}
	
	/**
	 * 删除章
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deleteChapter(){
		try{
			if(chapter.getChId()!=0){
				this.chapterService.delChapterById(chapter.getChId());
			}
		} catch (Exception e) {
			logger.error("ChapterAction.deleteChapter", e);
			return ERROR;
		}
		return "chapter_Add";
	}
	/**
	 * 打开修改章的页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toEditChapter(){
		try{
			String newIndex="";
			if(chapter.getChId()!=0){
				chapter=this.chapterService.getChapterById(chapter.getChId());
				newIndex=chapter.getChIndex();
				newIndex=newIndex.substring(newIndex.lastIndexOf("_")+1,newIndex.length());
				chapter.setChIndex(newIndex);
			}
			if(chapter.getCId()!=0){
				studycourse=this.studyCourseService.getStudyCourseById(chapter.getCId());
			}
		} catch (Exception e) {
			logger.error("ChapterAction.toEditChapter", e);
			return ERROR;
		}
		return "chapterEdit";
	}
	/**
	 * 章修改
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String chapterEdit(){
		try{
			String number="";
			Date date=new Date();
			chapter.setChCreatetime(date);
			//处理索引编号，专业编号+下划线+学科编号+下划线+章编号＝章编号
			if(studycourse.getCIndex()!=null&&!"".equals(studycourse.getCIndex())){
				number=studycourse.getCIndex()+"_"+chapter.getChIndex();
			}
			chapter.setChIndex(number);
			this.chapterService.updateChapter(chapter);
		} catch (Exception e) {
			logger.error("ChapterAction.chapterEdit", e);
			return ERROR;
		}
		return "chapter_Add";
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
	public QueryChapterCondition getQueryChapterCondition() {
		if(queryChapterCondition==null){
			queryChapterCondition=new QueryChapterCondition();
		}
		return queryChapterCondition;
	}
	public void setQueryChapterCondition(QueryChapterCondition queryChapterCondition) {
		this.queryChapterCondition = queryChapterCondition;
	}
	public List<Chapter> getChList() {
		return chList;
	}
	public void setChList(List<Chapter> chList) {
		this.chList = chList;
	}


}

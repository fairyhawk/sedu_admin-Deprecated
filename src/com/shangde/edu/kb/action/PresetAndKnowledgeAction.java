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
 * 预设和知识体系管理action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class PresetAndKnowledgeAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(PresetAndKnowledgeAction.class);
	/**
	 *预设项管理
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getPresetManage(){
		try{
			
		} catch (Exception e) {
			logger.error("PresetAndKnowledgeAction.getPresetManage", e);
			return ERROR;
		}
		return "prestmanage";
	}
	/**
	 *知识体系管理
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getKnowledgeManage(){
		try{
			
		} catch (Exception e) {
			logger.error("PresetAndKnowledgeAction.getKnowledgeManage", e);
			return ERROR;
		}
		return "knowledgemanage";
	}
}

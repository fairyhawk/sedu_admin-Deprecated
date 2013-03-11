package com.shangde.edu.kb.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.kb.condition.QueryDifficultyCondition;
import com.shangde.edu.kb.domain.Difficulty;
import com.shangde.edu.kb.service.IDifficulty;

public class DifficultyAction extends CommonAction {

	private static final Logger logger = Logger.getLogger(DifficultyAction.class);
	
	private IDifficulty difficultyService;

	private List<Difficulty> difficultyList = new ArrayList();

	private QueryDifficultyCondition queryDifficultyCondition;

	private Difficulty difficulty;

	public String showDifficultyList() { // 获取项目难度列表
		try {
			this.difficultyList = difficultyService
					.getDifficultyList(queryDifficultyCondition);
			if (difficultyList.size() != 0) {
				return "difficultyList";
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("DifficultyAction.showDifficultyList", e);
			return ERROR;
		}
		return "difficultyList";
	}

	public String toAddDifficulty() { // 添加知识体系前生成层级
		try {
			String dLevel = "0";
			int level = 0;
			if (this.difficulty != null) {
				this.difficulty = this.difficultyService.getDifficulty(difficulty);
				if (difficulty.getDLevel() != "") {
					level = Integer.parseInt(difficulty.getDLevel()) + 1;
					dLevel += level;
					difficulty.setDLevel(dLevel);
					return "insertSuccess";

				} else {
					if (this.difficulty == null) {
						this.difficulty = new Difficulty();
						this.difficulty.setDLevel("01");
					}
				}
			}

		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("DifficultyAction.showDifficultyList", e);
			return ERROR;
		}
		return "insertSuccess";
	}

	public String AddDifficulty() { // 添加项目难度
		int result = 0;
		try {
			if (difficulty != null) {

				result = difficultyService.addDifficulty(difficulty);
			}
			if (result == 0) {
				return ERROR;
			} else {
				return "Success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("DifficultyAction.AddDifficulty", e);
		}
		return null;
	}

	public String delDifficulty() { // 删除项目难度

		try {
			if (difficulty.getDId() != 0) {
				this.difficultyService.delDifficultyById(difficulty.getDId());
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("DifficultyAction.delDifficulty", e);
			return ERROR;
		}
		return "Success";
	}

	public String updateDifficulty() { // 修改知识体系

		try {
			if (difficulty.getDId() != 0 && difficulty.getDName() == null) {
				this.difficulty = difficultyService
						.getDifficultyById(difficulty.getDId());
				return "updateDifficulty";
			} else {
				difficultyService.updateDifficulty(difficulty);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("DifficultyAction.updateDifficulty", e);
			return ERROR;
		}
		return "Success";
	}

	public IDifficulty getDifficultyService() {
		return difficultyService;
	}

	public void setDifficultyService(IDifficulty difficultyService) {
		this.difficultyService = difficultyService;
	}

	public QueryDifficultyCondition getQueryDifficultyCondition() {
		if (queryDifficultyCondition == null) {
			queryDifficultyCondition = new QueryDifficultyCondition();
		}
		return queryDifficultyCondition;
	}

	public void setQueryDifficultyCondition(
			QueryDifficultyCondition queryDifficultyCondition) {
		this.queryDifficultyCondition = queryDifficultyCondition;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public List<Difficulty> getDifficultyList() {
		return difficultyList;
	}

	public void setDifficultyList(List<Difficulty> difficultyList) {
		this.difficultyList = difficultyList;
	}

}

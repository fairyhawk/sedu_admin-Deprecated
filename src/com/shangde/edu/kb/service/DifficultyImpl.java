package com.shangde.edu.kb.service;

import java.util.List;
import com.shangde.edu.kb.domain.Difficulty;
import com.shangde.edu.kb.condition.QueryDifficultyCondition;
import com.shangde.common.service.BaseService;

/**
 * Difficulty对象操作实现
 * User: guoqiang.liu
 * Date: 2010-12-27
 */
@SuppressWarnings("unchecked")
public class DifficultyImpl extends BaseService implements IDifficulty{
    public java.lang.Integer addDifficulty(Difficulty difficulty) {
return simpleDao.createEntity("Difficulty_NS.createDifficulty",difficulty);
    }

    public void delDifficultyById(int dId){
        simpleDao.deleteEntity("Difficulty_NS.deleteDifficultyById",dId);
    }

    public void updateDifficulty(Difficulty difficulty) {
        simpleDao.updateEntity("Difficulty_NS.updateDifficulty",difficulty);
    }

    public Difficulty getDifficultyById(int dId) {
        return simpleDao.getEntity("Difficulty_NS.getDifficultyById",dId);
    }

    public List<Difficulty> getDifficultyList(QueryDifficultyCondition queryDifficultyCondition) {
        return simpleDao.getForList("Difficulty_NS.getDifficultyList",queryDifficultyCondition);
    }

	public Difficulty getDifficulty(Difficulty difficulty) {
		return simpleDao.getEntity("Difficulty_NS.getDifficulty", difficulty);
	}
}

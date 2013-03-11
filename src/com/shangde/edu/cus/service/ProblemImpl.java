package com.shangde.edu.cus.service;

import java.util.List;


import com.shangde.edu.cus.domain.Problem;
import com.shangde.edu.cus.condition.QueryProblemCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.common.util.StringUtil;
import com.shangde.common.vo.PageResult;


public class ProblemImpl extends BaseServiceManyDb implements IProblem{
    public java.lang.Integer addProblem(Problem problem) {
return simpleDao.createEntity("Problem_NS.createProblem",problem);
    }

    public void delProblemById(int pblId){
        simpleDao.deleteEntity("Problem_NS.deleteProblemById",pblId);
    }

    public void updateProblem(Problem problem) {
        simpleDao.updateEntity("Problem_NS.updateProblem",problem);
    }

    public Problem getProblemById(int pblId) {
        return simpleDao.getEntity("Problem_NS.getProblemById",pblId);
    }

    public List<Problem> getProblemList(QueryProblemCondition queryProblemCondition) {
        return simpleDao.getForList("Problem_NS.getProblemList",queryProblemCondition);
    }

	public PageResult getPageProblemList(QueryProblemCondition queryProblemCondition) {
		PageResult pr = simpleDaoRead.getPageResult("Problem_NS.getProblemByList", "Problem_NS.getProblemCountByList", queryProblemCondition);
//		List<Problem> pageResult=pr.getPageResult();
//		StringUtil su = new StringUtil();
//		for(int i=0;pageResult!=null&&i<pageResult.size();i++){
//			pageResult.get(i).setPblTitle(su.chop(pageResult.get(i).getPblTitle(), 10, "..."));
//		}
		return pr;
	}

	public PageResult getPageProblemListById(QueryProblemCondition queryProblemCondition) {
		PageResult pe=simpleDao.getPageResult("Problem_NS.getProblemByIdList", "Problem_NS.getProblemCountByIdList", queryProblemCondition);
		List<Problem> pageResult=pe.getPageResult();
		StringUtil su = new StringUtil();
		for(int i=0;pageResult!=null&&i<pageResult.size();i++){
			pageResult.get(i).setPblTitle(su.chop(pageResult.get(i).getPblTitle(), 28, "..."));
		}
		return pe;
	}
	 public List<Problem> getProblemByHost(QueryProblemCondition queryProblemCondition){
		 List<Problem> pageResult=simpleDao.getForList("Problem_NS.getProblemByHost",queryProblemCondition);
		 StringUtil su = new StringUtil();
			for(int i=0;pageResult!=null&&i<pageResult.size();i++){
				pageResult.get(i).setPblTitle(su.chop(pageResult.get(i).getPblTitle(), 8, "..."));
			}
		 return pageResult;
	 }
	 public PageResult getReProblemByCusId(QueryProblemCondition queryProblemCondition){
			return simpleDao.getPageResult("Problem_NS.getReProblemByCusId","Problem_NS.getReProblemCountByCusId", queryProblemCondition);
		}
	 public List<Problem> getNewProblem(QueryProblemCondition queryProblemCondition){
		 List<Problem> pageResult=simpleDao.getForList("Problem_NS.getNewProblem", queryProblemCondition);
			StringUtil su = new StringUtil();
			for(int i=0;pageResult!=null&&i<pageResult.size();i++){
				pageResult.get(i).setPblTitle(su.chop(pageResult.get(i).getPblTitle(), 28, "..."));
			}
		 return pageResult;
	 }

	public Integer delBathProblemByCusIds(String ids) {
		 return simpleDao.delete("Problem_NS.deleteProblemByCusIds",ids);
	}
	
	public Integer getProblemCount(QueryProblemCondition queryProblemCondition) {
		 return simpleDao.getEntity("Problem_NS.getProblemCountByList", queryProblemCondition);
	}

	public PageResult getPageProblemListForExcel(
			QueryProblemCondition queryProblemCondition) {
		PageResult pr = simpleDaoRead.getPageResult("Problem_NS.getProblemByListForExcel", "Problem_NS.getProblemCountByList", queryProblemCondition);
		return pr;
	}
    
}

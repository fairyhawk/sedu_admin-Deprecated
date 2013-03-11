package com.shangde.edu.tk.service;

import java.util.List;

import com.shangde.edu.tk.domain.Task;
import com.shangde.edu.tk.domain.TaskCus;
import com.shangde.edu.tk.condition.QueryTaskCusCondition;
import com.shangde.common.dao.ISimpleDao;
import com.shangde.common.service.BaseService;

/**
 * TaskCus
 * User: guoqiang.liu
 * Date: 2010-12-20
 */
@SuppressWarnings("unchecked")
public class TaskCusImpl extends BaseService implements ITaskCus{
    public java.lang.Integer addTaskCus(TaskCus taskCus) {
    	return simpleDao.createEntity("TaskCus_NS.createTaskCus",taskCus);
    }

    public void delTaskCusById(int id){
        simpleDao.deleteEntity("TaskCus_NS.deleteTaskCusById",id);
    }
    
    public void delTaskCusByTaskId(int taskId){
    	simpleDao.deleteEntity("TaskCus_NS.deleteTaskCusByTaskId",taskId);//删除学员表中的数据
    }
    public void updateTaskCus(TaskCus taskCus) {
        simpleDao.updateEntity("TaskCus_NS.updateTaskCus",taskCus);
    }

    public TaskCus getTaskCusById(int id) {
        return simpleDao.getEntity("TaskCus_NS.getTaskCusById",id);
    }
    
    /**
     * @author cxs
     * 功能：通过关键字获取任务
     * @param args
     * @param key
     * @return
     */
    public TaskCus getTaskCusByKey(QueryTaskCusCondition queryTaskCusCondition){
    	return simpleDao.getEntity("TaskCus_NS.getTaskCusByKey", queryTaskCusCondition);
    }

    public List<TaskCus> getTaskCusList(QueryTaskCusCondition queryTaskCusCondition) {
        return simpleDao.getForList("TaskCus_NS.getTaskCusList",queryTaskCusCondition);
    }
    
    /**
     * @author chenshuai
     * 功能：获取用户前台任务
     * @param args
     * @param queryTaskCusCondition
     * @return
     */
    public List<TaskCus> getWebTaskCusList(QueryTaskCusCondition queryTaskCusCondition){
    	return simpleDao.getForList("TaskCus_NS.getWebTaskCusList",queryTaskCusCondition);
    }
    
    /**
     * @author chenshuai
     * 功能：根据用户的Id和任务Id查询记录条数
     * @param args
     * @param queryTaskCusCondition
     * @return
     */
    public Integer getCountByCusIdAndTaskId(QueryTaskCusCondition queryTaskCusCondition){
    	return simpleDao.getEntity("TaskCus_NS.getCountByCusIdAndTaskId",queryTaskCusCondition);
    }
    
    /**
     * @author chenshuai
     * 功能：根据任务ID及学员ID获取用户任务
     * @param args
     * @param queryTaskCusCondition
     * @return
     */
    public TaskCus getTaskCusByTaskIdAndCusId(QueryTaskCusCondition queryTaskCusCondition){
    	return simpleDao.getEntity("TaskCus_NS.getTaskCusByTaskIdAndCusId", queryTaskCusCondition);
    }
    
    /**
     * @author cxs
     * 功能：获取用户第一个任务
     * @param args
     * @param queryTaskCusCondition
     * @return
     */
    public TaskCus getFirstWebTaskCus(QueryTaskCusCondition queryTaskCusCondition){
    	return simpleDao.getEntity("TaskCus_NS.getFirstWebTaskCus", queryTaskCusCondition);
    }
    /**
     * @author yjd
     * 通过用户ID删除任务
     * @param cusId
     */
    public void deleteTaskCusByCusId(int cusId) {
    	simpleDao.deleteEntity("TaskCus_NS.deleteTaskCusByCusId",cusId);
    	
    }
    
	public Integer deleteBathTaskCusByCusIds(String cusIds) {
		return simpleDao.delete("TaskCus_NS.deleteTaskCusByIds", cusIds);
	}
}

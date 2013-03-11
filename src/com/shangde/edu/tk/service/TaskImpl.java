package com.shangde.edu.tk.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.kb.domain.StudyCourse;
import com.shangde.edu.sys.dto.KeyValueDTO;
import com.shangde.edu.tk.condition.QueryTaskCondition;
import com.shangde.edu.tk.domain.Task;


@SuppressWarnings("unchecked")
public class TaskImpl extends BaseService implements ITask{
    public java.lang.Integer addTask(Task task) {
    	return simpleDao.createEntity("Task_NS.createTask",task);
    }

    public void delTaskById(int taskId){
    	simpleDao.deleteEntity("TaskCus_NS.deleteTaskCusByTaskId",taskId);//删除学员表中的数据
        simpleDao.deleteEntity("Task_NS.deleteTaskById",taskId);
    }
    
    public void updateTask(Task task) {
        simpleDao.updateEntity("Task_NS.updateTask",task);
    }

    public Task getTaskById(int taskId) {
        return simpleDao.getEntity("Task_NS.getTaskById",taskId);
    }
    
    public Task getTaskByKey(String key) {
        return simpleDao.getEntity("Task_NS.getTaskByKey",key);
    }

    public List<Task> getTaskList(QueryTaskCondition queryTaskCondition) {
        return simpleDao.getForList("Task_NS.getTaskList",queryTaskCondition);
    }
    
    public PageResult getTaskListByCondition(QueryTaskCondition queryTaskCondition) {
		return simpleDao.getPageResult("Task_NS.getNomalTaskListByCondition", "Task_NS.getNomalTaskListCountByCondition", queryTaskCondition);
	}
    
    public List<KeyValueDTO> getTaskKeyValueList(QueryTaskCondition queryTaskCondition){
    	List<KeyValueDTO> result = new ArrayList();
    	KeyValueDTO  keyValueDTO = new KeyValueDTO();
    	keyValueDTO.setKey(0);
    	keyValueDTO.setValue("请选择");
    	
    	result.add(keyValueDTO);
        Collection<KeyValueDTO> res= simpleDao.getForList("Task_NS.getTaskKeyValueList",queryTaskCondition);
    	result.addAll(res);
    	return result;
    }
}

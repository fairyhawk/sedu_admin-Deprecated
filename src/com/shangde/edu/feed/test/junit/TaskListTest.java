package com.shangde.edu.feed.test.junit;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryTaskListCondition;
import com.shangde.edu.feed.dto.TaskListDTO;
import com.shangde.edu.feed.service.ITaskList;

/**
 * 任务业务测试类
 * 
 * @author Libg
 * 
 */
public class TaskListTest extends BaseTest {

	/**
	 * 任务详情统计列表测试
	 */
	public void test_taskListV() {
		// fail();// 表示不执行本函数

		ITaskList taskList = (ITaskList) springContext
				.getBean("taskListService");

		QueryTaskListCondition queryTaskListCondition = new QueryTaskListCondition();
		queryTaskListCondition.setCurrentPage(1);
		queryTaskListCondition.setPageSize(10);

		PageResult pr = taskList.getTaskListVList(queryTaskListCondition);
		List<TaskListDTO> list = pr.getPageResult();

		System.out.println("maxPage->" + pr.getTotalPage());
		for (TaskListDTO tl : list) {
			System.out.println(tl.getId() + "\t" + tl.getUac72Count() + "\t"
					+ tl.getTotals());
		}
	}
}

/**
 * 
 */
package com.shangde.edu.feed.test.junit;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryBrowseLogCondition;
import com.shangde.edu.feed.service.IBrowseLog;

/**
 * @author Libg
 * 
 */
public class BrowseLogServiceTest extends BaseTest {

	public void testGetBrowseLogDATEList() {
		fail();
		QueryBrowseLogCondition queryBrowseLogCondition = new QueryBrowseLogCondition();

		IBrowseLog browseLogService = (IBrowseLog) springContext
				.getBean("browseLogService");

		PageResult pageResult = browseLogService
				.getBrowseLogDATEList(queryBrowseLogCondition);

		System.out.println("------------------------------------");
		System.out.println("--->日数量-->" + pageResult.getTotalRecord());
		System.out.println("------------------------------------");

	}

	public void testGetBrowseLogMONTHList() {

		fail();
		QueryBrowseLogCondition queryBrowseLogCondition = new QueryBrowseLogCondition();

		IBrowseLog browseLogService = (IBrowseLog) springContext
				.getBean("browseLogService");

		PageResult pageResult = browseLogService
				.getBrowseLogMONTHList(queryBrowseLogCondition);

		System.out.println("------------------------------------");
		System.out.println("--->月数量-->" + pageResult.getTotalRecord());
		System.out.println("------------------------------------");
	}

	public void testGetBrowseLogWEEKList() {
		//fail();
		QueryBrowseLogCondition queryBrowseLogCondition = new QueryBrowseLogCondition();

		IBrowseLog browseLogService = (IBrowseLog) springContext
				.getBean("browseLogService");

		PageResult pageResult = browseLogService
				.getBrowseLogWEEKList(queryBrowseLogCondition);

		
		System.out.println("------------------------------------");
		System.out.println("--->周数量-->" + pageResult.getTotalRecord());
		System.out.println("------------------------------------");
	}

}

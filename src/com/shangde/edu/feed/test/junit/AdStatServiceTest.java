/**
 * 
 */
package com.shangde.edu.feed.test.junit;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryAdStatCondition;
import com.shangde.edu.feed.domain.AdStat;
import com.shangde.edu.feed.service.IAdStat;

/**
 * @author Libg
 * 
 */
public class AdStatServiceTest extends BaseTest {

	public int addAd(AdStat adStat) {
		fail();
		return 0;
	}

	public PageResult getAdList(QueryAdStatCondition queryAdStatCondition) {
		fail();
		return null;
	}

	public void testGetAdStatDATEList() {
		//fail();

		QueryAdStatCondition queryAdStatCondition = new QueryAdStatCondition();
		IAdStat adStatService = (IAdStat) springContext
				.getBean("adStatService");

		PageResult pageResult = adStatService
				.getAdStatDATEList(queryAdStatCondition);
		System.out.println("------------------------------------------");
		System.out.println("日数量--->" + pageResult.getTotalRecord());
		System.out.println("------------------------------------------");

	}

	public void testGetAdStatMONTHList() {
		fail();
		QueryAdStatCondition queryAdStatCondition = new QueryAdStatCondition();
		IAdStat adStatService = (IAdStat) springContext
				.getBean("adStatService");

		PageResult pageResult = adStatService
				.getAdStatMONTHList(queryAdStatCondition);

		System.out.println("月数量--->" + pageResult.getTotalRecord());
	}

	public void testGetAdStatWEEKList() {
		fail();
		QueryAdStatCondition queryAdStatCondition = new QueryAdStatCondition();
		IAdStat adStatService = (IAdStat) springContext
				.getBean("adStatService");

		PageResult pageResult = adStatService
				.getAdStatWEEKList(queryAdStatCondition);

		System.out.println("周数量--->" + pageResult.getTotalRecord());
	}

}

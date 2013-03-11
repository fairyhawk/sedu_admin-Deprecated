package com.shangde.edu.feed.test.junit;

import com.shangde.edu.feed.condition.QueryAdCondition;
import com.shangde.edu.feed.domain.Ad;
import com.shangde.edu.feed.service.IAd;

public class AdServiceTest extends BaseTest {

	public void testList() {
		fail();
		IAd adService = (IAd) springContext.getBean("adService");

		System.out.println("-------------------");
		System.out.println(adService);

		QueryAdCondition queryAdCondition = new QueryAdCondition();
		adService.getAdList(queryAdCondition);
	}
	
	public void testUpdate(){
		IAd adService = (IAd) springContext.getBean("adService");

		System.out.println("--------------------------");
		
		Ad ad = new Ad();
		ad.setId(1);
		ad.setStatus(1);
		
		adService.updateAd(ad);
		
		System.out.println("==============================");
	}
}

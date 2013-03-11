package com.shangde.edu.feed.test.junit;

import com.shangde.edu.feed.condition.QueryMicroVideoCondition;
import com.shangde.edu.feed.service.IMicroVideo;

public class VideoStatServiceTest extends BaseTest {

	public void testVideoStat() {

		IMicroVideo microVideoService = (IMicroVideo) springContext
				.getBean("microVideoService");

		System.out.println("----------------------------------------------");

		QueryMicroVideoCondition queryMicroVideoCondition = new QueryMicroVideoCondition();
		queryMicroVideoCondition.setId(1);

		microVideoService.videoStatSearch(queryMicroVideoCondition);

		System.out.println("----------------------------------------------");
	}

}
